package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;

public interface PersonDao {
	// Edit
	public void insertPerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Person person);
	
	// Fetch
	public Person getPersonByUser(User user);
	public Person getPersonByOrganization(MemberOrganization organization);
}
