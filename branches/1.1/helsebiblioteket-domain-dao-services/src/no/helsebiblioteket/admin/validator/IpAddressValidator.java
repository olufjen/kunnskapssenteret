package no.helsebiblioteket.admin.validator;

import java.util.regex.Pattern;

public class IpAddressValidator {
	private static IpAddressValidator instance = null;
	
	protected IpAddressValidator() {
		// Exists only to defeat instantiation.
	}
	   
	public static IpAddressValidator getInstance() {
		if(instance == null) {
	    	instance = new IpAddressValidator();
		}
	    return instance;
	}
	
	public boolean isValidIPAddress(String ipaddr) {
		String str_255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
		Pattern p = Pattern.compile( "^(?:" + str_255 + "\\.){3}" + str_255 + "$");
		if( p.matcher(ipaddr).matches()){
			 String[] parts = ipaddr.split("\\.");
			 if (Float.parseFloat(parts[0]) < 1) { return false; }
		 return true;
		}
		return false;
	}
}