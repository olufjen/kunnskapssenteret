package no.helsebiblioteket.admin.bean;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;
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