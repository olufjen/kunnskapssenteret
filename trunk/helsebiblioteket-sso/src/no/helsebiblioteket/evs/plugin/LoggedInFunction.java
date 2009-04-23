package no.helsebiblioteket.evs.plugin;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
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

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.OrganizationUserToXMLTranslator;
import no.helsebiblioteket.admin.translator.PositionToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.PluginEnvironment;

public class LoggedInFunction{
	// TODO: Set in bean, but global?
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private String sessionResultsVarName = "hbresults";
	private UserService userService;

	public void logInUser(User user){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInUserVarName, user);
	}
	public void logInOrganizationUser(OrganizationUser user){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInUserVarName, user);
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
	public void logOutUser(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInUserVarName, null);
	}
	public void logInOrganization(MemberOrganization organization){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInOrganizationVarName, organization);
	}
	public MemberOrganization loggedInOrganization() {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return (MemberOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
	}
	public Document getUserAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		// TODO: Rename this, but then also in XML for EVS.
		return translate(printLoggedIn());
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
			userMap.remove(key);
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
	private org.w3c.dom.Document printPositions() throws ParserConfigurationException {
		ListResultPosition resultPosition = this.userService.getPositionListAll("");
		Position[] positions = resultPosition.getList();
		org.w3c.dom.Document document = null;
		UserToXMLTranslator userTranslator = new UserToXMLTranslator();
		PositionToXMLTranslator positionToXMLTranslator = new PositionToXMLTranslator();
		document = userTranslator.newDocument();
		Element element = document.createElement("positions");
		for (Position position : positions) {
			positionToXMLTranslator.translate(position, document, element);
		}
		document.appendChild(element);
		return document;
	}

	private org.w3c.dom.Document printLoggedIn() throws ParserConfigurationException {
		Object user = this.loggedInUser();
		
		MemberOrganization memberOrganization = this.loggedInOrganization();
		Organization organization = null;
		if (memberOrganization != null) {
			organization = memberOrganization.getOrganization();
		}
		UserToXMLTranslator userTranslator = new UserToXMLTranslator();
		org.w3c.dom.Document result = userTranslator.newDocument();
		Element loggedinElement = result.createElement("loggedin");
    	if(organization != null){
    		OrganizationToXMLTranslator translator = new OrganizationToXMLTranslator();
    		translator.translate(organization, result, loggedinElement);
		} else {
    		loggedinElement.appendChild(result.createElement("noorganization"));
		}
		if(user instanceof User){
        	userTranslator.translate((User)user, result, loggedinElement);
		} else if(user instanceof OrganizationUser){
			OrganizationUserToXMLTranslator organizationUserTranslator = new OrganizationUserToXMLTranslator();
			organizationUserTranslator.translate((OrganizationUser) user, result, loggedinElement);
		} else {
    		loggedinElement.appendChild(result.createElement("nouser"));
		}
    	if(organization == null && user == null){
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
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}