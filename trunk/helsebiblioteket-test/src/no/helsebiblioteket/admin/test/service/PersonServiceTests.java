package no.helsebiblioteket.admin.test.service;

import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.service.PersonService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class PersonServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertPerson(){
		Person person = new Person();
		person.setFirstName("nyyt123Fornavn");
		PersonService personService = this.beanFactory.getPersonService();
		personService.insertPerson(person);
		
		// TODO: Reload person and change?
		
		personService.insertPerson(person);
		personService.deletePerson(person);
	}
}
