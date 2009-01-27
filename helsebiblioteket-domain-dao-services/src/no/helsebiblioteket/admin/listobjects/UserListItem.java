package no.helsebiblioteket.admin.listobjects;

import no.helsebiblioteket.admin.domain.Role;

public interface UserListItem {

	public String getName();

	public String getUsername();

	public boolean hasRole(Role role);

}
