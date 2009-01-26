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
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface UserService extends Serializable {
	public ListResult<Role> getRoleListAll(String DUMMY);
	public ListResult<Position> getPositionListAll(String DUMMY);
	public SingleResult<Role> getRoleByKey(String key);

	// TODO: Remove this and use findUsersBySearchStringRoles with empty string and list?
	public PageResult<User> getUserListAll(PageRequest<User> request);
	// TODO: Use UserListItem as the result here.
	public PageResult<User> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<User> request);

	public SingleResult<User> findUserByUsername(String username);

	public Boolean insertUser(User user);
	public Boolean updateUser(User user);
}
