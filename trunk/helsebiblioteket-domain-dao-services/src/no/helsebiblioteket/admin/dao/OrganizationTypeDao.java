package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import java.util.List;
import no.helsebiblioteket.admin.domain.OrganizationType;

public interface OrganizationTypeDao {
	// Edit
	public void insertOrganizationTypeDao(OrganizationTypeDao organizationTypeDao);
	public void updateOrganizationTypeDao(OrganizationTypeDao organizationTypeDao);
	public void deleteOrganizationTypeDao(OrganizationTypeDao organizationTypeDao);

	// Fetch
	public OrganizationType getOrganizationTypeByKey(String organizationTypeKey);
	public List<OrganizationType> getOrganizationTypeListAll();
}
