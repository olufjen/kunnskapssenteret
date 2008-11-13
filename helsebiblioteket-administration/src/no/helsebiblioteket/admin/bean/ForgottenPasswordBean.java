package no.helsebiblioteket.admin.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import no.helsebiblioteket.domain.User;
import no.helsebiblioteket.admin.service.AdminService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ForgottenPasswordBean {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private AdminService adminService;
	private String email;
	private String password;
	private UIComponent passwordComponent;
	private UIComponent emailComponent;
	public String login() {
		logger.info("method 'actionLogin' invoked");
		User user = new User();
		user.setUsername(getEmail());
		user = this.adminService.findUserByUsername(user);
		if(user == null || ! user.getPassword().equals(password)){
			// TODO: Use message from Resource Bundle
			 //String warningContractRequired =  MessageBundleLoader.getMessage("required") + this.getContractTypeComponent().getLabel();
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
					 "Feil passord eller ukjent bruker.",
					 "Prøv igjen");
			 context.addMessage(this.emailComponent.getClientId(context), facesMessage);
//			 context.getClientIdsWithMessages();
//
//			FacesContext.getCurrentInstance().addMessage("rart",
//					 new FacesMessage("user_unknown"));
			return null;
		} else {
			return "login_success";
		}
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

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}