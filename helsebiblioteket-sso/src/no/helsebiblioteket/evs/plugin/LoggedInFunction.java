package no.helsebiblioteket.evs.plugin;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.PluginEnvironment;

public class LoggedInFunction{
	// TODO: Set in bean, but global?
	private static String sessionLoggedInVarName = "loggedin";
	private static String sessionResultsVarName = "results";

	public static void logIn(Object user){
		PluginEnvironment.getInstance().getCurrentSession().setAttribute(sessionLoggedInVarName, user);
	}
	public static void logOut(){
		PluginEnvironment.getInstance().getCurrentSession().setAttribute(sessionLoggedInVarName, null);
	}
	public static Object loggedIn() {
		return PluginEnvironment.getInstance().getCurrentSession().getAttribute(sessionLoggedInVarName);
	}
	public Document getUserAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return translate(printUser());
	}
	public static void setResult(String key, org.w3c.dom.Document result) {
		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>) PluginEnvironment.getInstance().getCurrentSession().getAttribute("result");
		if(userMap == null){
			userMap = new HashMap<String, org.w3c.dom.Document>();
			PluginEnvironment.getInstance().getCurrentSession().setAttribute(sessionResultsVarName, userMap);
		}
		userMap.put(key, result);
	}
	public Document getResult(String key) throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>) PluginEnvironment.getInstance().getCurrentSession().getAttribute("result");
		org.w3c.dom.Document result = null;
		if(userMap != null){
			result = userMap.get(key);
			// TODO: Re-insert this line!
//			userMap.remove(key);
		}
		if(result == null){
			result = emptyXML(key);
		}
		return translate(result);
	}
	private org.w3c.dom.Document emptyXML(String key) throws ParserConfigurationException {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		org.w3c.dom.Document result = translator.newDocument();
		Element element = result.createElement(key);
		Element empty = result.createElement("empty");
		element.appendChild(empty);
		result.appendChild(element);
		return result;
	}
	public static org.w3c.dom.Document printUser() throws ParserConfigurationException {
	    	// Not like this anymore.
	//    	Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
	    	Object sessionVar = LoggedInFunction.loggedIn();
			UserToXMLTranslator userTranslator = new UserToXMLTranslator();
	//    	buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			org.w3c.dom.Document result = userTranslator.newDocument();
			Element loggedinElement = result.createElement("loggedin");
	    	if(sessionVar instanceof Organization){
	        	Organization organization = (Organization) sessionVar;
	    		OrganizationToXMLTranslator translator = new OrganizationToXMLTranslator();
	    		translator.translate(organization, result, loggedinElement);
	    	} else if (sessionVar instanceof User){
	        	User user = (User) sessionVar;
	        	userTranslator.translate(user, result, loggedinElement);
	    	} else {
	    		loggedinElement.appendChild(result.createElement("none"));
	    	}
			result.appendChild(loggedinElement);
			return result;
		}
	private Document translate(org.w3c.dom.Document document) throws TransformerException, JDOMException, IOException {
		// TODO: Different JDOM classes. Fix!
		Source source = new DOMSource(document);
        StringWriter stringWriter = new StringWriter();
		Result result = new StreamResult(stringWriter);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(source, result);
		
		SAXBuilder parser = new SAXBuilder();
    	StringReader sr = new StringReader(stringWriter.getBuffer().toString());
    	Document doc = parser.build(sr);
		return doc;
		
	}
}
