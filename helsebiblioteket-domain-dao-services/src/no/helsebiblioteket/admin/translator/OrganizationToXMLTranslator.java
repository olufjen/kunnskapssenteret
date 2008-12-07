package no.helsebiblioteket.admin.translator;

import no.helsebiblioteket.admin.domain.Organization;

public class OrganizationToXMLTranslator {
	public void translate(Organization organization, StringBuffer buffer){
		buffer.append("<organization>");
		buffer.append("<name>");
		buffer.append(organization.getName());
		buffer.append("</name>");
		
		
		// TODO Complete this!
		
		buffer.append("</organization>");
		
		
	}
}
