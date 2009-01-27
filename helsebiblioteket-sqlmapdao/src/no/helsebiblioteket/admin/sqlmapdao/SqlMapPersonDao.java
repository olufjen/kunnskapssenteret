package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapPersonDao extends SqlMapClientDaoSupport implements PersonDao {
	// TODO: Go through all!
	public Person insertPerson(Person person){

		getSqlMapClientTemplate().insert("insertPerson", person);
		return null;
	}
	public void updatePerson(Person person){
		
		getSqlMapClientTemplate().update("updatePerson", person);

	}
	public void deletePerson(Person person){
	
		getSqlMapClientTemplate().delete("deletePerson", person);

	}
	public Person getPersonByUser(User user){
		return null;
	}
	public Person getPersonByOrganization(Organization organization){
		return null;
	}

	
	
	
	
	
	
	public Person getPersonById(Integer personId) {
		return (Person) getSqlMapClientTemplate().queryForObject("getPersonById", personId);
	}
}
