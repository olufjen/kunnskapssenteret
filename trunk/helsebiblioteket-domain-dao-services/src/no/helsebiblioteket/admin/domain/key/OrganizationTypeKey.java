package no.helsebiblioteket.admin.domain.key;

public class OrganizationTypeKey {
//	content_supplier, health_enterprise, public_administration, teaching, other

	public static final OrganizationTypeKey content_supplier = new OrganizationTypeKey("content_supplier");
	public static final OrganizationTypeKey health_enterprise = new OrganizationTypeKey("health_enterprise");
	public static final OrganizationTypeKey public_administration = new OrganizationTypeKey("public_administration");
	public static final OrganizationTypeKey teaching = new OrganizationTypeKey("teaching");
	public static final OrganizationTypeKey other = new OrganizationTypeKey("other");

	private String value;
	public OrganizationTypeKey() {	}
	public OrganizationTypeKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
