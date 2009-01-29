package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface UserService extends Serializable {
	public System getSystemByKey(SystemKey key);
	public ListResult<Role> getRoleListBySystem(System system);
	public ListResult<Position> getPositionListAll(String DUMMY);
	public SingleResult<Role> getRoleByKeySystem(UserRoleKey key, System system);

	// TODO: Remove this and use findUsersBySearchStringRoles with empty string and list?
	public PageResult<UserListItem> getUserListAll(PageRequest<UserListItem> request);
	public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<UserListItem> request);

	public SingleResult<User> findUserByUsername(String username);

	public Boolean insertUser(User user);
	public Boolean updateUser(User user);
}
