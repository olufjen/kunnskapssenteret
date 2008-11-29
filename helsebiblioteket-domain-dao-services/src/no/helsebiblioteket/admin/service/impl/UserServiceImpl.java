package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private RoleDao roleDao;
	public void setUserDao(UserDao userDao) { this.userDao = userDao; }
	public User findUserByUsername(User user) { return userDao.findUserByUsername(user); }
	public void createUser(User user) { userDao.createUser(user); }
	public List<User> getAllUsers() {
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
		return userDao.getAllUsers();
	}
	public List<User> findUsersBySearchStringRoles(String searchString, List<Role> roles) {
		return this.userDao.getAllUsers(searchString, roles);
	}
	public List<Role> getAllUserRoles() {
		return this.roleDao.getAllRoles();
	}
}
