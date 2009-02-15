package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcRoleDao extends SimpleJdbcDaoSupport implements RoleDao{
    
	// TODO: Remove class

	
	protected final Log logger = LogFactory.getLog(getClass());
	public List<Role> getAllRoles() {
        logger.info("fetching all roles");
        List<Role> roles = getSimpleJdbcTemplate().query(
                "select name, descr, user_role_id, key from tbl_user_role_reg", 
                new RoleMapper());
        return roles;
	}

    private static class RoleMapper implements ParameterizedRowMapper<Role> {
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Role role = new Role();
            role.setUserRoleId(rs.getInt("user_role_id"));
            role.setName(rs.getString("name"));
//            role.setKey(rs.getString("key"));
            return role;
        }
    }

	public void deleteUserRoleLine(UserRoleLine userRoleLine) {
		// TODO: Need not be implemented!
		
	}

	public Role getUserRoleById(Integer userRoleId) {
		// FIXME: Should not use ID! Key is unique.
		return null;
	}

	public List<Role> getUserRoleListByUserId(Integer userId) {
		// FIXME: Should not use ID! Key is unique.
		return null;
	}

	public void insertUserRoleLine(UserRoleLine userRoleLine) {
		// TODO: Need not be implemented!		
	}


	@Override
	public Role getRoleByKeySystem(UserRoleKey key, System system) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoleListBySystem(System system) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
