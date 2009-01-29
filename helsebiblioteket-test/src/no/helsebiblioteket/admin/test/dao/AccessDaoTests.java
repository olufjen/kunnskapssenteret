package no.helsebiblioteket.admin.test.dao;

import java.util.List;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.daoobjects.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.factory.AccessTypeFactory;
import no.helsebiblioteket.admin.factory.ContactInformationFactory;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.ResourceAccessFactory;
import no.helsebiblioteket.admin.factory.ResourceTypeFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;

public class AccessDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testAccess() {
		// DAO
		OrganizationTypeDao organizationTypeDao = beanFactory.getOrganizationTypeDao();
		OrganizationDao organizationDao = beanFactory.getOrganizationDao();
		UserDao userDao = beanFactory.getUserDao();
		AccessDao accessDao = beanFactory.getAccessDao();
		ContactInformationDao contactInformationDao = beanFactory.getContactInformationDao();
		ResourceTypeDao resourceTypeDao = beanFactory.getResourceTypeDao();
		RoleDao roleDao = beanFactory.getRoleDao();
		
		// TEST
		OrganizationType organizationType = organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise);
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType);
		ContactInformation contactInformation = ContactInformationFactory.factory.completeContactInformation();
		contactInformationDao.insertContactInformation(contactInformation);
		organizationDao.insertOrganization(organization);
		User user = UserFactory.factory.completeUser(organization);
		userDao.insertUser(user);
		ResourceType resourceType = ResourceTypeFactory.factory.completeSupplierResourceType();
		resourceTypeDao.insertResourceType(resourceType);
		SupplierSource supplierSource = SupplierSourceFactory.factory.completeSupplierSource();
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(resourceType, supplierSource);
		AccessType accessType = AccessTypeFactory.factory.completeSupplierResourceType();
		ResourceAccess resourceAccess = ResourceAccessFactory.factory.completeResourceAccess(resource, accessType, organization);
		
		// Access for user
		ResourceAccessForeignKeys accessForUserKeys = new ResourceAccessForeignKeys();
		accessForUserKeys.setUserId(user.getId());
		accessForUserKeys.setResourceAccess(resourceAccess);
		accessDao.insertResourceAccessForeignKeys(accessForUserKeys);
		accessDao.updateResourceAccessForeignKeys(accessForUserKeys);
		List<ResourceAccessForeignKeys> userAccessList = accessDao.getAccessListByUser(user);
		Assert.notEmpty(userAccessList, "No access for user!");

		// Access for user roles
		UserRole userRole = roleDao.getRoleByKey(UserRoleKey.student);
		ResourceAccessForeignKeys accessForUserRoleKeys = new ResourceAccessForeignKeys();
		accessForUserRoleKeys.setUserRoleId(userRole.getUserRoleId());
		accessForUserRoleKeys.setResourceAccess(resourceAccess);
		accessDao.insertResourceAccessForeignKeys(accessForUserRoleKeys);
		List<ResourceAccessForeignKeys> userRoleAccessList = accessDao.getAccessListByUserRole(userRole);
		Assert.notEmpty(userRoleAccessList, "No access for user role!");

		// Access for organization
		ResourceAccessForeignKeys accessForOrganizationKeys = new ResourceAccessForeignKeys();
		accessForOrganizationKeys.setOrgUnitId(organization.getId());
		accessForOrganizationKeys.setResourceAccess(resourceAccess);
		accessDao.insertResourceAccessForeignKeys(accessForOrganizationKeys);
		List<ResourceAccessForeignKeys> organizationAccessList = accessDao.getAccessListByOrganization(organization);
		Assert.notEmpty(organizationAccessList, "No access for organization!");
		
		// Access for organization type
		ResourceAccessForeignKeys accessForOrganizationTypeKeys = new ResourceAccessForeignKeys();
		accessForOrganizationTypeKeys.setOrgTypeId(organizationType.getId());
		accessForOrganizationTypeKeys.setResourceAccess(resourceAccess);
		accessDao.insertResourceAccessForeignKeys(accessForOrganizationTypeKeys);
		List<ResourceAccessForeignKeys> organizationTypeAccessList = accessDao.getAccessListByOrganizationType(organizationType);
		Assert.notEmpty(organizationTypeAccessList, "No access for organization type!");
		
		// Remove
		accessDao.deleteResourceAccessForeignKeys(accessForOrganizationTypeKeys);
		accessDao.deleteResourceAccessForeignKeys(accessForOrganizationKeys);
		accessDao.deleteResourceAccessForeignKeys(accessForUserRoleKeys);
		accessDao.deleteResourceAccessForeignKeys(accessForUserKeys);
		resourceTypeDao.deleteResourceType(resourceType);
		userDao.deleteUser(user);
		organizationDao.deleteOrganization(organization);
		contactInformationDao.deleteContactInformation(contactInformation);
//		
	}
//	public List<ResourceAccessForeignKeys> getAccessListByUser(User user);
//	public List<ResourceAccessForeignKeys> getAccessListByUserRole(UserRole userRole);
//	public List<ResourceAccessForeignKeys> getAccessListByOrganization(Organization organization);
//	public List<ResourceAccessForeignKeys> getAccessListByOrganizationType(OrganizationType organizationType);

}
