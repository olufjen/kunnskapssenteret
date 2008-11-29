package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                "select user_id, username, password, tbl_person.person_id, first_name, last_name from tbl_user inner join tbl_person on tbl_user.person_id = tbl_person.person_id", 
                new UserPersonMapper());
        return users;
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
			if(user.getPerson().getName().toLowerCase().contains(name.toLowerCase())){
				some.add(user);
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
    private static class UserPersonMapper extends UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = super.mapRow(rs, rowNum);
            Person person = new Person();
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setId(rs.getInt("person_id"));
            user.setPerson(person);
            return user;
        }
    }
}
