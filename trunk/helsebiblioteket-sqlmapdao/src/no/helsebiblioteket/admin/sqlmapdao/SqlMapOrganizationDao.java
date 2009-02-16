package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.Organization;

public class SqlMapOrganizationDao extends SqlMapClientDaoSupport implements OrganizationDao {
	@Override
	public Organization getOrganizationById(Integer id){
		return (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", id);
	}
	@Override
	public Organization insertOrganization(Organization organization) {
		getSqlMapClientTemplate().insert("insertOrganization", organization);
		return (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
	}
	@Override
	public Organization updateOrganization(Organization organization) {
		getSqlMapClientTemplate().update("updateOrganization", organization);
		return (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
	}
	@Override
	public void deleteOrganization(Organization organization) {
		getSqlMapClientTemplate().delete("deleteOrganization", organization.getId());
	}
}
