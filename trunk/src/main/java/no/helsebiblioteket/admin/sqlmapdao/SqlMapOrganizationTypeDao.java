package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class SqlMapOrganizationTypeDao extends IbatisSqlMapClientDaoSupport implements OrganizationTypeDao {
	public void insertOrganizationType(OrganizationType organizationType) {
		getSqlMapClientTemplate().insert("insertOrganizationType", organizationType);
	}
	public OrganizationType getOrganizationTypeByKey(OrganizationTypeKey organizationTypeKey) {
		return (OrganizationType)getSqlMapClientTemplate().queryForObject("getOrganizationTypeByKey", organizationTypeKey.getValue());
	}
	@SuppressWarnings("unchecked")
	public List<OrganizationType> getOrganizationTypeListAll() {
		return (List<OrganizationType>)getSqlMapClientTemplate().queryForList("getOrganizationTypeListAll");
	}
	@Override
	public OrganizationType getOrganizationTypeById(Integer id) {
		return (OrganizationType)getSqlMapClientTemplate().queryForObject("getOrganizationTypeById", id);
	}
}
