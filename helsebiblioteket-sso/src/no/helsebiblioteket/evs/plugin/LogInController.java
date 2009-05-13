package no.helsebiblioteket.evs.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

public final class LogInController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String resultSessionVarName;
	private LoginService loginService;
	private Map<String, String> parameterNames;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
   		this.login(request, response);
    	this.logger.info("LogInController DONE");
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
       		LoggedInUserResult resultUser = this.loginService.loginUserByUsernamePassword(username, password);
       		if( ! resultUser.isSuccess()){
        		makeXML(username, password, result, element);
        		String referer = request.getParameter(this.parameterNames.get("from"));
        		response.sendRedirect(referer);
       		} else {
	       		// Found user!
       			HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
       			session.setAttribute(this.sessionLoggedInUserVarName, resultUser.getUser());
	       		element.appendChild(result.createElement("success"));
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
		ResultHandler.setResult(this.resultSessionVarName, result);
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
}
