package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;

public interface OrganizationService extends Serializable {
    public List<OrganizationType> getOrganizationTypeList();

    public List<Organization> getAllOrganizations();
	public PageResult<Organization> findOrganizationsBySearchStringRoles(String searchString, PageRequest<Organization> request);
	public Organization getOrganization(Integer organizationId);
	public MemberOrganization getMemberOrganization(Integer organizationId);
	public List<SupplierOrganization> getSupplierList();
	
	public void createOrganization(Organization organization);
	public void saveOrganization(Organization organization);
	
	public PositionList getAllPositions(String dummy);
}
