package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

public class PasswordValidator {
	private static PasswordValidator instance = null;
	// TODO: Comment?
	private static String validPasswordRegExpString = "[0-9A-Åa-å_]{6,}";
	private static Pattern validPasswordRegExpPattern = null;
	static {
		validPasswordRegExpPattern = Pattern.compile(validPasswordRegExpString);
	}
	
	protected PasswordValidator() {
		// Exists only to defeat instantiation.
	}
	   
	public static PasswordValidator getInstance() {
		if(instance == null) {
	    	instance = new PasswordValidator();
		}
	    return instance;
	}
	
	public boolean isValidPassword(String password){
		return validPasswordRegExpPattern.matcher(null == password ? "" : password).find();
	}
}
