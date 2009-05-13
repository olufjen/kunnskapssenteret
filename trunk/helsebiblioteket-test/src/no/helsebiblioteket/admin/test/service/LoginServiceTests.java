package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.domain.requestresult.SendPasswordEmailResult;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class LoginServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	private String username = "heltRandomMaaDetVaere" + new Random().nextInt(1000000000);
	private String password = "12QWErthuytSD1";
	private String organizationName = "orgRandomName" + new Random().nextInt(1000000000);
	private String randomIp0 = "" + new Random().nextInt(1000);
	private String randomIp1 = "" + new Random().nextInt(1000);
	private String randomIp2 = "" + new Random().nextInt(1000);
	public void testAll() {
		testLoginUserByUsernamePassword();
		testLoginOrganizationByIpAddress();
		testSendPasswordEmail();
	}
	@org.junit.Test
	public void testLoginUserByUsernamePassword() {
		OrganizationService organizationService = beanFactory.getOrganizationService();
		UserService userService = beanFactory.getUserService();
		LoginService loginService = beanFactory.getLoginService();
		
		MemberOrganization memberOrganization = createMemberOrganization();
		User user = createUser(memberOrganization);
		OrganizationUser organizationUser = new OrganizationUser();
		organizationUser.setUser(user);
		organizationUser.setOrganization(((ValueResultMemberOrganization)organizationService.insertMemberOrganization(memberOrganization)).getValue().getOrganization());
		userService.insertUser(user);
		
//	    TEST: public SingleResultUser loginUserByUsernamePassword(String username, String password);
		LoggedInUserResult result = loginService.loginUserByUsernamePassword(username, password);
		Assert.isTrue( ! result.isSuccess(), "User not found");
		Assert.isTrue(result.getUser().getUsername().equals(this.username), "Wrong user.");
		
		result = loginService.loginUserByUsernamePassword(username, password + "_NOTFOUND_" + new Random().nextInt());
		Assert.isTrue(result.isSuccess(), "User should not have been logged in");
		result = loginService.loginUserByUsernamePassword(username + "_NOTFOUND_" + new Random().nextInt(), password);
		Assert.isTrue(result.isSuccess(), "User should not have been logged in");
	}
	@org.junit.Test
	public void testLoginOrganizationByIpAddress() {
		OrganizationService organizationService = beanFactory.getOrganizationService();
		LoginService loginService = beanFactory.getLoginService();

		IpAddress ipAddress = new IpAddress();
		String prefix = this.randomIp0 + "." + this.randomIp1 + "." + this.randomIp2 + ".";
		ipAddress.setAddress(prefix + "002");
		IpAddressRange ipRange = new IpAddressRange();
		ipRange.setIpAddressFrom(new IpAddress(prefix + "001"));
		ipRange.setIpAddressTo(new IpAddress(prefix + "003"));
		OrganizationType type = createOrganizationHealthEnterprise();
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(type, createPositionJordmor(type));
		organization.getOrganization().setNameEnglish(this.organizationName);
		IpAddressRange[] ipRangeList = new IpAddressRange[1];
		ipRangeList[0] = ipRange;
//		organization.setIpAddressRangeList(ipRangeList);
		
		organization = ((ValueResultMemberOrganization)organizationService.insertMemberOrganization(organization)).getValue();
		IpAddressSet[] res = organizationService.addIpAddressRanges(organization.getOrganization(), ipRangeList).getList();
		Assert.notEmpty(res, "Should have returned inserted ones");
		
//	    TEST: public SingleResultMemberOrganization loginOrganizationByIpAddress(IpAddress ipAddress);
		LoggedInOrganizationResult result = loginService.loginOrganizationByIpAddress(ipAddress);
		Assert.isTrue( ! result.isSuccess(), "Organization not found");
		// TODO Fase2: Re-insert
//		Assert.isTrue((result).getOrganization().getNameEnglish().equals(
//				this.organizationName), "Wrong organization");
		
		organizationService.deleteIpAddresses(res);
		result = loginService.loginOrganizationByIpAddress(ipAddress);
		Assert.isTrue(result.isSuccess(), "Should not have been found");
	}
	@org.junit.Test
	public void testSendPasswordEmail() {
		LoginService loginService = beanFactory.getLoginService();
		MemberOrganization memberOrganization = createMemberOrganization();
		User user = createUser(memberOrganization);

//	    TEST: public Boolean sendPasswordEmail(User user);
//		Look in the log for the result of this!
//		TODO Fase2: Test with some kind of mock instead?
	    SendPasswordEmailResult res = loginService.sendPasswordEmail(user.getUsername());
	    Assert.notNull(res, "Failed");
	    Assert.isTrue( ! res.getFailed(), "Failed");
	}

	private OrganizationType createOrganizationHealthEnterprise(){
		return ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
	}
	private Position createPositionJordmor(OrganizationType organizationType){
		return ((ValueResultPosition)beanFactory.getUserService().getPositionByKey(PositionTypeKey.jordmor, organizationType)).getValue();
	}
	private MemberOrganization createMemberOrganization(){
		OrganizationType type = createOrganizationHealthEnterprise();
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(
				type, createPositionJordmor(type));
		return memberOrganization;
	}
	private User createUser(MemberOrganization memberOrganization){
		OrganizationType type = createOrganizationHealthEnterprise();
		User user = UserFactory.factory.completeUser(createPositionJordmor(type));
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}
}
