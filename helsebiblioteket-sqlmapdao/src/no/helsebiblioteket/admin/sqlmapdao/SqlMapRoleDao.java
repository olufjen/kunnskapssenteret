package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;


public class SqlMapRoleDao extends SqlMapClientDaoSupport implements RoleDao {
	// TODO: Go through all!
	public void insertRole(UserRole role){
		
	}
	public void updateRole(UserRole role){
		
	}
	public void deleteRole(UserRole role){
		
	}
	public UserRole getRoleByKey(UserRoleKey key){
		return null;
	}
	public List<UserRole> getRoleListAll(){
		return null;
	}

	
	
	
	public List<UserRole> getUserRoleListByUserId(Integer userId) {
		return (List<UserRole>) getSqlMapClientTemplate().queryForObject("getUserRoleListByUserId", userId);
	}
	
	public void insertUserRoleLine(UserRoleLine userRoleLine) {
		getSqlMapClientTemplate().insert("insertUserRoleLine", userRoleLine);	
	}
	
	public void deleteUserRoleLine(UserRoleLine userRoleLine) {
		getSqlMapClientTemplate().delete("deleteUserRoleLine", userRoleLine);		
	}

	public List<UserRole> getAllRoles() {
		return (List<UserRole>) getSqlMapClientTemplate().queryForList("getUserRoleList");
	}

	public UserRole getUserRoleById(Integer userRoleId) {
		return (UserRole) getSqlMapClientTemplate().queryForObject("getUserRoleById", userRoleId);
	}
}