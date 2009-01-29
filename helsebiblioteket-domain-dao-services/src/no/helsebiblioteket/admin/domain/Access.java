package no.helsebiblioteket.admin.domain;

/**
 * @author Leif Torger Grøndahl, nokc.no
 * Class represents the link between requestor and resource
 * Typical usage: A user, an organization, a role or a type of organization
 * contains lists of objects of type Access. The access object contains the
 * resources available to the requestor
 */

import java.util.Date;

import no.helsebiblioteket.admin.domain.base.Identifiable;

public abstract class Access implements Identifiable{
	// Primary key
	private Integer accessId;
	
	// Local values
	private Date validFrom;
	private Date validTo;
	private Date lastChanged;

	// References
	private AccessType accessType;
	private Organization providedBy;

	// Helpers
	public Integer getId() {
		return getAccessId();
	}

	// GET/SET
	public Integer getAccessId() {
		return accessId;
	}
	public void setAccessId(Integer accessId) {
		this.accessId = accessId;
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
	public Organization getProvidedBy() {
		return providedBy;
	}
	public void setProvidedBy(Organization providedBy) {
		this.providedBy = providedBy;
	}
}
