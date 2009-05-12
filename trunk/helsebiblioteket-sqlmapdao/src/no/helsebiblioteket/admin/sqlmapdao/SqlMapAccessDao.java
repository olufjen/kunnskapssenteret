package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;

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
	@Override
	public List<ResourceAccessListItem> getAccessListForAll() {
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListForAll");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByUser(User user){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByUserId", user.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByUserRole(Role userRole){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByUserRole", userRole.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByOrganization(Organization organization){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organization.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByOrganizationType(OrganizationType organizationType){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationType", organizationType.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessForeignKeys> getAccessListByResource(Resource resource) {
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByResource", resource);
	}
}
