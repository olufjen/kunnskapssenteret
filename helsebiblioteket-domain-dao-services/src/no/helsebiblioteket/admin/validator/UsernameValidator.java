package no.helsebiblioteket.admin.validator;

import java.util.ArrayList;
import java.util.List;

public class UsernameValidator {
	private static UsernameValidator instance = null;
	
	public enum ErrorCodes {
		NO_VALUE,
		TOO_LONG,
		LEADING_BLANK_SPACE,
		TRAILING_BLANK_SPACE,
		USER_EXISTS
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
	
	public List<ErrorCodes> validateAndGetErrorCodes(String username, List<ErrorCodes> knownErrors) {
		username = (username == null) ? "" : username;
		List<ErrorCodes> result = (null != knownErrors) ? knownErrors : new ArrayList<ErrorCodes>();
		if (username.startsWith(" ")) {
			result.add(ErrorCodes.LEADING_BLANK_SPACE);
		}
		if (username.endsWith(" ")) {
			result.add(ErrorCodes.TRAILING_BLANK_SPACE);
		}
		if (username.length() == 0) {
			result.add(ErrorCodes.NO_VALUE);
		}
		if (username.length() > 256) {
			result.add(ErrorCodes.TOO_LONG);
		}
		return result;
	}
	
	public List<ErrorCodes> validateAndGetErrorCodes(String username) {
		return validateAndGetErrorCodes(username, null);
	}
}