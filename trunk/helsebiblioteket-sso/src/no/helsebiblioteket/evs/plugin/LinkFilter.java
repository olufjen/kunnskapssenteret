package no.helsebiblioteket.evs.plugin;

/**
 * Important: Properties injected by Spring in this class are being set correctly,
 * but the same properties are null when the method "filterResponse" run.
 */


import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private String sessionVarName;
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private URLService urlService; 
    
    public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {
    	// TODO: Do not user springinjected properties like loggedinfunction.
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
    	
    	this.logger.debug("LinkFilter RUNNING");
    	SAXBuilder parser = new SAXBuilder();
    	StringReader sr = new StringReader(response);
    	Document doc = parser.build(sr);
    	XPath path = XPath.newInstance("//*[@href]");////a");
//    	Element root = doc.getRootElement();
//    	root.detach();
//    	Document newDoc = new Document(root);
//    	List<Element> allA = doc.getContent(new ElementFilter("a"));

    	List<Element> allAElements = path.selectNodes(doc);
    	for (Element element : allAElements) {
//    		this.logger.info("ELEM: " + element.getText());
    		String href = element.getAttributeValue("href");
    		URL url = this.deproxify(href);
    		if(url != null){
        		if(this.isAffected(url)){
        			url = this.translate(user, memberOrganization, url);
               		element.setAttribute("href", url.toExternalForm());
        		} else {
               		element.setAttribute("href", url.toExternalForm());
        		}
        		this.logger.debug("URL: " + url.toExternalForm());
    		} else {
        		this.logger.debug("URL: " + url);
    		}
    	}
    	this.logger.debug("LinkFilter DONE");
        return new XMLOutputter().outputString(doc);
//		result = response.replaceAll(TAG_TO_REPLACE, "USR: " + user.getUsername());
//		"";//element.getAttributeValue("href");
//		this.myService.fixURK(user/org, "gin.net")
//    	Element.
//    	 build("http://www.cafeconleche.org/");
//        String remoteAddr = request.getRemoteAddr();
        
    }
	private URL deproxify(String href) {
		if (href.contains("http://proxy.helsebiblioteket.no/login?url=")) {
			href = href.replace("http://proxy.helsebiblioteket.no/login?url=", "");
		} else if (href.contains("proxy.helsebiblioteket.no")) {
			href = href.replace("proxy.helsebiblioteket.no", "");
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
		// TODO: Cache as much as possible, but flush at some set interval!
		Url myurl = new Url();
		myurl.setStringValue(url.toExternalForm());
		return this.urlService.isAffected(myurl);
	}
	private URL translate(User user, MemberOrganization organization, URL url) throws MalformedURLException {
		// TODO: Cache as much as possible, but flush at some set interval!
		Url myurl = new Url();
		myurl.setStringValue(url.toExternalForm());
		SingleResultUrl result = this.urlService.translateUrlUserOrganization(user, organization, myurl);
		if(result instanceof EmptyResultUrl){
			// TODO: What to do here?
			return url;
		} else {
			ValueResultUrl value = (ValueResultUrl) result;
			return new URL(value.getValue().getStringValue());
		}
	}

	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
}
