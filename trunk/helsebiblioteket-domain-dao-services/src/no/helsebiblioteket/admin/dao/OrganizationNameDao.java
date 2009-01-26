package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import java.util.List;

import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.domain.Organization;

public interface OrganizationNameDao {
	// Edit
	public void insertOrganizationName(OrganizationName organizationName);
	public void updateOrganizationName(OrganizationName organizationName);
	public void deleteOrganizationName(OrganizationName organizationName);

	// Fetch
	public List<OrganizationName> getOrganizationNameListByOrganization(Organization organization);
}
