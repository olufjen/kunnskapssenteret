package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Person;

public class SqlMapContactInformationDao extends SqlMapClientDaoSupport implements ContactInformationDao {
	public ContactInformation insertContactInformation(ContactInformation contactInformation){
		getSqlMapClientTemplate().insert("insertContactInformation", contactInformation);
		return (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationById", contactInformation.getId());
	}
	public void updateContactInformation(ContactInformation contactInformation){
		getSqlMapClientTemplate().update("updateContactInformation", contactInformation);
	}
	public void deleteContactInformation(ContactInformation contactInformation){
		getSqlMapClientTemplate().delete("deleteContactInformation", contactInformation.getId());
	}
	public ContactInformation getContactInformationByPerson(Person person){
		return (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationByPerson", person.getId());
	}
	public ContactInformation getContactInformationByOrganization(MemberOrganization organization){
		return (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationByOrganization", organization.getOrganization().getId());
	}
}
