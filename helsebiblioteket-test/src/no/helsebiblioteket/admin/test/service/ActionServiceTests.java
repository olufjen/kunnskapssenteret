package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Action;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
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
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
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
		OrganizationType health_enterprise = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
		OrganizationType content_supplier = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.content_supplier)).getValue();
		Position vernepleier = ((ValueResultPosition)beanFactory.getUserService().getPositionByKey(PositionTypeKey.vernepleier)).getValue();
		AccessType general_GRANT = ((ValueResultAccessType)beanFactory.getAccessService().getAccessTypeByTypeCategory(AccessTypeKey.general, AccessTypeCategory.GRANT)).getValue();
		ResourceType supplier_source = ((ValueResultResourceType)beanFactory.getAccessService().getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();
		SupplierSource source = SupplierSourceFactory.factory.completeSupplierSource();
		source.setUrl(new Url("url_" + randomValue));
		
		// Create objects
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(health_enterprise, vernepleier);
		memberOrganization.getOrganization().setNameEnglish("mem_org_" + randomValue);
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(content_supplier, vernepleier);
		supplierOrganization.getOrganization().setNameEnglish("sup_org_" + randomValue);
		User user = UserFactory.factory.completeUser(memberOrganization, vernepleier);
		user.setUsername("username_" + randomValue);
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(supplier_source, source, supplierOrganization);
		
		memberOrganization.setOrganization(((ValueResultOrganization)beanFactory.getOrganizationService().insertOrganization(memberOrganization.getOrganization())).getValue());
		supplierOrganization.setOrganization(((ValueResultOrganization)beanFactory.getOrganizationService().insertOrganization(supplierOrganization.getOrganization())).getValue());
		user.setOrganization(memberOrganization.getOrganization());
		user = ((ValueResultUser)beanFactory.getUserService().insertUser(user)).getValue();
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
		
//		TEST: public Boolean insertOrganizationAction(Organization organization, Resource resource, AccessType accessType);
		actionService.insertOrganizationAction(memberOrganization.getOrganization(), resource.getResource(), general_GRANT);

//		TEST: public ListResultAction getActionListByOrganization(Organization organization);
		Action[] organizationActions = actionService.getActionListByOrganization(memberOrganization.getOrganization()).getList();
		Assert.isTrue(organizationActions.length == 1, "Should have found one");
		
		
//		TEST: public ListResultAction getActionListByResource(Resource resource);
		Action[] resourceActions = actionService.getActionListByResource(resource.getResource()).getList();
		Assert.isTrue(resourceActions.length == 2, "Should have found one");
		
//		TEST: public ListResultAction getActionListByAccessType(AccessType accessType);
		Action[] accessTypeActions = actionService.getActionListByAccessType(general_GRANT).getList();
		Assert.isTrue(accessTypeActions.length - oldActions.length == 2, "Should have added two");
	}
}
