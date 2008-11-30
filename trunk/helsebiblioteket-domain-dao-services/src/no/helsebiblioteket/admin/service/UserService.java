package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;

public interface UserService extends Serializable {
	public void createUser(User user);
	public User findUserByUsername(User user);
	public PageResult<User> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<User> request);
	public PageResult<User> getAllUsers(PageRequest<User> request);
	public List<Role> getAllUserRoles();
	public List<Position> getAllUserPositions();
}
