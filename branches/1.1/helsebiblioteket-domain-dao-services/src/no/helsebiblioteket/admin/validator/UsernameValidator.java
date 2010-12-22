package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

public class UsernameValidator {
	private static UsernameValidator instance = null;
	private static String validUsernameRegExpString = "[0-9A-Åa-å_]{1,}";
	private static Pattern validUsernameRegExpPattern = null;
	static {
		validUsernameRegExpPattern = Pattern.compile(validUsernameRegExpString);
	}
	
	protected UsernameValidator() {
		// Exists only to defeat instantiation.
	}
	   
	public static UsernameValidator getInstance() {
		if(instance == null) {
	    	instance = new UsernameValidator();
		}
	    return instance;
	}
	
	public boolean isValidUsername(String username){
		return validUsernameRegExpPattern.matcher(null == username ? "" : username).find();
	}
}
