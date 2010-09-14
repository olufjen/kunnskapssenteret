package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.requestresult.PageResult;

public class PageResultUserListItem extends PageResult implements Serializable {
	private UserListItem[] result;
	public UserListItem[] getResult() {
		return result;
	}
	public void setResult(UserListItem[] result) {
		this.result = result;
	}
}
