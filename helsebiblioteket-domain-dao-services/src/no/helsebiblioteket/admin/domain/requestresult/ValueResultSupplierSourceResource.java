package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;

public class ValueResultSupplierSourceResource extends SingleResultSupplierSourceResource {
	private SupplierSourceResource value;
	public ValueResultSupplierSourceResource() { }
	public ValueResultSupplierSourceResource(SupplierSourceResource value){
		this.value = value;
	}
	public SupplierSourceResource getValue() {
		return value;
	}
	public void setValue(SupplierSourceResource value) {
		this.value = value;
	}
}
