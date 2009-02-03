package no.helsebiblioteket.admin.test.service;

import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class AccessServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertAccess(){
		OrganizationType healthEnterprice = ((ValueResult<OrganizationType>)
				this.beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise))
				.getValue();
		OrganizationType contentSupplier = ((ValueResult<OrganizationType>)
				this.beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.content_supplier))
				.getValue();
		Position position = ((ValueResult<Position>)
				this.beanFactory.getUserService().getPositionByKey(PositionTypeKey.fotterapeut))
				.getValue();
		System system = ((ValueResult<System>)
				this.beanFactory.getUserService().getSystemByKey(SystemKey.helsebiblioteket_admin))
				.getValue();
		Role role = ((ValueResult<Role>)
				this.beanFactory.getUserService().getRoleByKeySystem(UserRoleKey.health_personnel_other, system))
				.getValue();
		
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(healthEnterprice, position);
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(contentSupplier, position);
		User user = UserFactory.factory.completeUser(memberOrganization, position);
		this.beanFactory.getOrganizationService().insertOrganization(memberOrganization);
		this.beanFactory.getOrganizationService().insertOrganization(supplierOrganization);
		this.beanFactory.getUserService().insertUser(user);
		
		AccessService accessService = this.beanFactory.getAccessService();
		ResourceAccess access = new ResourceAccess();

//		TEST: public SingleResult<AccessType> getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory);
		access.setAccessType(((ValueResult<AccessType>)
				accessService.getAccessTypeByTypeCategory(AccessTypeKey.general, AccessTypeCategory.GRANT))
				.getValue());
		
		access.setProvidedBy(supplierOrganization);
		access.setValidFrom(new Date(""));
		access.setValidTo(new Date(""));
		
		
//		TEST: public void insertUserAccess(User user, Access access);
		accessService.insertUserAccess(user, access);
 
//		TEST: public void insertOrganizationAccess(Organization organization, Access access);
		accessService.insertOrganizationAccess(memberOrganization, access);

//		TEST: public void insertUserRoleAccess(Role userRole, Access access);
		accessService.insertUserRoleAccess(role, access);
		
//		TEST: public void insertOrganizationTypeAccess(OrganizationType organizationType, Access access);
		accessService.insertOrganizationTypeAccess(healthEnterprice, access);
		
//		public List<ResourceAccess> getAccessListByUser(User user);
		List<ResourceAccess> userAccessList = accessService.getAccessListByUser(user);
		
//		TEST: public List<ResourceAccess> getAccessListByOrganization(MemberOrganization organization);
		List<ResourceAccess> orgAccessList = accessService.getAccessListByOrganization(memberOrganization);
		
//		TEST: public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType);
		List<ResourceAccess> orgTypeAccessList = accessService.getAccessListByOrganizationType(healthEnterprice);
		
//		TEST: public List<ResourceAccess> getAccessListByRole(Role role);
		List<ResourceAccess> roleAccessList = accessService.getAccessListByRole(role);
		
//		TEST: public List<SupplierSource> getSupplierSourceListAll();
		List<SupplierSource> sourceList = accessService.getSupplierSourceListAll();

		// Insert some Asserts here? 
		// Reload access and change?
		
	}
}
