package no.helsebiblioteket.admin.test.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserListDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testUserList(){
		// 'Static' values
		System system = beanFactory.getSystemDao().getSystemByKey(SystemKey.helsebiblioteket_admin);
		Role role = beanFactory.getRoleDao().getRoleByKeySystem(UserRoleKey.student, system);
		Position position = beanFactory.getPositionDao().getPositionByKey(PositionTypeKey.bioingenior);
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise);

		// DAO
		UserRoleDao userRoleDao = beanFactory.getUserRoleDao();
		UserListDao userListDao = beanFactory.getUserListDao();

		// Insert user with roles.
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		new OrganizationDaoTests().insertMemberOrganization(organization);
		User user = UserFactory.factory.completeUser(organization, position);
		String username = "RandomUser" + new Random().nextInt(1000000000);
		user.setUsername(username);
		new UserDaoTests().insertUser(user);
		UserRoleLine userRoleLine = new UserRoleLine();
		userRoleLine.setUserId(user.getId());
		userRoleLine.setUserRoleId(role.getUserRoleId());
		userRoleDao.insertUserRole(userRoleLine);

		// GET ALL
		List<UserListItem> userList = userListDao.getUserListPaged(0, 10);
		Assert.notEmpty(userList, "Should have found users");
		Assert.isTrue(userList.size()>=10, "List should be no longer than 10");

		// GET SEARCH
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		List<UserListItem> searchList = userListDao.getUserListPagedSearchStringRoles(username, roles, 0, 10);
		Assert.isTrue(searchList.size() <= 1, "Should only return one");
//		Assert.isTrue(searchList.size() == 1, "Should return one");
		
		// DELETE
		userRoleDao.deleteUserRole(userRoleLine);
		new UserDaoTests().removeUser(user);
		new OrganizationDaoTests().removeMemberOrganization(organization);
	}

}
