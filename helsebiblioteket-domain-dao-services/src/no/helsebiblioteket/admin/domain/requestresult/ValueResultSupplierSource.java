package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.SupplierSource;

@SuppressWarnings("serial")
public class ValueResultSupplierSource extends SingleResultSupplierSource implements Serializable {
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
