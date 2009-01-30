package no.helsebiblioteket.admin.test.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.PositionFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class OrganizationNameDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testOrganizationName(){
		OrganizationDaoTests organizationDaoTests = new OrganizationDaoTests();
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.public_administration);
		Position position = PositionFactory.factory.completePosition(organizationType);
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		organizationDaoTests.insertMemberOrganization(organization);
		
		OrganizationNameDao organizationNameDao = beanFactory.getOrganizationNameDao();
		OrganizationName name = new OrganizationName();
		name.setCategory(OrganizationNameCategory.NORMAL);
		name.setLanguageCode(LanguageCategory.en);
		String nameText = "EnglishNormal" +new Random().nextInt(100000000);
		name.setName(nameText);
		organizationNameDao.insertOrganizationName(organization, name);
		
		List<OrganizationName> list = organizationNameDao.getOrganizationNameListByOrganization(organization);
		Assert.notEmpty(list, "Name not saved.");
		Assert.isTrue(list.get(0).getName().equals(nameText), "Name wrong");
		
		name = list.get(0);
		
		String newNameText = nameText + "CHANGED";
		name.setName(newNameText);
		organizationNameDao.updateOrganizationName(name);
		list = organizationNameDao.getOrganizationNameListByOrganization(organization);
		Assert.notEmpty(list, "Name disappeard");
		Assert.isTrue(list.get(0).getName().equals(newNameText), "Name wrong");
		
		organizationNameDao.deleteOrganizationName(name);
		list = organizationNameDao.getOrganizationNameListByOrganization(organization);
		Assert.isTrue(list.size() == 0, "Name was not deleted!");
		
	}
}
