package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;

public interface OrganizationDao {
	
	// organization
	
	public Organization getOrganizationById(Integer organizationId);
	
	public List<Organization> getOrganizationList();
	
	public void updateOrganization(Organization organization);
	
	public void insertOrganization(Organization organization);
	

	// organization type
	
	public OrganizationType getOrganizationTypeById(Integer organizationTypeId);
	
	public OrganizationType getOrganizationTypeByKey(String organizationTypeKey);
	
	
	// organization name
	
	public void saveOrganizationName(OrganizationName organizationName);
	
	public void insertOrganizationName(OrganizationName organizationName);
	
	public void updateOrganizationName(OrganizationName organizationName);
	
	public void deleteOrganizationName(OrganizationName organizationName);
	
	public List<OrganizationName> getOrganizationNameListByOrganizationId(Integer organizationId);
}