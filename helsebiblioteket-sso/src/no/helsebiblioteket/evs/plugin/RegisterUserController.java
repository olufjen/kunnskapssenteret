package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultRole;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

public final class RegisterUserController extends ProfileController {
	private LoggedInFunction loggedInFunction;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String save = request.getParameter(this.parameterNames.get("saveName"));
		String cancel = request.getParameter(this.parameterNames.get("cancelName"));
		String formId = request.getParameter(this.parameterNames.get("formId"));
    	if(save != null && save.equals(this.parameterNames.get("saveValue"))){
    		if(cancel != null && cancel.equals(this.parameterNames.get("cancelValue"))){
    			this.cancel(request, response);
        	} else {
        		this.registerUser(request, response);
        	}
    	} else {
    		this.init(request, response);
    	}
	}
	private void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User();
		String usertype = request.getParameter(this.parameterNames.get("usertype"));
		if (null == usertype || "".equals(usertype)) {
			usertype = request.getParameter("form_3");
		}
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
//		element.appendChild(document.createElement("init"));
		Element values = document.createElement("values");
		values.appendChild(UserToXMLTranslator.element(document, "usertype", usertype));
		userXML(user, null, document, values);
		element.appendChild(values);
		document.appendChild(element);
		loggedInFunction.setResult(this.resultSessionVarName, document);
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
		loggedInFunction.setResult(this.resultSessionVarName, document);
		String gotoUrl = request.getParameter(this.parameterNames.get("from"));
		response.sendRedirect(gotoUrl);
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = createUserFromRequestParams(request);
		
		String hprNumber = request.getParameter(this.parameterNames.get("hprno"));
		if(hprNumber == null) { hprNumber = "";}
		String usertype = request.getParameter(this.parameterNames.get("usertype"));
		// TODO: Check for errors.
		// TODO: Load errror messages from props!
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		Element messages = document.createElement("messages");
		if(hprNumber.length() == 0 || ! isInteger(hprNumber)) {
			messages.appendChild(UserToXMLTranslator.element(document, "hprnumber", "NOT_NUMBER"));
		}
		this.validateUser(user, request, document, messages);
		
		// TODO: Deal with different user types!

		boolean success = false;
		String summary = "";
		// TODO: Bad test!
		if( ! messages.hasChildNodes()){
			user.getPerson().setHprNumber(hprNumber);
			// TODO: Saving may fail though!
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
			element.appendChild(document.createElement("success"));
			gotoUrl = request.getParameter(this.parameterNames.get("goto"));
		} else {
			Element values = document.createElement("values");
			values.appendChild(UserToXMLTranslator.element(document, "usertype", usertype));
			userXML(user, hprNumber, document, values);
			element.appendChild(values);
			element.appendChild(messages);
			if(summary.length() != 0){
				element.appendChild(UserToXMLTranslator.element(document, "summary", summary));
			}
			gotoUrl = request.getParameter(this.parameterNames.get("from"));
		}
		document.appendChild(element);
		loggedInFunction.setResult(this.resultSessionVarName, document);
    	response.sendRedirect(gotoUrl);
	}
	private User createUserFromRequestParams(HttpServletRequest request) throws Exception {	
		ContactInformation contactInformation = new ContactInformation();
		String email = request.getParameter(this.parameterNames.get("emailaddress"));
		contactInformation.setEmail(email);
		
		Profile profile = new Profile();
		
		String tmpString = null;
        if ((tmpString = request.getParameter(this.parameterNames.get("newsletter"))) != null && !"".equals(tmpString)) {
        	profile.setReceiveNewsletter(Boolean.valueOf(tmpString));
        }
        if ((tmpString = request.getParameter(this.parameterNames.get("questionaire"))) != null && !"".equals(tmpString)) {
        	profile.setParticipateSurvey(Boolean.valueOf(tmpString));
        }
		
        Position position = null;
        String usertype = null;
        if ((usertype = request.getParameter(this.parameterNames.get("usertype"))) != null && !"".equals(usertype != null)) {
        	if (UserRoleKey.health_personnel.getValue().equals(usertype)) {
        		String positionString = request.getParameter(this.parameterNames.get("position"));
        		if (!positionString.equals("choose") && !positionString.equals("none")) {
	        		SingleResultPosition positionResult = userService.getPositionByKey(PositionTypeKey.valueOf(positionString));
	        		if (positionResult instanceof EmptyResultPosition) {
	        			throw new Exception("user somehow selected a non-existing position: '" + positionString + "'");
	        		}
	        		position = (Position) ((ValueResultPosition) positionResult).getValue();
        		}
        	}
        }
		
		Person person = new Person();
		person.setHprNumber(request.getParameter(this.parameterNames.get("hprno")));
		person.setFirstName(request.getParameter(this.parameterNames.get("firstname")));
		person.setLastName(request.getParameter(this.parameterNames.get("lastname")));
		person.setEmployer(request.getParameter(this.parameterNames.get("employer")));
		person.setContactInformation(contactInformation);
		person.setProfile(profile);
		person.setPosition(position);
		
		User user = new User();
		user.setUsername(request.getParameter(this.parameterNames.get("username")));
		user.setPassword(request.getParameter(this.parameterNames.get("password")));
		user.setPerson(person);
		
		SingleResultSystem systemResult = userService.getSystemByKey(SystemKey.helsebiblioteket_admin);
		if (systemResult instanceof EmptyResultSystem) {
			throw new Exception("non existing system for system key '" + SystemKey.helsebiblioteket_admin + "");
		}
		System system = ((ValueResultSystem)systemResult).getValue();
		
		SingleResultRole roleOtherResult = userService.getRoleByKeySystem(UserRoleKey.health_personnel_other, system);
		if (roleOtherResult instanceof EmptyResultRole) {
			throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.health_personnel_other + "'");
		}
		Role roleOther = (Role) ((ValueResultRole) roleOtherResult).getValue();
		
		SingleResultRole roleHealthPersonnelResult = userService.getRoleByKeySystem(UserRoleKey.health_personnel, system);
		if (roleHealthPersonnelResult instanceof EmptyResultRole) {
			throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.health_personnel + "'");
		}
		Role roleHealthPersonell = (Role) ((ValueResultRole) roleHealthPersonnelResult).getValue();
		
		SingleResultRole roleStudentResult = userService.getRoleByKeySystem(UserRoleKey.student, system);
		if (roleStudentResult instanceof EmptyResultRole) {
			throw new Exception("non existing role for system key '" + SystemKey.helsebiblioteket_admin + "' and role key '" + UserRoleKey.student + "'");
		}
		Role roleStudent = (Role) ((ValueResultRole) roleStudentResult).getValue();
		
		Role roleOtherArray[] = { roleOther };
		Role roleHealthPersonellArray[] = { roleHealthPersonell };
		Role roleStudentArray[] = { roleStudent };
		
		usertype = request.getParameter(this.parameterNames.get("usertype"));
		if (null == usertype) {
			throw new Exception("usertype is required but is not set");
		}
		
		if (usertype.equals(UserRoleKey.health_personnel.getValue())) {
			user.setRoleList(roleHealthPersonellArray);
		} else	if (usertype.equals(UserRoleKey.health_personnel_other.getValue())) {
			user.setRoleList(roleOtherArray);
		} else if (usertype.equals(UserRoleKey.student.getValue())) {
			user.setRoleList(roleStudentArray);
		}
		
		
		return user;
	}
	
	protected void validateUser(User user, HttpServletRequest request, Document document, Element element){
		super.validateUser(user, request, document, element);
		String username = request.getParameter(this.parameterNames.get("username"));
		if(username == null) { username = "";}
		user.setUsername(username);
		if(username.length() == 0){
			element.appendChild(UserToXMLTranslator.element(document, "username", "NO_VALUE"));
		} else if(userExists(username)){
			element.appendChild(UserToXMLTranslator.element(document, "username", "USER_EXISTS"));
		}
	}
	private boolean userExists(String username) {
		SingleResultUser result = this.userService.findUserByUsername(username);
		return (result instanceof ValueResultUser);
	}
	protected void userXML(User user, String hprNumber, Document document, Element element) throws ParserConfigurationException, TransformerException {
		super.userXML(user, hprNumber, document, element);
		element.appendChild(UserToXMLTranslator.cDataElement(document, "username", user.getUsername()));
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}