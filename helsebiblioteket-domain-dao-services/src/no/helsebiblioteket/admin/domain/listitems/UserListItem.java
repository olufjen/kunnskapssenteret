package no.helsebiblioteket.admin.domain.list;

import no.helsebiblioteket.admin.domain.UserRole;

public interface UserListItem {

	public String getName();

	public String getUsername();

	public boolean hasRole(UserRole role);

}
