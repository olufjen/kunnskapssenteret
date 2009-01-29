package no.helsebiblioteket.admin.service;

/**
 * Services related to giving access and finding what
 * access users and organizations have.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.UserRole;

public interface AccessService extends Serializable {
	// TODO: Not in use yet. Define methods as needed by clients.
	public void insertUserAccess(User user, Access access);
	public void insertUserRoleAccess(UserRole userRole, Access access);
	public void insertOrganizationAccess(Organization organization, Access access);
	public void insertOrganizationTypeAccess(OrganizationType organizationType, Access access);

	public List<ResourceAccess> getAccessListByUser(User user);
	public List<ResourceAccess> getAccessListByOrganization(MemberOrganization organization);
	public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType);

	public List<SupplierSource> getSupplierSourceListAll();
}
