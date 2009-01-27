package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.helsebiblioteket.admin.daoobjects.UserRole;
import no.helsebiblioteket.admin.domain.Role;

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
            role.setRoleId(rs.getInt("user_role_id"));
            role.setName(rs.getString("name"));
            role.setKey(rs.getString("key"));
            return role;
        }
    }

	public void deleteUserRoleLine(UserRole userRoleLine) {
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

	public void insertUserRoleLine(UserRole userRoleLine) {
		// TODO: Need not be implemented!		
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role getRoleByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoleListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		
	}
}
