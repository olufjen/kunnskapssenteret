package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

public class PasswordValidator {
	private static PasswordValidator instance = null;
	private static String lettersNumbersAndLengthRegExpString = "[0-9A-Åa-å_]{6,}";
	private static String capitalLettersRegExpString = "[A-Å]";
	private static String lettersRegExpString = "[a-å]";
	private static String numbersRegExpString = "[0-9]";
	
	private static Pattern lettersNumbersAndLengthPattern = null;
	private static Pattern capitalLettersPatternPattern = null;
	private static Pattern lettersPatternPattern = null;
	private static Pattern numbersPatternPattern = null;
	static {
		lettersNumbersAndLengthPattern = Pattern.compile(lettersNumbersAndLengthRegExpString);
		lettersPatternPattern = Pattern.compile(lettersRegExpString);
		capitalLettersPatternPattern = Pattern.compile(capitalLettersRegExpString);
		numbersPatternPattern = Pattern.compile(numbersRegExpString);
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
		boolean validPassword = false;
		String notNullPassword = null == password ? "" : password;
		validPassword = (
				lettersNumbersAndLengthPattern.matcher(notNullPassword).find() &&
				lettersPatternPattern.matcher(notNullPassword).find() &&
				capitalLettersPatternPattern.matcher(notNullPassword).find() &&
				numbersPatternPattern.matcher(notNullPassword).find()
				);
		return validPassword;
	}
}
