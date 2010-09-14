package no.helsebiblioteket.admin.factory;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;

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
