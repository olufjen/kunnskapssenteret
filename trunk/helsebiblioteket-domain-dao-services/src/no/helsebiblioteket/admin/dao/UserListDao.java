package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.UserListItem;

public interface UserListDao {
	public List<UserListItem> getUserListPaged(int from, int max);
	public List<UserListItem> getUserListPagedSearchStringRoles(String searchString, List<Role> roles, int from, int max);

	// TODO: Insert into DAOImpl
//	for (UserListItem user : all.result) {
//		if(user.getName().toLowerCase().contains(searchString.toLowerCase()) ||
//				user.getUsername().toLowerCase().contains(searchString.toLowerCase())){
//			for (Role role : roles) {
//				if(user.hasRole(role)) { some.add(user); break; }
//			}
//		}
//	}
}
