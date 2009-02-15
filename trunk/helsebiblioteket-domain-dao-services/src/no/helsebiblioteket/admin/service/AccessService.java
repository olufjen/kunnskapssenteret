package no.helsebiblioteket.admin.service;

/**
 * Services related to giving access and finding what
 * access users and organizations have.
 */

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultAccessType;

public interface AccessService extends Serializable {
	// TODO: Not in use yet. Define methods as needed by clients.
	public Boolean insertUserAccess(User user, Access access);
	public Boolean insertUserRoleAccess(Role userRole, Access access);
	public Boolean insertOrganizationAccess(MemberOrganization organization, Access access);
	public Boolean insertOrganizationTypeAccess(OrganizationType organizationType, Access access);

	public SingleResultAccessType getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory);

	public ListResultResourceAccess getAccessListByUser(User user);
	public ListResultResourceAccess getAccessListByRole(Role role);
	public ListResultResourceAccess getAccessListByOrganization(MemberOrganization organization);
	public ListResultResourceAccess getAccessListByOrganizationType(OrganizationType organizationType);

	public ListResultSupplierSource getSupplierSourceListAll(String DUMMY);
}
