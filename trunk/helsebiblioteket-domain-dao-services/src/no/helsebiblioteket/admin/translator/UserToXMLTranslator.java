package no.helsebiblioteket.admin.translator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

public class UserToXMLTranslator {
	private RoleToXMLTranslator roleToXMLTranslator = new RoleToXMLTranslator();
	private PersonToXMLTranslator personToXMLTranslator = new PersonToXMLTranslator();
	private DocumentBuilder builder;
	public UserToXMLTranslator() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		this.builder = factory.newDocumentBuilder();
	}
	public Document newDocument() {
		return this.builder.newDocument();
	}
	public void translate(User user, Document document, Element element){
		Element userElement = document.createElement("user");
		userElement.appendChild(cDataElement(document, "username", user.getUsername()));

		Element rolesElement = document.createElement("roles");
		for (Role role : user.getRoleList()) {
			this.roleToXMLTranslator.translate(role, document, rolesElement);
		}
		userElement.appendChild(rolesElement);
		Person person = user.getPerson();
		if(person != null){
			// TODO: Should have person?
			this.personToXMLTranslator.translate(person, document, userElement);
		}
		if(element == null){
			document.appendChild(userElement);
		} else {
			element.appendChild(userElement);
		}

	}
	public static Element cDataElement(Document document, String name, String value){
		Element element =  document.createElement(name);
		CDATASection cdata = document.createCDATASection(value);
		element.appendChild(cdata);
		return element;
	}
	public static Element element(Document document, String name, String value){
		Element element =  document.createElement(name);
		element.setTextContent(value);
		return element;
	}
	public static Element element(Document document, String name, Integer value){
		// TODO: </nonum> ??
		if(value == null){
			return element(document, name, "");
		} else {
			return element(document, name, value.toString());
		}
	}
	public static Element element(Document document, String name, Boolean value){
		// TODO: </nobool> ??
		if(value == null){
			return element(document, name, "");
		} else {
			return element(document, name, value.toString());
		}
	}
}