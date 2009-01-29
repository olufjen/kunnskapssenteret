package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.ModifiedListHelper;
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
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.MorePageRequest;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private UserDao userDao;
	private UserListDao userListDao;
	private RoleDao roleDao;
	private UserRoleDao userRoleDao;
	private PersonDao personDao;
	private PositionDao positionDao;
    private OrganizationDao organizationDao;

    /**
     * Fetches all the roles from the database. Delegates the task to
	 * RoleDao. The variable DUMMY is never used.
     */
	public ListResult<UserRole> getRoleListAll(String DUMMY) {
		// TODO: Set role key with ENUM?
		List<UserRole> roleList = this.roleDao.getRoleListAll();
		UserRole[] roles = new UserRole[roleList.size()];
		int i = 0;
		for (UserRole role : roleList) {
			roles[i++] = role;
		}
		return new ListResult<UserRole>(roles);
	}
    /**
     * Fetches all the positions from the database. Delegates the task to
	 * PositionDao. The variable DUMMY is never used.
     */
	public ListResult<Position> getPositionListAll(String DUMMY) {
		List<Position> all = this.positionDao.getPositionListAll();
		Position[] positions = new Position[all.size()];
		int i = 0;
		for (Position position : all) {
			positions[i++]=position;
		}
		return new ListResult<Position>(positions);
	}
	/**
	 * Fetches the Role with the given key. If none is found
	 * EmptyResult is returned. Delegates the task to RoleDao.
	 * RoleDao.getRoleByKey(..) returns null if no Role is found.
	 */
	public SingleResult<UserRole> getRoleByKey(UserRoleKey key) {
		UserRole role = this.roleDao.getRoleByKey(key);
		if(role==null){
			return new EmptyResult<UserRole>();
		} else {
			return new ValueResult<UserRole>(role);
		}
	}
	/**
	 * Finds all the users in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task to UserListDao.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the UserListItem object.
	 */
	public PageResult<UserListItem> getUserListAll(PageRequest<UserListItem> request) {
		// TODO: Should we use Id for request.from?
		int from;
		if(request instanceof FirstPageRequest){
			from = 0;
		} else {
			from = ((MorePageRequest<UserListItem>)request).last + 1;
		}
		PageResult<UserListItem> result = new PageResult<UserListItem>();
		result.result = this.userListDao.getUserListPaged(from, request.maxResult);
		result.from = from;
		result.total = result.result.size();
		return result;
	}
	/**
	 * Finds all the users in the database that have at least one
	 * of the roles and has the search string in the name or user
	 * name. This method uses a page request and only fetches the
	 * next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task to UserListDao. It
	 * only fetches the most important values needed in a list,
	 * like names, etc. These are the values in the UserListItem
	 * object.
	 * 
	 * TODO: There is probably room for optimizations here.
	 * 
	 */
	public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<UserRole> roles, PageRequest<UserListItem> request) {
		// TODO: Should we use Id for request.from?
		int from;
		if(request instanceof FirstPageRequest){
			from = 0;
		} else {
			from = ((MorePageRequest<UserListItem>)request).last + 1;
		}
		List<UserListItem> some = this.userListDao.getUserListPagedSearchStringRoles(searchString, roles, from, request.maxResult);
		PageResult<UserListItem> result = new PageResult<UserListItem>();
		result.result = some;
		result.from = from;
		result.total = result.result.size();
		return result;
	}
	/**
	 * Fetches the whole user object. Every property of the user
	 * and of those properties will be initialized. Nothing is null.
	 * If something is missing default values will be created in the
	 * database. This helps clients by avoiding null-check and also
	 * makes it easier to update the user.
	 * 
	 * TODO: What do we do with users without an organization?
	 *       Is that allowed?
	 * 
	 */
    public SingleResult<User> findUserByUsername(String username) {
		User user = this.userDao.getUserByUsername(username);
		if(user == null){
			return new EmptyResult<User>();
		} else {
			Organization organization = this.organizationDao.getOrganizationById(user.getOrganization().getId());
			// TODO: Really?
			if(organization == null){ throw new NullPointerException("No organization for user"); }
			user.setOrganization(organization);

			Person person = this.personDao.getPersonByUser(user);
			if(person == null){ person = PersonFactory.factory.createPerson(); }
			user.setPerson(person);

			List<UserRoleLine> userRoleList = this.userRoleDao.getUserRoleListByUser(user);
			List<UserRole> roleList = new ArrayList<UserRole>();
			for (UserRoleLine userRole : userRoleList) {
				// TODO: Load using line object!
//				roleList.add(this.roleDao.getRoleByKey(userRole.getUserRole().getKey()));
			}
			return new ValueResult<User>(user);
		}
	}
    /**
     * Inserts a new User into the database. All properties must
     * be set. No NULL. Use UserFactory and PersonFactory if needed.
     * Inserts the user roles, but roles must exist.
     * The organization is not inserted and must be inserted
     * _separately_.
     * The roles are either not inserted and must be inserted
     * _separately_.
     * 
     */
	public Boolean insertUser(User user) {
		this.personDao.insertPerson(user.getPerson());
		List<UserRoleLine> userRoleList = translateRoles(user.getId(), user.getRoleList());
		for (UserRoleLine userRole : userRoleList) {
			this.userRoleDao.insertUserRole(userRole);
		}
		this.userDao.insertUser(user);
		return Boolean.TRUE;
	}
	/**
	 * Updates an existing user in the database. Throws an exception
	 * if it is does not exist. Since we initailize everything at
	 * load time (findUserByUsername) we assume that all values are
	 * set.
	 * 
	 */
	public Boolean updateUser(User user) {
		SingleResult<User> oldResult = this.findUserByUsername(user.getUsername());
		if(oldResult instanceof EmptyResult){
			throw new NullPointerException("User does not exist.");
		}
		User old = ((ValueResult<User>)oldResult).getValue();

		this.personDao.updatePerson(user.getPerson());

		List<UserRoleLine> newUserRoleList = translateRoles(user.getId(), user.getRoleList());
		List<UserRoleLine> oldUserRoleList = translateRoles(old.getId(), old.getRoleList());
		// TODO: Do this a little smarter without deleting all.
		//       Improve ModifiedListHelper.
		for (UserRoleLine userRole : oldUserRoleList) {
			this.userRoleDao.deleteUserRole(userRole);
		}
		for (UserRoleLine userRole : newUserRoleList) {
			this.userRoleDao.deleteUserRole(userRole);
		}
		this.userDao.updateUser(user);
		return Boolean.TRUE;

		

		
		
//		// FIXME: Remove this.
//		User old = null;// this.userDao.getUserByUsername(user);
//		if(user.getAccessList() != null){
//			for (Access access : user.getAccessList()) {
//				AccessType accessType = access.getType();
//				SupplierSource supplierSource = access.getSupplierSource();
//				ResourceType resourceType = supplierSource.getResourceType();
//			}
//		}
//		Organization organization = user.getOrganization();
//		if(organization==null) { user.setOrganization(old.getOrganization()); }
//		for (Role role : user.getRoleList()) {
////			if(role==null) { user.setRole(old.getRole()); }
//		}
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
////		user.getPerson().setUser(user);
//		this.personDao.updatePerson(user.getPerson());
//		this.userDao.updateUser(user);
//		
//		return Boolean.TRUE;
//		
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
	private List<UserRoleLine> translateRoles(Integer id, List<UserRole> roleList){
		List<UserRoleLine> result = new ArrayList<UserRoleLine>();
		for (UserRole role : roleList) {
			UserRoleLine userRole = new UserRoleLine();
			// TODO: User line object!
//			userRole.setUserRole(role);
			userRole.setUserId(id);
			userRole.setLastChanged(new Date());
		}
		return result;
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
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	public void setUserListDao(UserListDao userListDao) {
		this.userListDao = userListDao;
	}
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
}
