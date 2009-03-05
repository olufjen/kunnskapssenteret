package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Person;

public class SqlMapContactInformationDao extends SqlMapClientDaoSupport implements ContactInformationDao {
	public void insertContactInformation(ContactInformation contactInformation){
		getSqlMapClientTemplate().insert("insertContactInformation", contactInformation);
		ContactInformation tmp = (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationById", contactInformation.getId());
		contactInformation.setLastChanged(tmp.getLastChanged());
	}
	public void updateContactInformation(ContactInformation contactInformation){
		getSqlMapClientTemplate().update("updateContactInformation", contactInformation);
		ContactInformation tmp = (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationById", contactInformation.getId());
		contactInformation.setLastChanged(tmp.getLastChanged());
	}
	public void deleteContactInformation(ContactInformation contactInformation){
		getSqlMapClientTemplate().delete("deleteContactInformation", contactInformation);
	}
	public ContactInformation getContactInformationByPerson(Person person){
		return (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationByPerson", person.getId());
	}
	public ContactInformation getContactInformationByOrganization(MemberOrganization organization){
		return (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationByOrganization", organization.getOrganization().getId());
	}
}