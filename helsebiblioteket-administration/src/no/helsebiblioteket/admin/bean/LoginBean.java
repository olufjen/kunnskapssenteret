package no.helsebiblioteket.admin.bean;


import java.io.IOException;
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
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.ui.AbstractProcessingFilter;

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

	public String login() {
		return "login"; 
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
		//if(true) return;
		
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