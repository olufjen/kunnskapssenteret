package no.helsebiblioteket.admin.test.service;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class URLServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	public void testAll(){
		testIsAffected();
		// TODO: Write tests.
//	    TEST: public Boolean isAffected(Url url);
//		TEST: public SingleResultUrl translate(User user, Url url);
//		TEST: public SingleResultUrl translate(MemberOrganization organization, Url url);
//		TEST: public SingleResultUrl translate(User user, MemberOrganization organization, Url url);
//		TEST: public Boolean hasAccess(User user, Url url);
//		TEST: public Boolean hasAccess(MemberOrganization organization, Url url);
//		TEST: public Boolean hasAccess(User user, MemberOrganization organization, Url url);
//		TEST: public SingleResultString group(Url url);

		
	}
	@org.junit.Test
	public void testIsAffected(){
		URLService urlService = beanFactory.getURLService();
		
		ResourceAccess access = new ResourceAccess();
		SupplierOrganization supplier = new SupplierOrganization();
		access.getAccess().setProvidedBy(supplier);
		SupplierSourceResource resource = new SupplierSourceResource();
		
		
		Url url = new Url();
		url.setStringValue("http://www.google.no/firefox");
		SupplierSource supplierSource = new SupplierSource();
		supplierSource.setUrl(url);
		resource.setSupplierSource(supplierSource);
		
	}
}
