package no.helsebiblioteket.evs.plugin;

import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.translator.OrganizationToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LoggedInDataController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionVarName;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	this.logger.info("LoggedInData RUNNING");
    	Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
		// TODO: Use buffer, etc correctly!
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("<loggedin>");
    	if(sessionVar instanceof Organization){
        	Organization organization = (Organization) sessionVar;
    		OrganizationToXMLTranslator translator = new OrganizationToXMLTranslator();
    		translator.translate(organization, buffer);
    	} else if (sessionVar instanceof User){
        	User user = (User) sessionVar;
        	UserToXMLTranslator translator = new UserToXMLTranslator();
        	translator.translate(user, buffer);
    	} else {
    		buffer.append("<none />");
    	}
    	buffer.append("</loggedin>");
    	
    	// TODO: Is this the right heading?
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");

		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.append(buffer);
		writer.close();
    	this.logger.info("LoggedInData DONE");
	}
	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
}
