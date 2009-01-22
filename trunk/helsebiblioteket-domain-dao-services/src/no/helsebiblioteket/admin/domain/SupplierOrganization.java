package no.helsebiblioteket.admin.domain;

import java.util.List;

public class SupplierOrganization extends NamedOrganization {
	private List<SupplierSource> sourceList = null;
	
	public SupplierOrganization() {
	}

	public List<SupplierSource> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<SupplierSource> sourceList) {
		this.sourceList = sourceList;
	}
}