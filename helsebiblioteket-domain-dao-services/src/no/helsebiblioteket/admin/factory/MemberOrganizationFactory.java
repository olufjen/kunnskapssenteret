package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;

import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;

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
	public MemberOrganization completeOrganization(OrganizationType organizationType, Position position){
		MemberOrganization organization = new MemberOrganization();
		organization.setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		organization.setContactPerson(PersonFactory.factory.completePerson(position));
		organization.setType(organizationType);
		return organization;
	}
}
