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
import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.AccessResult;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultString;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.service.geoip.GeoIpService;
import no.helsebiblioteket.admin.translator.LoggedInOrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.LoggedInUserToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
	private boolean stripUrlParams = false;
	private Set<String> stripUrlParamsSet = null;
	private URLService urlService;
	private GeoIpService geoIpService;
	private String countryCodes;


	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestedUrlText = (stripUrlParams) ? stripUrlParams(request.getQueryString()) : request.getQueryString();
		if(requestedUrlText != null){
			requestedUrlText = requestedUrlText.substring(requestedUrlText.indexOf(this.urlParamName) + this.urlParamName.length() + 1);
		} else {
			requestedUrlText = "";
		}

		String redirectUrl = "";
		Url requestedUrl = new Url();
		requestedUrl.setStringValue(requestedUrlText);
		try{
			requestedUrl.setDomain(new URL(requestedUrlText).getHost());
		} catch (MalformedURLException e) {
			if(logger.isInfoEnabled())
			logger.info("ProxyLoginController recieved Malformed URL: " + requestedUrlText);
			requestedUrl.setDomain("");
		}
		
        UserToXMLTranslator translator = new UserToXMLTranslator();
        Document document = translator.newDocument();
        Element element = document.createElement(this.resultSessionVarName);
		LoggedInUser loggedInUser = loggedInUser();
		LoggedInOrganization loggedInOrganization = this.loggedInOrganization();
		boolean national = this.geoIpService.hasAccess(LogInInterceptor.getXforwardedForOrRemoteAddress(request), this.countryCodes);
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
    			AccessResult accessResult;
    			if(loggedInOrganization != null && loggedInUser != null){
    				OrganizationListItem organizationListItem = new OrganizationListItem();
    				organizationListItem.setId(loggedInOrganization.getId());
    				organizationListItem.setTypeKey(new OrganizationTypeKey(loggedInOrganization.getTypeKey()));
    				UserListItem userListItem = new UserListItem();
    				userListItem.setId(loggedInUser.getId());
    				if (loggedInUser.getRoleKey() != null && !"".equals(loggedInUser.getRoleKey())) {
    					userListItem.setRoleKeyValuesAsStrings(new String[] { loggedInUser.getRoleKey() });
    				}
    				accessResult = urlService.hasAccessUserOrganization(userListItem, organizationListItem, requestedUrl);
    			} else if(loggedInUser != null) {
    				UserListItem userListItem = new UserListItem();
    				userListItem.setId(loggedInUser.getId());
    				if (loggedInUser.getRoleKey() != null && !"".equals(loggedInUser.getRoleKey())) {
    					userListItem.setRoleKeyValuesAsStrings(new String[] { loggedInUser.getRoleKey() });
    				}
    				accessResult = urlService.hasAccessUser(userListItem, requestedUrl);
    			} else if(loggedInOrganization != null) {
    				OrganizationListItem organizationListItem = new OrganizationListItem();
    				organizationListItem.setId(loggedInOrganization.getId());
    				organizationListItem.setTypeKey(new OrganizationTypeKey(loggedInOrganization.getTypeKey()));
    				accessResult = urlService.hasAccessOrganization(organizationListItem, requestedUrl);
    			} else if(national) {
    				accessResult = urlService.hasAccessNational(requestedUrl);
    			} else {
    				accessResult = urlService.hasAccessNone(requestedUrl);
    			}
    			
    			if (accessResult.equals(AccessResult.logup)) {
   	        		createXML(false, loggedInUser, loggedInOrganization, requestedUrl, document, element);
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
    	        		createXML(true, loggedInUser, loggedInOrganization, requestedUrl, document, element);
    	        		redirectUrl = "";
    				} else {
    	        		createXML(false, loggedInUser, loggedInOrganization, requestedUrl, document, element);
    	        		element.appendChild(document.createElement("proxysessionerror"));
    		    		redirectUrl = logUpUrl;
    				}
    			}
    		} else {
    			logger.warn("Either: 1) URL '" + requestedUrl + "' exists in proxy configuration, but not in the administrative database.\n" +
	    			" or: 2) The above URL does not exist in either locations, but the enduser has tampered with the URL and sent a false URL to the proxy controller.");
    			createXML(false, loggedInUser, loggedInOrganization, requestedUrl, document, element);
	    		redirectUrl = logUpUrl;
    		}
    	//}
    	document.appendChild(element);
    	ResultHandler.setResult(this.resultSessionVarName, document);
    	if( ! redirectUrl.equals("")){
            redirect(response, redirectUrl);
    	}
    }
    private void createXML(boolean hasAccess, LoggedInUser loggedInUser, LoggedInOrganization loggedInOrganization, Url requestedUrl, Document document, Element element) throws ParserConfigurationException {
		Element loggedin = document.createElement("loggedin");
		if(loggedInUser != null){
//			proxyresult/loggedin/user
			LoggedInUserToXMLTranslator translator = new LoggedInUserToXMLTranslator();
			translator.translate(loggedInUser, document, loggedin);
		} else if (loggedInOrganization != null){
//			proxyresult/loggedin/organization
			LoggedInOrganizationToXMLTranslator organizationTranslator = new LoggedInOrganizationToXMLTranslator();
			organizationTranslator.translate(loggedInOrganization, document, loggedin);
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
////		proxyresult/resource/encodedurl
//		resource.appendChild(UserToXMLTranslator.cDataElement(document, "encodedurl", encodeURL(requestedUrl.getStringValue())));
		element.appendChild(resource);
		
		if(hasAccess){
//			proxyresult/access
			element.appendChild(document.createElement("access"));
		} else {
//			proxyresult/noaccess
			element.appendChild(document.createElement("noaccess"));
		}
	}
    
//    private String encodeURL(String requestedUrl){
//    	try {
//			return URLEncoder.encode(requestedUrl, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			if(logger.isErrorEnabled()){
//				logger.error("UnsupportedEncodingException in Proxy Login Controller: " + e.getMessage());
//			}
//			return "";
//		}
//    }
    
    private String stripUrlParams(String queryString) {
    	String result = queryString;
    	Map<String, String> queryMap = getQueryMap(result);
		if (queryMap != null && queryMap.size() > 0 && stripUrlParamsSet != null && stripUrlParamsSet.size() > 0) {
			for (String parameter : stripUrlParamsSet) {
				if (queryMap.containsKey(parameter)) {
					result = result.replace(queryMap.get(parameter), "");
				}
			}
		}
		return result;
    }
    
    private Map<String, String> getQueryMap(String query) {  
    	String[] params = query.split("[&\\?]");
    	Map<String, String> map = new HashMap<String, String>();
    	String[] keyValuePair  = null;
    	String name = null;
    	String value = null;
    	for (String param : params) {
    		keyValuePair = param.split("=");
    		if (keyValuePair != null && keyValuePair.length == 2) {
    			name = keyValuePair[0];
	    		value = keyValuePair[1];
	    		if (name != null && value != null) {
	    			map.put(name, value);
	    		}
    		}
    	}
    	return map;  
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
	public LoggedInUser loggedInUser() {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		Object userObject = session.getAttribute(sessionLoggedInUserVarName);
		LoggedInUser loggedInUser = null;
		if (null != userObject && userObject instanceof LoggedInUser) {
			loggedInUser = (LoggedInUser) userObject;
		}
		return loggedInUser;
	}
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return (LoggedInOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
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
	public Set<String> getStripUrlParamsSet() {
		return stripUrlParamsSet;
	}
	public void setStripUrlParamsSet(Set<String> stripUrlParamsSet) {
		this.stripUrlParamsSet = stripUrlParamsSet;
	}
	public boolean getStripUrlParams() {
		return stripUrlParams;
	}
	public void setStripUrlParams(boolean stripUrlParams) {
		this.stripUrlParams = stripUrlParams;
	}
	public void setGeoIpService(GeoIpService geoIpService) {
		this.geoIpService = geoIpService;
	}
	public void setCountryCodes(String countryCodes) {
		this.countryCodes = countryCodes;
	}
}
