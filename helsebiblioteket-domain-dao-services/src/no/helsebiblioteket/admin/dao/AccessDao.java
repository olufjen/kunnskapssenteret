package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;

public interface AccessDao {
	// Edit
	public void insertResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void updateResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void deleteResourceAccessForeignKeys(ResourceAccessForeignKeys access);

	// Fetch
	public List<ResourceAccessListItem> getAccessListForAll();
	public List<ResourceAccessListItem> getAccessListByUser(UserListItem user);
	public List<ResourceAccessListItem> getAccessListByUserRole(UserRoleKey userRole);
	public List<ResourceAccessListItem> getAccessListByOrganization(OrganizationListItem organization);
	public List<ResourceAccessListItem> getAccessListByOrganizationType(OrganizationTypeKey organizationType);

	public List<ResourceAccessForeignKeys> getAccessListByResource(Resource resource);
	public void deleteAccessByResourceId(Integer resourceId);
}
