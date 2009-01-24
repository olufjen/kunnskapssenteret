package no.helsebiblioteket.evs.plugin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

public final class RegisterUserController extends ProfileController {
	private LoggedInFunction loggedInFunction;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String save = request.getParameter(this.parameterNames.get("saveName"));
		String cancel = request.getParameter(this.parameterNames.get("cancelName"));
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
		// TODO: Should not need to init here!
		user.setRoleList(new ArrayList<Role>());
		user.getOrganization().setNameList(new ArrayList<OrganizationName>());
		String usertype = request.getParameter(this.parameterNames.get("usertype"));
		
		
		
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
		// TODO: How to initialize person?
		User user = new User();
		user.setPerson(new Person());
		user.setRoleList(new ArrayList<Role>());
		user.getOrganization().setNameList(new ArrayList<OrganizationName>());
		
		String hprNumber = request.getParameter(this.parameterNames.get("hprno"));
		if(hprNumber == null) { hprNumber = "";}
		String usertype = request.getParameter(this.parameterNames.get("usertype"));
		// TODO: Check for errors.
		// TODO: Load errror messages from props!
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document document = translator.newDocument();
		Element element = document.createElement(this.resultSessionVarName);
		Element messages = document.createElement("messages");
		if(hprNumber.length() == 0 || ! isInteger(hprNumber)){
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
	    	this.userService.createUser(user);
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
	protected void validateUser(User user, HttpServletRequest request, Document document, Element element){
		super.validateUser(user, request, document, element);
		String username = request.getParameter(this.parameterNames.get("username"));
		if(username == null) { username = "";}
		user.setUsername(username);
		if(username.length() == 0){
			element.appendChild(UserToXMLTranslator.element(document, "username", "NO_VALUE"));
		} else if(userExists(username)){
			element.appendChild(UserToXMLTranslator.element(document, "username", "USERNAME_IN_USER"));
		}
	}
	private boolean userExists(String username) {
		// FIXME: Write this!
		User test = new User();
		test.setUsername(username);
		// TODO: no,no,no
		test.setRoleList(new ArrayList<Role>());
		test.getOrganization().setNameList(new ArrayList<OrganizationName>());
		return this.userService.findUserByUsername(test) != null;
	}
	protected void userXML(User user, String hprNumber, Document document, Element element) throws ParserConfigurationException, TransformerException {
		super.userXML(user, hprNumber, document, element);
		element.appendChild(UserToXMLTranslator.cDataElement(document, "username", user.getUsername()));
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}
