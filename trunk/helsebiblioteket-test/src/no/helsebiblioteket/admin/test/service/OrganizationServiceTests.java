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
		organization.setNameEnglish(findMeName);
		return organization;
	}
	@org.junit.Test
	public void testGetOrganizationTypeListAll(){
		ListResult<OrganizationType> list = beanFactory.getOrganizationService().getOrganizationTypeListAll("");
		Assert.assertNotNull("No result", list);
	}
	@org.junit.Test
	public void testGetOrganizationTypeByKey(){
		SingleResult<OrganizationType> result = beanFactory.getOrganizationService().getOrganizationTypeByKey(
				OrganizationTypeKey.content_supplier);
		Assert.assertNotNull("No result", result);
	}
	@org.junit.Test
	public void testGetOrganizationListAll(){
		PageRequest<OrganizationListItem> request = new FirstPageRequest<OrganizationListItem>(Integer.MAX_VALUE);
		PageResult<OrganizationListItem> result = beanFactory.getOrganizationService().getOrganizationListAll(request);
		Assert.assertNotNull("No result", result);
	}
	@org.junit.Test
	public void testFindOrganizationsBySearchString(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		organizationService.insertOrganization(testOrganization());
		PageResult<OrganizationListItem> result = organizationService.findOrganizationsBySearchString(findMeName, null);
		Assert.assertNotNull("No result", result);
		Assert.assertTrue("Empty result", result.result.size()>0);
		Assert.assertEquals("Not the same", result.result.get(0).getNameEnglish(), findMeName);
	}
	@org.junit.Test
	public void testGetOrganizationByListItem(){
		OrganizationService organizationService = beanFactory.getOrganizationService();
		organizationService.insertOrganization(testOrganization());
		OrganizationListItem listItem = new OrganizationListItem();
		Organization organization = testOrganization();
//		listItem.setId(organization.getOrgUnitId());
		SingleResult<Organization> result = organizationService.getOrganizationByListItem(listItem);
		Assert.assertNotNull("No result", result);
		Assert.assertTrue("No result", result instanceof ValueResult);
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
		List<IpAddressSet> ipRangeList = new ArrayList<IpAddressSet>();
		organization.setIpAddressSetList(ipRangeList);

		organizationService.insertOrganization(organization);
		
		SingleResult<MemberOrganization> result = loginService.loginOrganizationByIpAddress(ipAddress);
		organization = ((ValueResult<MemberOrganization>)result).getValue();
		organization.setNameEnglish("changedName123");
		
		organizationService.updateOrganization(organization);
		
		result = loginService.loginOrganizationByIpAddress(ipAddress);
		Assert.assertNotNull("No result", result);
		Assert.assertEquals("Not the same", organization.getNameEnglish(), "changedName123");
	}
}
