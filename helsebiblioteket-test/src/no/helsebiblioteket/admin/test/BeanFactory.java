package no.helsebiblioteket.admin.test;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.dao.ActionDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.dao.SystemDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.service.ActionService;
import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.test.dao.AccessDaoTests;
import no.helsebiblioteket.admin.test.dao.AccessTypeDaoTests;
import no.helsebiblioteket.admin.test.dao.ActionDaoTests;
import no.helsebiblioteket.admin.test.dao.ContactInformationDaoTests;
import no.helsebiblioteket.admin.test.dao.IpRangeDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationListDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationNameDaoTests;
import no.helsebiblioteket.admin.test.dao.OrganizationTypeDaoTests;
import no.helsebiblioteket.admin.test.dao.PersonDaoTests;
import no.helsebiblioteket.admin.test.dao.PositionDaoTests;
import no.helsebiblioteket.admin.test.dao.ProfileDaoTests;
import no.helsebiblioteket.admin.test.dao.ResourceDaoTests;
import no.helsebiblioteket.admin.test.dao.ResourceTypeDaoTest;
import no.helsebiblioteket.admin.test.dao.RoleDaoTests;
import no.helsebiblioteket.admin.test.dao.SupplierSourceDaoTests;
import no.helsebiblioteket.admin.test.dao.SystemDaoTests;
import no.helsebiblioteket.admin.test.dao.UserDaoTests;
import no.helsebiblioteket.admin.test.dao.UserListDaoTests;
import no.helsebiblioteket.admin.test.dao.UserRoleDaoTests;
import no.helsebiblioteket.admin.test.service.AccessServiceTests;
import no.helsebiblioteket.admin.test.service.ActionServiceTests;
import no.helsebiblioteket.admin.test.service.EmailServiceTests;
import no.helsebiblioteket.admin.test.service.LoginServiceTests;
import no.helsebiblioteket.admin.test.service.OrganizationServiceTests;
import no.helsebiblioteket.admin.test.service.URLServiceTests;
import no.helsebiblioteket.admin.test.service.UserServiceTests;

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
	public ActionDao getActionDao() { return (ActionDao)this.factory.getBean("actionDao");}
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
	public ResourceDao getResourceDao() { return (ResourceDao)this.factory.getBean("resourceDao");}
	public SupplierSourceDao getSupplierSourceDao() { return (SupplierSourceDao)this.factory.getBean("supplierSourceDao");}
	public SystemDao getSystemDao() { return (SystemDao)this.factory.getBean("systemDao");}
	public UserListDao getUserListDao() { return (UserListDao)this.factory.getBean("userListDao");}
	public UserRoleDao getUserRoleDao() { return (UserRoleDao)this.factory.getBean("userRoleDao");}
	
	// Load Services
	public AccessService getAccessService() { return (AccessService)this.factory.getBean("accessService");}
	public ActionService getActionService() { return (ActionService)this.factory.getBean("actionService");}
	public EmailService getEmailService() { return (EmailService)this.factory.getBean("emailService");}
	public LoginService getLoginService() { return (LoginService)this.factory.getBean("loginService");}
	public OrganizationService getOrganizationService() { return (OrganizationService)this.factory.getBean("organizationService");}
	public URLService getURLService() { return (URLService)this.factory.getBean("urlService");}
	public UserService getUserService() { return (UserService)this.factory.getBean("userService");}
	
	// Main method when not using ant.
	public static void main(String[] args) {
		AccessDaoTests accessDaoTests = new AccessDaoTests();
		accessDaoTests.testAccess();
		AccessTypeDaoTests accessTypeDaoTests = new AccessTypeDaoTests();
		accessTypeDaoTests.testAccessType();
		ActionDaoTests actionDaoTests = new ActionDaoTests();
		actionDaoTests.testAction();
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
		ResourceDaoTests resourceDaoTests = new ResourceDaoTests();
		resourceDaoTests.testResource();
		ResourceTypeDaoTest resourceTypeDaoTest = new ResourceTypeDaoTest();
		resourceTypeDaoTest.testResourceType();
		RoleDaoTests roleDaoTests = new RoleDaoTests();
		roleDaoTests.testRole();
		SupplierSourceDaoTests supplierSourceDaoTests = new SupplierSourceDaoTests();
		supplierSourceDaoTests.testSupplierSource();
		SystemDaoTests systemDaoTests = new SystemDaoTests();
		systemDaoTests.testSystem();
		UserDaoTests userDaoTests = new UserDaoTests();
		userDaoTests.testUser();
		UserListDaoTests userListDaoTests = new UserListDaoTests();
		userListDaoTests.testUserList();
		UserRoleDaoTests userRoleDaoTests = new UserRoleDaoTests();
		userRoleDaoTests.testUserRole();
		
		AccessServiceTests accessServiceTests = new AccessServiceTests();
		accessServiceTests.testInsertAccess();
		
		ActionServiceTests actionServiceTests = new ActionServiceTests();
		actionServiceTests.testInsertAction();
		
		EmailServiceTests emailServiceTests = new EmailServiceTests();
		emailServiceTests.testSendMail();
		
		LoginServiceTests loginServiceTests = new LoginServiceTests();
		loginServiceTests.testAll();
		
		OrganizationServiceTests organizationServiceTests = new OrganizationServiceTests();
		organizationServiceTests.testAll();
		
		URLServiceTests urlServiceTests = new URLServiceTests();
		urlServiceTests.testAll();
		
		UserServiceTests userServiceTests = new UserServiceTests();
		userServiceTests.testAll();
		
		System.out.println("OK");
	}
}
