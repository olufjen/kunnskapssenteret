package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.list.UserListItem;

public interface UserListDao {
	public List<UserListItem> getUserListPaged(int skip, int max);
	public List<UserListItem> getUserListPagedSearchStringRoles(String searchString, List<Role> roles, int skip, int max);
}
