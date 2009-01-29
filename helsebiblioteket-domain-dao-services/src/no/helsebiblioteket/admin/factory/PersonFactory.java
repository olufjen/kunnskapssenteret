package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;

public class PersonFactory {
	public static PersonFactory factory = new PersonFactory();
	private PersonFactory(){}
	public Person createPerson(){
		Person person = new Person();
		person.setContactInformation(ContactInformationFactory.factory.createContactInformation());
		person.setEmployer("");
		person.setFirstName("");
		person.setHprNumber("");
		person.setIsStudent(false);
		person.setLastChanged(new Date());
		person.setLastName("");
		person.setPosition(PositionFactory.factory.createPosition());
		person.setProfile(ProfileFactory.factory.createProfile());
		person.setStudentNumber("");
		return person;
	}
	public Person completePerson(OrganizationType organizationType){
		Person person = createPerson();
		person.setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		person.setPosition(PositionFactory.factory.completePosition(organizationType));
		person.setProfile(ProfileFactory.factory.completeProfile());
		return person;
	}
}
