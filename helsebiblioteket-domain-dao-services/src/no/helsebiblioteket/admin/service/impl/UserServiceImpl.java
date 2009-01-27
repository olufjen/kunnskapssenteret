package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.daoobjects.UserRole;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.PersonService;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private UserDao userDao;
	private UserListDao userListDao;
	private RoleDao roleDao;
	private UserRoleDao userRoleDao;
	private PersonDao personDao;
	private ProfileDao profileDao;
	private PositionDao positionDao;
	private ContactInformationDao contactInformationDao;
    private OrganizationDao organizationDao;
    private PersonService personService;
    private AccessDao accessDao;

	public ListResult<Role> getRoleListAll(String DUMMY) {
		List<Role> roleList = this.roleDao.getRoleListAll();
		Role[] roles = new Role[roleList.size()];
		int i = 0;
		for (Role role : roleList) {
			roles[i++] = role;
		}
		return new ListResult<Role>(roles);
	}
	public ListResult<Position> getPositionListAll(String DUMMY) {
		List<Position> all = this.positionDao.getPositionListAll();
		Position[] positions = new Position[all.size()];
		int i = 0;
		for (Position position : all) {
			positions[i++]=position;
		}
		return new ListResult<Position>(positions);
	}
	public SingleResult<Role> getRoleByKey(String key) {
		Role role = this.roleDao.getRoleByKey(key);
		if(role==null){
			return new EmptyResult<Role>();
		} else {
			return new ValueResult<Role>(role);
		}
	}
	public PageResult<User> getUserListAll(PageRequest<User> request) {
		PageResult<User> result = new PageResult<User>();
		result.result = this.userListDao.getUserListAll();
		result.from = 0;
		result.total = result.result.size();
		return result;
	}
	public PageResult<User> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<User> request) {
		// FIXME: Cache and page results
	
		PageResult<User> all = this.getUserListAll(request);
		List<User> some = new ArrayList<User>();
		for (User user : all.result) {
			if(user.getPerson().getName().toLowerCase().contains(searchString.toLowerCase()) ||
					user.getUsername().toLowerCase().contains(searchString.toLowerCase())){
				for (Role role : roles) {
					if(user.hasRole(role)) { some.add(user); break; }
				}
			}
		}

		PageResult<User> result = new PageResult<User>();
		result.result = some;//this.userCompositeDao.getgetAllUsers(searchString, roles);
		result.from = 0;
		result.total = result.result.size();
		return result;
	}
    public SingleResult<User> findUserByUsername(String username) {
		User user = this.userDao.getUserByUsername(username);
		if(user == null){
			return new EmptyResult<User>();
		} else {
			// TODO: Add more data here!
			List<UserRole> userRoleList = this.userRoleDao.getUserRoleListByUser(user);
			List<Role> roleList = new ArrayList<Role>();
			for (UserRole userRole : userRoleList) {
				roleList.add(this.roleDao.getRoleByKey(userRole.getUserRole().getKey()));
			}
			user.setRoleList(roleList);
			user.setOrganization(this.organizationDao.getOrganizationById(user.getOrganization().getId()));
			user.setPerson(this.personDao.getPersonByUser(user));
			user.setAccessList(this.accessDao.getAccessListByUser(user));
			return new ValueResult<User>(user);
		}
	}
	public Boolean insertUser(User user) {
		this.organizationDao.insertOrganization(user.getOrganization());
		this.personDao.insertPerson(user.getPerson());
		for (Access access : user.getAccessList()) {
			this.accessDao.insertAccess(access);
		}
		for (Role role : user.getRoleList()) {
			this.roleDao.insertRole(role);
		}
		this.userDao.insertUser(user);
		return Boolean.TRUE;
	}
	public Boolean updateUser(User user) {
		this.organizationDao.updateOrganization(user.getOrganization());
		this.personDao.updatePerson(user.getPerson());
		for (Access access : user.getAccessList()) {
			this.accessDao.updateAccess(access);
		}
		for (Role role : user.getRoleList()) {
			this.roleDao.updateRole(role);
		}
		this.userDao.updateUser(user);
		return Boolean.TRUE;
//		// TODO: How to get users without person object!
//		// TODO: Does it fetch everything now?
//		User old = null;// this.userDao.getUserByUsername(user);
//		
//		if(user.getAccessList() != null){
//			for (Access access : user.getAccessList()) {
//				// FIXME: Save access.
//				AccessType accessType = access.getType();
//				SupplierSource supplierSource = access.getSupplierSource();
//				ResourceType resourceType = supplierSource.getResourceType();
//			}
//		}
//		// TODO: Assume that these are saved?
//		Organization organization = user.getOrganization();
//		if(organization==null) { user.setOrganization(old.getOrganization()); }
//		for (Role role : user.getRoleList()) {
////			if(role==null) { user.setRole(old.getRole()); }
//		}
//		
//		// FIXME: Save person.
//		Person person = user.getPerson();
//		if(person==null) {
//			user.setPerson(old.getPerson());
//		} else {
//			ContactInformation contactInformation = person.getContactInformation();
//			if(contactInformation==null) { user.getPerson().setContactInformation(old.getPerson().getContactInformation()); }
//			Position position = user.getPerson().getPosition();
//			if(position==null){ user.getPerson().setPosition(old.getPerson().getPosition()); }
//			Profile profile = person.getProfile();
//			if(profile==null){ user.getPerson().setProfile(old.getPerson().getProfile()); }
//		}
//
//		if(user.getPerson()==null) {
//			// TODO: How to save users without person object!
//			return null;
//		} 
//		// Move backwards
//		//this.personDao.updateContactInformation(user.getPerson().getContactInformation());
////		this.personDao.updatePosition(user.getPerson().getPosition());
//		user.getPerson().getProfile().setPerson(user.getPerson());
//		if(user.getPerson().getProfile() != null){
//			// TODO: Should all persons have a profile?
//			//	this.personDao.updateProfile(user.getPerson().getProfile());
//		}
//		// TODO: What to do here?
////		user.getPerson().setUser(user);
//		this.personDao.updatePerson(user.getPerson());
//		this.userDao.updateUser(user);
//
//		// TODO: Write this.
//		
//		return Boolean.TRUE;
//		
//		// TODO: Use this from UserServiceImpl2 instead???
////		if (changedUser.getId() != null) {
////			if (originalUser == null || (originalUser != null && !originalUser.getLastChanged().equals(changedUser.getLastChanged()))) {
////				throw new OptimisticLockingFailureException("User has been changed since last time loaded from datastore");
////			}
////			savePerson(changedUser.getPerson(), originalUser.getPerson());
////			userDao.updateUser(changedUser);
////			userDao.setForeignKeysForUser(changedUser);
////			// save access
////			saveUserRoleLineListForUser(changedUser, originalUser);
////		} else {
////			savePerson(changedUser.getPerson(), null);
////			userDao.insertUser(changedUser);
////			userDao.setForeignKeysForUser(changedUser);
////			// save access
////			saveUserRoleLineListForUser(changedUser, null);
////		}
////		
////		return changedUser;
//
	}
	
	
	
	
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	public void setContactInformationDao(ContactInformationDao contactInformationDao) {
		this.contactInformationDao = contactInformationDao;
	}
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}
