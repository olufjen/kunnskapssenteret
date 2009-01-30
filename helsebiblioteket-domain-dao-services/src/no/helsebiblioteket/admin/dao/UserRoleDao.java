package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;

public interface UserRoleDao {
	// Edit
	public void insertUserRole(UserRoleLine userRole);
	public void updateUserRole(UserRoleLine userRole);
	public void deleteUserRole(UserRoleLine userRole);

	// Fetch
	public List<UserRoleLine> getUserRoleListByUser(User user);
}
