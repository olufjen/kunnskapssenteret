package no.helsebiblioteket.jsf.validators;

import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class IntegerOrNullValidatorJSF implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String value = (((UIInput) arg1).getSubmittedValue()).toString();
		if (value != null) {
			try {
				Integer.valueOf(value);
			} catch (NumberFormatException nfe) {
				FacesMessage message = new FacesMessage();
				String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
				String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "must_be_valid_integer", "Value must be a valid number");
				message.setSummary(messageValue);
				throw new ValidatorException(message);
			}
		}
	}
}
