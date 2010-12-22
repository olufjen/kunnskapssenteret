package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;

public class LoggedInOrganizationToXMLTranslator {
	public void translate(LoggedInOrganization organization, Document document, Element element){
		Element orgElement = document.createElement("organization");
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "nameenglishnormal", organization.getNameEnglishNormal()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "namenorwegiannormal", organization.getNameNorwegianNormal()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "nameenglishshort", organization.getNameEnglishShort()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "namenorwegianshort", organization.getNameNorwegianShort()));

		if(element == null){
			document.appendChild(orgElement);
		} else {
			element.appendChild(orgElement);
		}
		
	}
}
