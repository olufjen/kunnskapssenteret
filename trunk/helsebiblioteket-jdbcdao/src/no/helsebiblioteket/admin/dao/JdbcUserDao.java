package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcUserDao extends SimpleJdbcDaoSupport {
    protected final Log logger = LogFactory.getLog(getClass());
	public User getUserByUsername(User user) {
        logger.info("finding user with username '" + ((user != null) ? user.getUsername() : "") + "'");
        List<User> users = getSimpleJdbcTemplate().query(
        		"select user_id, username, org_unit_id, password from tbl_user where username = :username", 
                new UserMapper(),
                new MapSqlParameterSource().addValue("username", user.getUsername())
        		);
        if(users.size() == 0) { return null; } else { return users.get(0); }
	}
	public void insertUser(User user) {
		// FIXME: Also include person_id!
		String sql = "insert into tbl_user (username, org_unit_id, password) values (" +
				":username, :org_unit_id, :password)";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("username", user.getUsername());
		sqlParameters.addValue("password", user.getPassword());
		sqlParameters.addValue("org_unit_id", user.getOrganization().getId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
	public void updateUser(User user) {
		String sql = "update tbl_user set username=:username, org_unit_id=:org_unit_id, password=:password " +
			"where user_id=:user_id";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("user_id", user.getId());
		sqlParameters.addValue("username", user.getUsername());
		sqlParameters.addValue("password", user.getPassword());
		sqlParameters.addValue("org_unit_id", user.getOrganization().getId());
		
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
    private static class UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            if(user.getUsername() == null) { user.setUsername(""); }
            user.setPassword(rs.getString("password"));
            if(user.getPassword() == null) { user.setPassword(""); }
            Organization organization = new Organization();
            user.setOrganization(organization);
            user.getOrganization().setId((rs.getInt("org_unit_id")));
            return user;
        }
    }
    
    public User findUser(String username) {
        User user = null;
        List<User> users = getSimpleJdbcTemplate().query(
                "select user_id, username, password, org_unit_id from tbl_user where username = :username", 
                new UserMapper(),
                new MapSqlParameterSource().addValue("username", username)
        		);
        if(users.size() == 0) return user;
        else{
        	// FIXME: Alt slettes her? Hvorfor? Får aldri logget inn.
        	// Må ihvertfall ha passordet!
        	String password = users.get(0).getPassword();
        	user = new User();
        	user.setUsername(username);
        	user.setPassword(password);
        	
     
        	Role role = new Role();
        	List<Role> roleList = new ArrayList<Role>();
            
        	role.setRoleId(1);
        	role.setName("ROLE_ALLACCESS");
        	roleList.add(role); 
        	user.setRoleList(roleList) ; 
        	return user;
        }
        
    }
    
}
