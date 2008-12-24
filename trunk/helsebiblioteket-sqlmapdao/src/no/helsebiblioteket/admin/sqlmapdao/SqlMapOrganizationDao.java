package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public class SqlMapOrganizationDao extends SqlMapClientDaoSupport implements OrganizationDao {

	public void saveOrganization(Organization organization) {
		if (organization.getId() != null) {
			updateOrganization(organization);
		} else {
			insertOrganization(organization);
		}
	}
	
	private void updateOrganization(Organization organization) {
		getSqlMapClientTemplate().update("updateOrganization", organization);
		getSqlMapClientTemplate().update("updateContactPerson", organization);
		getSqlMapClientTemplate().update("updateContactInformation", organization);
		getSqlMapClientTemplate().update("updateAccessList", organization);
		getSqlMapClientTemplate().update("updateIpRangeList", organization);
		getSqlMapClientTemplate().update("updateNameList", organization);
	}
	
	private void insertOrganization(Organization organization) {
		if (organization.getContactPerson() != null) {
			//getSqlMapClientTemplate().update("insertPerson", organization.getContactPerson());
		}
		getSqlMapClientTemplate().insert("insertOrganization", organization);
		//getSqlMapClientTemplate().insert("insertContactInformation", organization);
		//getSqlMapClientTemplate().insert("insertAccessList", organization);
		//getSqlMapClientTemplate().insert("insertIpRangeList", organization);
		//getSqlMapClientTemplate().insert("insertNameList", organization);
	}
	
	public List<Organization> getAllOrganizations() {
		List<Organization> organizationList = null;
		organizationList = getSqlMapClientTemplate().queryForList("getOrganizationList");
		for (Organization organization : organizationList) {
			organization = populateOrganization(organization);
		}
		return organizationList;
	}

	public Organization getOrganization(Integer organizationId) {
		Organization organization = null;
		organization = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organizationId);
		organization = populateOrganization(organization);
		return organization;
	}
	
	private Organization populateOrganization(Organization organization) {
		organization = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
		organization.setParent((Organization) getSqlMapClientTemplate().queryForObject("getParentOrganizationByOrganizationId", organization.getId()));
		organization.setContactInformation((ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationByOrganizationId", organization.getId()));
		organization.setContactPerson((Person) getSqlMapClientTemplate().queryForObject("getContactPersonByOrganizationId", organization.getId()));
		organization.setIpRangeList(getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organization.getId()));
		organization.setAccessList(getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organization.getId()));
		organization.setNameList(getSqlMapClientTemplate().queryForList("getNameListByOrganizationId", organization.getId()));	
		return organization;
	}

	public List<SupplierOrganization> getSupplierList() {
		return null;
	}
	
	public OrganizationType getOrganizationTypeById(Integer organizationTypeId) {
		return (OrganizationType) getSqlMapClientTemplate().queryForObject("getOrganizationTypeById", organizationTypeId);
	}
	
	public OrganizationType getOrganizationTypeByKey(String organizationTypeKey) {
		return (OrganizationType) getSqlMapClientTemplate().queryForObject("getOrganizationTypeByKey", organizationTypeKey);
	}
}