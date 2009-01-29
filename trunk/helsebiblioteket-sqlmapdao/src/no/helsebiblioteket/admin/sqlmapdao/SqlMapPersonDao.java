package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapPersonDao extends SqlMapClientDaoSupport implements PersonDao {
	public Person insertPerson(Person person){
		getSqlMapClientTemplate().insert("insertPerson", person);
		// TODO: Return user?
		return null;
	}
	public void updatePerson(Person person){
		getSqlMapClientTemplate().update("updatePerson", person);
	}
	public void deletePerson(Person person){
		getSqlMapClientTemplate().delete("deletePerson", person.getId());
	}
	public Person getPersonByUser(User user){
		return (Person) getSqlMapClientTemplate().queryForObject("getPersonByUserId", user.getId());
	}
	public Person getPersonByOrganization(Organization organization){
		return (Person) getSqlMapClientTemplate().queryForObject("getPersonByOrganizationId", organization.getId());
	}
}
