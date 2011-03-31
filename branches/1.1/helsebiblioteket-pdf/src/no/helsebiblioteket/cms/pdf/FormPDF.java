package no.helsebiblioteket.cms.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

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

import com.enonic.cms.api.plugin.HttpControllerPlugin;

/**
 * PDF Renderer-plugin
 * 
 * Pluginen har 2 operasjonsmoduser, enten som en ren GET-tjeneste som rendrer en PDF fra en gitt side, eller
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
public class FormPDF extends HttpControllerPlugin {
	
	private Log log = LogFactory.getLog(FormPDF.class);
	
	private static final String UTF8 = "UTF-8";
	
	private static final String DEFAULT_FILENAME = "Utskrift";
	
	private static final String PARAM_MODE = "mode";
	private static final String PARAM_TITLE = "pdftitle";
	private static final String PARAM_URL = "url";
	
	private List<String> allowedHosts = Arrays.asList(
			"www.helsebiblioteket.no",
			"www-t.helsebiblioteket.no");
	
	private enum RenderMode {
		PAGE, FORM
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormPDF() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getParameter(PARAM_URL);
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
		
		@SuppressWarnings("unchecked")
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			
			if (value.equals("on")) {
				html = html.replaceAll("<input.*name=\"" + name + "\".*type=\"checkbox\".*>", "x");
			}
			html = html.replaceAll("<input.*name=\"" + name + "\".*>", value);
		}
		return html;
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
		response.setContentType("application/pdf");
		ITextRenderer renderer = new ITextRenderer();
		
		if (!isUrl) {
			Document document = XMLResource.load(new ByteArrayInputStream(source.getBytes(UTF8))).getDocument();	
			renderer.setDocument(document, null);
		} else {
			renderer.setDocument(source);
		}
		OutputStream os = response.getOutputStream();
	    renderer.layout();
	    renderer.createPDF(os);
	}

	/**
	 * Fjerner tegn som er ugyldige i filnavn.
	 * 
	 * @param filename Filnavn som skal prosesseres
	 * @return String med strippet filnavn
	 */
	private String stripIllegalChars(String filename) {
		String source = filename.replaceAll("[ /]", "_");
		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < source.length(); i++) {
			String character = source.substring(i, i+1);
			if (character.matches("[a-zA-ZæøåÆØÅ_0-9]")) output.append(character);
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
	 */
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String mode = request.getParameter(PARAM_MODE);
		
		if (mode != null && mode.equalsIgnoreCase(RenderMode.PAGE.name())) {
			doGet(request, response);
		} else {
			doPost(request, response);
		}
	}
}
