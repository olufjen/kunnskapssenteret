package no.helsebiblioteket.admin.factory;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Position;

public class OrganizationUserFactory {
	public static OrganizationUserFactory factory = new OrganizationUserFactory();
	private OrganizationUserFactory(){}
	public OrganizationUser createUser(){
		OrganizationUser organizationUser = new OrganizationUser();
		organizationUser.setUser(UserFactory.factory.createUser());
		organizationUser.setOrganization(MemberOrganizationFactory.factory.createOrganization().getOrganization());
		return organizationUser;
	}
	public OrganizationUser completeUser(MemberOrganization organization, Position position){
		OrganizationUser organizationUser = createUser();
		organizationUser.setOrganization(organization.getOrganization());
		organizationUser.getUser().setPerson(PersonFactory.factory.completePerson(position));
		return organizationUser;
	}
}
