package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;

public class SupplierSourceFactory {
	public static SupplierSourceFactory factory = new SupplierSourceFactory();
	private SupplierSourceFactory(){}
	public SupplierSource createSupplierSource(){
		SupplierSource supplierSource = new SupplierSource();
		supplierSource.setLastChanged(new Date());
		supplierSource.setSupplierSourceName("");
		supplierSource.setUrl(new Url(""));
		return supplierSource;
	}
	public SupplierSource completeSupplierSource(){
		return createSupplierSource();
	}
}
