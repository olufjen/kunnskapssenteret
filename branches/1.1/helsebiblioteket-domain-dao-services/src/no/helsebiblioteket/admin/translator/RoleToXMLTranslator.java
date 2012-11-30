package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Role;

public class RoleToXMLTranslator {

	public void translate(Role role, Document document, Element element){
		Element roleElement = document.createElement("role");
		roleElement.appendChild(UserToXMLTranslator.element(document, "key", role.getKey().getValue()));
		roleElement.appendChild(UserToXMLTranslator.cDataElement(document, "name", role.getName()));
		
		
		
		// TODO Fase2: Complete this!
//		role.getLastChanged();
//		role.getName();
//		role.getId();
		
		element.appendChild(roleElement);
	}

}