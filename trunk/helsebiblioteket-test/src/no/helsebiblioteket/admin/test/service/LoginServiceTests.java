package no.helsebiblioteket.admin.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class LoginServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	private String username = "heltRandomMaaDetVaere";
	private String password = "12QWErthuytSD1";
	private User testUser(){
		User user = UserFactory.factory.createUser();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}
	@org.junit.Test
	public void testLoginUserByUsernamePassword() {
		UserService userService = beanFactory.getUserService();
		LoginService loginService = beanFactory.getLoginService();
		
		User user = testUser();
		userService.insertUser(user);
		
		SingleResultUser result = loginService.loginUserByUsernamePassword(username, password);
		Assert.assertTrue("User not found", result instanceof ValueResultUser);
	}
	@org.junit.Test
	public void testLoginOrganizationByIpAddress() {
		OrganizationService organizationService = beanFactory.getOrganizationService();
		LoginService loginService = beanFactory.getLoginService();

		IpAddress ipAddress = new IpAddress();
		ipAddress.setAddress("192.101.1.2");
		IpAddressRange ipRange = new IpAddressRange();
		ipRange.setIpAddressFrom(new IpAddress("192.101.1.1"));
		ipRange.setIpAddressTo(new IpAddress("192.101.1.3"));
		MemberOrganization organization = new MemberOrganization();
		IpAddressSet[] ipRangeList = new IpAddressSet[0];
		organization.setIpAddressSetList(ipRangeList);
		
		organizationService.insertMemberOrganization(organization);
		loginService.loginOrganizationByIpAddress(ipAddress);
	}
	@org.junit.Test
	public void testSendPasswordEmail() {
		LoginService loginService = beanFactory.getLoginService();
		// TODO: Test with Mock or something!
		User user = testUser();
		loginService.sendPasswordEmail(user);
	}
}
