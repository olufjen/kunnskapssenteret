package no.helsebiblioteket.admin.test.dao;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class OrganizationDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testOrganization() {
		// DAO
		OrganizationTypeDao organizationTypeDao = beanFactory.getOrganizationTypeDao();
		PositionDao positionDao = beanFactory.getPositionDao();
		OrganizationDao organizationDao = beanFactory.getOrganizationDao();

		// 'Static' values
		OrganizationType organizationType = organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
		Position position = positionDao.getPositionByKey(PositionTypeKey.ambulansearbeider);
		
		// MemberOrganization
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		this.insertMemberOrganization(memberOrganization);
		Organization reloadMember = organizationDao.getOrganizationById(memberOrganization.getOrganization().getId());
		Assert.notNull(reloadMember, "Did not reload member organization");
		this.removeMemberOrganization(memberOrganization);
		reloadMember = organizationDao.getOrganizationById(memberOrganization.getOrganization().getId());
		Assert.isNull(reloadMember, "Did not delete member organization");
		
//		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(organizationType, position);
//		this.insertSupplierOrganization(supplierOrganization);
//		SupplierOrganization reloadSupplier = organizationDao.getSupplierOrganizationById(supplierOrganization.getOrganization().getId());
//		Assert.notNull(reloadSupplier, "Did not reload supplier organization");
//		this.removeSupplierOrganization(supplierOrganization);
//		reloadSupplier = organizationDao.getSupplierOrganizationById(supplierOrganization.getOrganization().getId());
//		Assert.isNull(reloadSupplier, "Did not delete member organization");
	}

	public void insertMemberOrganization(MemberOrganization memberOrganization){
		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.insertContactInformation(memberOrganization.getOrganization().getContactInformation());

		PersonDaoTests personDaoTests = new PersonDaoTests();
		personDaoTests.insertPerson(memberOrganization.getOrganization().getContactPerson());

		OrganizationDao organizationDao = beanFactory.getOrganizationDao();
		organizationDao.insertOrganization(memberOrganization.getOrganization());
	}
	public void removeMemberOrganization(MemberOrganization  memberOrganization){
		OrganizationDao organizationDao = beanFactory.getOrganizationDao();
		organizationDao.deleteOrganization(memberOrganization.getOrganization());

		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.removeContactInformation(memberOrganization.getOrganization().getContactInformation());
		
		PersonDaoTests personDaoTests = new PersonDaoTests();
		personDaoTests.removePerson(memberOrganization.getOrganization().getContactPerson());
	}
	public void insertSupplierOrganization(SupplierOrganization supplierOrganization){
		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.insertContactInformation(supplierOrganization.getOrganization().getContactInformation());
		
		PersonDaoTests personDaoTests = new PersonDaoTests();
		personDaoTests.insertPerson(supplierOrganization.getOrganization().getContactPerson());

		OrganizationDao organizationDao = beanFactory.getOrganizationDao();
		organizationDao.insertOrganization(supplierOrganization.getOrganization());
	}
	public void removeSupplierOrganization(SupplierOrganization supplierOrganization){
		OrganizationDao organizationDao = beanFactory.getOrganizationDao();
		organizationDao.deleteOrganization(supplierOrganization.getOrganization());
		
		ContactInformationDaoTests contactInformationDaoTests = new ContactInformationDaoTests();
		contactInformationDaoTests.removeContactInformation(supplierOrganization.getOrganization().getContactInformation());

		PersonDaoTests personDaoTests = new PersonDaoTests();
		personDaoTests.removePerson(supplierOrganization.getOrganization().getContactPerson());
	}
}
