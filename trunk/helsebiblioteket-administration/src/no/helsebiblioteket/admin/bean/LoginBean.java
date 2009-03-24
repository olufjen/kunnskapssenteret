package no.helsebiblioteket.admin.bean;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultUser;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.stereotype.Component;


//@Component
//@Scope("request")
public class LoginBean {
	public static final String bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
	

	public LoginBean(){ 
		 Exception ex = (Exception) FacesContext
         .getCurrentInstance()
         .getExternalContext()
         .getSessionMap()
         .get(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
		 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "login_unknown_user", "no property found");
         if (ex != null)
         FacesContext.getCurrentInstance().addMessage(null
         ,
         new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        		 messageValue
        		, ex.getMessage()));
         //ex.getMessage()
         }



	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private LoginService loginService;
	private String email;
	private String password;
	private boolean failed = false;
	public boolean getFailed() {
		logger.debug("method 'getFailed' invoked");
		return this.failed;
	}
	public void setFailed(boolean failed) {
		// FIXME: Remove!
		logger.debug("method 'setFailed' invoked");
//		this.failed = failed;
	}
	public String login() {
		
		return "login";
	
		/*
		logger.info("method 'actionLogin' invoked");
		String username = this.getEmail();
		String password = this.getPassword();
		SingleResultUser result = this.loginService.loginUserByUsernamePassword(username, password);
		if(result instanceof EmptyResultUser){
			this.failed = true;
			return "back_to_login";
		} else {
			this.failed = false;
			return "login_success";
		}	
		*/
	}
	
	public String send() {
		logger.info("method 'send' invoked");
		User user = new User();
		user.setUsername(getEmail());
		this.loginService.sendPasswordEmail(user.getUsername());
		return "send_email_success";
	}
	public String actionBackToLogin() {
		return "back_to_login";
	}
	public String actionForgottenPassword() {
		return "goto_forgotten";
	}
	public void validateEmail(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		
		// TODO: No longer user email.
		// Check length?
		if(true) return;
		
		String email = (String)newValue;
		UIInput emailComponent = (UIInput)component;
		this.logger.debug("email: " + email);
		String msg = "";
		boolean valid = true;
//		if(email.length() == 0) { mes = "Email address is required."; valid = false; }
		if( ! EmailValidator.getInstance().isValidEmailAdress(email)) { msg = "email_not_valid"; valid = false; }
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
//		this.logger.error("email: " + email);
//		if(this.getEmail() == null || this.getPassword() != null) { return; }
//		User user = new User();
//		user.setUsername(this.getEmail());
//		user.setPassword(this.getPassword());
//		if(this.loginService.logInUser(user) == null){
//			UIInput passwordInput = (UIInput) component;
//			String msgId = "login_unknown_user";
//			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault() );
//			FacesMessage message = new FacesMessage(bundle.getString(msgId));
//			facesContext.addMessage(passwordInput.getClientId(facesContext), message);
//			throw new ValidatorException(message);
//		}
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
