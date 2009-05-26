package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.LoggedInUser;

public class LoggedInUserToXMLTranslator {
	public void translate(LoggedInUser user, Document document, Element element) {
		Element userElement = document.createElement("user");
		userElement.appendChild(UserToXMLTranslator.cDataElement(document, "username", user.getUsername()));

		Element roleElement = document.createElement("role");
		roleElement.appendChild(UserToXMLTranslator.element(document, "key", user.getRoleKey()));
		roleElement.appendChild(UserToXMLTranslator.cDataElement(document, "name", user.getRoleName()));
		userElement.appendChild(roleElement);

		Element personElement = document.createElement("person");
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "name", user.getName()));
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "firstname", user.getFirstName()));
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "lastname", user.getLastName()));
		personElement.appendChild(UserToXMLTranslator.cDataElement(document, "employer", user.getEmployer()));
		personElement.appendChild(UserToXMLTranslator.element(document, "hprnumber", user.getHprNumber()));
		personElement.appendChild(UserToXMLTranslator.element(document, "studentnumber", user.getStudentNumber()));
		personElement.appendChild(UserToXMLTranslator.element(document, "nationalidnumber", user.getNationalIdNumber()));
		personElement.appendChild(UserToXMLTranslator.element(document, "positiontext", user.getPositionText()));
		personElement.appendChild(UserToXMLTranslator.element(document, "isstudent", user.getIsStudent()));
		personElement.appendChild(UserToXMLTranslator.element(document, "positionkey", user.getPositionKey()));

		Element profileElement = document.createElement("profile");
		profileElement.appendChild(UserToXMLTranslator.element(document, "survey", user.getParticipateSurvey()));
		profileElement.appendChild(UserToXMLTranslator.element(document, "newsletter", user.getReceiveNewsletter()));
		personElement.appendChild(profileElement);

		Element contactElement = document.createElement("contactinformation");
		contactElement.appendChild(UserToXMLTranslator.cDataElement(document, "email",user.getEmail()));
		personElement.appendChild(contactElement);

		if(user.getPositionKey() != null){
			Element positionElement = document.createElement("position");
			positionElement.appendChild(UserToXMLTranslator.cDataElement(document, "description", user.getPositionDescription()));
			positionElement.appendChild(UserToXMLTranslator.cDataElement(document, "key", user.getPositionKey()));
			positionElement.appendChild(UserToXMLTranslator.cDataElement(document, "name", user.getPositionName()));
			personElement.appendChild(positionElement);
		}

		userElement.appendChild(personElement);
		
		if(element == null){
			document.appendChild(userElement);
		} else {
			element.appendChild(userElement);
		}
	}
}
