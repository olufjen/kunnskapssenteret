package no.helsebiblioteket.evs.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class SendPasswordController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String resultSessionVarName;
	private LoginService loginService;
	private Map<String, String> parameterNames;
	private LoggedInFunction loggedInFunction;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	this.logger.info("SendPasswordController RUNNING");
    	// TODO: Real parsing of query string
//    	String prefix = "&" + this.parameterNames.get("resultName") + "=" +
//    		this.parameterNames.get("resultValue");
//    	String initPrefix = "&" + this.parameterNames.get("initName") + "=" +
//			this.parameterNames.get("initValue");
//    	if(request.getQueryString().endsWith(prefix)){
//    		this.result(request, response);
//    	} else if(request.getQueryString().endsWith(initPrefix)){
//    		this.init(request, response);
//    	} else {
    		this.sendEmail(request, response);
//    	}
    	this.logger.info("SendPasswordController DONE");
	}
	private void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);

		String email = request.getParameter(this.parameterNames.get("emailaddress"));
    	if(email == null) { email = ""; }
    	User user = new User();
   		user.setUsername(email);
    	boolean success = true;
    	boolean sent = true;
    	String emailError = "";
    	// TODO: Validate emailaddress!
   		if( ! email.contains("@")) {
   			success = false;
   	    	// TODO: Return errors, etc from property files!
   			emailError = "NOT_VALID";
   		} else if(! this.loginService.sendPasswordEmail(user.getUsername())) {
   			success = false;
   			sent = false;
   		}
    	if(success){
    		element.appendChild(document.createElement("success"));
    		failXML(email, document, element, emailError, sent);
    		document.appendChild(element);
        	loggedInFunction.setResult(this.resultSessionVarName, document);

    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
    		response.sendRedirect(gotoUrl);
    	} else {
    		failXML(email, document, element, emailError, sent);
    		
    		document.appendChild(element);
        	loggedInFunction.setResult(this.resultSessionVarName, document);

        	String referer = request.getParameter(this.parameterNames.get("from"));
    		response.sendRedirect(referer);
    	}
	}
	private void failXML(String email, Document document, Element element, String emailError, boolean sent) {
//		result.append("<success>false</success>");
		Element values = document.createElement("values");
		values.appendChild(UserToXMLTranslator.element(document, "email", email));
		element.appendChild(values);

		Element messages = document.createElement("messages");
		messages.appendChild(UserToXMLTranslator.element(document, "email", emailError));
		element.appendChild(messages);
		
		if(!sent){
			element.appendChild(UserToXMLTranslator.element(document, "summary", "NOT_SENT"));
		} else {
			element.appendChild(document.createElement("summary"));
		}
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
