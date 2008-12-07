package no.helsebiblioteket.admin.translator;

import no.helsebiblioteket.admin.domain.User;

public class UserToXMLTranslator {
	private RoleToXMLTranslator roleToXMLTranslator = new RoleToXMLTranslator();
	private OrganizationToXMLTranslator organizationToXMLTranslator = new OrganizationToXMLTranslator();
	public void translate(User user, StringBuffer buffer){
		buffer.append("<user>");
		buffer.append("<username>");
		buffer.append(user.getUsername());
		buffer.append("</username>");
		this.roleToXMLTranslator.translate(user.getRole(), buffer);
		this.organizationToXMLTranslator.translate(user.getOrganization(), buffer);
		
		// TODO: Complete this!
		user.getAccessList();
		user.getPerson();
		
		buffer.append("</user>");
	}
}
