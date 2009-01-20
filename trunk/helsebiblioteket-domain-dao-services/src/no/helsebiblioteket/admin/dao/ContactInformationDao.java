package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.ContactInformation;

public interface ContactInformationDao {
	public void deleteContactInformation(ContactInformation contactInformation);
	
	public void insertContactInformation(ContactInformation contactInformation);
	
	public void updateContactInformation(ContactInformation contactInformation);
	
	public ContactInformation getContactInformationById(Integer contactInformationId);
	
	public void saveContactInformation(ContactInformation changedContactInformation, ContactInformation originalContactInformation);
}
