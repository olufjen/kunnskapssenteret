package no.helsebiblioteket.admin.domain.key;

public class ProxyResultKey {
	private Integer orgUnitId;
	private boolean isMultiple;
	private String orgTypeKey;
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
	public String getOrgTypeKey() {
		return orgTypeKey;
	}
	public void setOrgTypeKey(String orgTypeKey) {
		this.orgTypeKey = orgTypeKey;
	}
}
