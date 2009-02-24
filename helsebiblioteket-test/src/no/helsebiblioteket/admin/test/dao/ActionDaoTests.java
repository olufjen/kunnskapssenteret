package no.helsebiblioteket.admin.test.dao;

import java.util.List;
import java.util.Random;

import no.helsebiblioteket.admin.dao.ActionDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.line.ActionLine;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.PositionFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;

public class ActionDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testAction() {
		// Random string value
		String randomValue = "username_" + new Random().nextInt(1000000000);
		
		// 'static' values
		AccessType accessType = beanFactory.getAccessTypeDao().getAccessTypeByKey(
				AccessTypeKey.general, AccessTypeCategory.GRANT);
		ResourceType resourceType  = beanFactory.getResourceTypeDao().getResourceTypeByKey(
				ResourceTypeKey.supplier_source);
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(
				OrganizationTypeKey.content_supplier);

		// New objects
		SupplierSource supplierSource = SupplierSourceFactory.factory.completeSupplierSource();
		Position position = PositionFactory.factory.completePosition(organizationType);
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		User user = UserFactory.factory.completeUser(memberOrganization, position);
		user.setUsername(randomValue);
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(
				organizationType, position);
		SupplierSourceResource supplierSourceResource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(
				resourceType, supplierSource, supplierOrganization);

		// DAO
		SupplierSourceDao supplierSourceDao = beanFactory.getSupplierSourceDao();
		ResourceDao resourceDao = beanFactory.getResourceDao();

		// Prepare insert
		new OrganizationDaoTests().insertSupplierOrganization(supplierOrganization);
		new OrganizationDaoTests().insertMemberOrganization(memberOrganization);
		new UserDaoTests().insertUser(user);
		supplierSourceDao.insertSupplierSource(supplierSource);
		resourceDao.insertSupplierSourceResource(supplierSourceResource);
		
		ActionDao actionDao = beanFactory.getActionDao();
		ActionLine actionLine = createActionLine(null,
				user, supplierSourceResource.getResource(), accessType);
		
//		TEST: public void insertAction(ActionLine actionLine);
		actionDao.insertAction(actionLine);
//		TEST: public List<ActionLine> getActionListByUser(User user);
		List<ActionLine> userActionList = actionDao.getActionListByUser(user);
		Assert.notEmpty(userActionList, "User action not found");

		actionLine.setUserId(null);
		actionLine.setOrgUnitId(memberOrganization.getOrganization().getId());
//		TEST: public void updateAction(ActionLine actionLine);
		actionDao.updateAction(actionLine);
		userActionList = actionDao.getActionListByUser(user);
		Assert.isTrue(userActionList.size()==0, "User action not removed");
		
//		TEST: public List<ActionLine> getActionListByOrganization(Organization organization);
		List<ActionLine> organizationActionList = actionDao.getActionListByOrganization(memberOrganization.getOrganization());
		Assert.notEmpty(organizationActionList, "Organization action not found");
		
//		TEST: public List<ActionLine> getActionListByResource(Resource resource);
		List<ActionLine> resourceActionList = actionDao.getActionListByResource(supplierSourceResource.getResource());
		Assert.notEmpty(resourceActionList, "Resource acion not found");
		
//		TEST: public List<ActionLine> getActionListByAccessType(AccessType accessType);
		List<ActionLine> accessTypeActionList = actionDao.getActionListByAccessType(accessType);
		Assert.notEmpty(accessTypeActionList, "Access type action not found");
		
//		TEST: public void deleteAction(ActionLine actionLine);
		actionDao.deleteAction(actionLine);
		organizationActionList = actionDao.getActionListByOrganization(memberOrganization.getOrganization());
		Assert.isTrue(organizationActionList.size() == 0, "Action not deleted -- organization");
		resourceActionList = actionDao.getActionListByResource(supplierSourceResource.getResource());
		Assert.isTrue(resourceActionList.size() == 0, "Action not deleted -- Resource");
		List<ActionLine> afterDeleteAccessTypeActionList = actionDao.getActionListByAccessType(accessType);
		Assert.isTrue(accessTypeActionList.size() - afterDeleteAccessTypeActionList.size() == 1, "Action not deleted --  Access type");
	}
	private ActionLine createActionLine(Organization organization, User user, Resource resource, AccessType accessType){
		ActionLine actionLine = new ActionLine();
		actionLine.setAccessTypeId(accessType.getId());
		actionLine.setId(null);
		if(organization != null){
			actionLine.setOrgUnitId(organization.getId());
		}
		actionLine.setResourceId(resource.getId());
		if(user!=null){
			actionLine.setUserId(user.getId());
		}
		return actionLine;
	}
}
