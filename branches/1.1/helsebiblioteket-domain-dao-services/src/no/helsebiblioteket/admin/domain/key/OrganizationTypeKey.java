package no.helsebiblioteket.admin.domain.key;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrganizationTypeKey implements Serializable {
//	content_supplier, health_enterprise, public_administration, teaching, other

	public static final OrganizationTypeKey _content_supplier = new OrganizationTypeKey("content_supplier");
	public static final OrganizationTypeKey _health_enterprise = new OrganizationTypeKey("health_enterprise");
	public static final OrganizationTypeKey _government = new OrganizationTypeKey("government");
	public static final OrganizationTypeKey _teaching = new OrganizationTypeKey("teaching");
	public static final OrganizationTypeKey _other = new OrganizationTypeKey("other");

	public static final OrganizationTypeKey _helsebiblioteket = new OrganizationTypeKey("helsebiblioteket");
	public static final OrganizationTypeKey _primary_care = new OrganizationTypeKey("primary_care");
	public static final OrganizationTypeKey _university = new OrganizationTypeKey("university");

	private String value;
	public OrganizationTypeKey() {	}
	public OrganizationTypeKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
