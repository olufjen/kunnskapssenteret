package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcUserDao extends SimpleJdbcDaoSupport implements UserDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());


    public User findUserByUsername(User user) {
        logger.info("finding user with username '" + ((user != null) ? user.getUsername() : "") + "'");
        List<User> users = getSimpleJdbcTemplate().query(
                "select user_id, username, password, org_unit_id from tbl_user where username = :username", 
                new UserMapper(),
                new MapSqlParameterSource().addValue("username", user.getUsername())
        		);
        if(users.size() == 0) return null;
        else return users.get(0);
    }
    
	public List<User> getAllUsers() {
        logger.info("fetching all users");
        List<User> users = getSimpleJdbcTemplate().query(
                "select tbl_user.user_id, username, tbl_user.org_unit_id, password, tbl_person.person_id, first_name, last_name, "+
                "tbl_user_role.user_role_id, tbl_user_role_reg.name as role_name, tbl_user_role_reg.key, tbl_org_unit.name as org_name " +
                "from tbl_user " +
                "left outer join tbl_person on tbl_user.person_id = tbl_person.person_id " +
                "left outer join tbl_user_role on tbl_user.user_id = tbl_user_role.user_id " +
                "left outer join tbl_user_role_reg on tbl_user_role.user_role_id = tbl_user_role_reg.user_role_id " +
                "left outer join tbl_org_unit on tbl_org_unit.org_unit_id = tbl_user.org_unit_id ",
                new UserPersonRoleOrgMapper());
        // FIXME: Only include users once!
        Map<String, User> userMap = new HashMap<String, User>();
        List<User> uniqueUsers = new ArrayList<User>();
        for (User user : users) {
        	if(userMap.containsKey(""+user.getId())) { userMap.get(""+user.getId()).getRoleList().addAll(user.getRoleList()); }
        	else { userMap.put(""+user.getId(), user); uniqueUsers.add(user); }
		}
        return uniqueUsers;
    }

    public void createUser(User user) {
        logger.info("Creating user: " + user.getUsername());
        int count = getSimpleJdbcTemplate().update(
            "insert into tbl_user (username, password) values (:username, :password)",
            new MapSqlParameterSource().addValue("username", user.getUsername())
                .addValue("password", user.getPassword()));
        logger.info("Rows affected: " + count);
    }
    
    public List<User> getAllUsers(String name, List<Role> roles) {
		// FIXME: Improve this!
		// Use ILIKE for caseinsensitive LIKE: 'abc' LIKE 'a%'     true
		// Use view and/or index on name := fname || lname, etc
		List<User> all = this.getAllUsers();
		List<User> some = new ArrayList<User>();
		for (User user : all) {
			if(user.getPerson().getName().toLowerCase().contains(name.toLowerCase()) ||
					user.getUsername().toLowerCase().contains(name.toLowerCase())){
				for (Role role : roles) {
					if(user.hasRole(role)) { some.add(user); break; }
				}
			}
		}
		return some;
	}

    private static class UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            Organization organization = new Organization();
            user.setOrganization(organization);
            user.getOrganization().setId((rs.getInt("org_unit_id")));
            return user;
        }
    }
    private static class UserPersonRoleOrgMapper extends UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = super.mapRow(rs, rowNum);
            user.getOrganization().setName(rs.getString("org_name"));
            Person person = new Person();
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setId(rs.getInt("person_id"));
            user.setPerson(person);
            
            Role role = new Role();
            role.setKey(rs.getString("key"));
            role.setRoleName(rs.getString("role_name"));
            role.setRoleId(rs.getInt("user_role_id"));
            List<Role> roleList = new ArrayList<Role>();
            roleList.add(role);
            user.setRoleList(roleList);
            
//             

            return user;
        }
    }
}
