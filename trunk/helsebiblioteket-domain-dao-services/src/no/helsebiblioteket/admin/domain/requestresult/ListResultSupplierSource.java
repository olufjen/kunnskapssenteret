package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.SupplierSource;

public class ListResultSupplierSource {
	private SupplierSource[] list;

	public ListResultSupplierSource() {
	}

	public ListResultSupplierSource(SupplierSource[] list) {
		this.list = list;
	}

	public SupplierSource[] getList() {
		return list;
	}

	public void setList(SupplierSource[] list) {
		this.list = list;
	}
}
