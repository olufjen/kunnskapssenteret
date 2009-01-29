package no.helsebiblioteket.admin.test.dao;

import java.util.List;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.test.BeanFactory;

public class AccessTypeDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testAccessType() {
		AccessTypeDao accessTypeDao = beanFactory.getAccessTypeDao();

		List<AccessType> all = accessTypeDao.getAccessTypeListAll();
		Assert.notEmpty(all, "No accesstypes found!");
		
		AccessType accessType = accessTypeDao.getAccessTypeByKey(AccessTypeKey.general, AccessTypeCategory.DENY);
		Assert.notNull(accessType, "General/DENY not found!");
	}
}
