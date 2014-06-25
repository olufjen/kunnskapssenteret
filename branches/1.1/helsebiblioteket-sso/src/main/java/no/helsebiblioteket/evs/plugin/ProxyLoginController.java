package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

import com.enonic.cms.api.plugin.PluginEnvironment;
import com.enonic.cms.api.plugin.ext.http.HttpController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ProxyLoginController extends HttpController {
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
	private PluginEnvironment pluginEnvironment;
	private Map<String, String> trustedProxies;
	private LogInOrganization logInOrganization;
	
	public void setLogInOrganization(LogInOrganization logInOrganization) {
		this.logInOrganization = logInOrganization;
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// logging in user based on organization first because:
		// If a user clicks a proxy link without having visited the main site first,
		// then the LogInFilter has not been invoked, and the user has not been given the proper session 
		// based on IP/domain.
		logInOrganization.logIn(request, response);
		
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
		boolean national = this.geoIpService.hasAccess(LogInOrganization.getXforwardedForOrRemoteAddress(request), this.countryCodes);
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
				if (isTrustedProxy(requestedUrl)) {
					if (! handleTrustedProxy(response, requestedUrl, group)) {
						createXML(false, loggedInUser, loggedInOrganization, requestedUrl, document, element);
		        		element.appendChild(document.createElement("proxysessionerror"));
			    		redirectUrl = logUpUrl;
					}
				} else if(this.createProxySession(response, requestedUrlText, group)){
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
    	document.appendChild(element);
    	ResultHandler.setResult(this.resultSessionVarName, document, pluginEnvironment.getCurrentSession());
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
    
    private boolean isTrustedProxy(Url url) {
    	return trustedProxies.containsKey(url.getDomain());
    }
    
    /*
     * 1) fetch ticket from vendor site via proxy.
     * 2) redirect user to requested resource (not via proxy)
     * 
     * Using commons Apache's http client to handle ticket requests through ezproxy
     */
    private boolean handleTrustedProxy(HttpServletResponse clientResponse, Url destinationUrl, String group) {
    	String requestTicketUrl = null;
    	if (null != (requestTicketUrl = trustedProxies.get(destinationUrl.getDomain()))) {
    		String encodedTarget = null;
    		try {
				encodedTarget = URLEncoder.encode(destinationUrl.getStringValue(), "UTF-8");
			} catch (UnsupportedEncodingException use) {
				logger.error("Unable to url encode " + encodedTarget, use);
				return false;
			}
    		String redirectTo = null;
    		requestTicketUrl += encodedTarget;
    		URL proxyServerUrl = createProxyServerUrl(requestTicketUrl, group);
    		DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
                HttpHost target = new HttpHost(proxyServerUrl.getHost(), 80, "http");
                HttpGet req = new HttpGet(proxyServerUrl.getFile());
                logger.info("executing request to " + target);
                HttpResponse rsp = httpclient.execute(target, req);
                HttpEntity entity = rsp.getEntity();
                if (entity != null) {
                	redirectTo = EntityUtils.toString(entity);
                }
                logger.debug("----------------------------------------");
                logger.debug(rsp.getStatusLine());
                if (logger.isDebugEnabled()) {
                	Header[] headers = rsp.getAllHeaders();
	                for (int i = 0; i<headers.length; i++) {
	                    logger.debug(headers[i]);
	                }
                }
                logger.debug("----------------------------------------");
                logger.debug("Redirecting to " + redirectTo);
	    		logger.debug("requestTicketUrl was " + requestTicketUrl);
	    		logger.debug("group was " + group);
	    		logger.debug("proxyServerUrl was " + proxyServerUrl);
	    		logger.debug("Requested destination was " + destinationUrl.getStringValue()); 
	    		logger.debug("Encoded destination was " + encodedTarget);
                logger.info("Redirecting to " + redirectTo);
	    		clientResponse.sendRedirect(redirectTo);
            } catch(IOException ioe) {
            	logger.error("Unable to redirect user to " + redirectTo
    					+ ".\nrequestTicketUrl was " + requestTicketUrl
    					+ ".\ngroup was " + group
    					+ ".\nproxyServerUrl was " + proxyServerUrl
    					+ ".\nRequested destination was " + destinationUrl.getStringValue() 
    					+ ".\nEncoded destination was " + encodedTarget, ioe);
    			return false;
            }
            finally {
                // When HttpClient instance is no longer needed,
                // shut down the connection manager to ensure
                // immediate deallocation of all system resources
                httpclient.getConnectionManager().shutdown();
            }
    	}
    	return true;
    }
    
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
        //HttpURLConnection.setFollowRedirects(false);
        URL proxyServerUrl = createProxyServerUrl(destinationUrl, group);
        if (null == proxyServerUrl) return false;
        try {
            HttpURLConnection proxyServer = (HttpURLConnection) proxyServerUrl.openConnection();
            proxyServer.setInstanceFollowRedirects(false);
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
	
	private URL createProxyServerUrl(String destinationUrl, String group) {
		// Create a URLConnection to proxy server
        String url = this.proxyUrl + "?user=" + group + "&pass=" + this.proxyPassword
                + (this.proxyUseGroup && (group != null) ? "&group=" + group : null)
                + "&url=" + destinationUrl;
        URL proxyServerUrl = null;
		try {
			proxyServerUrl = new URL(url);
		} catch (MalformedURLException mue) {
			logger.error("Unable to create URL from string '" + url + "'", mue);
		}
        return proxyServerUrl;
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
		HttpSession session = pluginEnvironment.getCurrentSession();
		Object userObject = session.getAttribute(sessionLoggedInUserVarName);
		LoggedInUser loggedInUser = null;
		if (null != userObject && userObject instanceof LoggedInUser) {
			loggedInUser = (LoggedInUser) userObject;
		}
		return loggedInUser;
	}
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = pluginEnvironment.getCurrentSession();
        LoggedInOrganizationWrapper wrapped = (LoggedInOrganizationWrapper)session.getAttribute(sessionLoggedInOrganizationVarName);
        //return (LoggedInOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
        return wrapped != null ? wrapped.getWrapped() : null;
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
	public void setPluginEnvironment(PluginEnvironment pluginEnvironment) {
		this.pluginEnvironment = pluginEnvironment;
	}
	public void setTrustedProxies(Map<String, String> trustedProxies) {
		this.trustedProxies = trustedProxies;
	}
}