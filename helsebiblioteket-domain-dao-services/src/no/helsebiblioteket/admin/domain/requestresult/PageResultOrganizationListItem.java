package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public class PageResultOrganizationListItem {
	private OrganizationListItem[] result;
	private Integer total;
	private Integer skipped;
	public OrganizationListItem[] getResult() {
		return result;
	}
	public void setResult(OrganizationListItem[] result) {
		this.result = result;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSkipped() {
		return skipped;
	}
	public void setSkipped(Integer skipped) {
		this.skipped = skipped;
	}
}
