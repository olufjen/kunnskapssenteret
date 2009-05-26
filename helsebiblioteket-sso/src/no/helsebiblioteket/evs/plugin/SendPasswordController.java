package no.helsebiblioteket.evs.plugin;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class SendPasswordController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String resultSessionVarName;
	private LoginService loginService;
	private Email emailForgottenPassword;
	
	private String emailFromAddressText;
	private String emailFromNameText;
	private String emailMessageText;
	private String emailSubjectText;
	
	private Map<String, String> parameterNames;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	this.logger.debug("SendPasswordController RUNNING");
    	this.emailForgottenPassword = new Email();
    	
    	this.emailFromAddressText = request.getParameter(this.parameterNames.get("emailFromAddressText")) != null ? request.getParameter(this.parameterNames.get("emailFromAddressText")) : "";
    	this.emailFromNameText = request.getParameter(this.parameterNames.get("emailFromNameText")) != null ? request.getParameter(this.parameterNames.get("emailFromNameText")) : "";
    	this.emailMessageText = request.getParameter(this.parameterNames.get("emailMessageText")) != null ? request.getParameter(this.parameterNames.get("emailMessageText")) : "";
    	this.emailSubjectText = request.getParameter(this.parameterNames.get("emailSubjectText")) != null ? request.getParameter(this.parameterNames.get("emailSubjectText")) : "";
    	
    	emailForgottenPassword.setFromEmail(URLDecoder.decode(this.emailFromAddressText, "UTF-8"));
    	emailForgottenPassword.setFromName(URLDecoder.decode(this.emailFromNameText, "UTF-8"));
    	emailForgottenPassword.setMessage(URLDecoder.decode(this.emailMessageText, "UTF-8"));
    	emailForgottenPassword.setSubject(URLDecoder.decode(this.emailSubjectText, "UTF-8"));
   		
    	this.sendEmail(request, response);
    	this.logger.debug("SendPasswordController DONE");
	}
	private void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);

		String email = request.getParameter(this.parameterNames.get("emailaddress"));
    	if(email == null) { email = ""; }
    	boolean success = true;
    	boolean sent = true;
    	String emailError = "";
   		if( ! EmailValidator.getInstance().isValidEmailAdress(email)) {
   			success = false;
   	    	// TODO Fase2: Return errors, etc from property files!
   			emailError = "NOT_VALID";
   		} else if(this.loginService.sendPasswordEmail(email, this.emailForgottenPassword).getFailed()) {
   			success = false;
   			sent = false;
   		}
    	if(success){
    		element.appendChild(document.createElement("success"));
    		failXML(email, document, element, emailError, sent);
    		document.appendChild(element);
        	ResultHandler.setResult(this.resultSessionVarName, document);

    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
    		response.sendRedirect(gotoUrl);
    	} else {
    		failXML(email, document, element, emailError, sent);
    		
    		document.appendChild(element);
    		ResultHandler.setResult(this.resultSessionVarName, document);

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
}
