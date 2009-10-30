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
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.AccessResult;
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
		
		User user = UserFactory.factory.completeUser(ambulansearbeider);
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
		UserListItem userL = new UserListItem();
		userL.setId(user.getId());
		SingleResultUrl userUrl = urlService.translateUrlUser(userL, new Url(randomUrl));
		checkUrl(userUrl, randomUrl);

//		TEST: public SingleResultUrl translate(MemberOrganization organization, Url url);
		OrganizationListItem orgL = new OrganizationListItem();
		orgL.setId(memberOrganization.getOrganization().getId());
		SingleResultUrl organizationUrl = urlService.translateUrlOrganization(orgL, new Url(randomUrl));
		checkUrl(organizationUrl, randomUrl);
				
//		TEST: public SingleResultUrl translate(User user, MemberOrganization organization, Url url);
		SingleResultUrl userOrganizationUrl = urlService.translateUrlUserOrganization(userL, orgL, new Url(randomUrl));
		checkUrl(userOrganizationUrl, randomUrl);
		
//		TEST: public Boolean hasAccess(User user, Url url);
		AccessResult userAccess = urlService.hasAccessUser(userL, new Url(randomUrl));
		checkAccess(userAccess);
		
//		TEST: public Boolean hasAccess(MemberOrganization organization, Url url);
		AccessResult organizationAccess = urlService.hasAccessOrganization(orgL, new Url(randomUrl));
		checkAccess(organizationAccess);
		
//		TEST: public Boolean hasAccess(User user, MemberOrganization organization, Url url);
		AccessResult userOrganizationAccess = urlService.hasAccessUserOrganization(userL, orgL, new Url(randomUrl));
		checkAccess(userOrganizationAccess);
		
		// TODO Fase2: How do we test group?
//		TEST: public SingleResultString group(Url url);

		// Deleting test values
		AccessService accessService = beanFactory.getAccessService();
		OrganizationListItem item = new OrganizationListItem();
		item.setId(memberOrganization.getOrganization().getId());
		for (ResourceAccessListItem resourceAccessListItem : accessService.getAccessListByOrganization(item).getList()) {
			accessService.deleteResourceAccess(resourceAccessListItem);
		}
		UserListItem uItem = new UserListItem();
		uItem.setId(user.getId());
		for (ResourceAccessListItem resourceAccess : accessService.getAccessListByUser(uItem).getList()) {
			accessService.deleteResourceAccess(resourceAccess);
		}
		beanFactory.getAccessService().deleteSupplierSourceResource(resource);
	}
	private void checkUrl(SingleResultUrl singleResultUrl, String urlValue){
		Assert.isTrue(singleResultUrl instanceof ValueResultUrl, "Error rewriting URL");
		Assert.isTrue(((ValueResultUrl)singleResultUrl).getValue().getStringValue().contains(urlValue), "Error finding URL");
		Assert.isTrue(((ValueResultUrl)singleResultUrl).getValue().getStringValue().startsWith(this.urlPrefix), "URL not prefixed");
	}
	private void checkAccess(AccessResult value){
		Assert.notNull(value, "Should not be NULL");
		Assert.isTrue(value.getValue().equals(AccessResult.include.getValue()), "Should have access");
	}
	
	public void testPerformance() {
		Url myUrl = new Url();
		for (int i = 0; i < 100; i++) {
			myUrl.setStringValue("http://www.legehandboka.no");
			myUrl.setDomain("www.legehandboka.no");
			beanFactory.getURLService().isAffected(myUrl);
		}
	}
}
