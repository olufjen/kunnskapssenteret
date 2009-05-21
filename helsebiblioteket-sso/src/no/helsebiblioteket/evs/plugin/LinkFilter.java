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

import com.enonic.cms.api.plugin.HttpResponseFilterPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

public final class LinkFilter extends HttpResponseFilterPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private URLService urlService;
	private final static String invalidHrefRegExp = "javascript:.*|mailto:.*";
	private final static String linkRegExp = "<a href=(['\"])(.*?)\\1";
	private final static Pattern linkPattern;

	static {
		// precompile patterns
		linkPattern = Pattern.compile(linkRegExp);
	}
    
    public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {
    	// Do not use springinjected properties like loggedinfunction.
    	// See comments above
    	
    	HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		
    	LoggedInUser user = (LoggedInUser) session.getAttribute(sessionLoggedInUserVarName);
		LoggedInOrganization memberOrganization = (LoggedInOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
		
		//long timeStart = System.currentTimeMillis();
		
		Map<String, String> linkReplaceMap = new HashMap<String, String>();
		Matcher m = linkPattern.matcher(response);
		
    	while (m.find()) {
    		String oldLink = m.group(2);
    		String oldLinkDeampified = oldLink.replace("&amp;", "&");
			if (validHref(oldLink)) {
				URL url = this.deproxify(oldLinkDeampified);
				if(url != null) {
		    		if(this.isAffected(url)){
		    			url = this.translate(user, memberOrganization, url);
		    		}
		    		String newLink = url.toExternalForm();
		    		if (!oldLinkDeampified.equals(newLink)) {
		    			// using map to avoid duplicate replacements
		    			// also only adding links that are actually changed to the map.
		    			linkReplaceMap.put(oldLink, newLink);
		    		}
				}
			}
    	}
    	
    	for (String link : linkReplaceMap.keySet()) {
    		response = response.replace(link, linkReplaceMap.get(link));
    	}
    	
    	//logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
    	
		return response;
    }
	private URL deproxify(String href) {
		if (href.contains("http://proxy.helsebiblioteket.no/login?url=")) {
			href = href.replace("http://proxy.helsebiblioteket.no/login?url=", "");
		} else if (href.contains("proxy.helsebiblioteket.no")) {
			href = href.replace("proxy.helsebiblioteket.no", "");
		} else if (href.contains("http://proxy-t.helsebiblioteket.no/login?url=")) {
			href = href.replace("http://proxy-t.helsebiblioteket.no/login?url=", "");
		} else if (href.contains("proxy-t.helsebiblioteket.no")) {
			href = href.replace("proxy-t.helsebiblioteket.no", "");
		}
		URL url = null;
		try {
			url = new URL(href);
		} catch (MalformedURLException ignored) {
			// This will happen each time a relative URL is encountered. 
			// This is OK and is not to be treated as an error or logged as such.
		}
		return url;
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
		result = this.urlService.isAffected(myurl);
		return result;
	}
	
	private URL translate(LoggedInUser user, LoggedInOrganization organization, URL url) throws MalformedURLException {
		Url myUrl = new Url();
		myUrl.setStringValue(url.toExternalForm());
		
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
			}
			return new URL(value.getValue().getStringValue());
		}
	}

	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
}
