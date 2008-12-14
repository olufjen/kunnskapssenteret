package no.helsebiblioteket.evs.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.UserService;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public class ProfileController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	protected String resultSessionVarName;
	private String loggedInSessionVarName;
	protected UserService userService;
	protected Map<String, String> parameterNames;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// FIXME: Remove testing:
		User user = new User(); user.setUsername("test@example.org");
		request.getSession().setAttribute(this.loggedInSessionVarName, this.userService.findUserByUsername(user));
//		request.getSession().setAttribute(this.loggedInSessionVarName, null);
		
		this.logger.info("ProfileController RUNNING");
    	// TODO: Real parsing of query string
    	String resultPrefix = "&" + this.parameterNames.get("resultName") + "=" +
    		this.parameterNames.get("resultValue");
    	String initPrefix = "&" + this.parameterNames.get("initName") + "=" +
			this.parameterNames.get("initValue");
    	if(request.getQueryString().endsWith(resultPrefix)){
    		this.result(request, response);
    	} else if(request.getQueryString().endsWith(initPrefix)){
    		this.init(request, response);
    	} else {
    		this.saveProfile(request, response);
    	}
    	this.logger.info("ProfileController DONE");
	}
	private void saveProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = new StringBuffer();
		result.append("<profileresult>");
		Object loggedIn = request.getSession().getAttribute(this.loggedInSessionVarName);
		if((loggedIn == null) || ( ! (loggedIn instanceof User))){
			result.append("<success>false</success><notloggedin/>");
    		String referer = request.getParameter(this.parameterNames.get("from"));
    		response.sendRedirect(referer);
		} else {
			User user = (User) loggedIn;
			String hprNumber = request.getParameter(this.parameterNames.get("hprno"));
			if(hprNumber == null) { hprNumber = "";}
			// TODO: Check for errors.
			// TODO: Load errror messages from props!
			StringBuffer errorBuffer = new StringBuffer();
			if(hprNumber.length() == 0 || ! isInteger(hprNumber)){
				errorBuffer.append("<hprnumber>Not a number.</hprnumber>");
			}
			this.validateUser(user, request, errorBuffer);
			// TODO: Bad test!
			if(errorBuffer.toString().length() == 0){
				user.getPerson().setHprNumber(new Integer(hprNumber));
				// TODO: Saving may fail though!
		    	boolean saved = true;
		    	this.userService.saveUser(user);
		    	if( ! saved){
		    		result.append("<success>false</success>");
		    		result.append("<values>");
		    		userXML(user, hprNumber, result);
		    		result.append("</values>");
		    		result.append("<summary>Profile was not saved. Try again later.</summary>");
		    	} else {
		    		result.append("<success>true</success>");
		    	}
	    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
	    		response.sendRedirect(gotoUrl);
	    	} else {
	    		result.append("<success>false</success>");
	    		result.append("<values>");
	    		userXML(user, hprNumber, result);
	    		result.append("</values>");
	    		result.append("<messages>");
	    		result.append(errorBuffer);
	    		result.append("</messages>");
	    		String referer = request.getParameter(this.parameterNames.get("from"));
	    		response.sendRedirect(referer);
	    	}
		}
    	result.append("</profileresult>");
    	request.getSession().setAttribute(this.resultSessionVarName, result);
	}
	protected void validateUser(User user, HttpServletRequest request, StringBuffer errorBuffer){
		String firstName = request.getParameter(this.parameterNames.get("firstname"));
		if(firstName == null) { firstName = "";}
		String lastName = request.getParameter(this.parameterNames.get("lastname"));
		if(lastName == null) { lastName = "";}
		String employer = request.getParameter(this.parameterNames.get("employer"));
		if(employer == null) { employer = "";}
		String receiveNewsletter = request.getParameter(this.parameterNames.get("newsletter"));
		if(receiveNewsletter == null) { receiveNewsletter = "";}
		String participateSurvey = request.getParameter(this.parameterNames.get("questionaire"));
		if(participateSurvey == null) { participateSurvey = "";}
		String email = request.getParameter(this.parameterNames.get("emailaddress"));
		if(email == null) { email = "";}
//		String username = request.getParameter(this.parameterNames.get("username"));
		String password = request.getParameter(this.parameterNames.get("password"));
		if(password == null) { password = "";}
		String passwordRepeat = request.getParameter(this.parameterNames.get("passwordrepeat"));
		if(passwordRepeat == null) { passwordRepeat = "";}

		if(firstName.length() == 0){
			errorBuffer.append("<firstname>Must have value.</firstname>");
		}
		if(lastName.length() == 0){
			errorBuffer.append("<lastname>Must have value.</lastname>");
		}
		if(employer.length() == 0){
			errorBuffer.append("<employer>Must have value.</employer>");
		}
		if(email.length() == 0 || ! validEmail(email)){
			errorBuffer.append("<emailaddress>Not valid.</emailaddress>");
		}
		if(password.length() == 0 || ! validPassword(password)){
			errorBuffer.append("<password>Not long enough.</password>");
		}
//		if(passwordRepeat.length() == 0){ }
		if(! password.equals(passwordRepeat)){
			errorBuffer.append("<passwordrepeat>Not equal.</passwordrepeat>");
		}
//		if( ! isBoolean(receiveNewsletter)){
//			errorBuffer.append("<newsletter>Not valid.</newsletter>");
//		}
//		if( ! isBoolean(participateSurvey)){
//			errorBuffer.append("<questionaire>Not valid.</questionaire>");
//		}
		
		user.getPerson().setFirstName(firstName);
		user.getPerson().setLastName(lastName);
		user.getPerson().setEmployer(employer);
		user.getPerson().getProfile().setReceiveNewsletter(new Boolean(receiveNewsletter));
		user.getPerson().getProfile().setParticipateSurvey(new Boolean(participateSurvey));
		user.getPerson().getContactInformation().setEmail(email);
		user.setPassword(password);
	}
	private boolean isBoolean(String bool) {
		if(bool.equals(Boolean.FALSE.toString()) || bool.equals(Boolean.TRUE.toString())){
			return true;
		} else {
			return false;
		}
	}
	private boolean validPassword(String password) {
		// TODO Better!
		return password.length() >= 8;
	}
	private boolean validEmail(String email) {
		// TODO Better!
		return email.contains("@");
	}
	protected boolean isInteger(String integer) {
		try{Integer.parseInt(integer);} catch (NumberFormatException e) {return false;}
		return true;
	}
	protected void result(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = (StringBuffer) request.getSession().getAttribute(this.resultSessionVarName);
		if(result == null){
			result = new StringBuffer();
			result.append("<profileresult><empty/></profileresult>");
		}
		// TODO: Use buffer properties!
		response.setContentType("text/xml");
		response.getWriter().write(result.toString());
	}
	protected void userXML(User user, String hprNumber, StringBuffer result) {
		result.append("<hprnumber>");
		if(hprNumber != null){
			result.append(hprNumber);
		} else {
			Integer num =  user.getPerson().getHprNumber();
			if(num != null){ result.append(num); }
			else { result.append(""); }
		}
		result.append("</hprnumber>");
		result.append("<firstname>");
		result.append(user.getPerson().getFirstName());
		result.append("</firstname>");
		result.append("<lastname>");
		result.append(user.getPerson().getLastName());
		result.append("</lastname>");
		result.append("<employer>");
		result.append(user.getPerson().getEmployer());
		result.append("</employer>");
		result.append("<newsletter>");
		result.append(user.getPerson().getProfile().getReceiveNewsletter());
		result.append("</newsletter>");
		result.append("<questionaire>");
		result.append(user.getPerson().getProfile().getParticipateSurvey());
		result.append("</questionaire>");
		result.append("<emailaddress>");
		result.append(user.getPerson().getContactInformation().getEmail());
		result.append("</emailaddress>");
		result.append("<password></password>");
		result.append("<passwordrepeat></passwordrepeat>");
	}
	private void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer result = new StringBuffer();
		Object loggedIn = request.getSession().getAttribute(this.loggedInSessionVarName);
		if((loggedIn == null) || ( ! (loggedIn instanceof User))){
			result.append("<profileresult><notloggedin/></profileresult>");
		} else {
			User user = (User) loggedIn;
			result.append("<profileresult>");
			userXML(user, null, result);
			result.append("</profileresult>");
		}
		// TODO: Use buffer properties!
		response.setContentType("text/xml");
		response.getWriter().write(result.toString());
	}
	public void setParameterNames(Map<String, String> parameterNames) {
		this.parameterNames = parameterNames;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setLoggedInSessionVarName(String loggedInSessionVarName) {
		this.loggedInSessionVarName = loggedInSessionVarName;
	}
}
