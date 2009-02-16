package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;

public class SqlMapOrganizationNameDao extends SqlMapClientDaoSupport implements OrganizationNameDao {
	public void insertOrganizationName(Organization organization, OrganizationName organizationName) {
		organizationName.setOrgUnitId(organization.getId());
		getSqlMapClientTemplate().insert("insertOrganizationName", organizationName);
	}
	public void updateOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().update("updateOrganizationName", organizationName);
	}
	public void deleteOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().delete("deleteOrganizationName", organizationName);
	}
	public List<OrganizationName> getOrganizationNameListByOrganization(Organization organization) {
		return (List<OrganizationName>) getSqlMapClientTemplate().queryForList("getOrganizationNameListByOrganization", organization.getId());
	}
}
