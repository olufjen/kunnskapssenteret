package no.helsebiblioteket.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.Contract;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;

public class JdbcOrganizationDao extends SimpleJdbcDaoSupport implements OrganizationDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	public List<Organization> getOrganizationList() {
		logger.info("fetching all organization types");
        List<Organization> orgs = getSimpleJdbcTemplate().query(
		        "org_unit_id, org_unit_parent_id, name, name_short, descr, org_type_id from tbl_org_unit", 
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
	
	public List<Contract> getContractList(OrganizationType organizationType) {
		List<Contract> orgs = getSimpleJdbcTemplate().query(
		        "org_unit_contract_id, org_unit_customer_id, org_unit_supplier_id, org_type_id from tbl_org_unit_contract where org_type_id = :org_type_id", 
		        new ContractMapper(),
		        new MapSqlParameterSource().addValue("org_type_id", organizationType.getId())
		        );
		return orgs;
	}
	
	private static class ContractMapper implements ParameterizedRowMapper<Contract> {
        public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contract contract = new Contract();
            contract.setId(rs.getInt("contract_id"));
            contract.setCustomerId(rs.getInt("org_unit_customer_id"));
            contract.setSupplierId(rs.getInt("org_unit_supplier_id"));
            contract.setOrgTypeId(rs.getInt("org_type_id"));
            //contract.setAccessList(accessList);
            return contract;
        }
    }
	
	private static class OrgMapper implements ParameterizedRowMapper<Organization> {
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("org_unit_id"));
            // traverse upwards and set parents until root is reached
            org.setParentOrganization((rs.getObject("org_unit_parent_id") != null) ? (new JdbcOrganizationDao().getOrganization(rs.getInt("org_unit_parent_id"))) : null);
            org.setDescription(rs.getString("descr"));
            org.setName(rs.getString("name"));
            org.setNameShort(rs.getString("name_short"));
            org.setType(new JdbcOrganizationTypeDao().getOrganizationType(rs.getInt("org_type_id")));
            return org;
        }
    }
}