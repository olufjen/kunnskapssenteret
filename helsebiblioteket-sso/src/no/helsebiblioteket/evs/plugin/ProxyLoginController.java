package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProxyLoginController extends HttpControllerPlugin {
	// FIXME: Complete plugin-cofig including services!
	private String resultSessionVarName = "hbproxyresult";
	private String proxyPassword = "mypassword";
	private String urlParamName = "url";
	private String proxyUrl = "http://192.168.1.133:2048/login";
	private String logUpUrl = "http://192.168.1.100:8080/cms/site/2/Logg+inn";
	private boolean proxyUseGroup = true;
	private int proxyTimeout = 0;

	private URLService urlService;
	private LoginService loginService;
	private LoggedInFunction loggedInFunction;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		String requestedUrlText = request.getParameter(this.urlParamName);
        Object loggedin = loggedInFunction.loggedIn();
        String redirectUrl;
		Url requestedUrl = new Url();
		requestedUrl.setValue(requestedUrlText);
		
        UserToXMLTranslator translator = new UserToXMLTranslator();
        Document document = translator.newDocument();
        Element element = document.createElement(this.resultSessionVarName);
    	if(loggedin == null){
    		if(this.urlService.isAffected(requestedUrl)){
        		createXML(false, loggedin, requestedUrl, document, element);
        		redirectUrl = logUpUrl;
    		} else {
    			// TODO: What does it mean to not be affected.
    			// When everyone has access?
        		createXML(true, loggedin, requestedUrl, document, element);

        		// TODO: Go ahead!
	    		redirectUrl = logUpUrl;
//	    		redirectUrl = requestedUrlText;
    		}
    	} else {
    		if(this.urlService.isAffected(requestedUrl)){
    			User user = null;
    			Organization organization = null;
    			if(loggedin instanceof User) { user = (User) loggedin; }
    			if(loggedin instanceof Organization) { organization = (Organization) loggedin; }
    			if(this.urlService.hasAccess(user, organization, requestedUrl)){
    				String group = this.urlService.group(requestedUrl);
    				if(this.createProxySession(response, requestedUrlText, group)){
        				// Great, done!
    	        		createXML(true, loggedin, requestedUrl, document, element);

    	        		redirectUrl = "";
    				} else {
    	        		createXML(false, loggedin, requestedUrl, document, element);
    	        		element.appendChild(document.createElement("proxysessionerror"));
    		    		redirectUrl = logUpUrl;
    				}
    			} else {
    				// Add organization for IP!
    		    	IpAddress ipAddress = new IpAddress();
    		    	ipAddress.setAddress(LogInInterceptor.getXforwardedForOrRemoteAddress(request));
    		    	Organization altOrganization = this.loginService.logInIpAddress(ipAddress);
    		    	if(altOrganization != null) {
    	        		createXML(false, loggedin, requestedUrl, altOrganization, document, element);
    		    	} else {
    		    		createXML(false, loggedin, requestedUrl, document, element);
    		    	}
    				redirectUrl = logUpUrl;
    			}
    		} else {
        		createXML(true, loggedin, requestedUrl, document, element);
        		
        		// TODO: Go ahead!
	    		redirectUrl = logUpUrl;
//	    		redirectUrl = requestedUrlText;
    		}
    	}
    	document.appendChild(element);
    	loggedInFunction.setResult(this.resultSessionVarName, document);
    	if( ! redirectUrl.equals("")){
            redirect(response, redirectUrl);
    	}
    }
    private void createXML(boolean hasAccess, Object loggedinUser, Url requestedUrl, Organization altOrganization, Document document, Element element) throws ParserConfigurationException {
    	this.createXML(hasAccess, loggedinUser, requestedUrl, document, element);
//		proxyresult/altorganization
    	Element altorganization = document.createElement("altorganization");
		OrganizationToXMLTranslator organizationTranslator = new OrganizationToXMLTranslator();
		organizationTranslator.translate(altOrganization, document, altorganization);
		element.appendChild(altorganization);
    }
    private void createXML(boolean hasAccess, Object loggedinUser, Url requestedUrl, Document document, Element element) throws ParserConfigurationException {
		User user = null;
		Organization organization = null;
		if(loggedinUser instanceof User) { user = (User) loggedinUser; }
		if(loggedinUser instanceof Organization) { organization = (Organization) loggedinUser; }
		Element loggedin = document.createElement("loggedin");
		if(user != null){
//			proxyresult/loggedin/user
			UserToXMLTranslator translator = new UserToXMLTranslator();
			translator.translate(user, document, loggedin);
		} else if(organization != null){
//			proxyresult/loggedin/organization
			OrganizationToXMLTranslator organizationTranslator = new OrganizationToXMLTranslator();
			organizationTranslator.translate(organization, document, loggedin);
		} else {
//			proxyresult/loggedin/none
    		loggedin.appendChild(document.createElement("none"));
		}
		element.appendChild(loggedin);
		// TODO: Use resource class here! Fetch from service!
		Element resource = document.createElement("resource");
//		proxyresult/resource/name
		resource.appendChild(UserToXMLTranslator.cDataElement(document, "name", requestedUrl.getValue()));
//		proxyresult/resource/url
		resource.appendChild(UserToXMLTranslator.cDataElement(document, "url", requestedUrl.getValue()));
		element.appendChild(resource);
		
		if(hasAccess){
//			proxyresult/access
			element.appendChild(document.createElement("access"));
		} else {
//			proxyresult/noaccess
			element.appendChild(document.createElement("noaccess"));
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
            try {
            	if (!response.isCommitted()) {
                	url = response.encodeRedirectURL(url);
                	response.sendRedirect(url);
                } else {
//                  logger.warn("Response already committed, can't redirect to url");
              }

            } catch (IOException e) {
            	// TODO: Log error"
//                logger.warn("Failed redirect to url", e);
            }
    }
	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
// TODO: Remove!
//	Admin:
//    http://192.168.1.133:2048/login?user=admin&pass=password
//	http://192.168.1.133:2048/logout
//	http://192.168.1.133:2048/admin
//	http://192.168.1.133:2048/groups
//	http://192.168.1.133:2048/menu
//	Link:
//    http://localhost:2048/login?url=http://www.altavista.com/
//    http://192.168.1.133:2048/login?url=http://www.vg.no/
//	Return URL:
//	http://192.168.1.100:8080/cms/site/2/ProxyServlet?logup=UNS0u31JXwUajAF&url=http://www.google.com/
//	http://192.168.1.100:8080/cms/site/2/ProxyServlet?url=http://www.vg.no/
//	http://192.168.1.100:8080/cms/site/2/ProxyServlet?url=http://www.altavista.com/
//	http://192.168.1.100:8080/cms/site/2/ProxyServlet?url=http://www.digi.no/
//	Login URL:
//	http://192.168.1.133:2048/login?user=myusername&pass=mypassword&group=google&url=http://www.vg.no/
//	Redirect:
//	http://192.168.1.133:2048/connect?session=t8hvlMSUuUtshMZu&url=http://www.google.com/
//	http://192.168.1.133:2048/logout
}
