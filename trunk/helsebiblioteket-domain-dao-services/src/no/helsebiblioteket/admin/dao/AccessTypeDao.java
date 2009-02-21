package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;

public interface AccessTypeDao {
	// Edit not implemented.
	
	// Fetch
	public AccessType getAccessTypeByKey(AccessTypeKey key, AccessTypeCategory category);
	public AccessType getAccessTypeById(Integer id);
	public List<AccessType> getAccessTypeListAll();
}
