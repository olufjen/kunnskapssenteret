package no.helsebiblioteket.admin.factory;

import java.util.Date;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;

public class PersonFactory {
	public static PersonFactory factory = new PersonFactory();
	private PersonFactory(){}
	public Person createPerson(){
		Person person = new Person();
		person.setContactInformation(ContactInformationFactory.factory.createContactInformation());
		person.setEmployer("");
		person.setFirstName("");
		person.setHprNumber("");
		person.setNationalIdNumber("");
//		person.setIsStudent(false);
		person.setLastChanged(new Date());
		person.setLastName("");
		person.setPosition(PositionFactory.factory.createPosition());
		person.setProfile(ProfileFactory.factory.createProfile());
		person.setStudentNumber("");
		return person;
	}
	public Person completePerson(Position position){
		Person person = createPerson();
		person.setContactInformation(ContactInformationFactory.factory.completeContactInformation());
		person.setPosition(position);
		person.setProfile(ProfileFactory.factory.completeProfile());
		return person;
	}
}
