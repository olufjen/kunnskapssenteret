package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private RoleDao roleDao;
	public void setUserDao(UserDao userDao) { this.userDao = userDao; }
	public User findUserByUsername(User user) { return userDao.findUserByUsername(user); }
	public void createUser(User user) { userDao.createUser(user); }
	public List<Role> getAllUserRoles() { return this.roleDao.getAllRoles(); }
	public void setRoleDao(RoleDao roleDao) { this.roleDao = roleDao; }
	public PageResult<User> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<User> request) {
		// FIXME: Cache and page results
		PageResult<User> result = new PageResult<User>();
		result.result = this.userDao.getAllUsers(searchString, roles);
		result.from = 0;
		result.total = result.result.size();
		return result;
	}
	public PageResult<User> getAllUsers(PageRequest<User> request) {
		//FIXME: Delete!
//		List<User> myUsers = new ArrayList<User>();
//		{
//			User user = new User();
//			user.setUsername("fredriks");
//			List<Role> roleList  = new ArrayList<Role>();
//			Role role = new Role();
//			role.setRoleName("User");
//			roleList.add(role);
//			user.setRoleList(roleList);
//			Organization organizaion = new Organization();
//			organizaion.setName("Ullevål Sykehus");
//			user.setOrganization(organizaion);
//			user.setId(123);
//			myUsers.add(user);
//		}
//		{
//			User user = new User();
//			user.setUsername("leiftorger");
//			List<Role> roleList  = new ArrayList<Role>();
//			Role role = new Role();
//			role.setRoleName("User");
//			roleList.add(role);
//			Role role2 = new Role();
//			role2.setRoleName("Admin");
//			roleList.add(role2);
//			user.setRoleList(roleList);
//			Organization organization = new Organization();
//			organization.setName("Rikshospitalet");
//			user.setOrganization(organization);
//			user.setId(321);
//			myUsers.add(user);
//		}
//		this.users = myUsers;
//		return users;
		PageResult<User> result = new PageResult<User>();
		result.result = userDao.getAllUsers();
		result.from = 0;
		result.total = result.result.size();
		return result;
	}
	public List<Position> getAllUserPositions() {
		// FIXME: Implement this with DB fetch!
		List<Position> all = new ArrayList<Position>();
		{ Position position = new Position(); position.setKey("NRS"); position.setTitle("Nurse"); all.add(position); }
		{ Position position = new Position(); position.setKey("DCT"); position.setTitle("Doctor"); all.add(position); }
		return all;
	}
}
