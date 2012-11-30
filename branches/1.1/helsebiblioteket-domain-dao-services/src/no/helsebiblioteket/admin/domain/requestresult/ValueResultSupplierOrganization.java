package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.SupplierOrganization;

@SuppressWarnings("serial")
public class ValueResultSupplierOrganization extends SingleResultSupplierOrganization implements Serializable {
	private SupplierOrganization value;
	public ValueResultSupplierOrganization() { }
	public ValueResultSupplierOrganization(SupplierOrganization value){
		this.value = value;
	}
	public SupplierOrganization getValue() {
		return value;
	}
	public void setValue(SupplierOrganization value) {
		this.value = value;
	}
}