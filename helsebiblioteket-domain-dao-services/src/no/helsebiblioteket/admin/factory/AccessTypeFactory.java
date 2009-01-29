package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public class AccessTypeFactory {
	public static AccessTypeFactory factory = new AccessTypeFactory();
	private AccessTypeFactory(){}
	public AccessType createAccessType(){
		AccessType accessType = new AccessType();
		accessType.setCategory(AccessTypeCategory.GRANT);
		accessType.setDescription("");
		accessType.setName("");
		return accessType;
	}
	public AccessType completeAccessType(AccessTypeKey key){
		AccessType accessType = createAccessType();
		accessType.setKey(key);
		return accessType;
	}
}
