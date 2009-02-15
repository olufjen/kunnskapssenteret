package no.helsebiblioteket.admin.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class OrganizationServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	private String findMeName = "findMe123999";
	private MemberOrganization testOrganization(){
		MemberOrganization organization = new MemberOrganization();
		organization.getOrganization().setNameEnglish(findMeName);
		return organization;
	}
	@org.junit.Test
	public void testGetOrganizationTypeListAll(){
		ListResultOrganizationType list = beanFactory.getOrganizationService().getOrganizationTypeListAll("");
		Assert.assertNotNull("No result", list);
	}
	@org.junit.Test
	public void testGetOrganizationTypeByKey(){
		SingleResultOrganizationType result = beanFactory.getOrganizationService().getOrganizationTypeByKey(
				OrganizationTypeKey.content_supplier);
		Assert.assertNotNull("No result", result);
	}
	@org.junit.Test
	public void testGetOrganizationListAll(){
		PageRequest request = new FirstPageRequest(Integer.MAX_VALUE);
		PageResultOrganizationListItem result = beanFactory.getOrganizationService().getOrganizationListAll(request);
		Assert.assertNotNull("No result", result);
	}
	@org.junit.Test
	public void testFindOrganizationsBySearchString(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		organizationService.insertMemberOrganization(testOrganization());
		PageResultOrganizationListItem result = organizationService.findOrganizationsBySearchString(findMeName, null);
		Assert.assertNotNull("No result", result);
		Assert.assertTrue("Empty result", result.result.length>0);
		Assert.assertEquals("Not the same", result.result[0].getNameEnglish(), findMeName);
	}
	@org.junit.Test
	public void testGetOrganizationByListItem(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		organizationService.insertMemberOrganization(testOrganization());
		OrganizationListItem listItem = new OrganizationListItem();
		MemberOrganization organization = testOrganization();
//		listItem.setId(organization.getOrgUnitId());
		SingleResultOrganization result = organizationService.getOrganizationByListItem(listItem);
		Assert.assertNotNull("No result", result);
		Assert.assertTrue("No result", result instanceof ValueResultOrganization);
	}
	@org.junit.Test
	public void testUpdateOrganization(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		LoginService loginService = beanFactory.getLoginService();

		MemberOrganization organization = testOrganization();
		IpAddress ipAddress = new IpAddress();
		ipAddress.setAddress("192.101.1.2");
		IpAddressRange ipRange = new IpAddressRange();
		ipRange.setIpAddressFrom(new IpAddress("192.101.1.1"));
		ipRange.setIpAddressTo(new IpAddress("192.101.1.3"));
		IpAddressSet[] ipRangeList = new IpAddressSet[0];
		organization.setIpAddressSetList(ipRangeList);

		organizationService.insertMemberOrganization(organization);
		
		SingleResultMemberOrganization result = loginService.loginOrganizationByIpAddress(ipAddress);
		organization = ((ValueResultMemberOrganization)result).getValue();
		organization.getOrganization().setNameEnglish("changedName123");
		
		organizationService.updateMemberOrganization(organization);
		
		result = loginService.loginOrganizationByIpAddress(ipAddress);
		Assert.assertNotNull("No result", result);
		Assert.assertEquals("Not the same", organization.getOrganization().getNameEnglish(), "changedName123");
	}
}
