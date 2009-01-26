package no.helsebiblioteket.admin.test.dao;

import java.util.Date;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.factory.AccessFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;

public class AccessDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertAccess() {
		AccessDao accessDao = this.beanFactory.getAccessDao();

		Access access = AccessFactory.factory.createAccess();
		
		
		accessDao.insertAccess(access);
		Assert.notNull(accessDao, "Should be something!");
	}	
}
