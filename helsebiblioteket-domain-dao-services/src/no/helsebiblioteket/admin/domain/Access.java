package no.helsebiblioteket.admin.domain;

/**
 * @author Leif Torger Grøndahl, nokc.no
 * Class represents the link between requestor and resource
 * Typical usage: A user, an organization, a role or a type of organization
 * contains lists of objects of type Access. The access object contains the
 * resources available to the requestor/callee.
 */

import java.util.Date;

public class Access {
	private Integer id = null;
	private SupplierSource supplierSource = null;
	private Organization organization = null;
	private AccessType accessType = null;
	private Organization providedBy = null;
	private Date validFrom = null;
	private Date validTo = null;
	private Date lastChanged = null;
	
	// foreign keys
	private Integer accessTypeId;
	private Integer userId;
	private Integer userRoleId;
	private Integer orgTypeId;
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SupplierSource getSupplierSource() {
		return supplierSource;
	}
	public void setSupplierSource(SupplierSource supplierSource) {
		this.supplierSource = supplierSource;
	}
	public AccessType getAccessType() {
		return accessType;
	}
	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public Organization getProvidedBy() {
		return providedBy;
	}
	public void setProvidedBy(Organization providedBy) {
		this.providedBy = providedBy;
	}
	
	// foreign keys
	public Integer getAccessTypeId() {
		return accessTypeId;
	}
	public void setAccessTypeId(Integer accessTypeId) {
		this.accessTypeId = accessTypeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Integer getOrgTypeId() {
		return orgTypeId;
	}
	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
}