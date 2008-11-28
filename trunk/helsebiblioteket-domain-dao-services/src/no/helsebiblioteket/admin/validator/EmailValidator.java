package no.helsebiblioteket.admin.validator;

public class EmailValidator {
	public boolean isValidEmailAdress(String address){
		// TODO: Write this!
		if(!address.contains("@")) {  return false; }
		return true;
	}
}
