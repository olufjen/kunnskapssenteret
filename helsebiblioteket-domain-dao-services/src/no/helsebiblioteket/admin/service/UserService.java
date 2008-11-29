package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

public interface UserService extends Serializable {
	public void createUser(User user);
	public User findUserByUsername(User user);
	public List<User> findUsersBySearchStringRoles(String searchString, List<Role> roles);
	public List<User> getAllUsers();
	public List<Role> getAllUserRoles();
}
