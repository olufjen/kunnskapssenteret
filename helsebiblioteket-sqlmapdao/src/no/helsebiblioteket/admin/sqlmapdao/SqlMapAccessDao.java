package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;

/**
 * Used to insert, update, load and delete ResourceAccess
 * objects in the database.
 * 
 * Should also be extended for OrganizationAccess.
 */
public class SqlMapAccessDao extends SqlMapClientDaoSupport implements AccessDao {
	public void insertResourceAccessForeignKeys(ResourceAccessForeignKeys access) {
		getSqlMapClientTemplate().insert("insertResourceAccessForeignKeys", access);
	}
	public void updateResourceAccessForeignKeys(ResourceAccessForeignKeys access) {
		getSqlMapClientTemplate().update("updateResourceAccessForeignKeys", access);
	}
	public void deleteResourceAccessForeignKeys(ResourceAccessForeignKeys keys) {
		getSqlMapClientTemplate().delete("deleteResourceAccessForeignKeys", keys.getResourceAccess().getAccess().getId());
	}

	public List<ResourceAccessForeignKeys> getAccessListByUser(User user){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByUserId", user.getId());
	}
	public List<ResourceAccessForeignKeys> getAccessListByUserRole(Role userRole){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByUserRole", userRole.getId());
	}
	public List<ResourceAccessForeignKeys> getAccessListByOrganization(Organization organization){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organization.getId());
	}
	public List<ResourceAccessForeignKeys> getAccessListByOrganizationType(OrganizationType organizationType){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationType", organizationType.getId());
	}
}
