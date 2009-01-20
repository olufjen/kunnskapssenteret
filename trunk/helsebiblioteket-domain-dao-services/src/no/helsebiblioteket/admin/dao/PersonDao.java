package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;

public interface PersonDao {
	public void deletePerson(Person person);
	
	public void insertPerson(Person person);
	
	public void updatePerson(Person person);
	
	public Person getPersonById(Integer personId);
}
