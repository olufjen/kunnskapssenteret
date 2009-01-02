package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProxyLoginController extends HttpControllerPlugin {
	private String resultSessionVarName;
	private URLService urlService;
	private String proxyUsername = "myusername";
	private String proxyPassword = "mypassword";
	private boolean proxyUseGroup = true;
	private int proxyTimeout = 0;
	private String proxyUrl = "http://192.168.1.133:2048/login";
	private String logUpUrl = "http://localhost:8080/cms/site/2/Logg+Inn";
	private String logUpServletUrl = "http://localhost:8080/cms/site/2/ProxyServlet";

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Admin:
//	    http://192.168.1.133:2048/login?user=admin&pass=password
//		http://192.168.1.133:2048/logout
//		http://192.168.1.133:2048/admin
//		http://192.168.1.133:2048/groups
//		Link:
//	    http://192.168.1.133:2048/login?url=http://www.altavista.com/
//	    http://192.168.1.133:2048/login?url=http://www.vg.no/
//		Return URL:
//		http://192.168.1.100:8080/cms/site/2/ProxyServlet?logup=UNS0u31JXwUajAF&url=http://www.google.com/
//		Login URL:
//		http://192.168.1.133:2048/login?user=myusername&pass=mypassword&group=google&url=http://www.vg.no/
//		Menu:
//		http://192.168.1.133:2048/menu
//		Redirect:
//		http://192.168.1.133:2048/connect?session=t8hvlMSUuUtshMZu&url=http://www.google.com/
//		http://192.168.1.133:2048/logout
		
//		String logup = request.getParameter("logup");

		String requestedUrlText = request.getParameter("url");
        Object loggedin = LoggedInFunction.loggedIn();
        String redirectUrl;

        UserToXMLTranslator translator = new UserToXMLTranslator();
        Document document = translator.newDocument();
        Element element = document.createElement(this.resultSessionVarName);
        
        String init = request.getParameter("init");
        
        if(init != null && init.equals("true")){
        	redirect(response, this.logUpServletUrl + "?url=" + requestedUrlText);
        	return;
        }
    	if(loggedin == null){
    		// TODO: Set information for login!
    		element.appendChild(UserToXMLTranslator.element(document, "requestedUrl", requestedUrlText));
    		redirectUrl = logUpUrl;
    	} else {
    		Url requestedUrl = new Url();
    		requestedUrl.setValue(requestedUrlText);
    		if(this.urlService.isAffected(requestedUrl)){
    			User user = null;
    			Organization organization = null;
    			if(loggedin instanceof User) { user = (User) loggedin; }
    			if(loggedin instanceof Organization) { organization = (Organization) loggedin; }
    			if(this.urlService.hasAccess(user, organization, requestedUrl)){

    				String group = this.urlService.group(requestedUrl);
    				this.createProxySession(response, requestedUrlText, group);
    				
    				// Great, done!

    				redirectUrl = "";
    	    		element.appendChild(document.createElement("success"));

    			} else {
    	    		element.appendChild(UserToXMLTranslator.element(document, "requestedUrl", requestedUrlText));
    				if(user != null){
    					translator.translate(user, document, element);
    				} else if(organization != null){
    					OrganizationToXMLTranslator organizationTranslator = new OrganizationToXMLTranslator();
    					organizationTranslator.translate(organization, document, element);
    				} else {
        	    		element.appendChild(document.createElement("unknown"));
    				}
    				redirectUrl = logUpUrl;
    			}
    		} else {
        		redirectUrl = requestedUrlText;
    		}
    	}
    	document.appendChild(element);
    	LoggedInFunction.setResult(this.resultSessionVarName, document);
    	if( ! redirectUrl.equals("")){
            redirect(response, redirectUrl);
    	}
    }
    private boolean createProxySession(HttpServletResponse clientResponse, String destinationUrl, String group) {
        boolean sessionCreated = false;
        // Don't follow redirect returned from proxy. This redirect is to be given to user.
        HttpURLConnection.setFollowRedirects(false);
        try {
            // Create a URLConnection to proxy server
            String url = this.proxyUrl + "?user=" + group + "&pass=" + this.proxyPassword
                    + (this.proxyUseGroup && (group != null) ? "&group=" + group : null)
                    + "&url=" + destinationUrl;
            URL proxyServerUrl = new URL(url);
            HttpURLConnection proxyServer = (HttpURLConnection)proxyServerUrl.openConnection();
            if (this.proxyTimeout > 0) {
                proxyServer.setConnectTimeout(proxyTimeout);
            }
            proxyServer.connect();
            // Set statuscode on response based on statuscode from proxy server
            clientResponse.setStatus(proxyServer.getResponseCode());
            // Get the response headers from the proxy server and add to client response.
            // Skip the first headerField as it contains the server response.
            for (int i = 1; ; i++) {
                String headerName = proxyServer.getHeaderFieldKey(i);
                String headerValue = proxyServer.getHeaderField(i);

                if (headerName == null && headerValue == null) {
                    // No more headers
                    break;
                }
                if (headerName == null) {
                    // The header value contains the server's HTTP version
                }
                clientResponse.setHeader(headerName, headerValue);
            }
            sessionCreated = true;
        } catch (IOException e) {
        }
        return sessionCreated;
    }
    private void redirect(HttpServletResponse response, String url) {
        if (!response.isCommitted()) {
            try {
                url = response.encodeRedirectURL(url);
                response.sendRedirect(url);

            } catch (IOException e) {
            	// TODO: Log error"
//                logger.warn("Failed redirect to url", e);
            }
        } else {
//            logger.warn("Response already committed, can't redirect to url");
        }
    }
	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}

}
