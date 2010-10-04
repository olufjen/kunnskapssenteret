package no.helsebiblioteket.admin.test.dao;

import java.util.List;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.test.BeanFactory;

public class OrganizationTypeDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testOrganizationType(){
		OrganizationTypeDao typeDao = beanFactory.getOrganizationTypeDao();
		OrganizationType type = typeDao.getOrganizationTypeByKey(OrganizationTypeKey._teaching);
		Assert.notNull(type, "Did not find teaching");
		List<OrganizationType> list = typeDao.getOrganizationTypeListAll();
		Assert.isTrue(list.size()>=5, "Org types are misssing");
	}
}
