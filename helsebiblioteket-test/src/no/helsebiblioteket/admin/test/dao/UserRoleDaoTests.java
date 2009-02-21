package no.helsebiblioteket.admin.test.dao;

import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.RoleDao;
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
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserRoleDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testUserRole(){
		// 'static' values
		System system = beanFactory.getSystemDao().getSystemByKey(SystemKey.helsebiblioteket_admin);
		RoleDao roleDao = beanFactory.getRoleDao();
		Role healthPersonnelRole = roleDao.getRoleByKeySystem(UserRoleKey.health_personnel, system);
		Role studentRole = roleDao.getRoleByKeySystem(UserRoleKey.student, system);
		Position position = beanFactory.getPositionDao().getPositionByKey(PositionTypeKey.ambulansearbeider);
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise);

		// User
		MemberOrganization organization = MemberOrganizationFactory.factory.completeOrganization(organizationType, position);
		new OrganizationDaoTests().insertMemberOrganization(organization);
		User user = UserFactory.factory.completeUser(organization, position);
		user.setUsername("ROLETEST" + new Random().nextInt(1000000));
		new UserDaoTests().insertUser(user);
		
		// DAO
		UserRoleDao userRoleDao = beanFactory.getUserRoleDao();
		
		// INSERT
		UserRoleLine hRoleLine = createLine(user, healthPersonnelRole);
		userRoleDao.insertUserRole(hRoleLine);
		
		// GET
		List<UserRoleLine> list = userRoleDao.getUserRoleListByUser(user);
		Assert.isTrue(list.size()==1, "Should have one role");
		
		UserRoleLine sRoleLine = createLine(user, studentRole);
		userRoleDao.insertUserRole(sRoleLine);
		list = userRoleDao.getUserRoleListByUser(user);
		Assert.isTrue(list.size()==2, "Should have two roles");
		
		// Update is not tested!
		
		// DELETE
		userRoleDao.deleteUserRole(hRoleLine);
		list = userRoleDao.getUserRoleListByUser(user);
		Assert.isTrue(list.size()==1, "One role should have been deleted");
		userRoleDao.deleteUserRole(sRoleLine);
		list = userRoleDao.getUserRoleListByUser(user);
		Assert.isTrue(list.size()==0, "Both roles should have been deleted");
		new UserDaoTests().removeUser(user);
		new OrganizationDaoTests().removeMemberOrganization(organization);
	}
	private UserRoleLine createLine(User user, Role role){
		UserRoleLine roleLine = new UserRoleLine();
		roleLine.setLastChanged(null);
		roleLine.setUserId(user.getId());
		roleLine.setUserRoleId(role.getId());
		return roleLine;
	}
}
