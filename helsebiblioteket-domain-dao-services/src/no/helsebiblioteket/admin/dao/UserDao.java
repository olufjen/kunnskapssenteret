package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.OrganizationUser;

public interface UserDao {
	// Edit
	public void insertUser(OrganizationUser organizationUser);
	public void updateUser(OrganizationUser organizationUser);
	public void deleteUser(OrganizationUser organizationUser);

	// Fetch
	public OrganizationUser getUserByUsername(String username);
	public OrganizationUser getDeletedUserByUsername(String username);
	public OrganizationUser getUserById(Integer id);
	public List<OrganizationUser> getAdminUserByOrganizationId(Integer id);
}
