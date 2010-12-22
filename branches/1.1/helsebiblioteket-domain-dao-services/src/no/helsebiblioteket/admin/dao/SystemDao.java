package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.SystemKey;

public interface SystemDao {
	// Edit not implemented
	// Fetch
	public System getSystemByKey(SystemKey systemKey);
	
	// List not implemented
	// public List<System> getSystemListAll();
}
