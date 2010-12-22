package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;

public class SupplierOrganizationFactory {
	public static SupplierOrganizationFactory factory = new SupplierOrganizationFactory();
	private SupplierOrganizationFactory(){}
	public SupplierOrganization createOrganization(){
		SupplierOrganization organization = new SupplierOrganization();
		organization.getOrganization().setDescription("");
		organization.getOrganization().setLastChanged(new Date());
		organization.getOrganization().setNameEnglish("");
		organization.getOrganization().setNameNorwegian("");
		organization.getOrganization().setNameShortEnglish("");
		organization.getOrganization().setNameShortNorwegian("");
		organization.getOrganization().setContactInformation(ContactInformationFactory.factory.createContactInformation());
		organization.getOrganization().setContactPerson(PersonFactory.factory.createPerson());
		organization.setResourceList(new SupplierSourceResource[0]);
		return organization;
	}
	public SupplierOrganization completeOrganization(OrganizationType organizationType, Position position){
		SupplierOrganization organization = createOrganization();
		organization.getOrganization().setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		organization.getOrganization().setContactPerson(PersonFactory.factory.completePerson(position));
		organization.getOrganization().setType(organizationType);
		return organization;
	}
}
