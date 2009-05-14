package no.helsebiblioteket.admin.test.dao;

import java.util.List;
import java.util.Random;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.ResourceAccessFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;


public class AccessDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testAccess() {
		System system = beanFactory.getSystemDao().getSystemByKey(SystemKey.helsebiblioteket_admin);

		// Test classes
		OrganizationDaoTests organizationDaoTests = new OrganizationDaoTests();
		UserDaoTests userDaoTests = new UserDaoTests();

		// DAOs for 'static' data
		OrganizationTypeDao organizationTypeDao = beanFactory.getOrganizationTypeDao();
		RoleDao roleDao = beanFactory.getRoleDao();
		PositionDao positionDao = beanFactory.getPositionDao();
		AccessTypeDao accessTypeDao = beanFactory.getAccessTypeDao();

		// Fetch 'static data'
		OrganizationType organizationType = organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise);
		Position position = positionDao.getPositionByKey(PositionTypeKey.ambulansearbeider);
		Role userRole = roleDao.getRoleByKeySystem(UserRoleKey.student, system);
		AccessType accessType = accessTypeDao.getAccessTypeByKey(AccessTypeKey.general, AccessTypeCategory.GRANT);

		// DAOs for regular data
		AccessDao accessDao = beanFactory.getAccessDao();
		
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(organizationType, position);
		organizationDaoTests.insertSupplierOrganization(supplierOrganization);

		ResourceType resourceType = beanFactory.getResourceTypeDao().getResourceTypeByKey(ResourceTypeKey.supplier_source);
		SupplierSource supplierSource = SupplierSourceFactory.factory.completeSupplierSource();
		beanFactory.getSupplierSourceDao().insertSupplierSource(supplierSource);
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(resourceType, supplierSource, supplierOrganization);
		beanFactory.getResourceDao().insertSupplierSourceResource(resource);

		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
//		ContactInformation contactInformation = ContactInformationFactory.factory.completeContactInformation();
		organizationDaoTests.insertMemberOrganization(organization);

		User user = UserFactory.factory.completeUser(position);
		user.setUsername("RandomUser" + new Random().nextInt(1000000));
		OrganizationUser organizationUser = new OrganizationUser();
		organizationUser.setUser(user);
		userDaoTests.insertUser(organizationUser);
		
		
		// Access for user
		ResourceAccess resourceAccessForUser = ResourceAccessFactory.factory.completeResourceAccess(resource, accessType, supplierOrganization);
		ResourceAccessForeignKeys accessForUserKeys = new ResourceAccessForeignKeys();
		accessForUserKeys.setUserId(user.getId());
		accessForUserKeys.setResourceAccess(resourceAccessForUser);
		accessDao.insertResourceAccessForeignKeys(accessForUserKeys);
		accessDao.updateResourceAccessForeignKeys(accessForUserKeys);
		UserListItem item = new UserListItem();
		item.setId(user.getId());
		List<ResourceAccessListItem> userAccessList = accessDao.getAccessListByUser(item);
		Assert.notEmpty(userAccessList, "No access for user!");

		// Access for user roles
		ResourceAccess resourceAccessForUserRole = ResourceAccessFactory.factory.completeResourceAccess(resource, accessType, supplierOrganization);
		ResourceAccessForeignKeys accessForUserRoleKeys = new ResourceAccessForeignKeys();
		accessForUserRoleKeys.setUserRoleId(userRole.getId());
		accessForUserRoleKeys.setResourceAccess(resourceAccessForUserRole);
		accessDao.insertResourceAccessForeignKeys(accessForUserRoleKeys);
		List<ResourceAccessListItem> userRoleAccessList = accessDao.getAccessListByUserRole(userRole.getKey());
		Assert.notEmpty(userRoleAccessList, "No access for user role!");

		// Access for organization
		ResourceAccess resourceAccessForOrg = ResourceAccessFactory.factory.completeResourceAccess(resource, accessType, supplierOrganization);
		ResourceAccessForeignKeys accessForOrganizationKeys = new ResourceAccessForeignKeys();
		accessForOrganizationKeys.setOrgUnitId(organization.getOrganization().getId());
		accessForOrganizationKeys.setResourceAccess(resourceAccessForOrg);
		accessDao.insertResourceAccessForeignKeys(accessForOrganizationKeys);
		OrganizationListItem lItem = new OrganizationListItem();
		lItem.setId(organization.getOrganization().getId());
		List<ResourceAccessListItem> organizationAccessList = accessDao.getAccessListByOrganization(lItem);
		Assert.notEmpty(organizationAccessList, "No access for organization!");
		
		// Access for organization type
		ResourceAccess resourceAccessForOrgType = ResourceAccessFactory.factory.completeResourceAccess(resource, accessType, supplierOrganization);
		ResourceAccessForeignKeys accessForOrganizationTypeKeys = new ResourceAccessForeignKeys();
		accessForOrganizationTypeKeys.setOrgTypeId(organizationType.getId());
		accessForOrganizationTypeKeys.setResourceAccess(resourceAccessForOrgType);
		accessDao.insertResourceAccessForeignKeys(accessForOrganizationTypeKeys);
		List<ResourceAccessListItem> organizationTypeAccessList = accessDao.getAccessListByOrganizationType(organizationType.getKey());
		Assert.notEmpty(organizationTypeAccessList, "No access for organization type!");
		
		// Remove
		accessDao.deleteResourceAccessForeignKeys(accessForOrganizationTypeKeys);
		accessDao.deleteResourceAccessForeignKeys(accessForOrganizationKeys);
		accessDao.deleteResourceAccessForeignKeys(accessForUserRoleKeys);
		accessDao.deleteResourceAccessForeignKeys(accessForUserKeys);
		beanFactory.getResourceDao().deleteSupplierSourceResource(resource);
		beanFactory.getSupplierSourceDao().deleteSupplierSource(supplierSource);
		organizationUser.setUser(user);
		userDaoTests.removeUser(organizationUser);
		organizationDaoTests.removeSupplierOrganization(supplierOrganization);
		organizationDaoTests.removeMemberOrganization(organization);
	}
}
