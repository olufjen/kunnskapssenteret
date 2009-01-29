package no.helsebiblioteket.admin.test.dao;

import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ResourceTypeDaoTest {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testResourceType(){
		ResourceTypeDao resourceTypeDao = beanFactory.getResourceTypeDao();
		
	}
	public void insertResourceType(ResourceType resourceType){
		ResourceTypeDao resourceTypeDao = beanFactory.getResourceTypeDao();
		resourceTypeDao.insertResourceType(resourceType);
	}
	public void removeResourceType(ResourceType resourceType){
		ResourceTypeDao resourceTypeDao = beanFactory.getResourceTypeDao();
		resourceTypeDao.deleteResourceType(resourceType);
	}
}
