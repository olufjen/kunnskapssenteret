package no.helsebiblioteket.evs.plugin;

import java.io.OutputStreamWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LoggedInDataController extends HttpControllerPlugin {
	public static Document printUser() throws ParserConfigurationException {
    	// Not like this anymore.
//    	Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
    	Object sessionVar = LoggedInFunction.loggedIn();
		UserToXMLTranslator userTranslator = new UserToXMLTranslator();
//    	buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		Document result = userTranslator.newDocument();
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
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO: Remove this plugin!
		// FIXME: Move everything to LoggedInFunction!
    	StringBuffer buffer = new StringBuffer();
    	// TODO: Is this the right heading?
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
//		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.append(buffer);
		writer.close();
	}
}
