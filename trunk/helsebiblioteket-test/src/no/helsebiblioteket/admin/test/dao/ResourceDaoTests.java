package no.helsebiblioteket.admin.test.dao;

import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ResourceDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testResource(){
		// This is not in use.
		ResourceDao resourceDao = null;
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.createSupplierSourceResource();
	}
}
