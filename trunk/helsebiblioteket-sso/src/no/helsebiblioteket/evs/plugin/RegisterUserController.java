package no.helsebiblioteket.evs.plugin;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultRole;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.EmailMessageTranslator;
import no.helsebiblioteket.admin.translator.LoggedInUserToXMLTranslator;
import no.helsebiblioteket.admin.translator.UserToLoggedInUserTranslator;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.admin.validator.PasswordValidator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

public final class RegisterUserController extends HttpControllerPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private EmailService emailService;
	private Email emailNewUser;
	private String emailFromAddressText;
	private String emailFromNameText;
	private String emailMessageText;
	private String emailSubjectText;
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String resultSessionVarName;
	private Map<String, String> parameterNames;
	private UserService userService;
	private OrganizationService organizationService;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String save = request.getParameter(this.parameterNames.get("saveName"));
		String cancel = request.getParameter(this.parameterNames.get("cancelName"));
		String confirm = request.getParameter(this.parameterNames.get("confirmName"));
		
		this.emailNewUser = new Email();
		
		this.emailFromAddressText = request.getParameter(this.parameterNames.get("emailFromAddressText")) != null ? request.getParameter(this.parameterNames.get("emailFromAddressText")) : "";
    	this.emailFromNameText = request.getParameter(this.parameterNames.get("emailFromNameText")) != null ? request.getParameter(this.parameterNames.get("emailFromNameText")) : "";
    	this.emailMessageText = request.getParameter(this.parameterNames.get("emailMessageText")) != null ? request.getParameter(this.parameterNames.get("emailMessageText")) : "";
    	this.emailSubjectText = request.getParameter(this.parameterNames.get("emailSubjectText")) != null ? request.getParameter(this.parameterNames.get("emailSubjectText")) : "";
    	
    	emailNewUser.setFromEmail(URLDecoder.decode(this.emailFromAddressText, "UTF-8"));
    	emailNewUser.setFromName(URLDecoder.decode(this.emailFromNameText, "UTF-8"));
    	emailNewUser.setMessage(URLDecoder.decode(this.emailMessageText, "UTF-8"));
    	emailNewUser.setSubject(URLDecoder.decode(this.emailSubjectText, "UTF-8"));
		
    	if(save != null && save.equals(this.parameterNames.get("saveValue"))){
    		if(cancel != null && cancel.equals(this.parameterNames.get("cancelValue"))){
    			this.cancel(request, response);
        	} else {
            	this.registerUser(request, response);
        	}
    	} else {
    		if(confirm != null && confirm.equals(this.parameterNames.get("confirmValue"))){
    			this.confirm(request, response);
    		} else {
        		this.init(request, response);
    		}
    	}
	}
	private void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String usertype = request.getParameter(this.parameterNames.get("usertype"));
		if (null == usertype || "".equals(usertype)) {
			usertype = request.getParameter("form_3");
		}
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		Element values = document.createElement("values");
		values.appendChild(UserToXMLTranslator.element(document, "usertype", usertype));
		element.appendChild(values);
		document.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, document);
		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		response.sendRedirect(gotoUrl);
	}
	private void confirm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String usertype = request.getParameter(this.parameterNames.get("usertype"));
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		Element values = document.createElement("values");
		values.appendChild(UserToXMLTranslator.element(document, "usertype", usertype));
		element.appendChild(values);
		document.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, document);
		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		response.sendRedirect(gotoUrl);
	}
	
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		Element cancel = document.createElement("cancel");
		element.appendChild(cancel);
		document.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, document);
		String gotoUrl = request.getParameter(this.parameterNames.get("from"));
		response.sendRedirect(gotoUrl);
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		Element messages = document.createElement("messages");

		Element values = document.createElement("values");
		User user = createValidateUser(request, document, messages, values);
		
		boolean success = false;
		String summary = "";
		// TODO Fase2: Bad test!
		if( ! messages.hasChildNodes()){
			// TODO Fase2: Saving may fail though!
	    	boolean saved = true;
	    	this.userService.insertUser(user);
	    	if( ! saved){
	    		summary = "USER_NOT_REGISTERED";
	    	} else {
	    		success = true;
	    	}
    	}
		String gotoUrl = "";
		if(success){
	    	this.sendNewUserEmail(user);
			loginNewUser(user);
			element.appendChild(document.createElement("success"));
			gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		} else {
			UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
			userXML(userTranslator.translate(user), document, values);
			element.appendChild(values);
			element.appendChild(messages);
			if(summary.length() != 0){
				element.appendChild(UserToXMLTranslator.element(document, "summary", summary));
			}
			gotoUrl = request.getParameter(this.parameterNames.get("from"));
		}
		document.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, document);
    	response.sendRedirect(gotoUrl);
	}
	
	private void loginNewUser(User user) {
		UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInUserVarName, userTranslator.translate(user));
	}
	
	private void sendNewUserEmail(User user) {
		this.emailNewUser.setToName(user.getPerson().getName());
		this.emailNewUser.setToEmail(user.getPerson().getContactInformation().getEmail());
		EmailMessageTranslator emailMessageTranslator = new EmailMessageTranslator();
		this.emailNewUser.setMessage(emailMessageTranslator.translate(this.emailNewUser.getMessage(), user));
		this.emailService.sendEmail(this.emailNewUser);
	}
	
	private User createValidateUser(HttpServletRequest request, Document document, Element messages, Element values) throws Exception {
		User user = new User();
		user.setPerson(new Person());
		user.getPerson().setContactInformation(new ContactInformation());
		user.getPerson().setProfile(new Profile());
		
        String usertype = request.getParameter(this.parameterNames.get("usertype"));
        if(usertype == null){ usertype = ""; }
        String hprnumber = request.getParameter(this.parameterNames.get("hprnumber"));
        if(hprnumber == null){ hprnumber = ""; }
        String studentnumber = request.getParameter(this.parameterNames.get("studentnumber"));
        if(studentnumber == null){ studentnumber = ""; }
        String dateOfBirth = request.getParameter(this.parameterNames.get("dateofbirth"));
        if(dateOfBirth == null){ dateOfBirth = ""; }
        String username = request.getParameter(this.parameterNames.get("username"));
		if(username == null) { username = "";}
		String password = request.getParameter(this.parameterNames.get("password"));
		if(password == null) { password = ""; }
		String passwordrepeat = request.getParameter(this.parameterNames.get("passwordrepeat"));
		if(passwordrepeat == null) { passwordrepeat = ""; }
		String firstname = request.getParameter(this.parameterNames.get("firstname"));
		if(firstname == null) { firstname = ""; }
		String lastname = request.getParameter(this.parameterNames.get("lastname"));
		if(lastname == null) { lastname = ""; }
		String employer = request.getParameter(this.parameterNames.get("employer"));
		if(employer == null) { employer = ""; }
		String position = request.getParameter(this.parameterNames.get("position"));
		if(position == null) { position = ""; }
		String studentansatt = request.getParameter(this.parameterNames.get("studentansatt"));
		if(studentansatt == null) { studentansatt = ""; }
		String positiontext = request.getParameter(this.parameterNames.get("positiontext"));
		if(positiontext == null) { positiontext = ""; }
		String emailaddress = request.getParameter(this.parameterNames.get("emailaddress"));
		if(emailaddress == null) { emailaddress = ""; }
		String repeatemail = request.getParameter(this.parameterNames.get("repeatemail"));
		if(repeatemail == null) { repeatemail = ""; }
		String newsletter = request.getParameter(this.parameterNames.get("newsletter"));
		if(newsletter == null) { newsletter = ""; }
		String survey = request.getParameter(this.parameterNames.get("survey"));
		if(survey == null) { survey = ""; }

		if( ! validUserType(usertype)){
			messages.appendChild(UserToXMLTranslator.element(document, "usertype", "NOT_VALID"));
			user.setRoleList(new Role[0]);
		} else {
			user.setRoleList(roleListFromKey(usertype));
		}
		values.appendChild(UserToXMLTranslator.cDataElement(document, "usertype", usertype));

		if (usertype.equals(UserRoleKey.health_personnel.getValue())){
			if(hprnumber.length() == 0){
				messages.appendChild(UserToXMLTranslator.element(document, "hprnumber", "NO_VALUE"));	
			}
			user.getPerson().setHprNumber(hprnumber);
		} else if (usertype.equals(UserRoleKey.student.getValue())) {
			if(studentnumber.length() == 0){
				messages.appendChild(UserToXMLTranslator.element(document, "studentnumber", "NO_VALUE"));	
			}
			user.getPerson().setStudentNumber(studentnumber);
		} else if (usertype.equals(UserRoleKey.health_personnel_other.getValue())) {
			if(dateOfBirth.length() == 0){
				messages.appendChild(UserToXMLTranslator.element(document, "dateofbirth", "NO_VALUE"));	
			}
			user.getPerson().setDateOfBirth(dateOfBirth);
		}
		
		if(username.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "username", "NO_VALUE"));
		} else if(userExists(username)){
			messages.appendChild(UserToXMLTranslator.element(document, "username", "USER_EXISTS"));
		}
		user.setUsername(username);
		if(password.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NO_VALUE"));
		} else if( ! PasswordValidator.getInstance().hasLength(password)){
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NOT_LENGTH"));
		} else if( ! PasswordValidator.getInstance().hasLetters(password)){
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NO_LETTERS"));
		} else if( ! PasswordValidator.getInstance().hasNumbers(password)){
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NO_NUMBERS"));
		} else if( ! PasswordValidator.getInstance().notTooLong(password)){
			messages.appendChild(UserToXMLTranslator.element(document, "password", "TOO_LONG"));
		} else if( ! PasswordValidator.getInstance().isValidPassword(password)){
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NOT_VALID"));
		} else if( ! password.equals(passwordrepeat)){
			messages.appendChild(UserToXMLTranslator.element(document, "passwordrepeat", "NOT_EQUAL"));
		} else {
			user.setPassword(password);
		}
		if(firstname.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "firstname", "NO_VALUE"));	
		}
		user.getPerson().setFirstName(firstname);
		if(lastname.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "lastname", "NO_VALUE"));	
		}
		user.getPerson().setLastName(lastname);
		
		if(employer.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "employer", "NO_VALUE"));	
		}
		user.getPerson().setEmployer(employer);

		if(usertype.equals(UserRoleKey.health_personnel.getValue())){
			if (position.length() == 0 || position.equals("choose")) {
				messages.appendChild(UserToXMLTranslator.element(document, "position", "NOT_SELECTED"));	
			} else {
				Position selectedPosition = positionFromKey(position);
				if(selectedPosition == null) {
					messages.appendChild(UserToXMLTranslator.element(document, "position", "NOT_VALID"));	
				} else {
					user.getPerson().setPosition(selectedPosition);
				}
			}
		} else if(usertype.equals(UserRoleKey.student.getValue())){
			if(studentansatt.length() == 0){
				messages.appendChild(UserToXMLTranslator.element(document, "studentansatt", "NO_VALUE"));	
			} else {
				user.getPerson().setIsStudent(studentansatt.equals("student"));
			}
		}  else {
			if(positiontext.length() == 0){
				messages.appendChild(UserToXMLTranslator.element(document, "positiontext", "NO_VALUE"));	
			}
			user.getPerson().setPositionText(positiontext);
		}
		
		if(emailaddress.length() == 0){
			messages.appendChild(UserToXMLTranslator.element(document, "emailaddress", "NO_VALUE"));
		} else if( ! EmailValidator.getInstance().isValidEmailAdress(emailaddress)){
			messages.appendChild(UserToXMLTranslator.element(document, "emailaddress", "NOT_VALID"));
		} else if( ! emailaddress.equals(repeatemail)){
			messages.appendChild(UserToXMLTranslator.element(document, "repeatemail", "NOT_EQUAL"));
		}
		user.getPerson().getContactInformation().setEmail(emailaddress);
		values.appendChild(UserToXMLTranslator.cDataElement(document, "repeatemail", repeatemail));
		
		user.getPerson().getProfile().setReceiveNewsletter(Boolean.valueOf(newsletter));
		user.getPerson().getProfile().setParticipateSurvey(Boolean.valueOf(survey));

		return user;
	}
	private boolean userExists(String username) {
		return this.userService.usernameTaken(username, null);
	}
	private Position positionFromKey(String positionString) throws Exception {
		SingleResultOrganizationType organizationTypeResult = organizationService.getOrganizationTypeByKey(OrganizationTypeKey._health_enterprise);
		OrganizationType organizationType = (OrganizationType) ((ValueResultOrganizationType) organizationTypeResult).getValue();
		SingleResultPosition positionResult = userService.getPositionByKey(PositionTypeKey.valueOf(positionString), organizationType);
		if (positionResult instanceof EmptyResultPosition) {
			return null;
		} else {
			return ((ValueResultPosition) positionResult).getValue();
		}
	}
	private Role[] roleListFromKey(String usertype) throws Exception {
		if (null == usertype) { throw new Exception("usertype is required but is not set"); }

		SingleResultSystem systemResult = userService.getSystemByKey(SystemKey.helsebiblioteket_admin);
		if (systemResult instanceof EmptyResultSystem) {
			throw new Exception("non existing system for system key '" + SystemKey.helsebiblioteket_admin + "");
		}
		System system = ((ValueResultSystem)systemResult).getValue();
		
		if (usertype.equals(UserRoleKey.health_personnel.getValue())) {
			SingleResultRole roleHealthPersonnelResult = userService.getRoleByKeySystem(UserRoleKey.health_personnel, system);
			if (roleHealthPersonnelResult instanceof EmptyResultRole) {
				throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.health_personnel + "'");
			}
			Role roleHealthPersonell = ((ValueResultRole) roleHealthPersonnelResult).getValue();
			Role roleHealthPersonellArray[] = { roleHealthPersonell };
			return roleHealthPersonellArray;
		} else if (usertype.equals(UserRoleKey.student.getValue())) {
			SingleResultRole roleStudentResult = userService.getRoleByKeySystem(UserRoleKey.student, system);
			if (roleStudentResult instanceof EmptyResultRole) {
				throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.student + "'");
			}
			Role roleStudent = ((ValueResultRole) roleStudentResult).getValue();
			Role roleStudentArray[] = { roleStudent };
			return roleStudentArray;
		} else	if (usertype.equals(UserRoleKey.health_personnel_other.getValue())) {
			SingleResultRole roleOtherResult = userService.getRoleByKeySystem(UserRoleKey.health_personnel_other, system);
			if (roleOtherResult instanceof EmptyResultRole) {
				throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.health_personnel_other + "'");
			}
			Role roleOther = ((ValueResultRole) roleOtherResult).getValue();
			Role roleOtherArray[] = { roleOther };
			return roleOtherArray;
		} else {
			logger.error("Controller not able to resolve usertype from request params for usertype '" + usertype + "'");
			return new Role[0];
		}
	}
	private boolean validUserType(String usertype) {
		return usertype.equals(UserRoleKey.administrator.getValue()) ||
				usertype.equals(UserRoleKey.health_personnel.getValue()) ||
				usertype.equals(UserRoleKey.student.getValue()) ||
				usertype.equals(UserRoleKey.health_personnel_other.getValue());
	}
//	private boolean isInteger(String integer) {
//		try{Integer.parseInt(integer);} catch (NumberFormatException e) {return false;}
//		return true;
//	}
	private void userXML(LoggedInUser user, Document document, Element element) throws ParserConfigurationException, TransformerException {
		LoggedInUserToXMLTranslator loggedInUserToXMLTranslator = new LoggedInUserToXMLTranslator();
		loggedInUserToXMLTranslator.translate(user, document, element);
	}
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	public void setSessionLoggedInUserVarName(String sessionLoggedInUserVarName) {
		this.sessionLoggedInUserVarName = sessionLoggedInUserVarName;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setParameterNames(Map<String, String> parameterNames) {
		this.parameterNames = parameterNames;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
