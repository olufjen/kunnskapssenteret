package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Person;

public interface PersonService extends Serializable {
	// TODO: Remove this!
	public Person insertPerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Person person);
}
