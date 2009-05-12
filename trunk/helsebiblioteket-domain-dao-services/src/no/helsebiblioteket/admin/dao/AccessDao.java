package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;

public interface AccessDao {
	// Edit
	public void insertResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void updateResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void deleteResourceAccessForeignKeys(ResourceAccessForeignKeys access);

	// Fetch
	public List<ResourceAccessListItem> getAccessListForAll();
	public List<ResourceAccessListItem> getAccessListByUser(User user);
	public List<ResourceAccessListItem> getAccessListByUserRole(Role userRole);
	public List<ResourceAccessListItem> getAccessListByOrganization(Organization organization);
	public List<ResourceAccessListItem> getAccessListByOrganizationType(OrganizationType organizationType);

	public List<ResourceAccessForeignKeys> getAccessListByResource(Resource resource);
}
