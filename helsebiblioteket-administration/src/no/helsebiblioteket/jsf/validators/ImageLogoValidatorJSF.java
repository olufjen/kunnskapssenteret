package no.helsebiblioteket.jsf.validators;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.myfaces.custom.fileupload.UploadedFileDefaultMemoryImpl;

public class ImageLogoValidatorJSF implements Validator {
	private static final long MAX_FILE_SIZE = 3145728L; // 3MB.
	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
	ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault());

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("\n\n\nImageLogoValidatorJSF\n\n\n");
    	
        UploadedFileDefaultMemoryImpl file = (UploadedFileDefaultMemoryImpl) value;
        if (file != null && file.getBytes().length > MAX_FILE_SIZE) {
            file = null; // Free resources
            throw new ValidatorException(new FacesMessage(bundle.getString("image_size_not_valid")));
        }
        if (! Pattern.matches(IMAGE_PATTERN, file.getName())) {
        	throw new ValidatorException(new FacesMessage(bundle.getString("must_be_valid_file")));
        }
    }
}
