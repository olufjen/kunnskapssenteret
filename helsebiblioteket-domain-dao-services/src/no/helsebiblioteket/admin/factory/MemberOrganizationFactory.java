package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;

public class MemberOrganizationFactory {
	public static MemberOrganizationFactory factory = new MemberOrganizationFactory();
	private MemberOrganizationFactory(){}
	public MemberOrganization createOrganization(){
		MemberOrganization organization = new MemberOrganization();
		organization.setDescription("");
		organization.setLastChanged(new Date());
		organization.setNameEnglish("");
		organization.setNameNorwegian("");
		organization.setNameShortEnglish("");
		organization.setNameShortNorwegian("");
		organization.setContactInformation(ContactInformationFactory.factory.createContactInformation());
		organization.setContactPerson(PersonFactory.factory.createPerson());
		organization.setIpAddressSetList(new ArrayList<IpAddressSet>());
		return organization;
	}
	public MemberOrganization completeOrganization(OrganizationType type){
		MemberOrganization organization = new MemberOrganization();
		organization.setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		organization.setContactPerson(PersonFactory.factory.completePerson(type));
		organization.setType(type);
		return organization;
	}
}
