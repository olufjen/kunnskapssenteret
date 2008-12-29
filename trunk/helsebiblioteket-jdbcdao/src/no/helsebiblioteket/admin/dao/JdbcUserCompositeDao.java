package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcUserCompositeDao extends SimpleJdbcDaoSupport implements UserCompositeDao {
    protected final Log logger = LogFactory.getLog(getClass());
	public User getUserByUsername(User user) {
        logger.info("finding user with username '" + ((user != null) ? user.getUsername() : "") + "'");
        List<User> users = getSimpleJdbcTemplate().query(
        		"select tbl_user.user_id, username, tbl_user.org_unit_id, password, tbl_person.person_id, first_name, last_name, " +
                "tbl_user_role.user_role_id, tbl_user_role_reg.name as role_name, tbl_user_role_reg.key, tbl_org_unit.name_no as org_name," +
                "tbl_profile.receive_newsletter, tbl_profile.participate_survey, tbl_profile.profile_id " +
                "from tbl_user " +
                "left outer join tbl_person on tbl_user.person_id = tbl_person.person_id " +
                "left outer join tbl_user_role on tbl_user.user_id = tbl_user_role.user_id " +
                "left outer join tbl_user_role_reg on tbl_user_role.user_role_id = tbl_user_role_reg.user_role_id " +
                "left outer join tbl_org_unit on tbl_org_unit.org_unit_id = tbl_user.org_unit_id " +
                "left outer join tbl_profile on tbl_person.profile_id = tbl_profile.profile_id " +
                " where username = :username", 
                new UserPersonRoleOrgMapper(),
                new MapSqlParameterSource().addValue("username", user.getUsername())
        		);
        if(users.size() == 0) { return null; } else { return users.get(0); }
	}
	public List<User> getAllUsers() {
        logger.info("fetching all users");
        List<User> users = getSimpleJdbcTemplate().query(
                "select tbl_user.user_id, username, tbl_user.org_unit_id, password, tbl_person.person_id, first_name, last_name, " +
                "tbl_user_role.user_role_id, tbl_user_role_reg.name as role_name, tbl_user_role_reg.key, tbl_org_unit.name_no as org_name " +
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
        	if(userMap.containsKey(""+user.getId())) { userMap.get(""+user.getId()).setRole(user.getRole()); }
        	else { userMap.put(""+user.getId(), user); uniqueUsers.add(user); }
		}
        return uniqueUsers;
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
            
            int profileId = rs.getInt("profile_id");
            if(profileId != 0){
            	Profile profile = new Profile();
            	profile.setId(profileId);
            	profile.setReceiveNewsletter(rs.getBoolean("receive_newsletter"));
            	profile.setParticipateSurvey(rs.getBoolean("participate_survey"));
            }
            
            ContactInformation contactInformation = new ContactInformation();
            // FIXME: Use real email address!
            contactInformation.setEmail(rs.getString("username"));
            person.setContactInformation(contactInformation);
            
            Role role = new Role();
            role.setKey(rs.getString("key"));
            role.setName(rs.getString("role_name"));
            role.setRoleId(rs.getInt("user_role_id"));
            user.setRole(role);
            return user;
        }
    }
}
