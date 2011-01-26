package no.helsebiblioteket.evs.plugin;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;
import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.LoggedInOrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.LoggedInUserToXMLTranslator;
import no.helsebiblioteket.admin.translator.PositionToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;
import no.helsebiblioteket.evs.plugin.result.XMLTranslator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.PluginEnvironment;

public class LoggedInFunction{
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private UserService userService;
	private final Log logger = LogFactory.getLog(getClass());
	
	public Document getUserAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return XMLTranslator.translate(printLoggedIn());
	}
	public Document getPositionsAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return XMLTranslator.translate(printPositions());
	}
	public Document getResult(String key) throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return ResultHandler.getResult(key);
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
		LoggedInUser user = this.loggedInUser();
		LoggedInOrganization organization = this.loggedInOrganization();

		UserToXMLTranslator userTranslator = new UserToXMLTranslator();
		org.w3c.dom.Document result = userTranslator.newDocument();
		Element loggedinElement = result.createElement("loggedin");
    	if(organization != null){
    		LoggedInOrganizationToXMLTranslator translator = new LoggedInOrganizationToXMLTranslator();
    		translator.translate(organization, result, loggedinElement);
		} else {
    		loggedinElement.appendChild(result.createElement("noorganization"));
		}
    	if(user != null){
			LoggedInUserToXMLTranslator organizationUserTranslator = new LoggedInUserToXMLTranslator();
			organizationUserTranslator.translate(user, result, loggedinElement);
		} else {
    		loggedinElement.appendChild(result.createElement("nouser"));
		}
    	if(organization == null && user == null){
    		loggedinElement.appendChild(result.createElement("none"));
    	}
		result.appendChild(loggedinElement);
		return result;
	}
	
	private LoggedInUser loggedInUser() {
		
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		LoggedInUser loggedInUser = (LoggedInUser) session.getAttribute(sessionLoggedInUserVarName);
		// jan 2011: extra logging to nail enonic session trouble:
		{
			HttpServletRequest request = PluginEnvironment.getInstance().getCurrentRequest();
			LoggedInOrganization loggedInOrganization = (LoggedInOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
			logger.info("Logged in user: " + loggedInUser != null ? loggedInUser.getUsername() : " null logged in user "+ "org: " + loggedInOrganization != null ? loggedInOrganization.getNameNorwegianNormal() : " null logged in organization "
				+ " session id: " + session.getId() + " created at: " +  session.getCreationTime()
				+ " remote addr: " + request.getRemoteAddr() + " header: " + RequestPrinter.getRequestHeaders(request));
		}
		return loggedInUser;
	}
	
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return (LoggedInOrganization)session.getAttribute(sessionLoggedInOrganizationVarName);
		
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
