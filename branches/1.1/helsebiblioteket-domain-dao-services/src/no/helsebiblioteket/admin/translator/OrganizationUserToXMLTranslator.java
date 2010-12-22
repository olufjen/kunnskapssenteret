package no.helsebiblioteket.admin.translator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Role;

public class OrganizationUserToXMLTranslator {
	private RoleToXMLTranslator roleToXMLTranslator = new RoleToXMLTranslator();
	private OrganizationToXMLTranslator organizationToXMLTranslator = new OrganizationToXMLTranslator();
	private PersonToXMLTranslator personToXMLTranslator = new PersonToXMLTranslator();
	private DocumentBuilder builder;
	public OrganizationUserToXMLTranslator() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		this.builder = factory.newDocumentBuilder();
	}
	public Document newDocument() {
		return this.builder.newDocument();
	}
	public void translate(OrganizationUser user, Document document, Element element){
		Element userElement = document.createElement("user");
		userElement.appendChild(UserToXMLTranslator.cDataElement(document, "username", user.getUser().getUsername()));

		Element rolesElement = document.createElement("roles");
		for (Role role : user.getUser().getRoleList()) {
			this.roleToXMLTranslator.translate(role, document, rolesElement);
		}
		userElement.appendChild(rolesElement);
		Organization organization = user.getOrganization();
		this.organizationToXMLTranslator.translate(organization, document, userElement);

		Person person = user.getUser().getPerson();
		this.personToXMLTranslator.translate(person, document, userElement);
		if(element == null){
			document.appendChild(userElement);
		} else {
			element.appendChild(userElement);
		}

	}
}
