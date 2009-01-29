package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public interface RoleDao {
	// Edit
	public void insertRole(UserRole role);
	public void updateRole(UserRole role);
	public void deleteRole(UserRole role);

	// Fetch
	public UserRole getRoleByKey(UserRoleKey key);
	public List<UserRole> getRoleListAll();
}
