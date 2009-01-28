package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface UserService extends Serializable {
	public ListResult<UserRole> getRoleListAll(String DUMMY);
	public ListResult<Position> getPositionListAll(String DUMMY);
	public SingleResult<UserRole> getRoleByKey(String key);

	// TODO: Remove this and use findUsersBySearchStringRoles with empty string and list?
	// TODO: Or use UserListItem as the result here.
	public PageResult<UserListItem> getUserListAll(PageRequest<UserListItem> request);
	// TODO: Use UserListItem as the result here.
	public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<UserRole> roles, PageRequest<UserListItem> request);

	public SingleResult<User> findUserByUsername(String username);

	public Boolean insertUser(User user);
	public Boolean updateUser(User user);
}
