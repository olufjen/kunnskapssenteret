package no.helsebiblioteket.admin.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ForgottenPasswordBean {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private LoginService loginService;
	private String email;
	private String password;
	private UIComponent passwordComponent;
	private UIComponent emailComponent;
	public String send() {
		
		// TODO: Complete this Bean!
		
		logger.info("method 'send' invoked");
		User user = new User();
		user.setUsername(getEmail());
		this.loginService.sendPasswordEmail(user);
		
		// TODO: Use message from Resource Bundle
		 //String warningContractRequired =  MessageBundleLoader.getMessage("required") + this.getContractTypeComponent().getLabel();
//		FacesContext context = FacesContext.getCurrentInstance();
//		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
//				 "Feil passord eller ukjent bruker.",
//				 "Prøv igjen");
//		context.addMessage(this.emailComponent.getClientId(context), facesMessage);
		return "send_email_success";
	}
	public void validateEmail(FacesContext facesContext,UIComponent component, Object newValue) throws ValidatorException {
		String email = newValue.toString();
		this.emailComponent = component;
		if (false) {
			((UIInput)component).setValid(false);
			FacesMessage message = new FacesMessage("Email address is required.");
			facesContext.addMessage(component.getClientId(facesContext), message);
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
