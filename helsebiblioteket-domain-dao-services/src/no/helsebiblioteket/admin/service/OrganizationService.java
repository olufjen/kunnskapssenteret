package no.helsebiblioteket.admin.service;

/**
 * Organization service
 */

import java.io.Serializable;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.PageRequest;

public interface OrganizationService extends Serializable {
	public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY);
	public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey key);

	// TODO: Remove this and use findOrganizationsBySearchStringRoles with empty string?
	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request);
	public PageResultOrganizationListItem findOrganizationsBySearchString(String searchString, PageRequest request);

	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem);

<<<<<<< .mine
	public ListResultOrganizationListItem getOrganizationListByIpAdress(IpAddress ipAddress);

=======
	public ListResult<OrganizationListItem> getOrganizationListByIpAddress(IpAddress ipAddress);
	
>>>>>>> .r546
	public Boolean insertMemberOrganization(MemberOrganization organization);
	public Boolean updateMemberOrganization(MemberOrganization organization);
	public Boolean insertSupplierOrganization(SupplierOrganization organization);
	public Boolean updateSupplierOrganization(SupplierOrganization organization);
}
