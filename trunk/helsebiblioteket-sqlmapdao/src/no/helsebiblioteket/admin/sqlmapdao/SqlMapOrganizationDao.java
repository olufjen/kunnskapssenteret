package no.helsebiblioteket.admin.sqlmapdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierSource;

public class SqlMapOrganizationDao extends SqlMapClientDaoSupport implements OrganizationDao {
	// TODO: Go through all!
	public void insertOrganization(Organization organization){
		
		getSqlMapClientTemplate().insert("insertOrganization", organization);

	}
	public void updateOrganization(Organization organization){
		
		getSqlMapClientTemplate().update("updateOrganization", organization);

	}
	public void deleteOrganization(Organization organization){
		
	}
	public Organization getOrganizationByChild(Organization child){
		return null;
	}
	public Organization getOrganizationById(Integer id){
		Organization organization = null;
		organization = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", id);
		return organization;
	}

	
	
	
	
	
	
	
	// organization
	
	
	public List<Organization> getOrganizationList() {
		return (List<Organization>) getSqlMapClientTemplate().queryForList("getOrganizationList");
	}
	
	
	
	
	// organization type
	
	public OrganizationType getOrganizationTypeById(Integer organizationTypeId) {
		return (OrganizationType) getSqlMapClientTemplate().queryForObject("getOrganizationTypeById", organizationTypeId);
	}
	
	public OrganizationType getOrganizationTypeByKey(String organizationTypeKey) {
		return (OrganizationType) getSqlMapClientTemplate().queryForObject("getOrganizationTypeByKey", organizationTypeKey);
	}
	
	
	// organization name
	
	public void saveOrganizationName(OrganizationName organizationName) {
		if (organizationName.getId() == null) {
			insertOrganizationName(organizationName);
		} else {
			updateOrganizationName(organizationName);
		}
	}
	
	public void insertOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().insert("insertOrganizationName", organizationName);
	}
	
	public void updateOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().update("updateOrganizationName", organizationName);
	}
	
	public void deleteOrganizationName(OrganizationName organizationName) {
		getSqlMapClientTemplate().delete("deleteOrganizationName", organizationName);
	}
	
	public List<OrganizationName> getOrganizationNameListByOrganizationId(Integer organizationId) {
		return (List<OrganizationName>) getSqlMapClientTemplate().queryForList("getOrganizationNameListByOrganizationId", organizationId);
	}
}