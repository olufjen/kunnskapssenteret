package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Reference;
import no.helsebiblioteket.admin.domain.ValueReference;
import no.helsebiblioteket.admin.listobjects.OrganizationListItem;

public interface OrganizationDao {
	// Edit
	public void insertOrganization(Organization organization);
	public void updateOrganization(Organization organization);
	public void deleteOrganization(Organization organization);

	// Fetch
	public Organization getOrganizationByChild(Organization child);
	public Organization getOrganizationByListItem(OrganizationListItem organizationListItem);
	
	// TODO: Should not us id. Check where it is used!
	public Organization getOrganizationById(Integer id);
}
