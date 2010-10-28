package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapOrganizationDao extends IbatisSqlMapClientDaoSupport implements OrganizationDao {
	@Override
	public void insertOrganization(Organization organization) {
		getSqlMapClientTemplate().insert("insertOrganization", organization);
		Organization tmp = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
		organization.setLastChanged(tmp.getLastChanged());
	}
	@Override
	public void updateOrganization(Organization organization) {
		getSqlMapClientTemplate().update("updateOrganization", organization);
		Organization tmp = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
		organization.setLastChanged(tmp.getLastChanged());
	}
	@Override
	public void deleteOrganization(Organization organization) {
		getSqlMapClientTemplate().delete("deleteOrganization", organization);
	}
	@Override
	public Organization getOrganizationById(Integer id){
		return (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", id);
	}
	@Override
	public Organization getOrganizationByAdminUserId(Integer id) {
		return (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationByAdminUserId", id);
	}
}
