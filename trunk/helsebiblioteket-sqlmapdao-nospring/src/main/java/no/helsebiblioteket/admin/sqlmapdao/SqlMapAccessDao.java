package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;

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
	public List<ResourceAccessListItem> getAccessListByUser(UserListItem user){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByUserId", user.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByUserRole(UserRoleKey userRole){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByUserRole", userRole.getValue());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByOrganization(OrganizationListItem organization){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organization.getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListByOrganizationType(OrganizationTypeKey organizationType){
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationType", organizationType.getValue());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessListItem> getAccessListNational() {
//		try{
//			getSqlMapClientTemplate().queryForList("getAccessListNational");
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.print(e);
//		}
		return (List<ResourceAccessListItem>) getSqlMapClientTemplate().queryForList("getAccessListNational");
 
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceAccessForeignKeys> getAccessListByResource(Resource resource) {
		return (List<ResourceAccessForeignKeys>) getSqlMapClientTemplate().queryForList("getAccessListByResource", resource);
	}
	@Override
	public void deleteAccessByResourceId(Integer resourceId) {
		getSqlMapClientTemplate().delete("deleteAccessByResourceId", resourceId);
	}
}
