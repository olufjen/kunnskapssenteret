package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

public class PasswordValidator {
	private static PasswordValidator instance = null;
	// Graph is: !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
	private static String lettersNumbersSpeecialAndLengthRegExpString = "[\\p{Graph}ÆØÅæøå]{6,}";
//			"//!@#�\\$%&/\\\\|\\(\\)\\[\\]\\{\\}<>=+-*,.;:-_^~'�]{6,}";
//	private static String capitalLettersRegExpString = "[A-ZÆØÅ]";
	private static String lettersRegExpString = "[A-ZÆØÅa-zæøå]";
	private static String numbersRegExpString = "[0-9]";
	
	private static Pattern lettersNumbersSpeecialAndLengthPattern = null;
//	private static Pattern capitalLettersPatternPattern = null;
	private static Pattern lettersPattern = null;
	private static Pattern numbersPattern = null;
	static {
		lettersNumbersSpeecialAndLengthPattern = Pattern.compile(lettersNumbersSpeecialAndLengthRegExpString);
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
				numbersPattern.matcher(notNullPassword).find()
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

		// FALSE - Too short
		System.out.println(pv.isValidPassword("1;xE�"));
		System.out.println(pv.hasLength("1;xE�"));
		// FALSE - No letters
		System.out.println(pv.isValidPassword(";?_\\?6"));
		System.out.println(pv.hasLetters(";?_\\?6"));
		// FALSE - No caps
//		System.out.println(pv.isValidPassword(";e_\\?6"));
//		System.out.println(pv.hasCaps("1;xE�"));
		// FALSE - No numbers
		System.out.println(pv.isValidPassword(";e_\\?*"));
		System.out.println(pv.hasNumbers(";e_\\?*"));
	}
}
