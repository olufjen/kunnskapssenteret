package no.helsebiblioteket.evs.plugin;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.URLService;

import com.enonic.cms.api.plugin.HttpResponseFilterPlugin;

public final class LinkFilter extends HttpResponseFilterPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionVarName;
	private URLService urlService;
    
    public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {

    	// FIXME: Remove!
    	if(true) return response;
    	
    	
    	
    	Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
    	Organization organization = null;
    	User user = null; 
    	if(sessionVar instanceof Organization){
    		organization = (Organization) sessionVar;
    	} else if (sessionVar instanceof User){
    		user = (User) sessionVar;
    	}
    	this.logger.info("LinkFilter RUNNING");
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
    		URL url = this.deproxyfy(href);
    		if(url != null){
        		if(this.isAffected(url)){
            		// TODO: Add locks to links!
            		// TODO: Links are translated to contain proxy values!
            		url = this.translate(user, organization, url);
               		element.setAttribute("href", url.toExternalForm());
        		} else {
               		element.setAttribute("href", url.toExternalForm());
        		}
        		this.logger.info("URL: " + url.toExternalForm());
    		} else {
        		this.logger.info("URL: " + url);
    		}
    	}
    	this.logger.info("LinkFilter DONE");
        return new XMLOutputter().outputString(doc);
//		result = response.replaceAll(TAG_TO_REPLACE, "USR: " + user.getUsername());
//		"";//element.getAttributeValue("href");
//		this.myService.fixURK(user/org, "gin.net")
//    	Element.
//    	 build("http://www.cafeconleche.org/");
//        String remoteAddr = request.getRemoteAddr();
        
    }
	private URL deproxyfy(String href) {
		try {
			URL old = new URL(href);
//	 		Domain name always ends with proxy.helsebiblioteket.no
//			http://java.sun.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING");
//			protocol = http
//			authority = java.sun.com:80
//			host = java.sun.com
//			port = 80
//			path = /docs/books/tutorial/index.html
//			query = name=networking
//			filename = /docs/books/tutorial/index.html?name=networking
//			ref = DOWNLOADING

			String oldHost = old.getHost();
			if(oldHost.toLowerCase().startsWith("proxy.helsebiblioteket.no")){
//		    	http://proxy.helsebiblioteket.no/login?url=http://www.legehandboka.no
//		      	=> http://www.legehandboka.no
				// TODO: Better solution?
				return new URL(this.getQueryMap(old.getQuery()).get("url"));
			} else if (oldHost.toLowerCase().endsWith("proxy.helsebiblioteket.no")){
//		    	http://www.g-i-n.net.proxy.helsebiblioteket.no/
//		    	=> http://www.g-i-n.net
				// TODO: Better solution?
				String newHost = oldHost.replace("proxy.helsebiblioteket.no", "");
				String newHref = old.getProtocol() +
					newHost +
					old.getPort() +
					old.getPath() +
					old.getFile() +
					old.getQuery();
				return new URL(newHref);
			} else {
				return old;
			}
		} catch (MalformedURLException e) {
			// TODO What to do here?
			return null;
		}
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
		myurl.setValue(url.toExternalForm());
		return this.urlService.isAffected(myurl);
	}
	private URL translate(User user, Organization organization, URL url) throws MalformedURLException {
		// TODO: Cache as much as possible, but flush at some set interval!
		Url myurl = new Url();
		myurl.setValue(url.toExternalForm());
		return new URL(this.urlService.translate(user, organization, myurl).getValue());
	}

	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
}
