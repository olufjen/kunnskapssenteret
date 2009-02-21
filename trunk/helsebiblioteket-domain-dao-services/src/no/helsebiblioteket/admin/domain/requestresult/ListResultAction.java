package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Action;

public class ListResultAction {
	private Action[] list;

	public ListResultAction() {
	}

	public ListResultAction(Action[] list) {
		this.list = list;
	}

	public Action[] getList() {
		return list;
	}

	public void setList(Action[] list) {
		this.list = list;
	}
}
