package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class JdbcOrganizationTypeDao extends SimpleJdbcDaoSupport implements OrganizationTypeDao {

	// TODO Fase2: Remove class

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	public List<OrganizationType> getOrganizationTypeList() {
		logger.debug("fetching all organization types");
        List<OrganizationType> orgTypes = getSimpleJdbcTemplate().query(
		        "select org_type_id, name, key from tbl_org_type_reg", 
		        new OrgTypeMapper());
        return orgTypes;
	}
	
	public OrganizationType getOrganizationType(String organizationTypeKey) {
		logger.debug("fetching organization types with key: " + organizationTypeKey);
        List<OrganizationType> orgTypes = getSimpleJdbcTemplate().query(
		        "select org_type_id, name, key from tbl_org_type_reg where key='" + organizationTypeKey + "'",
		        new OrgTypeMapper());
		return (orgTypes != null && orgTypes.size() == 0 ? orgTypes.get(0) : null);
	}

	private static class OrgTypeMapper implements ParameterizedRowMapper<OrganizationType> {
        public OrganizationType mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrganizationType orgType = new OrganizationType();
//            orgType.setOrgTypeId(rs.getInt("org_type_id"));
            orgType.setName(rs.getString("name"));
//            orgType.setKey(rs.getString("key"));
            return orgType;
        }
    }


	public List<OrganizationType> getOrganizationTypeListAll() {
		return null;
	}


	@Override
	public OrganizationType getOrganizationTypeByKey(
			OrganizationTypeKey organizationTypeKey) {
		return null;
	}

	@Override
	public void insertOrganizationType(OrganizationType organizationType) {
		
	}

	@Override
	public OrganizationType getOrganizationTypeById(Integer id) {
		return null;
	}


}
