package no.helsebiblioteket.evs.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class SendPasswordController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private String resultSessionVarName;
	private LoginService loginService;
	private Map<String, String> parameterNames;
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
		StringBuffer result = new StringBuffer();
		result.append("<" + this.resultSessionVarName + ">");
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
   		} else if(! this.loginService.sendPasswordEmail(user)) {
   			success = false;
   			sent = false;
   		}
    	if(success){
    		result.append("<success/>");
    		failXML("", result, emailError, sent);
    		result.append("</" + this.resultSessionVarName + ">");
        	LoggedInFunction.setResult(this.resultSessionVarName, result);

    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
    		response.sendRedirect(gotoUrl);
    	} else {
    		failXML(email, result, emailError, sent);
    		result.append("</" + this.resultSessionVarName + ">");
        	LoggedInFunction.setResult(this.resultSessionVarName, result);

        	String referer = request.getParameter(this.parameterNames.get("from"));
    		response.sendRedirect(referer);
    	}
	}
	private void failXML(String email, StringBuffer result, String emailError, boolean sent) {
//		result.append("<success>false</success>");
		result.append("<values><email>");
		result.append(email);
		result.append("</email></values>");
		result.append("<messages><email>");
		result.append(emailError);
		result.append("</email></messages>");
		result.append("<summary>");
		if(!sent){ result.append("NOT_SENT");}
		result.append("</summary>");
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
