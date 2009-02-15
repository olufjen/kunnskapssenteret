package no.helsebiblioteket.admin.dao.keys;

public class OrganizationNameCompositeKey {
	private String orgUnitNameId;
	private String orgUnitId;
	public String getOrgUnitNameId() {
		return orgUnitNameId;
	}
	public void setOrgUnitNameId(String orgUnitNameId) {
		this.orgUnitNameId = orgUnitNameId;
	}
	public String getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
}
