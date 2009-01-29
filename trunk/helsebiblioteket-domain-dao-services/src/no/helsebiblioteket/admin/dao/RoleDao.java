package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public interface RoleDao {
	// Edit not implemented
	// Fetch
	public Role getRoleByKeySystem(UserRoleKey roleKey, System system);
	public List<Role> getRoleListBySystem(System system);
}
