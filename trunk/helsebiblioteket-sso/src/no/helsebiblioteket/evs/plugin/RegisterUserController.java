package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;

public final class RegisterUserController extends ProfileController {
	private final Log logger = LogFactory.getLog(getClass());
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.logger.info("RegisterUserController RUNNING");
    	// TODO: Real parsing of query string
    	String resultPrefix = "&" + this.parameterNames.get("resultName") + "=" +
    		this.parameterNames.get("resultValue");
    	if(request.getQueryString().endsWith(resultPrefix)){
    		this.result(request, response);
    	} else {
    		this.registerUser(request, response);
    	}
    	this.logger.info("RegisterUserController DONE");
	}
	private boolean userExists(String username) {
		// FIXME: Write this!
		User test = new User();
		test.setUsername(username);
		return this.userService.findUserByUsername(test) != null;
	}
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = new StringBuffer();
		// TODO: How to initialize person?
		User user = new User();
		user.setPerson(new Person());
		String hprNumber = request.getParameter(this.parameterNames.get("hprno"));
		if(hprNumber == null) { hprNumber = "";}
		// TODO: Check for errors.
		// TODO: Load errror messages from props!
		StringBuffer errorBuffer = new StringBuffer();
		if(hprNumber.length() == 0 || ! isInteger(hprNumber)){
			errorBuffer.append("<hprnumber>Not a number.</hprnumber>");
		}
		this.validateUser(user, request, errorBuffer);
		
		// TODO: Deal with different user types!
		
		boolean success = false;
		String summary = "";
		// TODO: Bad test!
		if(errorBuffer.toString().length() == 0){
			user.getPerson().setHprNumber(new Integer(hprNumber));
			// TODO: Saving may fail though!
	    	boolean saved = true;
	    	this.userService.createUser(user);
	    	if( ! saved){
	    		summary = "<summary>User was not registered. Try again later.</summary>";
	    	} else {
	    		success = true;
	    	}
    	}
		String gotoUrl = "";
		result.append("<profileresult>");
		if(success){
			result.append("<success>true</success>");
			gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		} else {
			result.append("<success>false</success>");
			result.append("<values>");
			userXML(user, hprNumber, result);
			result.append("</values>");
			result.append("<messages>");
			result.append(errorBuffer);
			result.append("</messages>");
			if(summary.length() != 0){
				result.append("<summary>");
				result.append(summary);
				result.append("</summary>");
			}
			gotoUrl = request.getParameter(this.parameterNames.get("from"));
		}
    	result.append("</profileresult>");
    	request.getSession().setAttribute(this.resultSessionVarName, result);
    	response.sendRedirect(gotoUrl);
	}
	protected void validateUser(User user, HttpServletRequest request, StringBuffer errorBuffer){
		super.validateUser(user, request, errorBuffer);
		String username = request.getParameter(this.parameterNames.get("username"));
		if(username == null) { username = "";}
		user.setUsername(username);
		if(username.length() == 0){
			errorBuffer.append("<username>Must have value.</username>");
		} else if(userExists(username)){
			errorBuffer.append("<username>Username in use.</username>");
		}
	}
	protected void result(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.result(request, response);
	}
	protected void userXML(User user, String hprNumber, StringBuffer result) {
		super.userXML(user, hprNumber, result);
		result.append("<username>");
		result.append(user.getUsername());
		result.append("</username>");
	}
}
