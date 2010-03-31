package no.helsebiblioteket.admin.domain.export;

import java.util.List;

import no.helsebiblioteket.admin.domain.key.ProxyResultKey;

public class ProxyResult {
	private ProxyResultKey key;
	private String orgName;
	private String orgTypeKey;
	private String orgTypeDescription;

	private List<PeriodResult> periods;
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
	public ProxyResultKey getKey() {
		return key;
	}
	public void setKey(ProxyResultKey key) {
		this.key = key;
	}
	public String getOrgTypeKey() {
		return orgTypeKey;
	}
	public void setOrgTypeKey(String orgTypeKey) {
		this.orgTypeKey = orgTypeKey;
	}
	public String getOrgTypeDescription() {
		return orgTypeDescription;
	}
	public void setOrgTypeDescription(String orgTypeDescription) {
		this.orgTypeDescription = orgTypeDescription;
	}
	@Override
	public String toString() {
		String result = "[" + this.key.getOrgUnitId() + ", " + this.orgName + ", ";
		for (PeriodResult period : this.periods) {
			result += "{" + period.getPeriod();
			result += ", " + period.getCount() + "}";
		}
		return result += "]";
	}
}
