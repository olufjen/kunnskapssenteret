package no.helsebiblioteket.admin.domain;

/**
 * @author Leif Torger Grøndahl, nokc.no
 * Class represents the link between requestor and resource
 * Typical usage: A user, an organization, a role or a type of organization
 * contains lists of objects of type Access. The access object contains the
 * resources available to the requestor
 */

import java.util.Date;

public class Access {
	private Integer id;
	private Date validFrom;
	private Date validTo;
	private Date lastChanged;
	
	private SupplierSource supplierSource;
	private Organization organizationAsResource;
	private AccessType type;
	private Organization providedBy;
	
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
	public AccessType getType() {
		return type;
	}
	public void setType(AccessType accessType) {
		this.type = accessType;
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
	public Organization getOrganizationAsResource() {
		return organizationAsResource;
	}
	public void setOrganizationAsResource(Organization organizationAsResource) {
		this.organizationAsResource = organizationAsResource;
	}
	
}
