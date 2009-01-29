package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public class SupplierOrganizationFactory {
	public static SupplierOrganizationFactory factory = new SupplierOrganizationFactory();
	private SupplierOrganizationFactory(){}
	public SupplierOrganization createOrganization(){
		SupplierOrganization organization = new SupplierOrganization();
		organization.setDescription("");
		organization.setLastChanged(new Date());
		organization.setNameEnglish("");
		organization.setNameNorwegian("");
		organization.setNameShortEnglish("");
		organization.setNameShortNorwegian("");
		organization.setContactInformation(ContactInformationFactory.factory.createContactInformation());
		organization.setContactPerson(PersonFactory.factory.createPerson());
		organization.setResourceList(new ArrayList<Resource>());
		return organization;
	}
	public SupplierOrganization completeOrganization(OrganizationType organizationType, Position position){
		SupplierOrganization organization = new SupplierOrganization();
		organization.setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		organization.setContactPerson(PersonFactory.factory.completePerson(position));
		organization.setType(organizationType);
		return organization;
	}
}
