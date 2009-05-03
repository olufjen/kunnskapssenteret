package no.helsebiblioteket.admin.domain;

/**
 * @author Leif Torger Grøndahl, nokc.no
 * Class represents the link between requestor and resource
 * Typical usage: A user, an organization, a role or a type of organization
 * contains lists of objects of type Access. The access object contains the
 * resources available to the requestor
 */

import java.io.Serializable;
import java.util.Date;

public class Access implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private Date validFrom;
	private Date validTo;
	private Date lastChanged;

	// References
	private AccessType accessType;
	private SupplierOrganization providedBy;

	// Helpers
	@Override
	public String toString() {
		return id + ", " + accessType;
	}

	// GET/SET
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
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public AccessType getAccessType() {
		return accessType;
	}
	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}
	public SupplierOrganization getProvidedBy() {
		return providedBy;
	}
	public void setProvidedBy(SupplierOrganization providedBy) {
		this.providedBy = providedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
