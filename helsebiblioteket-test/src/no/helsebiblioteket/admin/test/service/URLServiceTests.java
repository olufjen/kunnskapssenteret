package no.helsebiblioteket.admin.test.service;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class URLServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
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
		
		// TODO: Write tests.
//	    public Boolean isAffected(Url url);
//	    public SingleResult<Url> translate(User user, Url url);
//	    public SingleResult<Url> translate(Organization organization, Url url);
//	    public SingleResult<Url> translate(User user, Organization organization, Url url);
//	    public Boolean hasAccess(User user, Url url);
//	    public Boolean hasAccess(Organization organization, Url url);
//	    public Boolean hasAccess(User user, Organization organization, Url url);
//	    public SingleResult<String> group(Url url);
	}
}
