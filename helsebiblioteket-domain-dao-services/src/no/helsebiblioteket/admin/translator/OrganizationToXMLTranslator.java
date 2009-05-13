package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.Organization;

public class OrganizationToXMLTranslator {
	public void translate(Organization organization, Document document, Element element){
		Element orgElement = document.createElement("organization");
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "nameenglishnormal", organization.getNameEnglish()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "namenorwegiannormal", organization.getNameNorwegian()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "nameenglishshort", organization.getNameShortEnglish()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "namenorwegianshort", organization.getNameShortNorwegian()));
		
		// TODO Fase2: Complete this!
//		organization.getContactInformation();
//		organization.getContactPerson();
//		organization.getDescription();
//		organization.getLastChanged();
//		organization.getType();
		
		
		if(element == null){
			document.appendChild(orgElement);
		} else {
			element.appendChild(orgElement);
		}
		
	}
}
