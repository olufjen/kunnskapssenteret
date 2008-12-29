package no.helsebiblioteket.evs.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LogInController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String loggedInSessionVarName;
	private String resultSessionVarName;
	private LoginService loginService;
	private Map<String, String> parameterNames;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	this.logger.info("LogInController RUNNING");
    	// TODO: Real parsing of query string
//    	String prefix = "&" + this.parameterNames.get("resultName") + "=" +
//    		this.parameterNames.get("resultValue");
//    	if(request.getQueryString().endsWith(prefix)){
//    		this.result(request, response);
//    	} else {
    		this.login(request, response);
//    	}
    	this.logger.info("LogInController DONE");
    	// TODO: From and goto in Spring or in the HTML-forms?
//    	http://localhost:8080/cms/site/2/login?brukernavn=leiftorger&passord=password
//    	http://localhost:8080/cms/site/2/login?errors=true
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = new StringBuffer();
		result.append("<" + this.resultSessionVarName + ">");
    	String username = request.getParameter(this.parameterNames.get("username"));
    	String password = request.getParameter(this.parameterNames.get("password"));
    	if(username == null) { username = ""; }
    	if(password == null) { password = ""; }
    	User user = null;
       	if(username.length() != 0 && password.length() != 0){
       		user = new User();
       		user.setUsername(username);
           	user.setPassword(password);
        	user = this.loginService.logInUser(user);
       	}
    	if(user != null){
    		// Found user!
    		result.append("<success/>");
    		LoggedInFunction.logIn(this.loggedInSessionVarName, request.getSession(), user);
    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
//        	this.logger.info("Goto: " + gotoUrl);
    		response.sendRedirect(gotoUrl);
    	} else {
    		// Return!
    		makeXML(username, password, result);
    		String referer = request.getParameter(this.parameterNames.get("from"));
//        	this.logger.info("From: " + referer);
    		response.sendRedirect(referer);
    	}
		result.append("</" + this.resultSessionVarName + ">");
    	LoggedInFunction.setResult(this.resultSessionVarName, result);
	}
	private void makeXML(String username, String password, StringBuffer result) {
		boolean lookup = true;
		result.append("<values><username>");
		result.append(username);
		result.append("</username><password>");
		result.append(password);
		result.append("</password></values><messages><username>");
		if(username.length() == 0) {
	    	// TODO: Return errors, etc from property files!
			result.append("NO_USERNAME");
			lookup = false;
		}
		result.append("</username><password>");
		if(password.length() == 0) {
	    	// TODO: Return errors, etc from property files!
			result.append("NO_PASSWORD");
			lookup = false;
		}
		result.append("</password></messages><summary>");
		if(lookup){
	    	// TODO: Return errors, etc from property files!
			result.append("UNKNOWN_USER");
		}
		result.append("</summary>");
	}
//	private void emptyXML(StringBuffer result) {
//		// TODO: Make initial XML for forms!
//		result.append("<loginresult>");
//		result.append("<empty/>");
//		result.append("</loginresult>");
//	}
//	private void result(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		StringBuffer result = (StringBuffer) request.getSession().getAttribute(this.resultSessionVarName);
//		if(result == null){
//			result = new StringBuffer();
//			emptyXML(result);
//		}
//		// TODO: Use buffer properties!
//		response.setContentType("text/xml");
//		response.getWriter().write(result.toString());
//	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setParameterNames(Map<String, String> parameterNames) {
		this.parameterNames = parameterNames;
	}
	public void setLoggedInSessionVarName(String loggedInSessionVarName) {
		this.loggedInSessionVarName = loggedInSessionVarName;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
}
