package no.helsebiblioteket.evs.plugin;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.admin.validator.PasswordValidator;
import no.helsebiblioteket.admin.validator.UsernameValidator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public class ProfileController extends HttpControllerPlugin {
	protected String resultSessionVarName;
	protected UserService userService;
	protected Map<String, String> parameterNames;
	private LoggedInFunction loggedInFunction;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String save = request.getParameter(this.parameterNames.get("saveName"));
		String delete = request.getParameter(this.parameterNames.get("deleteName"));
		if(save != null && save.equals(this.parameterNames.get("saveValue"))){
    		this.saveProfile(request, response);
		} else if(delete != null && delete.equals(this.parameterNames.get("deleteValue"))){
			this.delete(request, response);
    	} else {
    		this.init(request, response);
    	}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		response.sendRedirect(gotoUrl);
	}
	private void saveProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		User user = loggedInFunction.loggedInUser();
		if(user == null){
			element.appendChild(document.createElement("notloggedin"));
    		String referer = request.getParameter(this.parameterNames.get("from"));
    		response.sendRedirect(referer);
		} else {
			String hprNumber = request.getParameter(this.parameterNames.get("hprno"));
			if(hprNumber == null) { hprNumber = "";}
			// TODO: Check for errors.
			// TODO: Load errror messages from props!
//			StringBuffer errorBuffer = new StringBuffer();
    		Element messages = document.createElement("messages");
			if(hprNumber.length() == 0 || ! isInteger(hprNumber)){
				messages.appendChild(UserToXMLTranslator.element(document, "hprnumber", "NOT_NUMBER"));
				user.getPerson().setHprNumber(null);
			}
			this.validateUser(user, request, document, messages);
			// TODO: Bad test!
			if( ! messages.hasChildNodes()){
				user.getPerson().setHprNumber(hprNumber);
				// TODO: Saving may fail though!
		    	boolean saved = true;
		    	this.userService.updateUser(user);
		    	if( ! saved){
		    		Element values = document.createElement("values");
		    		userXML(user, hprNumber, document, values);
		    		element.appendChild(values);
		    		element.appendChild(messages);
		    		element.appendChild(UserToXMLTranslator.element(document, "summary", "NOT_SAVED"));
		    	} else {
					element.appendChild(document.createElement("success"));
		    	}
	    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
	    		response.sendRedirect(gotoUrl);
	    	} else {
//	    		result.append("<success>false</success>");
	    		Element values = document.createElement("values");
	    		userXML(user, hprNumber, document, values);
	    		element.appendChild(values);
	    		element.appendChild(messages);
	    		String referer = request.getParameter(this.parameterNames.get("from"));
	    		response.sendRedirect(referer);
	    	}
		}
		document.appendChild(element);
		loggedInFunction.setResult(this.resultSessionVarName, document);
	}
	protected void validateUser(User user, HttpServletRequest request, Document document, Element messages){
		String position = request.getParameter(this.parameterNames.get("position"));
		if (position==null) {position = "";}
		String usertype = request.getParameter(this.parameterNames.get("username"));
		if (usertype==null) {usertype = "";}
		String username = request.getParameter(this.parameterNames.get("username"));
		if (username==null) {username = "";}
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
			messages.appendChild(UserToXMLTranslator.element(document, "firstname", "NO_VALUE"));
		}
		if(lastName.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "lastname", "NO_VALUE"));
		}
		if(employer.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "employer", "NO_VALUE"));
		}
		if(email.length() == 0 || ! EmailValidator.getInstance().isValidEmailAdress(email)){
			messages.appendChild(UserToXMLTranslator.element(document, "emailaddress", "NOT_VALID"));
		}
		if (usertype.length() == 0 || !validPosition(position)) {
			messages.appendChild(UserToXMLTranslator.element(document, "position", "NOT_VALID"));
		}
		if(username.length() == 0 || ! UsernameValidator.getInstance().isValidUsername(username)) {
			messages.appendChild(UserToXMLTranslator.element(document, "username", "NOT_VALID"));
		}
		if(password.length() == 0 || ! PasswordValidator.getInstance().isValidPassword(password)) {
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NOT_VALID"));
		}
		if (usertype.equals("choose")) {
			messages.appendChild(UserToXMLTranslator.element(document, "usertype", "NOT_VALID"));
		}
//		if(passwordRepeat.length() == 0){ }
		if(! password.equals(passwordRepeat)){
			messages.appendChild(UserToXMLTranslator.element(document, "passwordrepeat", "NOT_EQUAL"));
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
	
	private boolean validPosition(String position) {
		return (!"choose".equals(position));
	}
	
	protected boolean isInteger(String integer) {
		try{Integer.parseInt(integer);} catch (NumberFormatException e) {return false;}
		return true;
	}
//	protected void result(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		StringBuffer result = (StringBuffer) request.getSession().getAttribute(this.resultSessionVarName);
//		if(result == null){
//			result = new StringBuffer();
//			result.append("<profileresult><empty/></profileresult>");
//		}
//		// TODO: Use buffer properties!
//		response.setContentType("text/xml");
//		response.getWriter().write(result.toString());
//	}
	protected void userXML(User user, String hprNumber, Document document, Element element) throws ParserConfigurationException, TransformerException {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		OrganizationUser organizationUser = new OrganizationUser();
		organizationUser.setUser(user);
		translator.translate(organizationUser, document, element);
		if(hprNumber == null){
			String hpr = null;
			if (user.getPerson() != null) {
				hpr = user.getPerson().getHprNumber();
			}
			if(hpr == null) {
				hprNumber = "";
			} else {
				hprNumber = hpr.toString();
			}
		}
		element.appendChild(UserToXMLTranslator.element(document, "hprnumber", hprNumber));

//		Source source = new DOMSource(document);
//        StringWriter stringWriter = new StringWriter();
//		Result streamResult = new StreamResult(stringWriter);
//		TransformerFactory factory = TransformerFactory.newInstance();
//		Transformer transformer = factory.newTransformer();
//		transformer.transform(source, streamResult);
//		
//		// TODO: Use DOM all the way!
//		result.append(stringWriter.getBuffer().toString());

		
//		result.append("<hprnumber>");
//		result.append("</hprnumber>");
//		result.append("<firstname>");
//		result.append(user.getPerson().getFirstName());
//		result.append("</firstname>");
//		result.append("<lastname>");
//		result.append(user.getPerson().getLastName());
//		result.append("</lastname>");
//		result.append("<employer>");
//		result.append(user.getPerson().getEmployer());
//		result.append("</employer>");
//		result.append("<newsletter>");
//		result.append(user.getPerson().getProfile().getReceiveNewsletter());
//		result.append("</newsletter>");
//		result.append("<questionaire>");
//		result.append(user.getPerson().getProfile().getParticipateSurvey());
//		result.append("</questionaire>");
//		result.append("<emailaddress>");
//		result.append(user.getPerson().getContactInformation().getEmail());
//		result.append("</emailaddress>");
//		result.append("<password></password>");
//		result.append("<passwordrepeat></passwordrepeat>");
	}
	private void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = loggedInFunction.loggedInUser();
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		if(user == null){
			Element notloggedin = document.createElement("notloggedin");
			element.appendChild(notloggedin);
		} else {
			Element values = document.createElement("values");
			userXML(user, null, document, values);
			element.appendChild(values);
		}
		document.appendChild(element);
		loggedInFunction.setResult(this.resultSessionVarName, document);
		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		response.sendRedirect(gotoUrl);
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
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}
