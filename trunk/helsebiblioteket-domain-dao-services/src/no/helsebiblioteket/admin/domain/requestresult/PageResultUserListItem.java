package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.requestresult.PageResult;

public class PageResultUserListItem extends PageResult {
	private UserListItem[] result;
	public UserListItem[] getResult() {
		return result;
	}
	public void setResult(UserListItem[] result) {
		this.result = result;
	}
}
