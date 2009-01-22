package no.helsebiblioteket.evs.plugin;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.PositionToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.PluginEnvironment;

public class LoggedInFunction{
	// TODO: Set in bean, but global?
	private String sessionLoggedInVarName = "hbloggedin";
	private String sessionResultsVarName = "hbresults";
	private OrganizationService organizationService;

	public void logIn(Object user){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInVarName, user);
	}
	public void logOut(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInVarName, null);
	}
	public Object loggedIn() {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return session.getAttribute(sessionLoggedInVarName);
	}
	public Document getUserAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return translate(printUser());
	}
	public Document getPositionsAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return translate(printPositions());
	}
	public void setResult(String key, org.w3c.dom.Document result) {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 

		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>)
			session.getAttribute(sessionResultsVarName);
		
		if(userMap == null){
			userMap = new HashMap<String, org.w3c.dom.Document>();
			PluginEnvironment.getInstance().getCurrentSession().setAttribute(sessionResultsVarName, userMap);
		}
		userMap.put(key, result);
	}
	public Document getResult(String key) throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>)
			session.getAttribute(sessionResultsVarName);
		
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
	public org.w3c.dom.Document printPositions() throws ParserConfigurationException {
		UserToXMLTranslator userTranslator = new UserToXMLTranslator();
		PositionToXMLTranslator positionToXMLTranslator = new PositionToXMLTranslator();
		org.w3c.dom.Document document = userTranslator.newDocument();
		Element element = document.createElement("positions");
		Position[] positions = this.organizationService.getAllPositions("").getList();
		for (Position position : positions) {
			positionToXMLTranslator.translate(position, document, element);
		}
		document.appendChild(element);
		return document;
	}

	public org.w3c.dom.Document printUser() throws ParserConfigurationException {
	    	// Not like this anymore.
	//    	Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
	    	Object sessionVar = loggedIn();
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
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
