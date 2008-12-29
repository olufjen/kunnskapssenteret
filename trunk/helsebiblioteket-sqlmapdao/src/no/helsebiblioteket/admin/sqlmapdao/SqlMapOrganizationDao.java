package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.sqlmapdao.helper.ModifiedListHelper;

public class SqlMapOrganizationDao extends SqlMapClientDaoSupport implements OrganizationDao {
	
	public void saveOrganization(Organization organization) {
		saveOrganization(organization, (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId()));
	}

	public void saveOrganization(Organization changedOrganization, Organization originalOrganization) {
		if (changedOrganization.getId() != null) {
			if (originalOrganization == null || (originalOrganization != null && !originalOrganization.getLastChanged().equals(changedOrganization.getLastChanged()))) {
				throw new OptimisticLockingFailureException("Organization has been changed by another caller since last time it was loaded from datastore");
			}
			updateOrganization(changedOrganization, originalOrganization);
		} else {
			insertOrganization(changedOrganization);
		}
	}
	
	private void updateOrganization(Organization changedOrganization, Organization originalOrganization) {
		if (changedOrganization == null && originalOrganization != null) {
			getSqlMapClientTemplate().delete("deleteOrganization", originalOrganization);
		} else {
			getSqlMapClientTemplate().update("updateOrganization", changedOrganization);
			setForeignKeysForOrganization(changedOrganization);
			savePerson(changedOrganization.getContactPerson(), changedOrganization.getContactPerson());
			saveContactInformation(changedOrganization.getContactInformation(), originalOrganization.getContactInformation());
			saveIpRangeList(changedOrganization.getIpRangeList(), originalOrganization.getIpRangeList());
			saveOrganizationNameList(changedOrganization.getNameList(), originalOrganization.getNameList());
			// TODO: save access
		}
	}
	
	private void savePerson(Person person, Person originalPerson) {
		saveContactInformation(person.getContactInformation(), originalPerson.getContactInformation());
		saveUserProfile(person.getProfile(), originalPerson.getProfile());
		
		if (person.getId() != null) {
			getSqlMapClientTemplate().update("updatePerson", person);
		} else {
			getSqlMapClientTemplate().insert("insertPerson", person);
		}
	}
	
	private void saveContactInformation(ContactInformation changedContactInformation, ContactInformation originalContactInformation) {
		if (originalContactInformation != null && changedContactInformation == null) {
			getSqlMapClientTemplate().delete("deleteContactInformation", originalContactInformation);
		} else {
			if (changedContactInformation.getId() == null) {
				getSqlMapClientTemplate().insert("insertContactInformation", changedContactInformation);
			} else {
				getSqlMapClientTemplate().update("updateContactInformation", changedContactInformation);
			}
		}
	}
	
	private void saveAccess(Access access) {	
	}
	
	private void saveIpRange(IpRange ipRange) {
		if (ipRange.getId() == null) {
			getSqlMapClientTemplate().insert("insertIpRange", ipRange);
		} else {
			getSqlMapClientTemplate().update("updateIpRange", ipRange);
		}
	}
	
	private void saveOrganizationName(OrganizationName organizationName) {
		if (organizationName.getId() == null) {
			getSqlMapClientTemplate().insert("insertOrganizationName", organizationName);
		} else {
			getSqlMapClientTemplate().update("updateOrganizationName", organizationName);
		}
	}
	
