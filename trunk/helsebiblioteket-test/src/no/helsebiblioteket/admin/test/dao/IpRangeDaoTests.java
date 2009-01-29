package no.helsebiblioteket.admin.test.dao;

import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

import org.springframework.util.Assert;

public class IpRangeDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testIpRange() {
		// DAO
		IpRangeDao ipRangeDao = beanFactory.getIpRangeDao();
		OrganizationTypeDao organizationTypeDao = beanFactory.getOrganizationTypeDao();
		PositionDao positionDao = beanFactory.getPositionDao();
		
		// Test classes
		OrganizationDaoTests organizationDaoTests = new OrganizationDaoTests();

		// TEST
		IpAddressLine addressLine = new IpAddressLine();
		addressLine.setIpAddressFrom("999.168.101.001");
		addressLine.setIpAddressTo("999.168.101.004");
		addressLine.setLastChanged(new Date());
		OrganizationType organizationType = organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
		Position position = positionDao.getPositionByKey(PositionTypeKey.ambulansearbeider);
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		organizationDaoTests.insertMemberOrganization(organization);
		addressLine.setOrgUnitId(organization.getId());
		ipRangeDao.insertIpRange(addressLine);
		addressLine.setIpAddressTo("999.168.101.003");
		ipRangeDao.updateIpRange(addressLine);
		List<IpAddressLine> ipAdressList = ipRangeDao.getIpRangeListByOrganization(organization);
		Assert.notEmpty(ipAdressList, "IP not found for org!");

		// Remove
		ipRangeDao.deleteIpRange(addressLine);

	}
}
