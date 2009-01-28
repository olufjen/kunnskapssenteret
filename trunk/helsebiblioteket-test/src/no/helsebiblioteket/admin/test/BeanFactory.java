package no.helsebiblioteket.admin.test;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.test.dao.AccessDaoTests;
import no.helsebiblioteket.admin.test.service.LoginServiceTests;

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

		// TODO: Replace bean factory with something else when we know more about JUnit and Spring.
		// TODO: Before running replace the configLocation property for bean 'sqlMapClient' with this:
		//       <property name="configLocation" value="file:C:/Data/Eclipse-Workspaces/Diverse-prosjekter-SWAT/helsebiblioteket-administration-web/WebRoot/WEB-INF/sqlmap-config.xml"/>
		//       And replace these values in the list 'propertyConfigurer.locations'
	 	//		<value>file:C:/Data/Eclipse-Workspaces/Diverse-prosjekter-SWAT/helsebiblioteket-administration-web/WebRoot/WEB-INF/application.properties</value>
	 	//		<value>file:C:/Data/Eclipse-Workspaces/Diverse-prosjekter-SWAT/helsebiblioteket-administration-web/WebRoot/WEB-INF/jdbc.properties</value>
		res = new FileSystemResource(".\\desc\\applicationContext.xml");
//		res = new FileSystemResource("..\\helsebiblioteket-administration-web\\WebRoot\\WEB-INF\\applicationContext.xml");
		factory = new XmlBeanFactory(res);
	}
	// Load DAOs
	public AccessDao getAccessDao() { return (AccessDao)this.factory.getBean("accessDao");}

	// Load Services
	public LoginService getLoginService() { return (LoginService)this.factory.getBean("loginService");}
	public UserService getUserService() { return (UserService)this.factory.getBean("userService");}
	public OrganizationService getOrganizationService() { return (OrganizationService)this.factory.getBean("organizationService");}
	public AccessService getPersonService() { return (AccessService)this.factory.getBean("personService");}
	public URLService getURLService() { return (URLService)this.factory.getBean("urlService");}
	
	// Main method when not using ant.
	public static void main(String[] args) {
		AccessDaoTests accessDaoTests = new AccessDaoTests();
		accessDaoTests.testInsertAccess();
		LoginServiceTests loginServiceTests = new LoginServiceTests();
		loginServiceTests.testLoginOrganizationByIpAddress();
		loginServiceTests.testLoginUserByUsernamePassword();
		loginServiceTests.testSendPasswordEmail();
	
		System.out.println("OK");
	}
}
