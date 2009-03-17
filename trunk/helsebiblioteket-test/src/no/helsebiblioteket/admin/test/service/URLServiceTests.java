package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceAccess;
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
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.ResourceAccessFactory;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class URLServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	private String urlPrefix = "http://proxy-t.helsebiblioteket.no";
	
	@org.junit.Test
	public void testAll(){
		String randomValue = "" + new Random().nextInt(1000000000);
		String randomUrl = "url_" + randomValue;
		
		
		// 'static' values
		ResourceType supplier_source = ((ValueResultResourceType)beanFactory.getAccessService().getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();
		SupplierSource supplierSource = SupplierSourceFactory.factory.completeSupplierSource();
		OrganizationType content_supplier = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.content_supplier)).getValue();
		OrganizationType health_enterprise = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
		Position ambulansearbeider = ((ValueResultPosition)beanFactory.getUserService().getPositionByKey(PositionTypeKey.ambulansearbeider, health_enterprise)).getValue();
		AccessType general_GRANT = ((ValueResultAccessType)beanFactory.getAccessService().getAccessTypeByTypeCategory(AccessTypeKey.general, AccessTypeCategory.GRANT)).getValue();
		
		// Inserting test values
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(content_supplier, ambulansearbeider);
		supplierOrganization = ((ValueResultSupplierOrganization)beanFactory.getOrganizationService().insertSupplierOrganization(supplierOrganization)).getValue();
		
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(supplier_source,
				supplierSource, supplierOrganization);
		resource.getSupplierSource().setUrl(new Url(randomUrl));
		resource = ((ValueResultSupplierSourceResource)beanFactory.getAccessService().insertSupplierSourceResource(resource)).getValue();
		
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(health_enterprise, ambulansearbeider);
		memberOrganization = ((ValueResultMemberOrganization)beanFactory.getOrganizationService().insertMemberOrganization(memberOrganization)).getValue();
		
		User user = UserFactory.factory.completeUser(memberOrganization, ambulansearbeider);
		user.setUsername("user_" + randomValue);
		user = ((ValueResultUser)beanFactory.getUserService().insertUser(user)).getValue();
		
		ResourceAccess access = ResourceAccessFactory.factory.completeResourceAccess(resource, general_GRANT, supplierOrganization);
		beanFactory.getAccessService().insertUserResourceAccess(user, access);
		beanFactory.getAccessService().insertOrganizationResourceAccess(memberOrganization.getOrganization(), access);
		
		
		// Service
		URLService urlService = beanFactory.getURLService();

//	    TEST: public Boolean isAffected(Url url);
		Boolean shouldFind = urlService.isAffected(new Url(randomUrl));
		Boolean shouldNotFind = urlService.isAffected(new Url(randomUrl + "_NOT_FIND"));
		Assert.isTrue(shouldFind, "URL is affected");
		Assert.isTrue( ! shouldNotFind, "URL is not affected");
		
//		TEST: public SingleResultUrl translate(User user, Url url);
		SingleResultUrl userUrl = urlService.translateUrlUser(user, new Url(randomUrl));
		checkUrl(userUrl, randomUrl);

//		TEST: public SingleResultUrl translate(MemberOrganization organization, Url url);
		SingleResultUrl organizationUrl = urlService.translateUrlOrganization(memberOrganization, new Url(randomUrl));
		checkUrl(organizationUrl, randomUrl);
				
//		TEST: public SingleResultUrl translate(User user, MemberOrganization organization, Url url);
		SingleResultUrl userOrganizationUrl = urlService.translateUrlUserOrganization(user, memberOrganization, new Url(randomUrl));
		checkUrl(userOrganizationUrl, randomUrl);
		
//		TEST: public Boolean hasAccess(User user, Url url);
		Boolean userAccess = urlService.hasAccessUser(user, new Url(randomUrl));
		checkAccess(userAccess);
		
//		TEST: public Boolean hasAccess(MemberOrganization organization, Url url);
		Boolean organizationAccess = urlService.hasAccessOrganization(memberOrganization, new Url(randomUrl));
		checkAccess(organizationAccess);
		
//		TEST: public Boolean hasAccess(User user, MemberOrganization organization, Url url);
		Boolean userOrganizationAccess = urlService.hasAccessUserOrganization(user, memberOrganization, new Url(randomUrl));
		checkAccess(userOrganizationAccess);
		
		// TODO: How do we test group?
//		TEST: public SingleResultString group(Url url);

		// Deleting test values
		AccessService accessService = beanFactory.getAccessService();
		for (ResourceAccessListItem resourceAccessListItem : accessService.getAccessListByOrganization(memberOrganization.getOrganization()).getList()) {
			accessService.deleteResourceAccess(resourceAccessListItem);
		}
		for (ResourceAccessListItem resourceAccess : accessService.getAccessListByUser(user).getList()) {
			accessService.deleteResourceAccess(resourceAccess);
		}
		beanFactory.getAccessService().deleteSupplierSourceResource(resource);
	}
	private void checkUrl(SingleResultUrl singleResultUrl, String urlValue){
		Assert.isTrue(singleResultUrl instanceof ValueResultUrl, "Error rewriting URL");
		Assert.isTrue(((ValueResultUrl)singleResultUrl).getValue().getStringValue().contains(urlValue), "Error finding URL");
		Assert.isTrue(((ValueResultUrl)singleResultUrl).getValue().getStringValue().startsWith(this.urlPrefix), "URL not prefixed");
	}
	private void checkAccess(Boolean value){
		Assert.notNull(value, "Should not be NULL");
		Assert.isTrue(value, "Should have access");
	}
	
	public void testPerformance() {
		Url myUrl = new Url();
		for (int i = 0; i < 100; i++) {
			myUrl.setStringValue("http://www.legehandboka.no");
			beanFactory.getURLService().isAffected(myUrl);
		}
	}
}
