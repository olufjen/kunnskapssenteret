package no.helsebiblioteket.admin.test.service;

import java.util.Random;

import org.springframework.util.Assert;

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
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ListResultRole;
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.MemberOrganizationFactory;
import no.helsebiblioteket.admin.factory.UserFactory;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	public void testAll(){
		// 'static' values
		OrganizationType health_enterprise = ((ValueResultOrganizationType)beanFactory.getOrganizationService().getOrganizationTypeByKey(OrganizationTypeKey.health_enterprise)).getValue();
		
		UserService userService = beanFactory.getUserService();
		
//		TEST: public SingleResultSystem getSystemByKey(SystemKey key);
		SingleResultSystem resultSystem = userService.getSystemByKey(SystemKey.helsebiblioteket_admin);
		Assert.isTrue(resultSystem instanceof ValueResultSystem, "No system found");
		System system = ((ValueResultSystem)resultSystem).getValue();
		Assert.isTrue(system.getKey().getValue().equals(
				SystemKey.helsebiblioteket_admin.getValue()), "Wrong system found");
		
		
//		TEST: public ListResultRole getRoleListBySystem(System system);
		ListResultRole roleList = userService.getRoleListBySystem(system);
		Assert.isTrue(roleList.getList().length == 4, "Wrong number of roles for helsebiblioteket_admin");

//		TEST: public ListResultPosition getPositionListAll(String DUMMY);
		ListResultPosition positionList = userService.getPositionListAll("");
		Assert.isTrue(positionList.getList().length == 30, "Wrong number of positions in system");

//		TEST: public SingleResultPosition getPositionByKey(PositionTypeKey positionTypeKey, OrganizationType organizationType);
		SingleResultPosition positionResult = userService.getPositionByKey(PositionTypeKey.apotektekniker, health_enterprise);
		Assert.isTrue(positionResult instanceof ValueResultPosition, "No position found");
		Assert.isTrue(((ValueResultPosition)positionResult).getValue().getKey().getValue().equals(
				PositionTypeKey.apotektekniker.getValue()), "Wrong position");
		Position apotektekniker = ((ValueResultPosition)positionResult).getValue();

//		TEST: public SingleResultRole getRoleByKeySystem(UserRoleKey key, System system);
		SingleResultRole roleResult = userService.getRoleByKeySystem(UserRoleKey.student, system);
		Assert.isTrue(roleResult instanceof ValueResultRole, "No role found");
		Role student = ((ValueResultRole)roleResult).getValue();
		Assert.isTrue(student.getKey().getValue().equals(
				UserRoleKey.student.getValue()), "Wrong role");

		String randomValue = "" + new Random().nextInt(1000000000);
		MemberOrganization memberOrganization = MemberOrganizationFactory.factory.completeOrganization(health_enterprise, apotektekniker);
		memberOrganization = ((ValueResultMemberOrganization)beanFactory.getOrganizationService().insertMemberOrganization(memberOrganization)).getValue();
		User user = UserFactory.factory.completeUser(apotektekniker);
		String username = "username_" + randomValue;
		String firstname = "name_" + randomValue;
		String firstnameChanged = firstname + "CHANGED";
		String lastname = "Last";
		String fullNameChanged = firstnameChanged + " " + lastname;
		user.setUsername(username);
		user.getPerson().setFirstName(firstname);
		user.getPerson().setLastName(lastname);
//		TEST: public SingleResultUser insertUser(User user);
		// TODO: Finish tests.
		SingleResultUser userResult = userService.insertUser(user);
		Assert.isTrue(userResult instanceof ValueResultUser, "Failed to insert user");
		user = ((ValueResultUser)userResult).getValue();
		Assert.isTrue(user.getUsername().equals(username), "Wrong user returned");
		
		user.getPerson().setFirstName(firstnameChanged);
		user.setRoleList(new Role[1]);
		user.getRoleList()[0] = student;
//		TEST: public Boolean updateUser(User user);
		userService.updateUser(user);

		PageRequest request = new PageRequest(0, 20);
//		TEST: public PageResult<UserListItem> getUserListAll(PageRequest request);
		PageResultUserListItem usersAll = userService.getUserListAll(request);
		Assert.isTrue(usersAll.getResult().length == 20, "Wrong number in paged result");
		Assert.isTrue(usersAll.getResult()[0].getName().equals(fullNameChanged),
				"User not changed");

		Role health_personnel_other = ((ValueResultRole)beanFactory.getUserService().getRoleByKeySystem(UserRoleKey.health_personnel_other, system)).getValue();
		Role[] searchRoles = new Role[2];
		searchRoles[0] = student;
		searchRoles[1] = health_personnel_other;
//		TEST: public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest request);
		PageResultUserListItem usersSearch = userService.findUsersBySearchStringRoles("CHANGED", searchRoles, request);
		Assert.isTrue(usersAll.getResult()[0].getName().equals(fullNameChanged),
				"Wrong user found");
		
//		TEST: public SingleResultUser getUserByUserListItem(UserListItem userListItem);
		SingleResultUser userFromListResult = userService.getUserByUserListItem(usersSearch.getResult()[0]);
		Assert.isTrue(userFromListResult instanceof ValueResultUser, "User not found");
		Assert.isTrue(((ValueResultUser)userFromListResult).getValue().getPerson().getFirstName().equals(
				firstnameChanged), "Wrong user found");
				
//		TEST: public SingleResultUser findUserByUsername(String username);
		SingleResultUser userByUsername = userService.findUserByUsername(username);
		Assert.isTrue(userByUsername instanceof ValueResultUser, "User not found");
		Assert.isTrue(((ValueResultUser)userByUsername).getValue().getPerson().getFirstName().equals(
				firstnameChanged), "Wrong user found");
	}
}
