package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Action;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.service.ActionService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ActionServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertAction(){
		String randomValue = "" + new Random().nextInt(1000000000);

		// 'static' values
		OrganizationType health_enterprise = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey._health_enterprise)).getValue();
		OrganizationType content_supplier = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey._content_supplier)).getValue();
		Position vernepleier = ((ValueResultPosition)beanFactory.getUserService().getPositionByKey(PositionTypeKey.vernepleier, health_enterprise)).getValue();
		AccessType general_GRANT = ((ValueResultAccessType)beanFactory.getAccessService().getAccessTypeByTypeCategory(AccessTypeKey.general, AccessTypeCategory.GRANT)).getValue();
		ResourceType supplier_source = ((ValueResultResourceType)beanFactory.getAccessService().getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();
		SupplierSource source = SupplierSourceFactory.factory.completeSupplierSource();
		Url url = new Url();
		url.setStringValue("url_" + randomValue);
		source.setUrl(url);
		
		// Create objects
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(health_enterprise, vernepleier);
		String nameEnglish = "mem_org_" + randomValue;
		memberOrganization.getOrganization().setNameEnglish(nameEnglish);
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(content_supplier, vernepleier);
		supplierOrganization.getOrganization().setNameEnglish("sup_org_" + randomValue);
		User user = UserFactory.factory.completeUser(vernepleier);
		OrganizationUser organizationUser = new OrganizationUser();
		organizationUser.setUser(user);
		String username = "username_" + randomValue;
		user.setUsername(username);
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(supplier_source, source, supplierOrganization);
		
		memberOrganization = ((ValueResultMemberOrganization)beanFactory.getOrganizationService().insertMemberOrganization(memberOrganization)).getValue();
		supplierOrganization = ((ValueResultSupplierOrganization)beanFactory.getOrganizationService().insertSupplierOrganization(supplierOrganization)).getValue();
		organizationUser.setOrganization(memberOrganization.getOrganization());
		user = ((ValueResultUser)beanFactory.getUserService().insertOrganizationUser(organizationUser)).getValue();
		resource = ((ValueResultSupplierSourceResource)beanFactory.getAccessService().insertSupplierSourceResource(resource)).getValue();
		
		// Action service
		ActionService actionService = beanFactory.getActionService();
		
		// TMP value for testing.
		Action[] oldActions = actionService.getActionListByAccessType(general_GRANT).getList();
		
//		TEST: public Boolean insertUserAction(User user, Resource resource, AccessType accessType);
		actionService.insertUserAction(user, resource.getResource(), general_GRANT);

//		TEST: public ListResultAction getActionListByUser(User user);
		Action[] userActions = actionService.getActionListByUser(user).getList();
		Assert.isTrue(userActions.length == 1, "Should have found one");
		Assert.isTrue(userActions[0].getUserOrganizationAccess().equals(Action.USER_ACCESS), "Not user access");
//		TEST: public SingleResultUser getUserByAction(Action action);
		SingleResultUser userRes = actionService.getUserByAction(userActions[0]);
		Assert.isTrue(userRes instanceof ValueResultUser, "User should have been found");
		Assert.isTrue(((ValueResultUser)userRes).getValue().getUsername().equals(username), "Wrong user found");
		
//		TEST: public Boolean insertOrganizationAction(Organization organization, Resource resource, AccessType accessType);
		actionService.insertOrganizationAction(memberOrganization.getOrganization(), resource.getResource(), general_GRANT);

//		TEST: public ListResultAction getActionListByOrganization(Organization organization);
		Action[] organizationActions = actionService.getActionListByOrganization(memberOrganization.getOrganization()).getList();
		Assert.isTrue(organizationActions.length == 1, "Should have found one");
		Assert.isTrue(organizationActions[0].getUserOrganizationAccess().equals(Action.ORGANIZATION_ACCESS), "Not organization access");
//		TEST: public SingleResultOrganization getOrganizationByAction(Action action);
		SingleResultOrganization orgRes = actionService.getOrganizationByAction(organizationActions[0]);
		Assert.isTrue(orgRes instanceof ValueResultMemberOrganization, "Organization should have been found");
		Assert.isTrue(((ValueResultMemberOrganization)orgRes).getValue().getOrganization().getNameEnglish().equals(nameEnglish), "Wrong organization found");
		
//		TEST: public ListResultAction getActionListByResource(Resource resource);
		Action[] resourceActions = actionService.getActionListByResource(resource.getResource()).getList();
		Assert.isTrue(resourceActions.length == 2, "Should have found one");
		
//		TEST: public ListResultAction getActionListByAccessType(AccessType accessType);
		Action[] accessTypeActions = actionService.getActionListByAccessType(general_GRANT).getList();
		Assert.isTrue(accessTypeActions.length - oldActions.length == 2, "Should have added two");
	}
}
