package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import no.helsebiblioteket.admin.domain.Organization;

public interface OrganizationDao {
	// Edit
	public void insertOrganization(Organization organization);
	public void updateOrganization(Organization organization);
	public void deleteOrganization(Organization organization);

	// Fetch
	// Parents not in use yet.
	public Organization getOrganizationById(Integer id);
	public Organization getOrganizationByAdminUserId(Integer id);
}