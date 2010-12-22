package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.SupplierSource;

@SuppressWarnings("serial")
public class ListResultSupplierSource implements Serializable {
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
