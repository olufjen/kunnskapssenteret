package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.UserRoleLine;

public interface RoleDao {
	public List<Role> getAllRoles();
	
	public List<Role> getUserRoleListByUserId(Integer userId);
	
	public void insertUserRoleLine(UserRoleLine userRoleLine);
	
	public void deleteUserRoleLine(UserRoleLine userRoleLine);

	public Role getUserRoleById(Integer userRoleId);
}
