package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.UserRole;

public interface RoleDao {
	// Edit
	public void insertRole(UserRole role);
	public void updateRole(UserRole role);
	public void deleteRole(UserRole role);

	// Fetch
	public UserRole getRoleByKey(String key);
	public List<UserRole> getRoleListAll();
}
