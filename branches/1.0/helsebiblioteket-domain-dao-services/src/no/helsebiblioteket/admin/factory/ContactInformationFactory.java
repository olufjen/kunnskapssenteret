package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.ContactInformation;

public class ContactInformationFactory {
	public static ContactInformationFactory factory = new ContactInformationFactory();
	private ContactInformationFactory(){}
	public ContactInformation createContactInformation(){
		ContactInformation contactInformation = new ContactInformation();
		contactInformation.setEmail("");
		contactInformation.setLastChanged(new Date());
		contactInformation.setPostalAddress("");
		contactInformation.setPostalCode("");
		contactInformation.setPostalLocation("");
		contactInformation.setTelephoneNumber("");
		return contactInformation;
	}
	public ContactInformation completeContactInformation(){
		return createContactInformation();
	}
}
