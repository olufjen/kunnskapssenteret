package no.helsebiblioteket.admin.domain.export;

import java.util.List;

public class ProxyResult {
	private Integer orgUnitId;
	private String orgName;
	private List<PeriodResult> periods;
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<PeriodResult> getPeriods() {
		return periods;
	}
	public void setPeriods(List<PeriodResult> periods) {
		this.periods = periods;
	}
	@Override
	public String toString() {
		String result = "[" + this.orgUnitId + ", " + this.orgName + ", ";
		for (PeriodResult period : this.periods) {
			result += "{" + period.getPeriod();
			result += ", " + period.getCount() + "}";
		}
		return result += "]";
	}
}
