package no.helsebiblioteket.admin.service.impl;

import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.service.PersonService;

public class PersonServiceImpl implements PersonService {
	// TODO: Remove this class!
	private PersonDao personDao;
	private PositionDao positionDao;
	private ContactInformationDao contactInformationDao;
	private ProfileDao profileDao;
	public void deletePerson(Person person) {
		// TODO: What more?
		this.personDao.deletePerson(person);
	}
	public Person insertPerson(Person person) {
		// TODO: What more?
		this.profileDao.insertProfile(person.getProfile());
		this.positionDao.insertPosition(person.getPosition());
		this.contactInformationDao.insertContactInformation(person.getContactInformation());
		this.personDao.insertPerson(person);
		return null;
	}
	public void updatePerson(Person person) {
		// TODO: What more?
		this.profileDao.updateProfile(person.getProfile());
		this.positionDao.updatePosition(person.getPosition());
		this.contactInformationDao.updateContactInformation(person.getContactInformation());
		this.personDao.updatePerson(person);
	}
}
