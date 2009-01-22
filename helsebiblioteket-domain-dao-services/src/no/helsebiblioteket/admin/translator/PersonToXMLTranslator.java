package no.helsebiblioteket.admin.translator;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Person;

public class PersonToXMLTranslator {
	private ProfileToXMLTranslator profileToXMLTranslator = new ProfileToXMLTranslator();
	private ContactInformationToXMLTranslator contactInformationToXMLTranslator = new ContactInformationToXMLTranslator();
	private PositionToXMLTranslator positionToXMLTranslator = new PositionToXMLTranslator();
	public void translate(Person person, Document document, Element element){
		Element personElement = document.createElement("person");
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "name", person.getName()));
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "firstname", person.getFirstName()));
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "lastname", person.getLastName()));
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "employer", person.getEmployer()));

		personElement.appendChild(UserToXMLTranslator.element(document, "hprnumber", person.getHprNumber()));
		personElement.appendChild(UserToXMLTranslator.element(document, "studentnumber", person.getStudentNumber()));

		this.profileToXMLTranslator.translate(person.getProfile(), document, personElement);
		this.contactInformationToXMLTranslator.translate(person.getContactInformation(), document, personElement);
		this.positionToXMLTranslator.translate(person.getPosition(), document, element);

		
		
		// TODO: Complete this!
		person.getId();
		person.getIsStudent();
		person.getLastChanged();
		
		if(element == null){
			document.appendChild(personElement);
		} else {
			element.appendChild(personElement);
		}
		
	}
}
