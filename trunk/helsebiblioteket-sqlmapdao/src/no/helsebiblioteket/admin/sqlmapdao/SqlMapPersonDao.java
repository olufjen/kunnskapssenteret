package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.Person;

public class SqlMapPersonDao extends SqlMapClientDaoSupport implements PersonDao {
	
	public void deletePerson(Person person) {
		getSqlMapClientTemplate().delete("deletePerson", person);
	}
	
	public void insertPerson(Person person) {
		getSqlMapClientTemplate().insert("insertPerson", person);
	}
	
	public void updatePerson(Person person) {
		getSqlMapClientTemplate().update("updatePerson", person);
	}
	
	public Person getPersonById(Integer personId) {
		return (Person) getSqlMapClientTemplate().queryForObject("getPersonById", personId);
	}
}