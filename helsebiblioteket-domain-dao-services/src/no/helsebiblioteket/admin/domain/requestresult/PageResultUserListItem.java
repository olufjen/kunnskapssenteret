package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.UserListItem;

public class PageResultUserListItem {
	private UserListItem[] result;
	private Integer total;
	private Integer skipped;
	public UserListItem[] getResult() {
		return result;
	}
	public void setResult(UserListItem[] result) {
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
