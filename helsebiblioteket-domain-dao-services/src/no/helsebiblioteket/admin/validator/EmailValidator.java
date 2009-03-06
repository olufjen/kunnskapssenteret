package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

public class EmailValidator {
	private static EmailValidator instance = null;
	private static String validEmailRegExpString = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
	private static Pattern validEmailRegExpPattern = null;
	static {
		validEmailRegExpPattern = Pattern.compile(validEmailRegExpString);
	}
	
	protected EmailValidator() {
		// Exists only to defeat instantiation.
	}
	   
	public static EmailValidator getInstance() {
		if(instance == null) {
	    	instance = new EmailValidator();
		}
	    return instance;
	}
	
	public boolean isValidEmailAdress(String address){
		return validEmailRegExpPattern.matcher(null == address ? "" : address).find();
	}
}
