package no.helsebiblioteket.admin.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.validator.EmailValidator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginBean {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private LoginService loginService;
	private String email;
	private String password;
	private boolean failed = false;
	public boolean getFailed() {
		logger.error("method 'getFailed' invoked");
		return this.failed;
	}
	public void setFailed(boolean failed) {
		// FIXME: Remove!
		logger.error("method 'setFailed' invoked");
//		this.failed = failed;
	}
	public String login() {
		logger.info("method 'actionLogin' invoked");
		User user = new User();
		user.setUsername(this.getEmail());
		user.setPassword(this.getPassword());
		user = this.loginService.logInUser(user);
		if( user == null ){
			this.failed = true;
			return "back_to_login";
		} else {
			this.failed = false;
			return "login_success";
		}
	}
	public String send() {
		logger.info("method 'send' invoked");
		User user = new User();
		user.setUsername(getEmail());
		this.loginService.sendPasswordEmail(user);
		return "send_email_success";
	}
	public String actionBackToLogin() {
		return "back_to_login";
	}
	public String actionactionForgottenPassword() {
		return "goto_forgotten";
	}
	public void validateEmail(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		String email = (String)newValue;
		UIInput emailComponent = (UIInput)component;
		this.logger.debug("email: " + email);
		EmailValidator emailValidator = new EmailValidator();
		String msg = "";
		boolean valid = true;
//		if(email.length() == 0) { mes = "Email address is required."; valid = false; }
		if( ! emailValidator.isValidEmailAdress(email)) { msg = "email_not_valid"; valid = false; }
		if ( ! valid) {
			emailComponent.setValid(false);
			// TODO: Set with Spring
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault() );
			FacesMessage message = new FacesMessage(bundle.getString(msg));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
	public void validatePassword(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		// FIXME: This must go!
		this.logger.error("email: " + email);
		if(this.getEmail() == null || this.getPassword() != null) { return; }
		User user = new User();
		user.setUsername(this.getEmail());
		user.setPassword(this.getPassword());
		if(this.loginService.logInUser(user) == null){
			UIInput passwordInput = (UIInput) component;
			String msgId = "login_unknown_user";
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault() );
			FacesMessage message = new FacesMessage(bundle.getString(msgId));
			facesContext.addMessage(passwordInput.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
