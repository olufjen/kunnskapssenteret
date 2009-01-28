package no.helsebiblioteket.admin.translator;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.ContactInformation;

public class ContactInformationToXMLTranslator {
	public void translate(ContactInformation contactInformation, Document document, Element element){
		Element contactElement = document.createElement("contactinformation");
		contactElement.appendChild(UserToXMLTranslator.cDataElement(document, "email", contactInformation.getEmail()));
		
		// TODO: Complete this!
		contactInformation.getContactInformationId();
		contactInformation.getLastChanged();
		contactInformation.getPostalAddress();
		contactInformation.getPostalCode();
		contactInformation.getPostalLocation();
		contactInformation.getTelephoneNumber();
		
		
		if(element == null){
			document.appendChild(contactElement);
		} else {
			element.appendChild(contactElement);
		}
		
	}
}
