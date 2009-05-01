package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.SupplierSource;

public class ValueResultSupplierSource extends SingleResultSupplierSource {
	private SupplierSource value;
	public ValueResultSupplierSource() { }
	public ValueResultSupplierSource(SupplierSource value){
		this.value = value;
	}
	public SupplierSource getValue() {
		return value;
	}
	public void setValue(SupplierSource value) {
		this.value = value;
	}
}
