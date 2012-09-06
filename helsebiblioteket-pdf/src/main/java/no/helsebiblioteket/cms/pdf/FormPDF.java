package no.helsebiblioteket.cms.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import com.enonic.cms.api.plugin.ext.http.HttpController;

/**
 * PDF Renderer-plugin
 * 
 * Pluginen har 2 operasjonsmoduser, enter som en ren GET-tjeneste som rendrer en PDF fra en gitt side, eller
 * i POST-modus hvor den rendrer en PDF av siden som angis, samtidig som den fyller ut alle input-feltene
 * med verdiene fra POST.
 * 
 * Parametre:
 *  * mode
 *    Gyldige verdier er enten "page" eller tom. "mode=page" vil sette plugin'en i GET-mode, mens ingen mode
 *    vil sette den i POST-mode.
 *  
 *  * pdftitle
 *    Denne kan du sette for å angi hvilket filnavn PDF'en skal ha når brukeren får nedlastingsdialog.
 *     
 *  * url
 *    URL til webside det skal genereres PDF fra.
 * 
 * Eksempler på bruk:
 * 
 * GET = www.helsebiblioteket.no/formpdf?mode=page&pdftitle=Min%20tittel&url=http://www.helsebiblioteket.no/randomPage
 * 
 * POST = www.helsebiblioteket.no/formpdf?url=http://www.helsebiblioteket.no/randomPage
 * 
 */
public class FormPDF extends HttpController {
	
	private Log log = LogFactory.getLog(FormPDF.class);
	
	private static final String UTF8 = "UTF-8";
	private static final String CONTENTTYPE_PDF = "application/pdf";
	
	private static final String DEFAULT_FILENAME = "Utskrift";
	
	private static final String PARAM_MODE = "mode";
	private static final String PARAM_TITLE = "pdftitle";
	private static final String PARAM_URL = "url";
	
	private static final String CHECKBOX_CHECKED_URL = "<img src=\"http://www.helsebiblioteket.no/_public/Helsebiblioteket.no/images/checkbox_large_checked.png\" style=\"height: 10px; width: 10px\"/>";
	private static final String CHECKBOX_UNCHECKED_URL = "<img src=\"http://www.helsebiblioteket.no/_public/Helsebiblioteket.no/images/checkbox_large_unchecked.png\" style=\"height: 10px; width: 10px\"/>";
	
	private List<String> allowedHosts = Arrays.asList(
			"www.helsebiblioteket.no",
			"www-t.helsebiblioteket.no",
			"www.pasientsikkerhetskampanjen.no", 
			"http://192.168.2.102:8080/", 
			"www.kvalitetogprioritering.no", 
			"http://192.168.2.131:8080/", 
			"www.kunnskapssenteret.no", 
			"http://192.168.2.111:8080/", 
			"www.mednytt.no", 
			"http://192.168.2.121:8080/", 
			"http://localhost:8080", 
			"http://www.psyktest.no", 
			"http://www-t.psyktest.no");
	
	private enum RenderMode {
		PAGE, FORM
	}
	
	private enum InputType {
		TEXT, RADIO, BUTTON, SUBMIT, CHECKBOX, HIDDEN
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormPDF() {
        super();
    }
    
