package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;

public interface PersonDao {
	// Edit
	public Person insertPerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Person person);
	
	// Fetch
	public Person getPersonByUser(User user);
	public Person getPersonByOrganization(Organization organization);
}
