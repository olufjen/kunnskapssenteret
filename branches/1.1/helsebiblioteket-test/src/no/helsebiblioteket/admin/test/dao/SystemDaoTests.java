package no.helsebiblioteket.admin.test.dao;

import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;

public class SystemDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testSystem(){
		System system = beanFactory.getSystemDao().getSystemByKey(SystemKey.helsebiblioteket_admin);
		Assert.notNull(system, "System not found");
	}
}
