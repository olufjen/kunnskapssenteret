package no.helsebiblioteket.admin.test.dao;

import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testUser(){
		// 'Static' data
		Position position = beanFactory.getPositionDao().getPositionByKey(PositionTypeKey.bioingenior);
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise);
		
		// Organization
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		new OrganizationDaoTests().insertMemberOrganization(organization);
		
		User user = UserFactory.factory.completeUser(organization, position);
		String username1 = "RandomUser" + new Random().nextInt(1000000000);
		String username2 = "RandomUser" + new Random().nextInt(1000000000);
		
		// INSERT
		user.setUsername(username1);
		this.insertUser(user);
		
		// GET
		UserDao userDao = beanFactory.getUserDao();
		user = userDao.getUserByUsername(username1);
		Assert.notNull(user, "User not found");
		
		
		// UPDATE
		user.setUsername(username2);
		userDao.updateUser(user);
		User notFound = userDao.getUserByUsername(username1);
		Assert.isNull(notFound, "User should not be found");
		user = userDao.getUserByUsername(username2);
		Assert.notNull(user, "User not found");
		
		// DELETE
		this.removeUser(user);
		new OrganizationDaoTests().removeMemberOrganization(organization);
	}

	public void insertUser(User user){
		UserDao userDao = beanFactory.getUserDao();
		userDao.insertUser(user);
	}
	public void removeUser(User user){
		UserDao userDao = beanFactory.getUserDao();
		userDao.deleteUser(user);
	}
}
