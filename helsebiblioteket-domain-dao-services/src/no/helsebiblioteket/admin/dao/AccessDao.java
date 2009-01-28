package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.User;

public interface AccessDao {
	// Edit
	public void insertAccess(ResourceAccess access);
	public void updateAccess(ResourceAccess access);
	public void deleteAccess(ResourceAccess access);

	// Fetch
	// TODO: Change all names with get to something else. Like load or find? 
	public List<ResourceAccess> getAccessListByUser(User user);
	public List<ResourceAccess> getAccessListByOrganization(Organization organization);
	public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType);
}
