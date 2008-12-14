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
		// FIXME: Remove test!
//		request.getSession().setAttribute(this.resultSessionVarName, null);
		
		
    	this.logger.info("SendPasswordController RUNNING");
    	// TODO: Real parsing of query string
    	String prefix = "&" + this.parameterNames.get("resultName") + "=" +
    		this.parameterNames.get("resultValue");
    	if(request.getQueryString().endsWith(prefix)){
    		this.result(request, response);
    	} else {
    		this.sendEmail(request, response);
    	}
    	this.logger.info("SendPasswordController DONE");
	}
	private void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = new StringBuffer();
		result.append("<passwordresult>");
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
   			emailError = "Not a valid address.";
   		} else if(! this.loginService.sendPasswordEmail(user)) {
   			success = false;
   			sent = false;
   		}
    	if(success){
    		result.append("<success>true</success>");
    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
        	this.logger.info("Goto: " + gotoUrl);
        	result.append("</passwordresult>");
        	request.getSession().setAttribute(this.resultSessionVarName, result);
    		response.sendRedirect(gotoUrl);
    	} else {
    		failXML(email, result, emailError, sent);
    		String referer = request.getParameter(this.parameterNames.get("from"));
        	result.append("</passwordresult>");
    		request.getSession().setAttribute(this.resultSessionVarName, result);
    		response.sendRedirect(referer);
    	}
	}
	private void failXML(String email, StringBuffer result, String emailError, boolean sent) {
		result.append("<success>false</success>");
		result.append("<values><email>");
		result.append(email);
		result.append("</email></values>");
		result.append("<messages><email>");
		result.append(emailError);
		result.append("</email></messages>");
		result.append("<summary>");
		if(!sent){ result.append("Could not send email!");}
		result.append("</summary>");
	}
	private void emptyXML(StringBuffer result) {
		// TODO: Make initial XML for forms!
		result.append("<passwordresult>");
		result.append("<empty/>");
		result.append("</passwordresult>");
	}
	private void result(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = (StringBuffer) request.getSession().getAttribute(this.resultSessionVarName);
		if(result == null){
			result = new StringBuffer();
			emptyXML(result);
		}
		// TODO: Use buffer properties!
		response.setContentType("text/xml");
		response.getWriter().write(result.toString());
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
