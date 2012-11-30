package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.list.UserListItem;

public interface UserListDao {
	public Integer getUserNumberSearchStringRoles(String searchString, List<Role> roles);
	public Integer getUserNumber();
	public List<UserListItem> getUserListByEmail(String email);
	public List<UserListItem> getUserListPaged(int skip, int max);
	public List<UserListItem> getUserListPagedSearchStringRoles(String searchString, List<Role> roles, int skip, int max);
}