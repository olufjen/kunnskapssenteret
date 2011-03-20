package no.helsebiblioteket.admin.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import no.helsebiblioteket.admin.validator.UsernameValidator.ErrorCodes;

public class PasswordValidator {
	private static PasswordValidator instance = null;
	// Graph is: !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
	private static String lettersNumbersSpeecialAndLengthRegExpString = "[\\p{Graph}ÆØÅæøå]{6,}";
	private static String lettersNumbersSpeecialAndTooLongRegExpString = "[\\p{Graph}ÆØÅæøå]{13,}";
//			"//!@#�\\$%&/\\\\|\\(\\)\\[\\]\\{\\}<>=+-*,.;:-_^~'�]{6,}";
//	private static String capitalLettersRegExpString = "[A-ZÆØÅ]";
	private static String lettersRegExpString = "[A-ZÆØÅa-zæøå]";
	private static String numbersRegExpString = "[0-9]";
	
	private static Pattern lettersNumbersSpeecialAndLengthPattern = null;
	private static Pattern lettersNumbersSpeecialAndTooLongPattern = null;
//	private static Pattern capitalLettersPatternPattern = null;
	private static Pattern lettersPattern = null;
	private static Pattern numbersPattern = null;
	static {
		lettersNumbersSpeecialAndLengthPattern = Pattern.compile(lettersNumbersSpeecialAndLengthRegExpString);
		lettersNumbersSpeecialAndTooLongPattern = Pattern.compile(lettersNumbersSpeecialAndTooLongRegExpString);
		lettersPattern = Pattern.compile(lettersRegExpString);
//		capitalLettersPatternPattern = Pattern.compile(capitalLettersRegExpString);
		numbersPattern = Pattern.compile(numbersRegExpString);
	}
	
	public enum ErrorCodes {
		NO_VALUE,
		NOT_LENGTH,
		LEADING_BLANK_SPACE,
		TRAILING_BLANK_SPACE,
		NO_LETTERS,
		NO_NUMBERS,
		TOO_LONG,
		PASSWORD_NOT_VALID,
		NOT_EQUAL
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
				noLeadingOrTrailingWhiteSpaces(notNullPassword) &&
				// Must be at least 6 characters long
				lettersNumbersSpeecialAndLengthPattern.matcher(notNullPassword).find() &&
				// Must have letters
				lettersPattern.matcher(notNullPassword).find() &&
				// Must have capital letters
//				capitalLettersPatternPattern.matcher(notNullPassword).find() &&
				// Must have numbers
				numbersPattern.matcher(notNullPassword).find() &&
				// No longer than 12 characters
				( ! lettersNumbersSpeecialAndTooLongPattern.matcher(notNullPassword).find() )
				);
		return validPassword;
	}
	public boolean hasLength(String password){
		boolean validPassword = false;
		String notNullPassword = null == password ? "" : password;
		// Must have letters
		validPassword = ( lettersNumbersSpeecialAndLengthPattern.matcher(notNullPassword).find() );
		return validPassword;
	}
	public boolean hasLetters(String password){
		boolean validPassword = false;
		String notNullPassword = null == password ? "" : password;
		// Must have letters
		validPassword = ( lettersPattern.matcher(notNullPassword).find() );
		return validPassword;
	}
//	public boolean hasCaps(String password){
//		boolean validPassword = false;
//		String notNullPassword = null == password ? "" : password;
//		// Must have caps
//		validPassword = ( capitalLettersPatternPattern.matcher(notNullPassword).find() );
//		return validPassword;
//	}
	public boolean hasNumbers(String password){
		boolean validPassword = false;
		String notNullPassword = null == password ? "" : password;
		// Must have caps
		validPassword = ( numbersPattern.matcher(notNullPassword).find() );
		return validPassword;
	}
	public boolean notTooLong(String password){
		boolean validPassword = false;
		String notNullPassword = null == password ? "" : password;
		// No longer than 12 characters
		validPassword = ( ! lettersNumbersSpeecialAndTooLongPattern.matcher(notNullPassword).find() );
		return validPassword;
	}
	public boolean noLeadingOrTrailingWhiteSpaces(String password) {
		boolean validPassword = false;
		String notNullPassword = (null == password) ? "" : password;
		validPassword = (!(notNullPassword.endsWith(" ") || notNullPassword.startsWith(" ")));
		return validPassword;
	}

	public List<ErrorCodes> validateAndGetErrorCodes(String password, List<ErrorCodes> knownErrors) {
		password = (password == null) ? "" : password;
		List<ErrorCodes> result = (null != knownErrors) ? knownErrors : new ArrayList<ErrorCodes>();
		if (password.startsWith(" ")) {
			result.add(ErrorCodes.LEADING_BLANK_SPACE);
		}
		if (password.endsWith(" ")) {
			result.add(ErrorCodes.TRAILING_BLANK_SPACE);
		}
		if (password.length() == 0) {
			result.add(ErrorCodes.NO_VALUE);
		}
		// Must be at least 6 characters long
		if (lettersNumbersSpeecialAndLengthPattern.matcher(password).find()) {
			result.add(ErrorCodes.NOT_LENGTH);
		}
		// Must have letters
		if (lettersPattern.matcher(password).find()) {
			result.add(ErrorCodes.NO_LETTERS);
		}
		// Must have numbers
		if (numbersPattern.matcher(password).find()) {
			result.add(ErrorCodes.NO_NUMBERS);
		}
		// No longer than 12 characters
		if ( ! lettersNumbersSpeecialAndTooLongPattern.matcher(password).find()) {
			result.add(ErrorCodes.TOO_LONG);
		}
		if (result.size() > 0) {
			result.add(ErrorCodes.PASSWORD_NOT_VALID);
		}
		return result;
	}
	
	public List<ErrorCodes> validateAndGetErrorCodes(String password) {
		return validateAndGetErrorCodes(password, null);
	}
	
	public static void main(String[] args) {
		PasswordValidator pv = new PasswordValidator();
		
		// OK
		System.out.println(pv.isValidPassword("1;xEÅ6"));
		// OK
		System.out.println(pv.isValidPassword("12æåÆÅ"));
		// OK
		System.out.println(pv.isValidPassword("12*'~ÆÅ"));
		// OK
		System.out.println(pv.isValidPassword("12*a_å"));
		// OK
		System.out.println(pv.isValidPassword("1;xEÅa_.9TøÅ"));

		// FALSE - Too short
		System.out.println(pv.isValidPassword("1;xEÅ"));
		System.out.println(pv.hasLength("1;xEÅ"));
		// FALSE - Too long
		System.out.println(pv.isValidPassword("1;xEÅa_.9TøÅv"));
		System.out.println(pv.notTooLong("1;xEÅa_.9TøÅv"));
		// FALSE - No letters
		System.out.println(pv.isValidPassword(";?_\\?6"));
		System.out.println(pv.hasLetters(";?_\\?6"));
		// FALSE - No numbers
		System.out.println(pv.isValidPassword(";e_\\?*"));
		System.out.println(pv.hasNumbers(";e_\\?*"));
	}
}
