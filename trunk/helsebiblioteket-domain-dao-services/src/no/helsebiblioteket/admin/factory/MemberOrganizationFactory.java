package no.helsebiblioteket.admin.factory;

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
		organization.getOrganization().setDescription("");
		organization.getOrganization().setLastChanged(new Date());
		organization.getOrganization().setNameEnglish("");
		organization.getOrganization().setNameNorwegian("");
		organization.getOrganization().setNameShortEnglish("");
		organization.getOrganization().setNameShortNorwegian("");
		organization.getOrganization().setContactInformation(ContactInformationFactory.factory.createContactInformation());
		organization.getOrganization().setContactPerson(PersonFactory.factory.createPerson());
		organization.setIpAddressSetList(new IpAddressSet[0]);
		return organization;
	}
	public MemberOrganization completeOrganization(OrganizationType organizationType, Position position){
		MemberOrganization organization = createOrganization();
		organization.getOrganization().setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		organization.getOrganization().setContactPerson(PersonFactory.factory.completePerson(position));
		organization.getOrganization().setType(organizationType);
		return organization;
	}
}
