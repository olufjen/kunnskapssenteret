package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.User;

public class JdbcUserDao extends SimpleJdbcDaoSupport implements UserDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());


    public User findUserByUsername(User user) {
        logger.info("finding user with username '" + ((user != null) ? user.getUsername() : "") + "'");
        List<User> users = getSimpleJdbcTemplate().query(
                "select id, username, password from hb_user where username = :username", 
                new UserMapper(),
                new MapSqlParameterSource().addValue("username", user.getUsername())
        		);
        // TODO: Create empty user or throw exception 
        if(users.size()==0) return null;
        return users.get(0);
    }
    
    public List<User> getUserList() {
        logger.info("fetching all users");
        List<User> users = getSimpleJdbcTemplate().query(
                "select id, username, password from hb_user", 
                new UserMapper());
        return users;
    }

    public void createUser(User user) {
        logger.info("Creating user: " + user.getUsername());
        int count = getSimpleJdbcTemplate().update(
            "insert into hb_user (username, password) values (:username, :password)",
            new MapSqlParameterSource().addValue("username", user.getUsername())
                .addValue("password", user.getPassword()));
        logger.info("Rows affected: " + count);
    }
    
    private static class UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}