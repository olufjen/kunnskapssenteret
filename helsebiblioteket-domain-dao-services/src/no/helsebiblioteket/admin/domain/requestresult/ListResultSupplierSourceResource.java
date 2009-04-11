package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;

public class ListResultSupplierSourceResource {
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
