package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.User;
public interface AccessDao {
	// Edit
	public void insertAccess(Access access);
	public void updateAccess(Access access);
	public void deleteAccess(Access access);

	// Fetch
	// TODO: Change all names with get to something else. Like load or find 
	public List<Access> getAccessListByUser(User user);
	public List<Access> getAccessListByOrganization(Organization organization);
	public List<Access> getAccessListByOrganizationType(OrganizationType organizationType);
}
