package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Action;

@SuppressWarnings("serial")
public class ListResultAction implements Serializable {
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
