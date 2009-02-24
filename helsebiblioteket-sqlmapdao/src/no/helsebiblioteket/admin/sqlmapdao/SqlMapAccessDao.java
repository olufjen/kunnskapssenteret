package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
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
		ResourceAccessForeignKeys tmp = (ResourceAccessForeignKeys) getSqlMapClientTemplate().queryForObject("getResourceAccessForeignKeysByAccessId", access.getResourceAccess().getAccess().getId());
		access.getResourceAccess().getAccess().setLastChanged(tmp.getResourceAccess().getAccess().getLastChanged());
	}
	public void updateResourceAccessForeignKeys(ResourceAccessForeignKeys access) {
		getSqlMapClientTemplate().update("updateResourceAccessForeignKeys", access);
		ResourceAccessForeignKeys tmp = (ResourceAccessForeignKeys) getSqlMapClientTemplate().queryForObject("getResourceAccessForeignKeysByAccessId", access.getResourceAccess().getAccess().getId());
		access.getResourceAccess().getAccess().setLastChanged(tmp.getResourceAccess().getAccess().getLastChanged());
	}
	public void deleteResourceAccessForeignKeys(ResourceAccessForeignKeys keys) {
		getSqlMapClientTemplate().delete("deleteResourceAccessForeignKeys", keys);
	}
	@SuppressWarnings("unchecked")
	public List<ResourceAccessForeignKeys> getAccessListByUser(User user){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByUserId", user.getId());
	}
	@SuppressWarnings("unchecked")
	public List<ResourceAccessForeignKeys> getAccessListByUserRole(Role userRole){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByUserRole", userRole.getId());
	}
	@SuppressWarnings("unchecked")
	public List<ResourceAccessForeignKeys> getAccessListByOrganization(Organization organization){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organization.getId());
	}
	@SuppressWarnings("unchecked")
	public List<ResourceAccessForeignKeys> getAccessListByOrganizationType(OrganizationType organizationType){
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationType", organizationType.getId());
	}
}
