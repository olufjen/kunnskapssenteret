package no.helsebiblioteket.admin.test.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.test.BeanFactory;

public class OrganizationListDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testOrganizationList(){
		OrganizationListDao organizationListDao = beanFactory.getOrganizationListDao();
		OrganizationDao organizationDao = beanFactory.getOrganizationDao();
		IpRangeDao ipRangeDao = beanFactory.getIpRangeDao();

		// Can never find this!
		List<OrganizationListItem> organizationList = organizationListDao.getOrganizationListPagedSearchString(""+new Random().nextInt(), false, 0, Integer.MAX_VALUE);
		Assert.isTrue(organizationList.size()==0, "Should find no orgs");

		organizationList = organizationListDao.getOrganizationListPagedSearchString("", false, 0, 20);
		OrganizationListItem item = organizationList.get(0);
//		OrganizationNameDao organizationNameDao = beanFactory.getOrganizationNameDao();
		Assert.isTrue(organizationList.size()<=20, "Too many orgs");
		
		MemberOrganization organization = new MemberOrganization();
		organization.setOrganization(organizationDao.getOrganizationById(item.getId()));
		OrganizationName organizationName = new OrganizationName();
		organizationName.setCategory(OrganizationNameCategory.NORMAL);
		organizationName.setLanguageCode(LanguageCategory.en);
		organizationName.setLastChanged(new Date());
		organizationName.setName("English name" + new Random(1000000000).nextInt());
		// TODO Fase2: Test differently
//		organizationNameDao.insertOrganizationName(organization.getOrganization(), organizationName);

		int randomaddress1 = new Random().nextInt(1000);
		int randomaddress2 = new Random().nextInt(1000);
	    NumberFormat formatter = new DecimalFormat("000");

	    String random1 = formatter.format(randomaddress1);
	    String random2 = formatter.format(randomaddress2);
	    String random = "999." + random1 + "." + random2 + ".";
	    
	    IpAddressLine addressLine = new IpAddressLine();
		addressLine.setIpAddressFrom(random + "001");
		addressLine.setIpAddressTo(random + "004");
		addressLine.setLastChanged(new Date());
		addressLine.setOrgUnitId(item.getId());
		ipRangeDao.insertIpRange(addressLine);

		IpAddress ipAddress = new IpAddress(random + "004");
		organizationList = organizationListDao.getOrganizationListByIpAddress(ipAddress);
		Assert.notEmpty(organizationList, "Should have found IP");
		
		ipAddress.setAddress(random + "005");
		organizationList = organizationListDao.getOrganizationListByIpAddress(ipAddress);
		Assert.isTrue(organizationList.size()==0, "Should have been out of range");
	}
}
