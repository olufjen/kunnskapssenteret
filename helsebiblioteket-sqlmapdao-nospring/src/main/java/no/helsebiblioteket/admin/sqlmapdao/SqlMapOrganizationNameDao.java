package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;

public class SqlMapOrganizationNameDao extends IbatisSqlMapClientDaoSupport implements OrganizationNameDao {
	public void insertOrganizationName(Organization organization, OrganizationName organizationName) {
		organizationName.setOrgUnitId(organization.getId());
		getSqlMapClientTemplate().insert("insertOrganizationName", organizationName);
		OrganizationName tmp = (OrganizationName) getSqlMapClientTemplate().queryForObject("getOrganizationNameById", organizationName);
		organizationName.setLastChanged(tmp.getLastChanged());
	}
	public void updateOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().update("updateOrganizationName", organizationName);
		OrganizationName tmp = (OrganizationName) getSqlMapClientTemplate().queryForObject("getOrganizationNameById", organizationName);
		organizationName.setLastChanged(tmp.getLastChanged());
	}
	public void deleteOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().delete("deleteOrganizationName", organizationName);
	}
	@SuppressWarnings("unchecked")
	public List<OrganizationName> getOrganizationNameListByOrganization(Organization organization) {
		return (List<OrganizationName>) getSqlMapClientTemplate().queryForList("getOrganizationNameListByOrganization", organization.getId());
	}
}
