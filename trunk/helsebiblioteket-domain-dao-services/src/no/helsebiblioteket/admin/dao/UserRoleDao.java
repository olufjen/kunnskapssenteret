package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.daoobjects.UserRole;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

public interface UserRoleDao {
	// Edit
	public void insertUserRole(UserRole userRole);
	public void updateUserRole(UserRole userRole);
	public void deleteUserRole(UserRole userRole);

	// Fetch
	public List<UserRole> getUserRoleListByUser(User user);
}
