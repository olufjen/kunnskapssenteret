package no.helsebiblioteket.evs.plugin;

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
    		// Nothing
    	}
		String referer = request.getHeader("referer");
    	this.logger.info("Referer: " + referer);
		response.sendRedirect(referer);
    	this.logger.info("LogInController DONE");
    	
//    	http://localhost:8080/cms/site/2/logIn?brukernavn=leiftorger&passord=password
	}
	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
