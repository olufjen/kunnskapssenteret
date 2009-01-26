package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.Role;

public interface RoleDao {
	// Edit
	public void insertRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(Role role);

	// Fetch
	public Role getRoleByKey(String key);
	public List<Role> getRoleListAll();
}
