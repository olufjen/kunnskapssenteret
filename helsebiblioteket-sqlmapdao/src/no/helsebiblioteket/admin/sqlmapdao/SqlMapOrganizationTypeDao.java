package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class SqlMapOrganizationTypeDao extends SqlMapClientDaoSupport implements OrganizationTypeDao {
	public void insertOrganizationType(OrganizationType organizationType) {
		getSqlMapClientTemplate().insert("insertOrganizationType", organizationType);
	}
	public OrganizationType getOrganizationTypeByKey(OrganizationTypeKey organizationTypeKey) {
		return (OrganizationType)getSqlMapClientTemplate().queryForObject("getOrganizationTypeByKey", organizationTypeKey.toString());
	}
	public List<OrganizationType> getOrganizationTypeListAll() {
		return (List<OrganizationType>)getSqlMapClientTemplate().queryForList("getOrganizationTypeListAll");
	}
}
