package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.OptimisticLockingFailureException;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl2 implements UserService {

	// TODO: Remove this class!

	@Override
	public SingleResult<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ListResult<Position> getPositionListAll(String DUMMY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageResult<UserListItem> findUsersBySearchStringRoles(
			String searchString, List<Role> roles,
			PageRequest<UserListItem> request) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public PageResult<UserListItem> getUserListAll(
			PageRequest<UserListItem> request) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ListResult<Role> getRoleListBySystem(System system) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public SingleResult<Role> getRoleByKeySystem(UserRoleKey key, System system) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SingleResult<Position> getPositionByKey(
			PositionTypeKey positionTypeKey) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SingleResult<System> getSystemByKey(SystemKey key) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private UserDao userDao;
//	private RoleDao roleDao;
//	private PersonDao personDao;
//	private ProfileDao profileDao;
//	private ContactInformationDao contactInformationDao;
//	private static final Logger LOG = Logger.getLogger(UserServiceImpl2.class.toString());
//	
//	public UserDao getUserDao() {
//		return userDao;
//	}
//
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//	public RoleDao getRoleDao() {
//		return roleDao;
//	}
//
//	public void setRoleDao(RoleDao roleDao) {
//		this.roleDao = roleDao;
//	}
//
//	public PersonDao getPersonDao() {
//		return personDao;
//	}
//
//	public void setPersonDao(PersonDao personDao) {
//		this.personDao = personDao;
//	}
//
//	public ProfileDao getProfileDao() {
//		return profileDao;
//	}
//
//	public void setProfileDao(ProfileDao profileDao) {
//		this.profileDao = profileDao;
//	}
//
//	public ContactInformationDao getContactInformationDao() {
//		return contactInformationDao;
//	}
//
//	public void setContactInformationDao(ContactInformationDao contactInformationDao) {
//		this.contactInformationDao = contactInformationDao;
//	}
//
//	public User createUser(User user) {
//		saveUser(user, null);
//		return user;
//	}
//
//	public User findUserByUsername(User user) {
//		return userDao.getUserByUsername(user);
//	}
//
//	@Override
//	public PageResult<User> findUsersBySearchStringRoles(String searchString,
//			List<Role> roles, PageRequest<User> request) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Position> getAllUserPositions() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Role> getAllUserRoles() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PageResult<User> getAllUsers(PageRequest<User> request) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//	public Role getRoleByKey(Role role) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public User saveUser(User user) {
//		saveUser(user, userDao.getUserById(user.getId()));
//		return user;
//	}
//	
//	public User saveUser(User changedUser, User originalUser) {
//		if (changedUser.getId() != null) {
//			if (originalUser == null || (originalUser != null && !originalUser.getLastChanged().equals(changedUser.getLastChanged()))) {
//				throw new OptimisticLockingFailureException("User has been changed since last time loaded from datastore");
//			}
//			savePerson(changedUser.getPerson(), originalUser.getPerson());
//			userDao.updateUser(changedUser);
//			userDao.setForeignKeysForUser(changedUser);
//			// save access
//			saveUserRoleLineListForUser(changedUser, originalUser);
//		} else {
//			savePerson(changedUser.getPerson(), null);
//			userDao.insertUser(changedUser);
//			userDao.setForeignKeysForUser(changedUser);
//			// save access
//			saveUserRoleLineListForUser(changedUser, null);
//		}
//		
//		return changedUser;
//	}
//	
//	private void savePerson(Person changedPerson, Person originalPerson) {
//		
//		contactInformationDao.saveContactInformation(changedPerson.getContactInformation(), (originalPerson != null) ? originalPerson.getContactInformation() : null);
//		profileDao.saveProfile(changedPerson.getProfile(), (originalPerson != null) ? originalPerson.getProfile() : null);
//		
//		if (changedPerson.getId() != null) {
//			personDao.updatePerson(changedPerson);
//		} else {
//			personDao.insertPerson(changedPerson);
//		}
//	}
//	
//	private void saveUserRoleLineListForUser(User changedUser, User originalUser) {
//		List<UserRole> changedUserRoleLineList = null;
//		List<UserRole> originalUserRoleLineList = null;
//		UserRole userRoleLine = null;
//		if (changedUser != null && changedUser.getRoleList() != null) {
//			changedUserRoleLineList = new ArrayList<UserRole>();
//			userRoleLine = null;
//			for (Role role : changedUser.getRoleList()) {
//				userRoleLine = new UserRole();
//				userRoleLine.setUserId(changedUser.getId());
//				userRoleLine.setUserRoleId(role.getRoleId());
//				changedUserRoleLineList.add(userRoleLine);
//			}
//		}
//		if (originalUser != null && originalUser.getRoleList() != null) {
//			originalUserRoleLineList = new ArrayList<UserRole>();
//			userRoleLine = null;
//			for (Role role : changedUser.getRoleList()) {
//				userRoleLine = new UserRole();
//				userRoleLine.setUserId(originalUser.getId());
//				userRoleLine.setUserRoleId(role.getRoleId());
//				originalUserRoleLineList.add(userRoleLine);
//			}
//		}
//		
//		ModifiedListHelper<UserRole> listHelper = new ModifiedListHelper<UserRole>();
//		List<UserRole> deleteList = listHelper.getDeleteList(changedUserRoleLineList, originalUserRoleLineList);;
//		List<UserRole> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedUserRoleLineList, originalUserRoleLineList);
//		if (deleteList != null) {
//			for (UserRole line : deleteList) {
//				roleDao.deleteUserRoleLine(line);
//			}
//		}
//		if (insertAndUpdateList != null) {
//			for (UserRole line : insertAndUpdateList) {
//				// rolelines can _only_ be inserted, not updated
//				if (line.getUserRoleId() == null) {
//					LOG.log(Level.SEVERE, "userRoleId is NULL for user with username '" + changedUser.getUsername() + "'");
//				} else {
//					roleDao.insertUserRoleLine(line);
//				}
//			}
//		}
//	}
}
