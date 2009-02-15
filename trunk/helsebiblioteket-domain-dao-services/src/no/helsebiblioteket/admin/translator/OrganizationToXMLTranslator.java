package no.helsebiblioteket.admin.translator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;

public class OrganizationToXMLTranslator {
	public void translate(MemberOrganization organization, Document document, Element element){
		Element orgElement = document.createElement("organization");
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "nameenglishnormal", organization.getOrganization().getNameEnglish()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "namenorwegiannormal", organization.getOrganization().getNameNorwegian()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "nameenglishshort", organization.getOrganization().getNameShortEnglish()));
		orgElement.appendChild(UserToXMLTranslator.cDataElement(document, "namenorwegianshort", organization.getOrganization().getNameShortNorwegian()));
		
		// TODO Complete this!
//		organization.getAccessList();
		organization.getOrganization().getContactInformation();
		organization.getOrganization().getContactPerson();
		organization.getOrganization().getDescription();
//		organization.getOrgUnitId();
//		organization.getIpAddressSetList();
		organization.getOrganization().getLastChanged();
//		organization.getNameList();
//		organization.getNameMap()
//		organization.getParent();
		organization.getOrganization().getType();
		
		
		if(element == null){
			document.appendChild(orgElement);
		} else {
			element.appendChild(orgElement);
		}
		
	}
}
