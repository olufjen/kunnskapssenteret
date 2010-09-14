package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;

public class SupplierSourceFactory {
	public static SupplierSourceFactory factory = new SupplierSourceFactory();
	private SupplierSourceFactory(){}
	public SupplierSource createSupplierSource(){
		SupplierSource supplierSource = new SupplierSource();
		supplierSource.setLastChanged(new Date());
		supplierSource.setSupplierSourceName("");
		Url url = new Url();
		url.setStringValue("");
		supplierSource.setUrl(url);
		return supplierSource;
	}
	public SupplierSource completeSupplierSource(){
		return createSupplierSource();
	}
}
