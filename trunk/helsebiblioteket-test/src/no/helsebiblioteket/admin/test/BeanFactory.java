package no.helsebiblioteket.admin.test;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.test.dao.AccessDaoTests;
import no.helsebiblioteket.admin.test.dao.AccessTypeDaoTests;
import no.helsebiblioteket.admin.test.dao.ContactInformationDaoTests;
import no.helsebiblioteket.admin.test.dao.IpRangeDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationListDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationNameDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationTypeDaoTests;
import no.helsebiblioteket.admin.test.dao.PersonDaoTests;
import no.helsebiblioteket.admin.test.dao.PositionDaoTests;
import no.helsebiblioteket.admin.test.dao.ProfileDaoTests;

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
	public UserDao getUserDao() { return (UserDao)this.factory.getBean("userDao");}
	public OrganizationDao getOrganizationDao() { return (OrganizationDao)this.factory.getBean("organizationDao");}
	public OrganizationTypeDao getOrganizationTypeDao() { return (OrganizationTypeDao)this.factory.getBean("organizationTypeDao");}
	public ContactInformationDao getContactInformationDao() { return (ContactInformationDao)this.factory.getBean("contactInformationDao");}
	public ResourceTypeDao getResourceTypeDao() { return (ResourceTypeDao)this.factory.getBean("resourceTypeDao");}
	public RoleDao getRoleDao() { return (RoleDao)this.factory.getBean("roleDao");}
	public IpRangeDao getIpRangeDao() { return (IpRangeDao)this.factory.getBean("ipRangeDao");}
	public PositionDao getPositionDao() { return (PositionDao)this.factory.getBean("positionDao");}
	public PersonDao getPersonDao() { return (PersonDao)this.factory.getBean("personDao");}
	public ProfileDao getProfileDao() { return (ProfileDao)this.factory.getBean("profileDao");}
	public AccessTypeDao getAccessTypeDao() { return (AccessTypeDao)this.factory.getBean("accessTypeDao");}
	public OrganizationListDao getOrganizationListDao() { return (OrganizationListDao)this.factory.getBean("organizationListDao");}
	public OrganizationNameDao getOrganizationNameDao() { return (OrganizationNameDao)this.factory.getBean("organizationNameDao");}
	
	// Load Services
	public LoginService getLoginService() { return (LoginService)this.factory.getBean("loginService");}
	public UserService getUserService() { return (UserService)this.factory.getBean("userService");}
	public OrganizationService getOrganizationService() { return (OrganizationService)this.factory.getBean("organizationService");}
	public AccessService getAccessService() { return (AccessService)this.factory.getBean("accessService");}
	public URLService getURLService() { return (URLService)this.factory.getBean("urlService");}
	
	// Main method when not using ant.
	public static void main(String[] args) {
		AccessDaoTests accessDaoTests = new AccessDaoTests();
		accessDaoTests.testAccess();
		AccessTypeDaoTests accessTypeDaoTests = new AccessTypeDaoTests();
		accessTypeDaoTests.testAccessType();
		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.testContactInformation();
		IpRangeDaoTests ipRangeDaoTests = new IpRangeDaoTests();
		ipRangeDaoTests.testIpRange();
		OrganizationDaoTests organizationDaoTests = new OrganizationDaoTests();
		organizationDaoTests.testOrganization();
		OrganizationListDaoTests organizationListDaoTests = new OrganizationListDaoTests();
		organizationListDaoTests.testOrganizationList();
		OrganizationNameDaoTests organizationNameDaoTests = new OrganizationNameDaoTests();
		organizationNameDaoTests.testOrganizationName();
		OrganizationTypeDaoTests organizationTypeDaoTests = new OrganizationTypeDaoTests();
		organizationTypeDaoTests.testOrganizationType();
		PersonDaoTests personDaoTests = new PersonDaoTests();
		personDaoTests.testPerson();
		PositionDaoTests positionDaoTests = new PositionDaoTests();
		positionDaoTests.testPosition();
		ProfileDaoTests profileDaoTests = new ProfileDaoTests();
		profileDaoTests.testProfile();
		
//		LoginServiceTests loginServiceTests = new LoginServiceTests();
//		loginServiceTests.testLoginOrganizationByIpAddress();
//		loginServiceTests.testLoginUserByUsernamePassword();
//		loginServiceTests.testSendPasswordEmail();
//	
		System.out.println("OK");
	}
}
