package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;

public interface ContactInformationDao {
	// Edit
	public ContactInformation insertContactInformation(ContactInformation contactInformation);
	public void updateContactInformation(ContactInformation contactInformation);
	public void deleteContactInformation(ContactInformation contactInformation);
	
	// Fetch
	// TODO: Change this!
	public ContactInformation getContactInformationByPerson(Person person);
	public ContactInformation getContactInformationByOrganization(MemberOrganization organization);
}
