package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultString;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
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
	// proxyUrl and logUpUrl are configured in spring(plugin)conf.xml+environment.properties
	private String proxyUrl;
	private String logUpUrl;
	private boolean proxyUseGroup = true;
	private int proxyTimeout = 0;

	private URLService urlService;
	private LoggedInFunction loggedInFunction;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		String requestedUrlText = request.getParameter(this.urlParamName);
        String redirectUrl;
		Url requestedUrl = new Url();
		requestedUrl.setStringValue(requestedUrlText);
		
        UserToXMLTranslator translator = new UserToXMLTranslator();
        Document document = translator.newDocument();
        Element element = document.createElement(this.resultSessionVarName);
		User user = this.loggedInFunction.loggedInUser();
		MemberOrganization organization = this.loggedInFunction.loggedInOrganization();
    	if(user == null && organization == null){
    		if(this.urlService.isAffected(requestedUrl)){
        		createXML(false, user, organization, requestedUrl, document, element);
        		redirectUrl = logUpUrl;
    		} else {
    			// TODO: What does it mean to not be affected.
    			// When everyone has access?
        		createXML(true, user, organization, requestedUrl, document, element);

        		// TODO: Go ahead!
	    		redirectUrl = logUpUrl;
//	    		redirectUrl = requestedUrlText;
    		}
    	} else {
    		if(this.urlService.isAffected(requestedUrl)){
    			boolean hasAcces = false;
    			if(this.urlService.hasAccess(user, organization, requestedUrl)){
    				SingleResultString result = this.urlService.group(requestedUrl);
    				String group;
    				if(result instanceof EmptyResultString){
    					// TODO: What to do here?
    					group = null;
    				} else {
    					group = ((ValueResultString)result).getValue();
    				}
    				if(this.createProxySession(response, requestedUrlText, group)){
        				// Great, done!
    	        		createXML(true, user, organization, requestedUrl, document, element);

    	        		redirectUrl = "";
    				} else {
    	        		createXML(false, user, organization, requestedUrl, document, element);
    	        		element.appendChild(document.createElement("proxysessionerror"));
    		    		redirectUrl = logUpUrl;
    				}
    			} else {
    				// Add organization for IP!
    		    	IpAddress ipAddress = new IpAddress();
    		    	ipAddress.setAddress(LogInInterceptor.getXforwardedForOrRemoteAddress(request));
   	        		createXML(false, user, organization, requestedUrl, document, element);
    				redirectUrl = logUpUrl;
    			}
    		} else {
        		createXML(true, user, organization, requestedUrl, document, element);
        		
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
    private void createXML(boolean hasAccess, User user, MemberOrganization organization, Url requestedUrl, Document document, Element element) throws ParserConfigurationException {
		Element loggedin = document.createElement("loggedin");
		if(user != null){
//			proxyresult/loggedin/user
			UserToXMLTranslator translator = new UserToXMLTranslator();
			translator.translate(user, document, loggedin);
		} else if(organization != null){
//			proxyresult/loggedin/organization
			OrganizationToXMLTranslator organizationTranslator = new OrganizationToXMLTranslator();
			organizationTranslator.translate(organization.getOrganization(), document, loggedin);
	} else {
//			proxyresult/loggedin/none
    		loggedin.appendChild(document.createElement("none"));
		}
		element.appendChild(loggedin);
		// TODO: Use resource class here! Fetch from service!
		Element resource = document.createElement("resource");
//		proxyresult/resource/name
		resource.appendChild(UserToXMLTranslator.cDataElement(document, "name", requestedUrl.getStringValue()));
//		proxyresult/resource/url
		resource.appendChild(UserToXMLTranslator.cDataElement(document, "url", requestedUrl.getStringValue()));
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
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}
	public void setUrlParamName(String urlParamName) {
		this.urlParamName = urlParamName;
	}
	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}
	public void setLogUpUrl(String logUpUrl) {
		this.logUpUrl = logUpUrl;
	}
	public void setProxyUseGroup(boolean proxyUseGroup) {
		this.proxyUseGroup = proxyUseGroup;
	}
	public void setProxyTimeout(int proxyTimeout) {
		this.proxyTimeout = proxyTimeout;
	}
}
