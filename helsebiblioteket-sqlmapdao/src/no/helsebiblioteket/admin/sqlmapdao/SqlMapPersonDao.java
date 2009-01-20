package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.User;

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