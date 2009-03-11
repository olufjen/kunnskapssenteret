package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.SupplierOrganization;

public class ValueResultSupplierOrganization extends SingleResultSupplierOrganization{
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
