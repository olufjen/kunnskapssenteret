package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;

@SuppressWarnings("serial")
public class ListResultSupplierSourceResource implements Serializable {
	private SupplierSourceResource[] list;

	public ListResultSupplierSourceResource() {
	}

	public ListResultSupplierSourceResource(SupplierSourceResource[] list) {
		this.list = list;
	}

	public SupplierSourceResource[] getList() {
		return list;
	}

	public void setList(SupplierSourceResource[] list) {
		this.list = list;
	}
}
