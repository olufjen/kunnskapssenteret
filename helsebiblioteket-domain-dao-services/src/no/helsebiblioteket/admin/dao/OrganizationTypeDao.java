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
	public List<OrganizationType> getOrganizationTypeList();
	public OrganizationType getOrganizationType(String organizationTypeKey);
}
