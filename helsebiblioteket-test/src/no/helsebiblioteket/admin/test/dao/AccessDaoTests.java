package no.helsebiblioteket.admin.test.dao;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.factory.AccessFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;

public class AccessDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testInsertAccess() {
		AccessDao accessDao = this.beanFactory.getAccessDao();
		ResourceAccess insertAccess = null;//AccessFactory.factory.createAccess();
		accessDao.insertAccess(insertAccess);
//		Access lookUpAccess = accessDao.getAccessListByUser(user);
		
		accessDao.deleteAccess(insertAccess);
		Assert.notNull(accessDao, "Should be something!");
	}
//	public void insertAccess(Access access);
//	public void updateAccess(Access access);
//	public void deleteAccess(Access access);

//	public List<Access> getAccessListByUser(User user);
//	public List<Access> getAccessListByOrganization(Organization organization);
//	public List<Access> getAccessListByOrganizationType(OrganizationType organizationType);

}
