package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import java.util.List;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public interface OrganizationTypeDao {
	// Edit
	public void insertOrganizationType(OrganizationType organizationType);
	public void updateOrganizationType(OrganizationType organizationType);
	public void deleteOrganizationType(OrganizationType organizationType);

	// Fetch
	public OrganizationType getOrganizationTypeByKey(OrganizationTypeKey organizationTypeKey);
	public List<OrganizationType> getOrganizationTypeListAll();
}
