package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.listobjects.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface OrganizationService extends Serializable {
	public ListResult<OrganizationType> getOrganizationTypeListAll(String DUMMY);
	public SingleResult<OrganizationType> getOrganizationTypeByKey(String key);

	// TODO: Remove this and use findOrganizationsBySearchStringRoles with empty string?
	// TODO: Or use OrganizationListItem as the result here.
	public PageResult<Organization> getOrganizationListAll(PageRequest<Organization> request);
	// TODO: Use OrganizationListItem as the result here.
	public PageResult<Organization> findOrganizationsBySearchString(String searchString, PageRequest<Organization> request);

	public SingleResult<Organization> getOrganizationByListItem(OrganizationListItem organizationListItem);
	
	public Boolean insertOrganization(Organization organization);
	public Boolean updateOrganization(Organization organization);
}
