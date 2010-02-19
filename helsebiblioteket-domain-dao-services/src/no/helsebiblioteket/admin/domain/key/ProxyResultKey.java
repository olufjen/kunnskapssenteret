package no.helsebiblioteket.admin.domain.key;

public class ProxyResultKey {
	private Integer orgUnitId;
	private boolean isMultiple;
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public boolean isMultiple() {
		return isMultiple;
	}
	public void setMultiple(boolean isMultiple) {
		this.isMultiple = isMultiple;
	}
}
