package no.helsebiblioteket.admin.test.dao;

import java.util.List;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.test.BeanFactory;

public class RoleDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testRole(){
		System system = beanFactory.getSystemDao().getSystemByKey(SystemKey.helsebiblioteket_admin);

		RoleDao roleDao = beanFactory.getRoleDao();
		
		// Single
		Role role = roleDao.getRoleByKeySystem(UserRoleKey.student, system);
		Assert.notNull(role, "student role not found");

		// All
		List<Role> list = roleDao.getRoleListBySystem(system);
		Assert.isTrue(list.size()>=4, "Too few roles");
	}
}
