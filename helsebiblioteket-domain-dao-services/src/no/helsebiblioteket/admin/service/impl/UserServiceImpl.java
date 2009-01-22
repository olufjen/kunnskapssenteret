package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.OptimisticLockingFailureException;

import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.UserCompositeDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private UserDao userDao;
	private RoleDao roleDao;
	private PersonDao personDao;
	private ProfileDao profileDao;
	private ContactInformationDao contactInformationDao;
    private OrganizationDao organizationDao;
	public void setUserDao(UserDao userDao) { this.userDao = userDao; }
	public void setRoleDao(RoleDao roleDao) { this.roleDao = roleDao; }
	public void setPersonDao(PersonDao personDao) { this.personDao = personDao; }
//	public void setUserCompositeDao(UserCompositeDao userCompositeDao) { this.userCompositeDao = userCompositeDao; }

	public User findUserByUsername(User user) {
		User foundUser = this.userDao.getUserByUsername(user);
		// TODO: Add more data here!
		foundUser.setRoleList(this.roleDao.getUserRoleListByUserId(user.getId()));
//		foundUser.setOrganization(this.organizationDao.getOrganizationTypeByKey(organizationTypeKey))
		
		
		return foundUser;
	}
	public List<Role> getAllUserRoles() { return this.roleDao.getAllRoles(); }
	public User createUser(User user) {
		// FIXME: Insert data into all tables!
		//int pId = this.personDao.insertPerson(user.getPerson());
		//user.getPerson().setId(pId);
		user.getPerson().getProfile().setPerson(user.getPerson());
		user.getPerson().getContactInformation().setPerson(user.getPerson());
		
		//int ciId = this.personDao.insertContactInformation(user.getPerson().getContactInformation());
		//user.getPerson().getContactInformation().setId(ciId);
		
		// FIXME: No dummy org!
		Organization organization = new Organization();
		organization.setId(3);
		user.setOrganization(organization);
		//this.personDao.insertProfile(user.getPerson().getProfile());
//		user.getPerson().getProfile().setId(proId);
		
		this.userDao.insertUser(user);

		logger.info("Saved user:" + user.getUsername());
		
//		userDao.insertUser(user);
		
		return this.findUserByUsername(user);
	}
	
	public User saveUser(User user) {
		// TODO: How to get users without person object!
		// TODO: Does it fetch everything now?
		User old = this.userDao.getUserByUsername(user);
		
		if(user.getAccessList() != null){
			for (Access access : user.getAccessList()) {
				// FIXME: Save access.
				AccessType accessType = access.getType();
				SupplierSource supplierSource = access.getSupplierSource();
				ResourceType resourceType = supplierSource.getResourceType();
			}
		}
		// TODO: Assume that these are saved?
		Organization organization = user.getOrganization();
		if(organization==null) { user.setOrganization(old.getOrganization()); }
		for (Role role : user.getRoleList()) {
//			if(role==null) { user.setRole(old.getRole()); }
		}
		
		// FIXME: Save person.
		Person person = user.getPerson();
		if(person==null) {
			user.setPerson(old.getPerson());
		} else {
			ContactInformation contactInformation = person.getContactInformation();
			if(contactInformation==null) { user.getPerson().setContactInformation(old.getPerson().getContactInformation()); }
			Position position = user.getPerson().getPosition();
			if(position==null){ user.getPerson().setPosition(old.getPerson().getPosition()); }
			Profile profile = person.getProfile();
			if(profile==null){ user.getPerson().setProfile(old.getPerson().getProfile()); }
		}

		if(user.getPerson()==null) {
			// TODO: How to save users without person object!
			return null;
		} 
		// Move backwards
		//this.personDao.updateContactInformation(user.getPerson().getContactInformation());
//		this.personDao.updatePosition(user.getPerson().getPosition());
		user.getPerson().getProfile().setPerson(user.getPerson());
		if(user.getPerson().getProfile() != null){
			// TODO: Should all persons have a profile?
			//	this.personDao.updateProfile(user.getPerson().getProfile());
		}
		user.getPerson().setUser(user);
		this.personDao.updatePerson(user.getPerson());
		this.userDao.updateUser(user);

		// TODO: Write this.
		
		return this.findUserByUsername(user);
		
		// TODO: Use this from UserServiceImpl2 instead???
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

	}
	public PageResult<User> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<User> request) {
		// FIXME: Cache and page results
		
		List<User> all = this.getAllUsers(request).result;
		List<User> some = new ArrayList<User>();
		for (User user : all) {
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
	public PageResult<User> getAllUsers(PageRequest<User> request) {
		PageResult<User> result = new PageResult<User>();
		result.result = this.userDao.getAllUsers();
		result.from = 0;
		result.total = result.result.size();
		return result;
	}
	public List<Position> getAllUserPositions() {
		// FIXME: Implement this with DB fetch!
		List<Position> all = new ArrayList<Position>();
		{ Position position = new Position(); position.setKey("NRS"); position.setName("Nurse"); all.add(position); }
		{ Position position = new Position(); position.setKey("DCT"); position.setName("Doctor"); all.add(position); }
		return all;
	}
	public Role getRoleByKey(Role role) {
		// TODO Use single select
		List<Role> roles = this.getAllUserRoles();
		for (Role fRole : roles) {
			if(fRole.getKey().equals(role.getKey())){
				return fRole;
			}
		}
		return null;
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
