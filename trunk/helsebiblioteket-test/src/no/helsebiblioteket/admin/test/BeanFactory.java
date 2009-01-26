package no.helsebiblioteket.admin.test;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.test.dao.AccessDaoTests;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class BeanFactory {
	private Resource res;
	private XmlBeanFactory factory;
	private static BeanFactory single;
	public static BeanFactory factory(){
		if(single == null) single = new BeanFactory();
		return single;
	}
	private BeanFactory(){
		res = new FileSystemResource("..\\helsebiblioteket-administration-web\\WebRoot\\WEB-INF\\applicationContext.xml");
		factory = new XmlBeanFactory(res);
//		BasicDataSource dataSourceHelsebiblioteket = (BasicDataSource)this.factory.getBean("dataSourceHelsebiblioteket");
//		SqlMapClientFactoryBean mapClientFactoryBean = new SqlMapClientFactoryBean();
//		mapClientFactoryBean.setConfigLocation(new FileSystemResource("..\\helsebiblioteket-administration-web\\WebRoot\\WEB-INF\\sqlmap-config.xml"));
//		mapClientFactoryBean.setDataSource(dataSourceHelsebiblioteket);
//		this.factory.getb("sqlMapClient");
	}
	public AccessDao getAccessDao() { return (AccessDao)this.factory.getBean("accessDao");}

	
	public static void main(String[] args) {
//		BeanFactory beanFactory = new BeanFactory();
//		AccessDaoTests accessDaoTests = new AccessDaoTests();
//		accessDaoTests.testInsertAccess();
		
		org.junit.runner.JUnitCore.runClasses(AccessDaoTests.class);
	}
}
