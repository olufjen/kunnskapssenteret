package no.helsebiblioteket.admin.test.dao;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class PersonDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testPerson(){
		Position position = beanFactory.getPositionDao().getPositionByKey(PositionTypeKey.ambulansearbeider);
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.public_administration);
		String firstName = "FN" + new Random().nextInt(1000000);
		Person person = PersonFactory.factory.completePerson(position);
		person.setFirstName(firstName);

		// INSERT
		this.insertPerson(person);

		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		organization.getOrganization().setContactPerson(person);
		new OrganizationDaoTests().insertMemberOrganization(organization);
		User user = UserFactory.factory.completeUser(organization, position);
		user.setPerson(person);
		user.setUsername("UN" + firstName);
		new UserDaoTests().insertUser(user);
		PersonDao personDao = beanFactory.getPersonDao();
		
		// FIND BY USER
		Person foundUser = personDao.getPersonByUser(user);
		Assert.isTrue(foundUser.getFirstName().equals(firstName), "Lost user person");
		
		firstName += "CHANGED";
		foundUser.setFirstName(firstName);

		// UPDATE
		personDao.updatePerson(foundUser);

		// FIND BY ORGANIZATION
		Person foundOrganization = personDao.getPersonByOrganization(organization.getOrganization());
		Assert.isTrue(foundOrganization.getFirstName().equals(firstName), "Lost org person");
		
		
		// REMOVE
		new UserDaoTests().removeUser(user);
		new OrganizationDaoTests().removeMemberOrganization(organization);
		this.removePerson(person);
	}
	public void insertPerson(Person person){
		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.insertContactInformation(person.getContactInformation());

		ProfileDaoTests profileDaoTests = new ProfileDaoTests();
		profileDaoTests.insertProfile(person.getProfile());

		PersonDao personDao = beanFactory.getPersonDao();
		personDao.insertPerson(person);
	}
	public void removePerson(Person person){
		PersonDao personDao = beanFactory.getPersonDao();
		personDao.deletePerson(person);

		ProfileDaoTests profileDaoTests = new ProfileDaoTests();
		profileDaoTests.removeProfile(person.getProfile());

		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.removeContactInformation(person.getContactInformation());
		
	}
}
