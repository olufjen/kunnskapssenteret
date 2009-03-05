package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.ResourceAccessFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class AccessServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertAccess(){
		String randomValue = "" + new Random().nextInt(1000000000);
		
		System helsebiblioteket_admin = ((ValueResultSystem)this.beanFactory.getUserService().getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue();
		OrganizationType healthEnterprice = ((ValueResultOrganizationType)this.beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
		OrganizationType contentSupplier = ((ValueResultOrganizationType)this.beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.content_supplier)).getValue();
		Position fotterapeut = ((ValueResultPosition) this.beanFactory.getUserService().getPositionByKey(PositionTypeKey.fotterapeut)).getValue();
		Role health_personnel_other = ((ValueResultRole)this.beanFactory.getUserService().getRoleByKeySystem(UserRoleKey.health_personnel_other, helsebiblioteket_admin)).getValue();
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(healthEnterprice, fotterapeut);
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(contentSupplier, fotterapeut);
		User user = UserFactory.factory.completeUser(memberOrganization, fotterapeut);
		SupplierSource urlSource = SupplierSourceFactory.factory.completeSupplierSource();
		memberOrganization.setOrganization(((ValueResultOrganization)this.beanFactory.getOrganizationService().insertOrganization(memberOrganization.getOrganization())).getValue());
		supplierOrganization.setOrganization(((ValueResultOrganization)this.beanFactory.getOrganizationService().insertOrganization(supplierOrganization.getOrganization())).getValue());
		user.setOrganization(memberOrganization.getOrganization());
		user.setUsername("username_" + randomValue);
		user = ((ValueResultUser)this.beanFactory.getUserService().insertUser(user)).getValue();

		AccessService accessService = this.beanFactory.getAccessService();

//		TEST: public SingleResult<AccessType> getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory);
		AccessType GRANT_general = ((ValueResultAccessType) accessService.getAccessTypeByTypeCategory(AccessTypeKey.general, AccessTypeCategory.GRANT)) .getValue();

//		TEST: public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey);
		ResourceType supplier_source = ((ValueResultResourceType)accessService.getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();

		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(supplier_source, urlSource, supplierOrganization);

//		TEST: public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource);
		resource = ((ValueResultSupplierSourceResource) accessService.insertSupplierSourceResource(resource)).getValue();

//		TEST: public void insertUserResourceAccess(User user, Access access);
		ResourceAccess access = ResourceAccessFactory.factory.completeResourceAccess(resource, GRANT_general, supplierOrganization);
		accessService.insertUserResourceAccess(user, access);
		
//		TEST: public List<ResourceAccess> getAccessListByUser(User user);
		ListResultResourceAccess userAccessList = accessService.getAccessListByUser(user);
		// Important need this for deleting!
		Assert.notEmpty(userAccessList.getList(), "Should have value");
		ResourceAccess deleteAccess1 = userAccessList.getList()[0];
		
//		TEST: public void insertOrganizationResourceAccess(Organization organization, Access access);
		accessService.insertOrganizationResourceAccess(memberOrganization.getOrganization(), access);
		
//		TEST: public List<ResourceAccess> getAccessListByOrganization(MemberOrganization organization);
		ListResultResourceAccess organizationAccessList = accessService.getAccessListByOrganization(memberOrganization.getOrganization());
		Assert.notEmpty(organizationAccessList.getList(), "Should have value");
		ResourceAccess deleteAccess2 = organizationAccessList.getList()[0];

//		TEST: public void insertUserRoleAccess(Role userRole, Access access);
		accessService.insertUserRoleResourceAccess(health_personnel_other, access);
		
//		TEST: public List<ResourceAccess> getAccessListByRole(Role role);
		ListResultResourceAccess roleAccessList = accessService.getAccessListByRole(health_personnel_other);
		Assert.notEmpty(roleAccessList.getList(), "Should have value");
//		ResourceAccess deleteAccess3 = roleAccessList.getList()[roleAccessList.getList().length-1];
				
//		TEST: public void insertOrganizationTypeResourceAccess(OrganizationType organizationType, Access access);
		accessService.insertOrganizationTypeResourceAccess(healthEnterprice, access);
		
//		TEST: public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType);
		ListResultResourceAccess organizationTypeAccessList = accessService.getAccessListByOrganizationType(healthEnterprice);
		Assert.notEmpty(organizationTypeAccessList.getList(), "Should have value");
//		ResourceAccess deleteAccess4 = organizationTypeAccessList.getList()[organizationTypeAccessList.getList().length-1];
		
//		TEST: public List<SupplierSource> getSupplierSourceListAll();
		ListResultSupplierSource sourceList = accessService.getSupplierSourceListAll("");
		Assert.notEmpty(sourceList.getList(), "Should have value");

//		TEST: public Boolean deleteResourceAccess(ResourceAccess access);
		accessService.deleteResourceAccess(deleteAccess1);
		accessService.deleteResourceAccess(deleteAccess2);
		for (ResourceAccess deleteAccessRole : roleAccessList.getList()) {
			accessService.deleteResourceAccess(deleteAccessRole);
		}
		for (ResourceAccess deleteAccessOrganization : organizationTypeAccessList.getList()) {
			accessService.deleteResourceAccess(deleteAccessOrganization);
		}
		
//		TEST: public Boolean deleteSupplierSourceResource(SupplierSourceResource resource);
		accessService.deleteSupplierSourceResource(resource);
		
	}
}