package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;

public interface ContactInformationDao {
	// Edit
	public void insertContactInformation(ContactInformation contactInformation);
	public void updateContactInformation(ContactInformation contactInformation);
	public void deleteContactInformation(ContactInformation contactInformation);
	
	// Fetch
	public ContactInformation getContactInformationByPerson(Person person);
	public ContactInformation getContactInformationByOrganization(Organization organization);
	public ContactInformation getContactInformationById(Integer id);
}
