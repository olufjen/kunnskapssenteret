package no.helsebiblioteket.evs.plugin;

import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LogInController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionVarName;
	private LoginService loginService;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	this.logger.info("LogInController RUNNING");
    	// FIXME: Use a map of parameters inSpring with sessionVar and these two!
    	String username = request.getParameter("brukernavn");
    	String password = request.getParameter("passord");
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	user = this.loginService.logInUser(user);
    	if(user == null){
    		request.getSession().setAttribute(this.sessionVarName, user);
    	} else {
    		
    	}
    		
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");

		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.close();
    	this.logger.info("LoggedInData DONE");
	}
	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
}