    /**
     * Henter ut URL-parameter fra request.
     * 
     * Grunnen til at dette ikke gjøres med request.getParameter() er at vi vil ha med resten av querystringen
     * etter "&url=", slik at eventuelle parametre i URL'en ikke blir filtrert bort.
     * 
     * @param request
     * @return
     */
    protected String getPageUrl(HttpServletRequest request) {
    	String qs = request.getQueryString();
		return qs.substring(qs.indexOf("&url=")+5);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = getPageUrl(request);
		
		// Escaper mellomrom i URL
		url = url.replaceAll(" ", "+");
		if (acceptHost(url)) {
			try {
				addHeaders(request, response);
				renderPDF(response, url, true);
			} catch (Exception e) {
				log.error("Feil ved rendering av PDF for " + url, e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getHeader("Referer");
		
		if (acceptHost(url)) {
			try {
				String html = getPage(url);
				html = formFill(request, html);
				addHeaders(request, response);
				renderPDF(response, html, false);
			} catch (Exception e) {
				log.error("Feil ved rendering av PDF form for " + url, e);
			}
		}
	}
	
	/**
	 * Sjekker om vi godtar å lage PDF for angitt host.
	 * 
	 * @param url URL som skal sjekkes
	 * @return true om vi godtar hosten, false ellers
	 */
	private boolean acceptHost(String url) {
		if (url == null) return false;
		String r = url.toLowerCase();
		
		for (String host : allowedHosts) {
			if (r.startsWith("http://" + host) || r.startsWith(host)) return true;
		}
		return false;
	}
	
	/**
	 * Erstatter <input> tags med verdiene fra POST
	 * 
	 * @param request
	 * @param html
	 * @return Utfylt HTML
	 */
	private String formFill(HttpServletRequest request, String html) {
		StringBuffer result = new StringBuffer();
		
		// Match alle input-elementer
		Pattern pattern = Pattern.compile("<input.*?>");
		Matcher matcher = pattern.matcher(html);
		
		// Loop gjennom
		while (matcher.find()) {
			String matchedString = html.substring(matcher.start(), matcher.end()).toLowerCase();

			// sjekk type input-felt
			InputType type = getInputType(matchedString);
			String name = getAttributeValue(matchedString, "name");
			
			// Sjekk om name fins som verdi fra POST
			String value = request.getParameter(name);
			
			switch (type) {
				case RADIO:
					if (value != null && value.equals(getAttributeValue(matchedString, "value"))) matcher.appendReplacement(result, CHECKBOX_CHECKED_URL);
					else matcher.appendReplacement(result, CHECKBOX_UNCHECKED_URL);
					break;
				case CHECKBOX:
					if (value != null) {
						matcher.appendReplacement(result, CHECKBOX_CHECKED_URL);
					} else {
						matcher.appendReplacement(result, CHECKBOX_UNCHECKED_URL);
					}
					break;
				default:
					if (value != null) {
						matcher.appendReplacement(result, value);
					}
			}
		
			// erstatt verdier fra request om de er satt
			//matcher.appendReplacement(result, replacement);
		}
		matcher.appendTail(result);
		String resultString = result.toString();
		return resultString;
	}
	
	/**
	 * Finner ut hva slags type input-felt det gitte elementet er.
	 * 
	 * @param inputElement
	 * @return
	 */
	private InputType getInputType(String inputElement) {
		String typeValue = getAttributeValue(inputElement, "type");
		if (typeValue.equals("radio")) return InputType.RADIO;
		else if (typeValue.equals("button")) return InputType.BUTTON;
		else if (typeValue.equals("checkbox")) return InputType.CHECKBOX;
		else if (typeValue.equals("hidden")) return InputType.HIDDEN;
		return InputType.TEXT;
	}
	
	/**
	 * Henter ut verdien for angitt attributt.
	 * 
	 * @param element
	 * @param attributeName
	 * @return
	 */
	private String getAttributeValue(String element, String attributeName) {
		Pattern pattern = Pattern.compile("<[^>]+" + attributeName + "\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		Matcher matcher = pattern.matcher(element);
		
		if (matcher.find()) {
			return matcher.group(matcher.groupCount());
		}
		return null;
	}
	
	/**
	 * Henter gitt URL som en String
	 * 
	 * @param url URL som skal hentes
	 * @return String med innholdet fra URL
	 * @throws Exception
	 */
	private String getPage(String url) throws Exception {

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, UTF8);
		params.setBooleanParameter("http.protocol.expect-continue", false);

		HttpClient httpclient = new DefaultHttpClient(params);
		
		HttpGet httpget = new HttpGet(url);

		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			return EntityUtils.toString(entity, UTF8);
		}
		return "";
	}
	
	/**
	 * Rendrer HTML String til PDF ut på responsen
	 * 
	 * @param response
	 * @param html HTML som skal rendres
	 * @throws Exception
	 */
	private void renderPDF(HttpServletResponse response, String source, boolean isUrl) throws Exception {
		ITextRenderer renderer = new ITextRenderer();
		
		if (!isUrl) {
			Document document = XMLResource.load(new ByteArrayInputStream(source.getBytes(UTF8))).getDocument();	
			renderer.setDocument(document, null);
		} else {
			renderer.setDocument(source);
		}
		OutputStream os = response.getOutputStream();
		response.setContentType(CONTENTTYPE_PDF);
		renderer.layout();
	    renderer.createPDF(os);
	}

	/**
	 * Fjerner tegn som er ugyldige i filnavn, og lager generelt mer filnavn-vennlig tittel.
	 * 
	 * @param filename Filnavn som skal prosesseres
	 * @return String med strippet filnavn
	 */
	private String stripIllegalChars(String filename) {
		String source = filename.replaceAll("[ /]", "_");
		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < source.length(); i++) {
			String character = source.substring(i, i+1);
			if (character.matches("[a-zA-ZæøåÆØÅ_0-9\\-]")) output.append(character);
		}
		
		return output.toString();
	}
	
	/**
	 * Henter ut ønsket tittel/filnavn fra requesten og returnerer en
	 * validert og prosessert utgave.
	 * 
	 * @param request HttpServletRequest
	 * @return String med filnavn
	 */
	private String getTitle(HttpServletRequest request) {
		String title = request.getParameter(PARAM_TITLE);
		if (title != null && title.length() > 0) title = stripIllegalChars(title);
		if (title == null || title.length() == 0) title = DEFAULT_FILENAME;
		return title;
	}
	
	/**
	 * Legger på PDF-headers.
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	private void addHeaders(HttpServletRequest request, HttpServletResponse response) {
		String title = getTitle(request);
		response.setHeader("Content-Disposition", "attachment;filename=" + title + ".pdf");
	}
	
	/**
	 * HttpControllerPlugin entry point
	 * 
	 * Delegerer videre til doPost eller doGet avhengig av mode.
	 */
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String mode = request.getParameter(PARAM_MODE);
			
			if (mode != null && mode.equalsIgnoreCase(RenderMode.PAGE.name())) {
				doGet(request, response);
			} else {
				doPost(request, response);
			}
		} catch (Exception e) {
			log.error("Feil ved rendering: ", e);
		}
	}
	
}
