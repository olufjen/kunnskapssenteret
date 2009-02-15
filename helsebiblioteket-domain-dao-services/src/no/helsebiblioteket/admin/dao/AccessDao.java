package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;

public interface AccessDao {
	// Edit
	public void insertResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void updateResourceAccessForeignKeys(ResourceAccessForeignKeys access);
	public void deleteResourceAccessForeignKeys(ResourceAccessForeignKeys access);

	// Fetch
	public List<ResourceAccessForeignKeys> getAccessListByUser(User user);
	public List<ResourceAccessForeignKeys> getAccessListByUserRole(Role userRole);
	public List<ResourceAccessForeignKeys> getAccessListByOrganization(MemberOrganization organization);
	public List<ResourceAccessForeignKeys> getAccessListByOrganizationType(OrganizationType organizationType);
}