	private void saveIpRangeList(List<IpRange> changedIpRangeList, List<IpRange> originalIpRangeList) {
		ModifiedListHelper<IpRange> listHelper = new ModifiedListHelper<IpRange>();
		List<IpRange> deleteList = listHelper.getDeleteList(changedIpRangeList, originalIpRangeList);;
		List<IpRange> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedIpRangeList, originalIpRangeList);
		if (deleteList != null) {
			for (IpRange ipRange : deleteList) {
				getSqlMapClientTemplate().delete("deleteIpAddress", ipRange);
			}
		}
		if (insertAndUpdateList != null) {
			for (IpRange ipRange : insertAndUpdateList) {
				saveIpRange(ipRange);
			}
		}
	}
	
	private void saveOrganizationNameList(List<OrganizationName> changedOrganizationNameList, List<OrganizationName> originalOrganizationNameList) {
		ModifiedListHelper<OrganizationName> listHelper = new ModifiedListHelper<OrganizationName>();
		List<OrganizationName> deleteList = listHelper.getDeleteList(changedOrganizationNameList, originalOrganizationNameList);;
		List<OrganizationName> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedOrganizationNameList, originalOrganizationNameList);
		if (deleteList != null) {
			for (OrganizationName organizationName : deleteList) {
				getSqlMapClientTemplate().delete("deleteOrganizationName", organizationName);
			}
		}
		if (insertAndUpdateList != null) {
			for (OrganizationName organizationName : insertAndUpdateList) {
				saveOrganizationName(organizationName);
			}
		}
	}
	
	private void saveUserProfile(Profile changedProfile, Profile originalProfile) {
		if (changedProfile == null && originalProfile != null) {
			// TODO: create sql map for user profile
			// getSqlMapClientTemplate().delete("deleteUserProfile", originalProfile);
		} else {
			if (changedProfile.getId() == null) {
				// TODO: create sql map for user profile
				//getSqlMapClientTemplate().insert("insertUserProfile", changedProfile);
			} else {
				// TODO: create sql map for user profile
				//getSqlMapClientTemplate().update("updateUserProfile", changedProfile);
			}
		}
	}
	
	private void insertOrganization(Organization organization) {
		if (organization.getContactPerson() != null) {
			if (organization.getContactPerson().getContactInformation() != null) {
				//organization.getContactPerson().getContactInformation().setId(
						getSqlMapClientTemplate().insert("insertContactInformation", organization.getContactPerson().getContactInformation());
				//		);
			}
			// TODO create sqlmap for user profile
			// if (organization.getContactPerson().getProfile() != null) {
			// 	organization.getContactPerson().getProfile().setId(
			// 			(Integer) getSqlMapClientTemplate().insert("insertUserProfile", organization.getContactPerson().getProfile())
			// 			);
			// }
			getSqlMapClientTemplate().insert("insertPerson", organization.getContactPerson());
		}
		
		if (organization.getContactInformation() != null) {
			// organization.getContactInformation().setId(
					getSqlMapClientTemplate().insert("insertContactInformation", organization.getContactInformation());
			//		);
		}
		
		getSqlMapClientTemplate().insert("insertOrganization", organization);
		setForeignKeysForOrganization(organization);
		
		if (organization.getNameList() != null) {
			for (OrganizationName organizationName : organization.getNameList()) {		
				getSqlMapClientTemplate().insert("insertOrganizationName", organizationName);
			}
		}
		if (organization.getIpRangeList() != null) {
			for (IpRange ipRange : organization.getIpRangeList()) {
				getSqlMapClientTemplate().insert("insertIpRange", ipRange);
			}
		}
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
		if (organization.getParent() != null) {
			organization.setParent((Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getParent().getId()));
		}
		if (organization.getContactInformation() != null) {
			organization.setContactInformation((ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationById", organization.getContactInformation().getId()));
		}
		if (organization.getContactPerson() != null) {
			if (organization.getContactPerson().getContactInformation() != null) {
				organization.getContactPerson().setContactInformation((ContactInformation) getSqlMapClientTemplate().queryForObject("getContactInformationById", organization.getContactPerson().getContactInformation().getId()));
			}
			// TODO create sqlmap for user profile
			// if (organization.getContactPerson().getProfile() != null) {
			// 	organization.getContactPerson().setProfile((Profile) getSqlMapClientTemplate().queryForObject("getUserProfileById", organization.getContactPerson().getProfile().getId()));
			// }
			organization.setContactPerson((Person) getSqlMapClientTemplate().queryForObject("getPersonById", organization.getContactPerson().getId()));
		}
		organization.setIpRangeList(getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organization.getId()));
		// TODO: create sqlmap for access
		// organization.setAccessList(getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organization.getId()));
		organization.setNameList(getSqlMapClientTemplate().queryForList("getOrganizationNameListByOrganizationId", organization.getId()));	
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
	
	private void setForeignKeysForOrganization(Organization organization) {
		if (organization != null) {
			if (organization.getIpRangeList() != null) {
				for (IpRange ipRange : organization.getIpRangeList()) {
					ipRange.setOrganizationId(organization.getId());
				}
			}
			if (organization.getNameList() != null) {
				for (OrganizationName organizationName: organization.getNameList()) {
					organizationName.setOrganizationId(organization.getId());
				}
			}
		}
	}
}