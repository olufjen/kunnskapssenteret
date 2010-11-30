package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;

@SuppressWarnings("serial")
public class ValueResultSupplierSourceResource extends SingleResultSupplierSourceResource implements Serializable {
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