package no.helsebiblioteket.evs.plugin;

/**
 * Important: Properties injected by Spring in this class are being set correctly,
 * but the same properties are null when the method "filterResponse" run.
 */


import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
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
    	// TODO: Do not use springinjected properties like loggedinfunction.
    	// See comments above
    	
    	HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		
    	Object userObject = session.getAttribute(sessionLoggedInUserVarName);
    	User user = null;
    	if (userObject instanceof User) {
			user = (User) userObject;
		} else if (userObject instanceof OrganizationUser) {
			user = ((OrganizationUser) userObject).getUser();
		}
    	
		MemberOrganization memberOrganization = (MemberOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
		
		//long timeStart = System.currentTimeMillis();
		
		URL url = null;
		String oldLink = null;
		String newLink = null;
		Map<String, String> linkReplaceMap = new HashMap<String, String>();
		Matcher m = linkPattern.matcher(response);
		
    	while (m.find()) {
    		oldLink = m.group(2);
			if (validHref(oldLink)) {
				url = this.deproxify(oldLink);
				if(url != null) {
		    		if(this.isAffected(url)){
		    			url = this.translate(user, memberOrganization, url);
		    		}
		    		newLink = url.toExternalForm();
		    		if (!oldLink.equals(newLink)) {
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

	
	private Map<String, String> getQueryMap(String query){
	    String[] params = query.split("&");
	    Map<String, String> map = new HashMap<String, String>();
	    for (String param : params) {
	        String name = param.split("=")[0];
	        String value = param.split("=")[1];
	        map.put(name, value);
	    }
	    return map;
	}
	
	private boolean isAffected(URL url) {
		Url myurl = new Url();
		myurl.setStringValue(url.toExternalForm());
		return this.urlService.isAffected(myurl);
	}
	
	private URL translate(User user, MemberOrganization organization, URL url) throws MalformedURLException {
		Url myUrl = new Url();
		myUrl.setStringValue(url.toExternalForm());
		SingleResultUrl result = this.urlService.translateUrlUserOrganization(user, organization, myUrl);
		if(result instanceof EmptyResultUrl){
			logger.error("Unable to translate URL '" + myUrl + "' for user '" + user + "' and organization '" + organization + "'" );
			return url;
		} else {
			ValueResultUrl value = (ValueResultUrl) result;
			return new URL(value.getValue().getStringValue());
		}
	}

	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
}
