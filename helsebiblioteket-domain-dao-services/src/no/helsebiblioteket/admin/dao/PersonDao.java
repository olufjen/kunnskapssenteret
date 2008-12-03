package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;

public interface PersonDao {
	public int insertContactInformation(ContactInformation contactInformation);
	public void updateContactInformation(ContactInformation contactInformation);

	public void insertProfile(Profile profile);
	public void updateProfile(Profile profile);

	public int insertPerson(Person person);
	public void updatePerson(Person person);
}
