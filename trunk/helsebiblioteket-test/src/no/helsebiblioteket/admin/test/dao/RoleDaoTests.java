package no.helsebiblioteket.admin.test.dao;

import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.test.BeanFactory;

public class RoleDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testRole(){
		RoleDao roleDao = beanFactory.getRoleDao();
		
	}

}
