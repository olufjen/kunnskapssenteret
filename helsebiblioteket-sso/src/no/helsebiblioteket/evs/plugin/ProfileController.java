package no.helsebiblioteket.evs.plugin;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.LoggedInUserToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToLoggedInUserTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.admin.validator.PasswordValidator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

public class ProfileController extends HttpControllerPlugin {
	protected String resultSessionVarName;
	protected String editPasswordresultVarName;
	protected String editPasswordFromVarName;
	protected String editPasswordUserVarName;
	protected UserService userService;
	protected OrganizationService organizationService;
	protected Map<String, String> parameterNames;
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String save = request.getParameter(this.parameterNames.get("saveName"));
		String delete = request.getParameter(this.parameterNames.get("deleteName"));
		String cancel = request.getParameter(this.parameterNames.get("cancelName"));
		String cancelPassword = request.getParameter(this.parameterNames.get("cancelPasswordName"));
		String savePassword = request.getParameter(this.parameterNames.get("savePasswordName"));
		String editPassword = request.getParameter(this.parameterNames.get("editPasswordName"));
		String editProfile = request.getParameter(this.parameterNames.get("editProfileName"));
		
		if(save != null && save.equals(this.parameterNames.get("saveValue"))){
    		this.saveProfile(request, response);
		} else if(delete != null && delete.equals(this.parameterNames.get("deleteValue"))){
			this.delete(request, response);
		} else if(editPassword != null && editPassword.equals(this.parameterNames.get("editPasswordValue"))){
			this.editPassword(request, response);
		} else if(savePassword != null && savePassword.equals(this.parameterNames.get("savePasswordValue"))){
			this.savePassword(request, response);
		} else if(cancel != null && cancel.equals(this.parameterNames.get("cancelValue"))){
			this.cancel(request, response);
		} else if(cancelPassword != null && cancelPassword.equals(this.parameterNames.get("cancelPasswordValue"))){
			this.cancelPassword(request, response);
		} else if(editProfile != null && editProfile.equals(this.parameterNames.get("editProfileValue"))){
    		this.editProfile(request, response);
		} else {
    		this.init(request, response);
    	}
	}
	private void savePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = request.getParameter(this.parameterNames.get("password"));
		if(password == null) { password = "";}
		String passwordRepeat = request.getParameter(this.parameterNames.get("passwordrepeat"));
		if(passwordRepeat == null) { passwordRepeat = "";}
	
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.editPasswordresultVarName);
		Element messages = document.createElement("messages");

		if(password.length() == 0 || ! PasswordValidator.getInstance().isValidPassword(password)) {
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NOT_VALID"));
		}
		if( ! password.equals(passwordRepeat)){
			messages.appendChild(UserToXMLTranslator.element(document, "passwordrepeat", "NOT_EQUAL"));
		}

		boolean success;
		if( ! messages.hasChildNodes()){
			LoggedInUser loggedin = this.loggedInUser();
			SingleResultUser res = this.userService.findUserByUsername(loggedin.getUsername());
			User user;
			if(res instanceof ValueResultUser){
				user = ((ValueResultUser)res).getValue();
			} else {
				user = ((ValueResultOrganizationUser)res).getValue().getUser();
			}
			user.setPassword(password);
			this.userService.updateUser(user);
			element.appendChild(document.createElement("success"));
			success = true;
		} else {
			success = false;
		}

		element.appendChild(messages);
		document.appendChild(element);
		ResultHandler.setResult(this.editPasswordresultVarName, document);
		if(success){
			this.goBack(request, response);
		} else {
			String passwordPage = request.getParameter(this.parameterNames.get("passwordPage"));
			response.sendRedirect(passwordPage);
		}
	}
	private void editPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		
		String from = request.getParameter(this.parameterNames.get("fromform"));
		session.setAttribute(this.editPasswordFromVarName, from);
		
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element messages = document.createElement("messages");

		User user = new User();
		user.setRoleList(new Role[0]);
		user.setPerson(new Person());
		user.getPerson().setContactInformation(new ContactInformation());
		user.getPerson().setProfile(new Profile());
		validateUser(user, request, document, messages);
		session.setAttribute(this.editPasswordUserVarName, user);
		
		Element element = document.createElement(this.resultSessionVarName);
		element.appendChild(messages);
		document.appendChild(element);
		messages = document.createElement("messages");
		Element values = document.createElement("values");
		UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
		userXML(userTranslator.translate(user), document, values);
		element.appendChild(values);
		ResultHandler.setResult(this.resultSessionVarName, document);
		
		document = translator.newDocument();
		messages = document.createElement("messages");
		element = document.createElement(this.editPasswordresultVarName);
		element.appendChild(messages);
		document.appendChild(element);
		ResultHandler.setResult(this.editPasswordresultVarName, document);
		
		String passwordPage = request.getParameter(this.parameterNames.get("passwordPage"));
		response.sendRedirect(passwordPage);
	}
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewPage = request.getParameter(this.parameterNames.get("goto"));
		response.sendRedirect(viewPage);
	}
	private void cancelPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.goBack(request, response);
	}
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		session.setAttribute(this.editPasswordUserVarName, new Object());
		String from = (String)session.getAttribute(this.editPasswordFromVarName);
		if(from != null && from.equals("edit")){
			String editPage = request.getParameter(this.parameterNames.get("editPage"));
			response.sendRedirect(editPage);
		} else if(from != null && from.equals("view")){
			String viewPage = request.getParameter(this.parameterNames.get("viewPage"));
			response.sendRedirect(viewPage);
		} else {
			// Ok?
			String viewPage = request.getParameter(this.parameterNames.get("viewPage"));
			response.sendRedirect(viewPage);
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		// TODO Fase2: Implement delete
		response.sendRedirect(gotoUrl);
	}
	private User reloadUserByUsername(String username) {
		SingleResultUser res = this.userService.findUserByUsername(username);
		User user;
		if(res instanceof ValueResultUser){
			user = ((ValueResultUser)res).getValue();
		} else if(res instanceof ValueResultOrganizationUser){
			user = ((ValueResultOrganizationUser)res).getValue().getUser();
		} else {
			user = null;
		}
		return user;
	}
	private void saveProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		LoggedInUser loggedinuser = this.loggedInUser();
		User user = reloadUserByUsername(loggedinuser.getUsername());
		if(user == null){
			element.appendChild(document.createElement("notloggedin"));
    		String referer = request.getParameter(this.parameterNames.get("from"));
    		response.sendRedirect(referer);
		} else {
    		Element messages = document.createElement("messages");
			this.validateUser(user, request, document, messages);
			// TODO Fase2: Bad test!
			if( ! messages.hasChildNodes()){
				// TODO Fase2: Saving may fail though!
		    	boolean saved = true;
		    	user.getRoleList()[0] = this.getRole(user.getRoleList()[0].getKey());
		    	this.userService.updateUser(user);
		    	user = this.reloadUserByUsername(user.getUsername());
		    	if( ! saved){
		    		Element values = document.createElement("values");
		    		UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
		    		userXML(userTranslator.translate(user), document, values);
		    		element.appendChild(values);
		    		element.appendChild(messages);
		    		element.appendChild(UserToXMLTranslator.element(document, "summary", "NOT_SAVED"));
		    	} else {
	       			HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
	       			UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
	       			session.setAttribute(this.sessionLoggedInUserVarName, userTranslator.translate(user));
					element.appendChild(document.createElement("success"));
		    	}
	    		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
	    		response.sendRedirect(gotoUrl);
	    	} else {
//	    		result.append("<success>false</success>");
	    		Element values = document.createElement("values");
	    		UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
	    		userXML(userTranslator.translate(user), document, values);
	    		element.appendChild(values);
	    		element.appendChild(messages);
	    		String referer = request.getParameter(this.parameterNames.get("from"));
	    		response.sendRedirect(referer);
	    	}
		}
		document.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, document);
	}
	private Role getRole(UserRoleKey key) {
		System system = ((ValueResultSystem)this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue();
		SingleResultRole res = this.userService.getRoleByKeySystem(key, system);
		if (res instanceof ValueResultRole){
			return ((ValueResultRole)res).getValue();
		}
		return null;
	}
	protected void validateUser(User user, HttpServletRequest request, Document document, Element messages){
//		String username = request.getParameter(this.parameterNames.get("username"));
//		if (username==null) {username = "";}
		String firstName = request.getParameter(this.parameterNames.get("firstname"));
		if(firstName == null) { firstName = "";}
		String lastName = request.getParameter(this.parameterNames.get("lastname"));
		if(lastName == null) { lastName = "";}
		String email = request.getParameter(this.parameterNames.get("emailaddress"));
		if(email == null) { email = "";}
		String role = request.getParameter(this.parameterNames.get("role"));
		if (role==null) {role = "";}
		String hprno = request.getParameter(this.parameterNames.get("hprno"));
		if(hprno == null) { hprno = ""; }
		String studentnumber = request.getParameter(this.parameterNames.get("studentnumber")); 
		if(studentnumber == null) { studentnumber = ""; }
		String employer = request.getParameter(this.parameterNames.get("employer"));
		if(employer == null) { employer = "";}
		String receiveNewsletter = request.getParameter(this.parameterNames.get("newsletter"));
		if(receiveNewsletter == null) { receiveNewsletter = "";}
		String participateSurvey = request.getParameter(this.parameterNames.get("questionaire"));
		if(participateSurvey == null) { participateSurvey = "";}

		if(firstName.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "firstname", "NO_VALUE"));
		}
		if(lastName.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "lastname", "NO_VALUE"));
		}
		if(email.length() == 0 || ! EmailValidator.getInstance().isValidEmailAdress(email)){
			messages.appendChild(UserToXMLTranslator.element(document, "emailaddress", "NOT_VALID"));
		}
		if (role.equals("choose")) {
			messages.appendChild(UserToXMLTranslator.element(document, "role", "NOT_VALID"));
		} else {
			if(getRole(new UserRoleKey(role)) == null){
				messages.appendChild(UserToXMLTranslator.element(document, "role", "NOT_VALID"));
			}
		}
		if(hprno.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "hprnumber", "NO_VALUE"));
		} else if( ! isInteger(hprno)){
			messages.appendChild(UserToXMLTranslator.element(document, "hprnumber", "NOT_NUMBER"));
		}

		if(studentnumber.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "studentnumber", "NO_VALUE"));
		}
		if(employer.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "employer", "NO_VALUE"));
		}
		
		user.getPerson().setFirstName(firstName);
		user.getPerson().setLastName(lastName);
		user.getPerson().getContactInformation().setEmail(email);
		user.setRoleList(new Role[1]);
		user.getRoleList()[0] = new Role();
		user.getRoleList()[0].setKey(new UserRoleKey(role));
		user.getPerson().setHprNumber(hprno);
		user.getPerson().setStudentNumber(studentnumber);
		user.getPerson().setEmployer(employer);
		user.getPerson().getProfile().setReceiveNewsletter(new Boolean(receiveNewsletter));
		user.getPerson().getProfile().setParticipateSurvey(new Boolean(participateSurvey));
	}
	
	protected boolean isInteger(String integer) {
		try{Integer.parseInt(integer);} catch (NumberFormatException e) {return false;}
		return true;
	}

	protected void userXML(LoggedInUser user, Document document, Element element) throws ParserConfigurationException, TransformerException {
		LoggedInUserToXMLTranslator loggedInUserToXMLTranslator = new LoggedInUserToXMLTranslator();
		loggedInUserToXMLTranslator.translate(user, document, element);
	}
	private void editProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.init(request, response);
	}

	private void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoggedInUser user = this.loggedInUser();
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		if(user == null){
			Element notloggedin = document.createElement("notloggedin");
			element.appendChild(notloggedin);
		} else {
			Element values = document.createElement("values");
			userXML(user, document, values);
			element.appendChild(values);
		}
		document.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, document);
		String editPage = request.getParameter(this.parameterNames.get("editPage"));
		response.sendRedirect(editPage);
	}
	public LoggedInUser loggedInUser() {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		return (LoggedInUser) session.getAttribute(sessionLoggedInUserVarName);
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
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	public void setEditPasswordresultVarName(String editPasswordresultVarName) {
		this.editPasswordresultVarName = editPasswordresultVarName;
	}
	public void setEditPasswordFromVarName(String editPasswordFromVarName) {
		this.editPasswordFromVarName = editPasswordFromVarName;
	}
	public void setEditPasswordUserVarName(String editPasswordUserVarName) {
		this.editPasswordUserVarName = editPasswordUserVarName;
	}
}
