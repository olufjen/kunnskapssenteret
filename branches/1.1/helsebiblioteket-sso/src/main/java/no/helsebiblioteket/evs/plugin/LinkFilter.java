package no.helsebiblioteket.evs.plugin;

/**
 * Important: Properties injected by Spring in this class are being set correctly,
 * but the same properties are null when the method "filterResponse" run.
 */


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;
import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUrl;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import com.enonic.cms.api.plugin.PluginEnvironment;
import com.enonic.cms.api.plugin.ext.http.HttpResponseFilter;

public final class LinkFilter extends HttpResponseFilter {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private static final String linkFilterOverrideUrlParam = "linkfilteroverride";
	private URLService urlService;
	private final static String invalidHrefRegExp = "javascript:.*|mailto:.*";
	private final static String linkRegExp = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";// "<a href=(['\"])(.*?)\\1";
	private final static String completeLinkRegExp = "<a\\b[^>]*href=\"[^>]*>(.*?)</a>";
	private final static Pattern linkPattern;
	private final static Pattern completeLinkPattern;
	private GeoIpService geoIpService;
	private String countryCodes;
	private PluginEnvironment pluginEnvironment;

	static {
		// precompile patterns
		linkPattern = Pattern.compile(linkRegExp);
		completeLinkPattern = Pattern.compile(completeLinkRegExp);
	}
    
    public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {
    	// Do not use springinjected properties like loggedinfunction.
    	// See comments above
    	if (urlService == null) {
			logger.error("urlService is null in beginning of method 'filterResponse'!");
		}
    	HttpSession session = pluginEnvironment.getCurrentSession();
    	return generateProxyLinks(request, session, response, contentType);
    }
    
    public String generateProxyLinks(HttpServletRequest request, HttpSession session, String response, String contentType) throws Exception {
    	//long timeStart = System.currentTimeMillis();
    	LoggedInUser user = (LoggedInUser) session.getAttribute(sessionLoggedInUserVarName);
		LoggedInOrganization memberOrganization = (LoggedInOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
		Map<String, String> linkReplaceMap = new HashMap<String, String>();
		Matcher linkMatcher = null;
		Matcher completeLinkMatcher = completeLinkPattern.matcher(response);
		boolean linkFilterOverride = false;
		String originalCompleteHref = null;
		String newCompleteHref = null;
		String oldLink = null;
		URL url = null;
    	while (completeLinkMatcher.find()) {
    		originalCompleteHref = completeLinkMatcher.group(0);
    		linkMatcher = linkPattern.matcher(originalCompleteHref);
    		if (linkMatcher.find()) {
	    		oldLink = linkMatcher.group(2);
	    		linkFilterOverride = oldLink.contains(LinkFilter.linkFilterOverrideUrlParam);
				//String oldLinkDeampified = oldLink.replace("&amp;", "&");
				if (!linkFilterOverride && validHref(oldLink)) {
					oldLink = deproxify(oldLink);
					url = generateURL(oldLink);
					if(url != null) {
			    		if (!linkFilterOverride && this.isAffected(url)) {
			    			try {
			    				boolean national = this.geoIpService.hasAccess(LogInInterceptor.getXforwardedForOrRemoteAddress(request), this.countryCodes);
			    				url = this.translate(user, memberOrganization, url, national);
			    			} catch (MalformedURLException e) {
			    				url = null;
							}
				    		if(url != null){
					    		String newLink = url.toExternalForm();
					    		if (! oldLink.equals(newLink)) {
					    			//originalCompleteHref = deproxify(originalCompleteHref);
					    			newCompleteHref = deproxify(originalCompleteHref).replace(oldLink, newLink);
					    			// using map to avoid duplicate replacements
					    			// also only adding links that are actually changed to the map.
					    			linkReplaceMap.put(originalCompleteHref, newCompleteHref);
					    		}
				    		}
			    		}
					}
				}
    		}
    	}
    	
    	String toLink = null;
    	for (String fromLink : linkReplaceMap.keySet()) {
    		//logger.info("replacing " + fromLink + " with " + linkReplaceMap.get(fromLink));
    		toLink = linkReplaceMap.get(fromLink);
    		response = response.replace(fromLink, toLink);
    		//if (content.contains("proxy.helsebiblioteket.no/login?url=http://proxy.helsebiblioteket.no/login?url=")) {
    		//	logger.info("\n----------------\ndouble proxy prefix detected! see previous line\n----------------");
    		//}
    	}
    	//logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
    	return response;
    }
    
    private URL generateURL(String href) {
    	URL url = null;
		try {
			url = new URL(href);
		} catch (MalformedURLException ignored) {
			// This will happen each time a relative URL is encountered. 
			// This is OK and is not to be treated as an error or logged as such.
		}
		return url;
    }
    
	private String deproxify(String href) {
		if (href.contains("http://proxy.helsebiblioteket.no/login?url=")) {
			href = href.replace("http://proxy.helsebiblioteket.no/login?url=", "");
		} else if (href.contains(".proxy.helsebiblioteket.no")) {
			href = href.replace(".proxy.helsebiblioteket.no", "");
		} else if (href.contains("http://proxy-t.helsebiblioteket.no/login?url=")) {
			href = href.replace("http://proxy-t.helsebiblioteket.no/login?url=", "");
		} else if (href.contains(".proxy-t.helsebiblioteket.no")) {
			href = href.replace(".proxy-t.helsebiblioteket.no", "");
		}
		return href;
	}
	
	private boolean validHref(String s) {
		if (s.matches(invalidHrefRegExp)) {
			return false;
		}
		return true;
	}
	
	private boolean isAffected(URL url) {
		boolean result = false;
		Url myurl = new Url();
		myurl.setStringValue(url.toExternalForm());
		myurl.setDomain(url.getHost());
		result = this.urlService.isAffected(myurl);
		return result;
	}
	
	private URL translate(LoggedInUser user, LoggedInOrganization organization, URL url, boolean national) throws MalformedURLException {
		Url myUrl = new Url();
		myUrl.setStringValue(url.toExternalForm());
		myUrl.setDomain(url.getHost());
		SingleResultUrl result;
 		if(user != null && organization != null){
			UserListItem userL = new UserListItem();
			userL.setId(user.getId());
			userL.setRoleKeys(new UserRoleKey[]{new UserRoleKey(user.getRoleKey())});
			userL.setRoleKeyValuesAsStrings(new String[] { user.getRoleKey() });
			OrganizationListItem orgL = new OrganizationListItem();
			orgL.setId(organization.getId());
			orgL.setTypeKey(new OrganizationTypeKey(organization.getTypeKey()));
			result = this.urlService.translateUrlUserOrganization(userL, orgL, myUrl);
		} else if(user != null){
			UserListItem userL = new UserListItem();
			userL.setId(user.getId());
			userL.setRoleKeys(new UserRoleKey[]{new UserRoleKey(user.getRoleKey())});
			userL.setRoleKeyValuesAsStrings(new String[] { user.getRoleKey() });
			result = this.urlService.translateUrlUser(userL, myUrl);
		} else if(organization != null){
			OrganizationListItem orgL = new OrganizationListItem();
			orgL.setId(organization.getId());
			orgL.setTypeKey(new OrganizationTypeKey(organization.getTypeKey()));
			result = this.urlService.translateUrlOrganization(orgL, myUrl);
		} else if(national){
			result = this.urlService.translateUrlNational(myUrl);
		} else {
			result = this.urlService.translateUrlNone(myUrl);
		}
		
		if(result instanceof EmptyResultUrl){
			logger.error("Unable to translate URL '" + myUrl + "' for user '" + user + "' and organization '" + organization + "'" );
			return url;
		} else {
			ValueResultUrl value = (ValueResultUrl) result;
			if (value == null) {
				logger.error("value is null for attempted translated URL '" + myUrl + "'");
				return url;
			} else {
				return new URL(value.getValue().getStringValue());
			}
		}
	}

	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
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
}
