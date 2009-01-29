package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.daoobjects.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.UserRole;

public interface AccessDao {
	// Edit
	public void insertResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void updateResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void deleteResourceAccessForeignKeys(ResourceAccessForeignKeys access);

	// Fetch
	public List<ResourceAccessForeignKeys> getAccessListByUser(User user);
	public List<ResourceAccessForeignKeys> getAccessListByUserRole(UserRole userRole);
	public List<ResourceAccessForeignKeys> getAccessListByOrganization(Organization organization);
	public List<ResourceAccessForeignKeys> getAccessListByOrganizationType(OrganizationType organizationType);
}
