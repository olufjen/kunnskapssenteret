package no.helsebiblioteket.jsf.validators;


import no.helsebiblioteket.admin.validator.IpAddressValidator;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class IpAddressValidatiorJSF implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
	if (!IpAddressValidator.getInstance().isValidIPAddress((((UIInput) arg1).getSubmittedValue()).toString())) {
		FacesMessage message = new FacesMessage();
		String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
		String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
		message.setSummary(messageValue);
		throw new ValidatorException(message);
		}
	}
}
