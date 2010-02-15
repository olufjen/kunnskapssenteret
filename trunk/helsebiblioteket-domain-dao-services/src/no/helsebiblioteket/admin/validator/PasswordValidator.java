package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

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
