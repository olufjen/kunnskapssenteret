package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public class PageResultOrganizationListItem {
	private OrganizationListItem[] result;
	private Integer number;
	private Integer skipped;
	private Integer total;
	public OrganizationListItem[] getResult() {
		return result;
	}
	public void setResult(OrganizationListItem[] result) {
		this.result = result;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer total) {
		this.number = total;
	}
	public Integer getSkipped() {
		return skipped;
	}
	public void setSkipped(Integer skipped) {
		this.skipped = skipped;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
