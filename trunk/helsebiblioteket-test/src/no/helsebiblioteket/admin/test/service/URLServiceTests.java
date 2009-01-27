package no.helsebiblioteket.admin.test.service;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class URLServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testIsAffected(){
		URLService urlService = beanFactory.getURLService();
		
		Access access = new Access();
		Organization supplier = new Organization();
		access.setProvidedBy(supplier);
		Resource resource = new Resource();
		
		
		Url url = new Url();
		url.setValue("http://www.google.no/firefox");
		SupplierSource supplierSource = new SupplierSource();
		supplierSource.setUrl(url);
		resource.setSupplierSourceId(null);
		
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
