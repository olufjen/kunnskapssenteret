package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.daoobjects.UserRole;
import no.helsebiblioteket.admin.domain.Role;


public class SqlMapRoleDao extends SqlMapClientDaoSupport implements RoleDao {
	// TODO: Go through all!
	public void insertRole(Role role){
		
	}
	public void updateRole(Role role){
		
	}
	public void deleteRole(Role role){
		
	}
	public Role getRoleByKey(String key){
		return null;
	}
	public List<Role> getRoleListAll(){
		return null;
	}

	
	
	
	public List<Role> getUserRoleListByUserId(Integer userId) {
		return (List<Role>) getSqlMapClientTemplate().queryForObject("getUserRoleListByUserId", userId);
	}
	
	public void insertUserRoleLine(UserRole userRoleLine) {
		getSqlMapClientTemplate().insert("insertUserRoleLine", userRoleLine);	
	}
	
	public void deleteUserRoleLine(UserRole userRoleLine) {
		getSqlMapClientTemplate().delete("deleteUserRoleLine", userRoleLine);		
	}

	public List<Role> getAllRoles() {
		return (List<Role>) getSqlMapClientTemplate().queryForList("getUserRoleList");
	}

	public Role getUserRoleById(Integer userRoleId) {
		return (Role) getSqlMapClientTemplate().queryForObject("getUserRoleById", userRoleId);
	}
}