package no.helsebiblioteket.admin.test.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class AccessServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertAccess(){
		OrganizationType healthEnterprice = ((ValueResultOrganizationType)this.beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
		OrganizationType contentSupplier = ((ValueResultOrganizationType)this.beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.content_supplier)).getValue();

		Position fotterapeut = ((ValueResultPosition) this.beanFactory.getUserService().getPositionByKey(PositionTypeKey.fotterapeut)).getValue();
		System helsebiblioteket_admin = ((ValueResultSystem)this.beanFactory.getUserService().getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue();
		Role role = ((ValueResultRole)this.beanFactory.getUserService().getRoleByKeySystem(UserRoleKey.health_personnel_other, helsebiblioteket_admin)).getValue();
		
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(healthEnterprice, fotterapeut);
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(contentSupplier, fotterapeut);
		User user = UserFactory.factory.completeUser(memberOrganization, fotterapeut);
		this.beanFactory.getOrganizationService().insertMemberOrganization(memberOrganization);
		this.beanFactory.getOrganizationService().insertSupplierOrganization(supplierOrganization);
		this.beanFactory.getUserService().insertUser(user);
		
		AccessService accessService = this.beanFactory.getAccessService();
		ResourceAccess access = new ResourceAccess();
		access.setAccessType(((ValueResultAccessType)
				accessService.getAccessTypeByTypeCategory(AccessTypeKey.general, AccessTypeCategory.GRANT))
				.getValue());
		access.setProvidedBy(supplierOrganization);
		Calendar calendar = Calendar.getInstance();
		Date from = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Date to = calendar.getTime();
		access.setValidFrom(from);
		access.setValidTo(to);
		
//		TEST: public void insertUserAccess(User user, Access access);
		accessService.insertUserAccess(user, access);
		
		
//		public List<ResourceAccess> getAccessListByUser(User user);
		ListResultResourceAccess userAccessList = accessService.getAccessListByUser(user);
		Assert.notEmpty(userAccessList.getList(), "Should have value");
		
 
//		TEST: public void insertOrganizationAccess(Organization organization, Access access);
		accessService.insertOrganizationAccess(memberOrganization, access);

//		TEST: public void insertUserRoleAccess(Role userRole, Access access);
		accessService.insertUserRoleAccess(role, access);
		
//		TEST: public void insertOrganizationTypeAccess(OrganizationType organizationType, Access access);
		accessService.insertOrganizationTypeAccess(healthEnterprice, access);
		
	
//		TEST: public SingleResult<AccessType> getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory);

//		TEST: public List<ResourceAccess> getAccessListByOrganization(MemberOrganization organization);
		ListResultResourceAccess orgAccessList = accessService.getAccessListByOrganization(memberOrganization);
		
//		TEST: public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType);
		ListResultResourceAccess orgTypeAccessList = accessService.getAccessListByOrganizationType(healthEnterprice);
		
//		TEST: public List<ResourceAccess> getAccessListByRole(Role role);
		ListResultResourceAccess roleAccessList = accessService.getAccessListByRole(role);

	
//		TEST: public List<SupplierSource> getSupplierSourceListAll();
		ListResultSupplierSource sourceList = accessService.getSupplierSourceListAll("");

		// Insert some Asserts here? 
		// Reload access and change?
		
	}
}
