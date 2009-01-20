package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.domain.ContactInformation;

public class SqlMapContactInformationDao extends SqlMapClientDaoSupport implements ContactInformationDao {
	
	public void saveContactInformation(ContactInformation changedContactInformation, ContactInformation originalContactInformation) {
		if (changedContactInformation.getId() == null) {
			insertContactInformation(changedContactInformation);
		} else {
			updateContactInformation(changedContactInformation);
		}
	}
	
	public void deleteContactInformation(ContactInformation contactInformation) {
		getSqlMapClientTemplate().delete("deleteContactInformation", contactInformation);
	}
	
	public void insertContactInformation(ContactInformation contactInformation) {
		getSqlMapClientTemplate().insert("insertContactInformation", contactInformation);
	}
	
	public void updateContactInformation(ContactInformation contactInformation) {
		getSqlMapClientTemplate().update("updateContactInformation", contactInformation);
	}
	
	public ContactInformation getContactInformationById(Integer contactInformationId) {
		return (ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationById", contactInformationId);
	}
}