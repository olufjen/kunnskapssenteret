package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public interface OrganizationDao {
	public List<Organization> getAllOrganizations();
	public Organization getOrganization(Integer organizationId);
	public List<SupplierOrganization> getSupplierList();
	public void saveOrganization(Organization organization);
	public OrganizationType getOrganizationTypeById(Integer organizationTypeId);
	public OrganizationType getOrganizationTypeByKey(String organizationTypeKey);
}