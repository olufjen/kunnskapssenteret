package no.helsebiblioteket.admin.test.dao;

import java.util.List;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ResourceTypeDaoTest {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testResourceType(){
		ResourceTypeDao resourceTypeDao = beanFactory.getResourceTypeDao();
		ResourceType resourceType = resourceTypeDao.getResourceTypeByKey(ResourceTypeKey.supplier_source);
		Assert.notNull(resourceType, "Did not find supplier_source");
		
		List<ResourceType> list = resourceTypeDao.getResourceTypeListAll();
		Assert.notEmpty(list, "No resource types found");
	}
}
