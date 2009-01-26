package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;

public interface OrganizationService extends Serializable {
	public OrganizationType getOrganizationTypeByKey(String key);
	
	public void saveOrganization(Organization organization);
	
	public List<Organization> getOrganizationList();
	
	public void saveOrganization(Organization changedOrganization, Organization originalOrganization);
	
	public Organization getOrganizationById(Integer organizationId);
	
	public List<OrganizationType> getOrganizationTypeList();
	
	public PageResult<Organization> findOrganizationsBySearchStringRoles( String searchString, PageRequest<Organization> request);
	
	public PositionList getAllPositions(String dummy);
}
