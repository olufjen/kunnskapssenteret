package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.OrganizationType;

public class JdbcOrganizationTypeDao extends SimpleJdbcDaoSupport implements OrganizationTypeDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	public List<OrganizationType> getOrganizationTypeList() {
		logger.info("fetching all organization types");
        List<OrganizationType> orgTypes = getSimpleJdbcTemplate().query(
		        "org_type_id, descs, name, key from tbl_org_type_reg", 
		        new OrgTypeMapper());
        return orgTypes;
	}
	
	public OrganizationType getOrganizationType(Integer organizationTypeId) {
		logger.info("fetching all organization types");
        List<OrganizationType> orgTypes = getSimpleJdbcTemplate().query(
		        "select org_type_id, descr, name, key from tbl_org_type_reg", 
		        new OrgTypeMapper());
		return (orgTypes != null && orgTypes.size() == 0 ? orgTypes.get(0) : null);
	}

	private static class OrgTypeMapper implements ParameterizedRowMapper<OrganizationType> {
        public OrganizationType mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrganizationType orgType = new OrganizationType();
            orgType.setId(rs.getInt("org_type_id"));
            orgType.setDescription(rs.getString("descr"));
            orgType.setName(rs.getString("name"));
            orgType.setKey(rs.getString("key"));
            return orgType;
        }
    }
}
