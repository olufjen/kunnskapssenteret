package no.helsebiblioteket.evs.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LogInController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String resultSessionVarName;
	private LoginService loginService;
	private Map<String, String> parameterNames;
	private LoggedInFunction loggedInFunction;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document result = translator.newDocument();
		Element element = result.createElement(this.resultSessionVarName);
		
    	String username = request.getParameter(this.parameterNames.get("username"));
    	String password = request.getParameter(this.parameterNames.get("password"));
    	if(username == null) { username = ""; }
    	if(password == null) { password = ""; }
       	if(username.length() != 0 && password.length() != 0){
       		SingleResult<User> sResult = this.loginService.loginUserByUsernamePassword(username, password);
       		if(sResult instanceof EmptyResult){
        		makeXML(username, password, result, element);
        		String referer = request.getParameter(this.parameterNames.get("from"));
        		response.sendRedirect(referer);
       		} else {
	       		// Found user!
	           	User user = ((ValueResult<User>)sResult).getValue();
	       		element.appendChild(result.createElement("success"));
	       		loggedInFunction.logInUser(user);
	       		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
	       		response.sendRedirect(gotoUrl);
        	}
       	} else {
    		// Return!
    		makeXML(username, password, result, element);
    		String referer = request.getParameter(this.parameterNames.get("from"));
    		response.sendRedirect(referer);
       	}
		result.appendChild(element);
    	loggedInFunction.setResult(this.resultSessionVarName, result);
	}
	private void makeXML(String username, String password, Document document, Element element) {
		boolean lookup = true;
		
		Element values = document.createElement("values");
		values.appendChild(UserToXMLTranslator.element(document, "username", username));
		values.appendChild(UserToXMLTranslator.element(document, "password", password));
		element.appendChild(values);

		Element messages = document.createElement("messages");
		if(username.length() == 0) {
			messages.appendChild(UserToXMLTranslator.element(document, "username", "NO_USERNAME"));
			lookup = false;
		}
		if(password.length() == 0) {
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NO_PASSWORD"));
			lookup = false;
		}
		element.appendChild(messages);

		Element summary = document.createElement("summary");
		if(lookup){
			summary.appendChild(UserToXMLTranslator.element(document, "summary", "UNKNOWN_USER"));
		}
		element.appendChild(summary);
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setParameterNames(Map<String, String> parameterNames) {
		this.parameterNames = parameterNames;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}
