package no.helsebiblioteket.admin.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.requestresult.SendPasswordEmailResult;
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
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private LoginService loginService;
	private String email;
	private String password;
	private String username;
	private boolean failed = false;
	
	private Email emailLostPasswordAdminTemplate;
	
	private boolean sentUser = false;
	private boolean sentEmail = false;
	private boolean notFoundUser = false;
	private boolean notFoundEmail = false;
	private boolean multipleEmail = false;

	public LoginBean(){ 
		Exception ex = (Exception) FacesContext.
			getCurrentInstance().
			getExternalContext().
			getSessionMap().
			get(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
		String messageValue = MessageResourceReader.getMessageResourceString(bundleMain,
				"login_unknown_user", "no property found");
		if (ex != null){
	         FacesContext.getCurrentInstance().addMessage(
	        		 null,
	                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                		 messageValue,
	                		 ex.getMessage()));
		}
	}

	public boolean getFailed() {
		logger.debug("method 'getFailed' invoked");
		return this.failed;
	}
	public String login() {
		return "login"; 
	}
	public String send() {
		Email emailLostPassword = new Email();
		emailLostPassword.setFromEmail(emailLostPasswordAdminTemplate.getFromEmail());
		emailLostPassword.setFromName(emailLostPasswordAdminTemplate.getFromName());
		emailLostPassword.setMessage(emailLostPasswordAdminTemplate.getMessage());
		emailLostPassword.setSubject(emailLostPasswordAdminTemplate.getSubject());
		emailLostPassword.setToEmail(getEmail());
		emailLostPassword.setToName(getEmail());
		SendPasswordEmailResult result = this.loginService.sendPasswordEmail(this.getEmail(), emailLostPassword);
		this.multipleEmail = false;
		this.notFoundEmail = false;
		this.notFoundUser = false;
		this.sentEmail = false;
		this.sentUser = false;
		if(result.getValue().equals(SendPasswordEmailResult.sentUser)){
			this.sentUser = true;
		} else if(result.getValue().equals(SendPasswordEmailResult.sentEmail)){
			this.sentEmail = true;
		} else if(result.getValue().equals(SendPasswordEmailResult.notFoundUser)){
			this.notFoundUser = true;
		} else if(result.getValue().equals(SendPasswordEmailResult.notFoundEmail)){
			this.notFoundEmail = true;
		} else if(result.getValue().equals(SendPasswordEmailResult.multipleEmail)){
			this.multipleEmail = true;
		}
		return "send_email_success";
	}
	public String actionBackToLogin() {
		return "back_to_login";
	}
	public String actionForgottenPassword() {
		return "goto_forgotten";
	}
	public void validateEmail(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {

		String email = (String)newValue;
		UIInput emailComponent = (UIInput)component;
		this.logger.debug("email: " + email);
		String msg = "";
		boolean valid = true;
		//if(email.length() == 0) { mes = "Email address is required."; valid = false; }
		if( ! EmailValidator.getInstance().isValidEmailAdress(email)) { msg = "email_not_valid"; valid = false; }
		if ( ! valid) {
			emailComponent.setValid(false);
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean getSentUser() {
		return sentUser;
	}
	public void setSentUser(boolean sentUser) {
		this.sentUser = sentUser;
	}
	public boolean getSentEmail() {
		return sentEmail;
	}
	public void setSentEmail(boolean sentEmail) {
		this.sentEmail = sentEmail;
	}
	public boolean getNotFoundUser() {
		return notFoundUser;
	}
	public void setNotFoundUser(boolean notFoundUser) {
		this.notFoundUser = notFoundUser;
	}
	public boolean getNotFoundEmail() {
		return notFoundEmail;
	}
	public void setNotFoundEmail(boolean notFoundEmail) {
		this.notFoundEmail = notFoundEmail;
	}
	public boolean getMultipleEmail() {
		return multipleEmail;
	}
	public void setMultipleEmail(boolean multipleEmail) {
		this.multipleEmail = multipleEmail;
	}
	public void setEmailLostPasswordAdminTemplate(Email emailLostPasswordAdminTemplate) {
		this.emailLostPasswordAdminTemplate = emailLostPasswordAdminTemplate;
	}
	public Email getEmailLostPasswordAdminTemplate() {
		return this.emailLostPasswordAdminTemplate;
	}
}
