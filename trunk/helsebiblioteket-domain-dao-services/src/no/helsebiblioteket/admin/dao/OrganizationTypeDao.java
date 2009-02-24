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
	// Not in use and not implemented
//	public void updateOrganizationType(OrganizationType organizationType);
//	public void deleteOrganizationType(OrganizationType organizationType);

	// Fetch
	public OrganizationType getOrganizationTypeByKey(OrganizationTypeKey organizationTypeKey);
	public OrganizationType getOrganizationTypeById(Integer id);
	public List<OrganizationType> getOrganizationTypeListAll();
}
