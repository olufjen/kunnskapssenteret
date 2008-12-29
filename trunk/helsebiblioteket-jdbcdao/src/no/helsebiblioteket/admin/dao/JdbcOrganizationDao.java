package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public class JdbcOrganizationDao extends SimpleJdbcDaoSupport implements OrganizationDao {
    protected final Log logger = LogFactory.getLog(getClass());
	public List<Organization> getAllOrganizations() {
		logger.info("fetching all organizations");
		List<Organization> orgs = getSimpleJdbcTemplate().query(
				"select org_unit_id, name, descr, name_short, org_type_key, org_unit_parent_id, org_type_id from tbl_org_unit",
                new OrgMapper());
        return orgs;
	}
	public Organization getOrganization(Integer organizationId) {
		List<Organization> orgs = getSimpleJdbcTemplate().query(
		        "org_unit_id, org_unit_parent_id, name, name_short, descr, org_type_id from tbl_org_unit where org_unit_id = :org_unit_id", 
		        new OrgMapper(),
		        new MapSqlParameterSource().addValue("org_unit_id", organizationId)
		        );
		return (orgs != null && orgs.size() == 1) ? orgs.get(0) : null;
	}
	
	
	private static class OrgMapper implements ParameterizedRowMapper<Organization> {
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        	//select org_unit_id, name, descr, name_short, org_type_key, org_unit_parent_id from tbl_org_unit
            Organization org = new Organization();
            org.setId(rs.getInt("org_unit_id"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("descr"));
            
            // TODO: Is this the right way to do this!
            // traverse upwards and set parents until root is reached
//            org.setParentOrganization((rs.getObject("org_unit_parent_id") != null) ? (new JdbcOrganizationDao().getOrganization(rs.getInt("org_unit_parent_id"))) : null);
            // TODO: Is this the right way to do this!
            // Set the Org Type
            //org.setType(new JdbcOrganizationTypeDao().getOrganizationType(rs.getInt("org_type_id")));
            return org;
        }
    }
	public List<SupplierOrganization> getSupplierList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveOrganization(Organization organization) {
		// noop
	}
	@Override
	public OrganizationType getOrganizationTypeById(Integer organizationTypeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrganizationType getOrganizationTypeByKey(String organizationTypeKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
