package no.helsebiblioteket.admin.sqlmapdao;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapPersonDao extends IbatisSqlMapClientDaoSupport implements PersonDao {
	public void insertPerson(Person person){
		getSqlMapClientTemplate().insert("insertPerson", person);
		Person tmp = (Person) getSqlMapClientTemplate().queryForObject("getPersonById", person.getId());
		person.setLastChanged(tmp.getLastChanged());
	}
	public void updatePerson(Person person){
		getSqlMapClientTemplate().update("updatePerson", person);
		Person tmp = (Person) getSqlMapClientTemplate().queryForObject("getPersonById", person.getId());
		person.setLastChanged(tmp.getLastChanged());
	}
	public void deletePerson(Person person){
		getSqlMapClientTemplate().delete("deletePerson", person);
	}
	public Person getPersonByUser(User user){
		return (Person) getSqlMapClientTemplate().queryForObject("getPersonByUserId", user.getId());
	}
	public Person getPersonByOrganization(Organization organization){
		return (Person) getSqlMapClientTemplate().queryForObject("getPersonByOrganizationId", organization.getId());
	}
}
