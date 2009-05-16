package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.AccessResult;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultString;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.translator.LoggedInOrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.OrganizationUserToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProxyLoginController extends HttpControllerPlugin {
	private String resultSessionVarName;
	private String proxyPassword;
	private String urlParamName;
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	// proxyUrl and logUpUrl are configured in spring(plugin)conf.xml+environment.properties
	private String proxyUrl;
	private String logUpUrl;
	private boolean proxyUseGroup = true;
	private int proxyTimeout = 0;
	protected final Log logger = LogFactory.getLog(getClass());

	private URLService urlService;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestedUrlText = request.getQueryString();
		requestedUrlText = requestedUrlText.substring(requestedUrlText.indexOf(this.urlParamName) + this.urlParamName.length() + 1);
		
		String redirectUrl = "";
		Url requestedUrl = new Url();
		
		requestedUrl.setStringValue(requestedUrlText);
		
        UserToXMLTranslator translator = new UserToXMLTranslator();
        Document document = translator.newDocument();
        Element element = document.createElement(this.resultSessionVarName);
		Object objectUser = this.loggedInUser();
		LoggedInOrganization memberOrganization = this.loggedInOrganization();
    	//if(objectUser == null && memberOrganization == null){
		//	if(this.urlService.isAffected(requestedUrl)){
		//		createXML(false, objectUser, memberOrganization, requestedUrl, document, element);
		//		redirectUrl = logUpUrl;
		//	} else {
    			// Special case: 
    			// See error messages below.
    			// No other choice than to redirect user to default "logup page"
		//		logger.error("Either: 1) URL '" + requestedUrl + "' exists in proxy configuration, but not in the administrative database" +
		//			"or: 2) The above URL does not exist in either locations, but the enduser has tampered with the URL and sent a false URL to the proxy controller.");
		//		createXML(true, objectUser, memberOrganization, requestedUrl, document, element);
		//		redirectUrl = logUpUrl;
		//	}
    	//} else {
    		if(this.urlService.isAffected(requestedUrl)) {
    			User user = null;
    			if (objectUser instanceof User) {
    				user = (User) objectUser;
    			} else if (objectUser instanceof OrganizationUser) {
    				user = ((OrganizationUser) objectUser).getUser();
    			}
    			
    			AccessResult accessResult;
    			if(memberOrganization != null && user != null){
    				OrganizationListItem organizationListItem = new OrganizationListItem();
    				organizationListItem.setId(memberOrganization.getId());
    				organizationListItem.setTypeKey(new OrganizationTypeKey(memberOrganization.getTypeKey()));
    				UserListItem userListItem = new UserListItem();
    				userListItem.setId(user.getId());
    				accessResult = urlService.hasAccessUserOrganization(userListItem, organizationListItem, requestedUrl);
    			} else if(user != null){
    				UserListItem userListItem = new UserListItem();
    				userListItem.setId(user.getId());
    				accessResult = urlService.hasAccessUser(userListItem, requestedUrl);
    			} else if(memberOrganization != null){
    				OrganizationListItem organizationListItem = new OrganizationListItem();
    				organizationListItem.setId(memberOrganization.getId());
    				organizationListItem.setTypeKey(new OrganizationTypeKey(memberOrganization.getTypeKey()));
    				accessResult = urlService.hasAccessOrganization(organizationListItem, requestedUrl);
    			} else {
    				accessResult = urlService.hasAccessNone(requestedUrl);
    			}
    			
    			if (accessResult.equals(AccessResult.logup)) {
   	        		createXML(false, objectUser, memberOrganization, requestedUrl, document, element);
    				redirectUrl = logUpUrl;
    			} else if (accessResult.equals(AccessResult.exclude)) {
    				redirectUrl = requestedUrl.getStringValue();
    			} else if (accessResult.equals(AccessResult.include)) {
    				SingleResultString result = this.urlService.group(requestedUrl);
    				String group;
    				if(result instanceof EmptyResultString){
    					group = null;
    					logger.error("Group name was expected but was not found for URL: " + requestedUrl);
    				} else {
    					group = ((ValueResultString)result).getValue();
    				}
    				if(this.createProxySession(response, requestedUrlText, group)){
        				// Great, done!
    	        		createXML(true, objectUser, memberOrganization, requestedUrl, document, element);
    	        		redirectUrl = "";
    				} else {
    	        		createXML(false, objectUser, memberOrganization, requestedUrl, document, element);
    	        		element.appendChild(document.createElement("proxysessionerror"));
    		    		redirectUrl = logUpUrl;
    				}
    			}
    		} else {
    			logger.error("Either: 1) URL '" + requestedUrl + "' exists in proxy configuration, but not in the administrative database\n" +
	    			" or: 2) The above URL does not exist in either locations, but the enduser has tampered with the URL and sent a false URL to the proxy controller.");
    			createXML(true, objectUser, memberOrganization, requestedUrl, document, element);
	    		redirectUrl = logUpUrl;
    		}
    	//}
    	document.appendChild(element);
    	ResultHandler.setResult(this.resultSessionVarName, document);
    	if( ! redirectUrl.equals("")){
            redirect(response, redirectUrl);
    	}
    }
    private void createXML(boolean hasAccess, Object user, LoggedInOrganization organization, Url requestedUrl, Document document, Element element) throws ParserConfigurationException {
		Element loggedin = document.createElement("loggedin");
		if(user != null){
//			proxyresult/loggedin/user
			UserToXMLTranslator translator = new UserToXMLTranslator();
			if(user instanceof User){
				translator.translate((User) user, document, loggedin);
			} else {
				OrganizationUserToXMLTranslator orgTranslator = new OrganizationUserToXMLTranslator();
				orgTranslator.translate((OrganizationUser) user, document, loggedin);
			}
		} else if(organization != null){
//			proxyresult/loggedin/organization
			LoggedInOrganizationToXMLTranslator organizationTranslator = new LoggedInOrganizationToXMLTranslator();
			organizationTranslator.translate(organization, document, loggedin);
	} else {
//			proxyresult/loggedin/none
    		loggedin.appendChild(document.createElement("none"));
		}
		element.appendChild(loggedin);
		// TODO Fase2: Use resource class here! Fetch from service!
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
            	logger.error("Failed redirect to url '" + url + "'", e);
            }
    }
	public Object loggedInUser() {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		Object user = session.getAttribute(sessionLoggedInUserVarName);
		if(user instanceof User || user instanceof OrganizationUser){
			return user;
		} else {
			return null;
		}
	}
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return (LoggedInOrganization)session.getAttribute(sessionLoggedInOrganizationVarName);
	}
	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
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