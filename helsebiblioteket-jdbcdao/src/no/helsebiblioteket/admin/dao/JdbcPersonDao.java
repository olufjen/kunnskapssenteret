package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcPersonDao extends SimpleJdbcDaoSupport implements PersonDao {
  
	// TODO: Remove class

	protected final Log logger = LogFactory.getLog(getClass());
	public int insertContactInformation(ContactInformation contactInformation) {
		String sql ="insert into tbl_contact_information (postal_address, postal_code, " +
		"telephone_number, postal_location, org_unit_id, " +
		"person_id, email) values (:email)";

		sql = "insert into tbl_contact_information (person_id, email) values (:person_id, :email)";

		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
//		sqlParameters.addValue("person_id", contactInformation.getPerson().getId());
		sqlParameters.addValue("email", contactInformation.getEmail());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
		return getSimpleJdbcTemplate().queryForInt("select currval('tbl_contact_information_contact_information_id_seq');");
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(sql);
//		psc.addParameter(new SqlParameter("email", Types.VARCHAR));
//		psc.addParameter(new SqlParameter("person_id", Types.INTEGER));
//		getJdbcTemplate().update(psc.newPreparedStatementCreator(new Object[]{
//				contactInformation.getEmail(),
//				contactInformation.getPerson().getId()}), keyHolder);
//		return keyHolder.getKey().intValue();
	}
	public void insertProfile(Profile profile) {
		// FIXME: Translate into bit!
		String sql ="insert into tbl_profile (receive_newsletter, participate_survey) values (" +
			"'" + (profile.getReceiveNewsletter() ? "1" : "0") + "', " +
			"'" + (profile.getParticipateSurvey() ? "1" : "0") + "')";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
//		sqlParameters.addValue("person_id", profile.getPerson().getId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
	public Person insertPerson(Person person) {
		String sql = "insert into tbl_person (first_name, last_name, " +
		"student_number, hpr_number "+//, org_unit_id=:org_unit_id, " +
		") values (" +
		":first_name, :last_name, " +
		":student_number, :hpr_number "+//, org_unit_id=:org_unit_id, " +
		")";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("first_name", person.getFirstName());
		sqlParameters.addValue("last_name", person.getLastName());
		sqlParameters.addValue("student_number", person.getStudentNumber());
		sqlParameters.addValue("hpr_number", person.getHprNumber());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
//		return getSimpleJdbcTemplate().queryForInt("select currval('tbl_person_person_id_seq');");
		return null;
		
//		// TODO Auto-generated method stub
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory(sql);
//		psc.addParameter(new SqlParameter("person_id", Types.INTEGER));
//		psc.addParameter(new SqlParameter("first_name", Types.VARCHAR));
//		psc.addParameter(new SqlParameter("last_name", Types.VARCHAR));
//		psc.addParameter(new SqlParameter("student_number", Types.INTEGER));
//		psc.addParameter(new SqlParameter("hpr_number", Types.INTEGER));
//		getJdbcTemplate().update(psc.newPreparedStatementCreator(new Object[]{
//				null,
//				person.getFirstName(),
//				person.getLastName(),
//				person.getStudentNumber(),
//				person.getHprNumber()}), keyHolder);
//		final String INSERT_SQL = "tbl_person (first_name, last_name, student_number, hpr_number) " +
//				"values(?,?,?,?)";
//		final String fName = person.getFirstName();
//		final String lName = person.getLastName();
//		final Integer sNumber = person.getStudentNumber();
//		final Integer hNumber = person.getHprNumber();
//		
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		getJdbcTemplate().update(
//		    new PreparedStatementCreator() {
//		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//		            PreparedStatement ps =
//		                connection.prepareStatement(INSERT_SQL, new String[] {"person_id"});
//		            ps.setString(1, fName);
//		            ps.setString(2, lName);
//		            ps.setInt(3, sNumber);
//		            ps.setInt(4, hNumber);
//		            return ps;
//		        }
//		    },
//		    keyHolder);
//
//		return keyHolder.getKey().intValue();
	}

	public void updateContactInformation(ContactInformation contactInformation) {
		String sql ="update tbl_contact_information set postal_address=:postal_address, postal_code=:postal_code " +
			"telephone_number=:telephone_number, postal_location=:postal_location, org_unit_id=:org_unit_id"+
			"person_id fk=:person_id, email=:email where contact_information_id=:contact_information_id";
		
		sql = "update tbl_contact_information set email=:email where contact_information_id=:contact_information_id";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("email", contactInformation.getEmail());
		sqlParameters.addValue("contact_information_id", contactInformation.getContactInformationId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
	public void updateProfile(Profile profile) {
		// FIXME: Translate into bit!
		String sql ="update tbl_profile set " +
			"receive_newsletter='" + (profile.getReceiveNewsletter() ? "1" : "0") + "', " +
			"participate_survey='" + (profile.getParticipateSurvey() ? "1" : "0") + "' " +
			"where profile_id=:profile_id";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
//		sqlParameters.addValue("receive_newsletter", null);
//		sqlParameters.addValue("participate_survey", null);
		sqlParameters.addValue("profile_id", profile.getProfileId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
	public void updatePerson(Person person) {
		String sql = "update tbl_person set first_name=:first_name, last_name=:last_name, " +
				"student_number=:student_number, hpr_number=:hpr_number "+//, org_unit_id=:org_unit_id, " +
				"where person_id=:person_id";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("person_id", person.getPersonId());
		sqlParameters.addValue("first_name", person.getFirstName());
		sqlParameters.addValue("last_name", person.getLastName());
		sqlParameters.addValue("student_number", person.getStudentNumber());
		sqlParameters.addValue("hpr_number", person.getHprNumber());
//		sqlParameters.addValue("org_unit_id", person.getXXX);
//		sqlParameters.addValue("person_id", person.getUser().getId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}

	
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
		String sql = "insert into tbl_user (username, org_unit_id, password) value (" +
				":username, :org_unit_id, :password)";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("username", user.getOrganization().getOrgUnitId());
		sqlParameters.addValue("org_unit_id", user.getPassword());
		sqlParameters.addValue("password", user.getPerson().getPersonId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
	public void updateUser(User user) {
		String sql = "update tbl_user set username=:username, org_unit_id=:org_unit_id, password=:password " +
			"where user_id=:user_id";
		MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		sqlParameters.addValue("user_id", user.getUsername());
		sqlParameters.addValue("username", user.getOrganization().getOrgUnitId());
		sqlParameters.addValue("org_unit_id", user.getPassword());
		sqlParameters.addValue("password", user.getPerson().getPersonId());
		getSimpleJdbcTemplate().update(sql, sqlParameters);
	}
    private static class UserMapper implements ParameterizedRowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            MemberOrganization organization = new MemberOrganization();
            user.setOrganization(organization);
            user.getOrganization().setOrgUnitId((rs.getInt("org_unit_id")));
            return user;
        }
    }

    public void deletePerson(Person person) {
		// TODO: Need not be implemented!
		
	}

	public Person getPersonById(Integer personId) {
		// FIXME: We should not do this! Username is unique.
		return null;
	}
	@Override
	public Person getPersonByOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Person getPersonByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
