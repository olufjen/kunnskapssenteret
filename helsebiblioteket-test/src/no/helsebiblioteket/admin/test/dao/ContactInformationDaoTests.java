package no.helsebiblioteket.admin.test.dao;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.factory.ContactInformationFactory;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ContactInformationDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testContactInformation() {
		ContactInformation contactInformation = ContactInformationFactory.factory.completeContactInformation();
		this.insertContactInformation(contactInformation);

		ContactInformationDao contactInformationDao = beanFactory.getContactInformationDao();

		contactInformation.setEmail("newmail");
		contactInformationDao.updateContactInformation(contactInformation);
		
		PositionDao positionDao = beanFactory.getPositionDao();
		Position position = positionDao.getPositionByKey(PositionTypeKey.ambulansearbeider);

		Person person = PersonFactory.factory.completePerson(position);
		person.setContactInformation(contactInformation);
		new PersonDaoTests().insertPerson(person);
		ContactInformation personContactInformation = contactInformationDao.getContactInformationByPerson(person);
		Assert.notNull(personContactInformation, "No contact information for user");
		
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.other);
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		new OrganizationDaoTests().insertMemberOrganization(organization);
		
		ContactInformation orgContactInformation = contactInformationDao.getContactInformationByOrganization(organization.getOrganization());
		Assert.notNull(orgContactInformation, "No contact information for organization");
	}
	public void insertContactInformation(ContactInformation contactInformation){
		ContactInformationDao contactInformationDao = beanFactory.getContactInformationDao();
		contactInformationDao.insertContactInformation(contactInformation);
	}
	public void removeContactInformation(ContactInformation contactInformation){
		ContactInformationDao contactInformationDao = beanFactory.getContactInformationDao();
		contactInformationDao.deleteContactInformation(contactInformation);
	}
}
