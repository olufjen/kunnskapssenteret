package no.helsebiblioteket.admin.dao;

/**
 * 
 * @author ltg
 * 
 * Purpose of this class is retrieve and store types of organizations
 */

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public interface OrganizationDao {
	// Edit
	// TODO: Re-factor! One for supplier and one for member. 
	public void insertMemberOrganization(MemberOrganization organization);
	public void updateMemberOrganization(MemberOrganization organization);
	public void deleteMemberOrganization(MemberOrganization organization);
	public void insertSupplierOrganization(SupplierOrganization organization);
	public void updateSupplierOrganization(SupplierOrganization organization);
	public void deleteSupplierOrganization(SupplierOrganization organization);

	// Fetch
	// Parents not in use yet.
//	public Organization getOrganizationByChild(Organization child);
	// TODO: Should not us id? Check where it is used!
	public MemberOrganization getMemberOrganizationById(Integer id);
	public SupplierOrganization getSupplierOrganizationById(Integer id);
}
