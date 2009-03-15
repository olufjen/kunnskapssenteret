package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.SystemDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
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
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultRole;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ListResultRole;
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
//	private final Log logger = LogFactory.getLog(getClass());
	private UserDao userDao;
	private UserListDao userListDao;
	private RoleDao roleDao;
	private UserRoleDao userRoleDao;
	private PersonDao personDao;
	private PositionDao positionDao;
    private OrganizationDao organizationDao;
    private SystemDao systemDao;

    /**
     * Fetches the system with the given key from the
     * database.
     */
	@Override
	public SingleResultSystem getSystemByKey(SystemKey key){
		System system = this.systemDao.getSystemByKey(key);
		if(system == null) return new EmptyResultSystem();
		return new ValueResultSystem(system);
	}
    /**
     * Fetches all the roles from the database for the given
     * system. Delegates the task to RoleDao.
     */
	@Override
	public ListResultRole getRoleListBySystem(System system){
		List<Role> roleList = this.roleDao.getRoleListBySystem(system);
		Role[] roles = new Role[roleList.size()];
		int i = 0;
		for (Role role : roleList) {
			role.setSystem(((ValueResultSystem)
					this.getSystemByKey(role.getSystem().getKey())).getValue());
			roles[i++] = role;
		}
		return new ListResultRole(roles);
	}
    /**
     * Fetches all the positions from the database. Delegates the task to
	 * PositionDao. The variable DUMMY is never used.
     */
	@Override
	public ListResultPosition getPositionListAll(String DUMMY) {
		List<Position> all = this.positionDao.getPositionListAll();
		Position[] positions = new Position[all.size()];
		int i = 0;
		for (Position position : all) {
			positions[i++]=position;
		}
		ListResultPosition result = new ListResultPosition(positions);
		return result;
	}
		
	@Override
	public SingleResultPosition getPositionByKey(PositionTypeKey positionTypeKey, OrganizationType organizationType) {
		List<Position> all = this.positionDao.getPositionListAll();
		for (Position position : all) {
			if(position.getKey().getValue().equals(positionTypeKey.getValue()) &&
					position.getOrganizationType().getId().equals(organizationType.getId())){
				ValueResultPosition result = new ValueResultPosition();
				result.setValue(position);
				return result;
			}
		}
		return new EmptyResultPosition();
	}
	
	/**
	 * Fetches the Role with the given key for the given system.
	 * If none is found EmptyResult is returned. Delegates the
	 * task to RoleDao.
	 * 
	 * RoleDao.getRoleByKeySystem(..) returns null if no Role is
	 * found.
	 */
	@Override
	public SingleResultRole getRoleByKeySystem(UserRoleKey key, System system){
		Role role = this.roleDao.getRoleByKeySystem(key, system);
		if(role==null){
			return new EmptyResultRole();
		} else {
			role.setSystem(((ValueResultSystem)
					this.getSystemByKey(role.getSystem().getKey())).getValue());
			return new ValueResultRole(role);
		}
	}
	/**
	 * Finds all the users in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task to UserListDao.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the UserListItem object.
	 */
	@Override
	public PageResultUserListItem getUserListAll(PageRequest request) {
		if(request.getSkip() != 0 || request.getMaxResult() > 40) {
			throw new NullPointerException("Cannot skip and max result must be 40 or less.");
		}
		PageResultUserListItem result = new PageResultUserListItem();
		result.setResult(translateUserList(this.userListDao.getUserListPaged(request.getSkip(), request.getMaxResult())));
		result.setSkipped(request.getSkip());
		result.setTotal(result.getResult().length);
		return result;
	}
	private UserListItem[] translateUserList(List<UserListItem> userListPaged) {
		UserListItem[] list = new UserListItem[userListPaged.size()];
		int i = 0;
		for (UserListItem userListItem : userListPaged) {
			list[i] = userListItem;
			i++;
		}
		return list;
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
	 */
	@Override
	public PageResultUserListItem findUsersBySearchStringRoles(String searchString, Role[] roles, PageRequest request) {
		if(request.getSkip() != 0 || request.getMaxResult() > 40) {
			throw new NullPointerException("Cannot skip and max result must be 40 or less.");
		}

		List<Role> roleList = new ArrayList<Role>();
		for (Role role : roles) {
			roleList.add(role);
		}
		List<UserListItem> some = this.userListDao.getUserListPagedSearchStringRoles(searchString, roleList, request.getSkip(), request.getMaxResult());
		PageResultUserListItem result = new PageResultUserListItem();
		result.setResult(translateUserList(some));
		result.setSkipped(request.getSkip());
		result.setTotal(result.getResult().length);
		return result;
	}
	/**
	 * Fetches the whole user object. Every property of the user
	 * and of those properties will be initialized. Nothing is null.
	 * If something is missing default values will be created in the
	 * database. This helps clients by avoiding null-check and also
	 * makes it easier to update the user.
	 * 
	 */
	@Override
    public SingleResultUser findUserByUsername(String username) {
		User user = this.userDao.getUserByUsername(username);
		if(user == null){
			return new EmptyResultUser();
		} else {
			MemberOrganization organization = new MemberOrganization();
			organization.setOrganization(organizationDao.getOrganizationById(user.getId()));
			// TODO: Really?
			// No. Leif says there are users without organizations!
			if(organization == null){ throw new NullPointerException("No organization for user"); }
			user.setOrganization(organization.getOrganization());

			Person person = this.personDao.getPersonByUser(user);
			if(person == null){ person = PersonFactory.factory.createPerson(); }
			user.setPerson(person);

			List<UserRoleLine> userRoleList = this.userRoleDao.getUserRoleListByUser(user);
			Role[] roleList = new Role[userRoleList.size()];
			int i=0;
			for (UserRoleLine userRole : userRoleList) {
				roleList[i] = this.roleDao.getRoleById(userRole.getUserRoleId());
				i++;
			}
			user.setRoleList(roleList);
			return new ValueResultUser(user);
		}
	}
	/**
	 * Fetches user from a user list item. Uses findUserByUsername.
	 * 
	 */
	@Override
	public SingleResultUser getUserByUserListItem(UserListItem userListItem) {
		User tmp = this.userDao.getUserById(userListItem.getId());
		return this.findUserByUsername(tmp.getUsername());
	}

    /**
     * Inserts a new User into the database. All properties must
     * be set. No NULL. Use UserFactory and PersonFactory if needed.
     * Inserts the user roles, but roles must exist.
     * The organization is not inserted and must be inserted
     * _separately_.
     * 
     */
	@Override
	public SingleResultUser insertUser(User user) {
		// TODO: This should be removed, because it is only written to get
		//       around an error(?)in Axis2.
		if(user.getRoleList() == null){
			user.setRoleList(new Role[0]);
		}

		checkNull(user);
		this.personDao.insertPerson(user.getPerson());
		List<UserRoleLine> userRoleList = translateRoles(user.getId(), user.getRoleList());
		for (UserRoleLine userRole : userRoleList) {
			this.userRoleDao.insertUserRole(userRole);
		}
		this.userDao.insertUser(user);
		return new ValueResultUser(user);
	}
	private void checkNull(User user) {
		if(user.getOrganization() == null) throw new NullPointerException("organization == null");
//		if(user.getOrganization() == null) throw new NullPointerException("organization.organization == null");
		if(user.getOrganization().getId() == null) throw new NullPointerException("organization.id == null");
		if(user.getPassword() == null) throw new NullPointerException("password == null");
		if(user.getPerson() == null) throw new NullPointerException("person == null");
		if(user.getRoleList() == null) throw new NullPointerException("roleList == null");
		if(user.getUsername() == null) throw new NullPointerException("username == null");
	}
	/**
	 * Updates an existing user in the database. Throws an exception
	 * if it is does not exist. Since we initailize everything at
	 * load time (findUserByUsername) we assume that all values are
	 * set.
	 * 
	 */
	@Override
	public Boolean updateUser(User user) {
		// TODO: This should be removed, because it is only written to get
		//       around an error(?)in Axis2.
		if(user.getRoleList() == null){
			user.setRoleList(new Role[0]);
		}

		SingleResultUser oldResult = this.findUserByUsername(user.getUsername());
		if(oldResult instanceof EmptyResultUser){
			throw new NullPointerException("User does not exist.");
		}
		User old = ((ValueResultUser)oldResult).getValue();

		this.personDao.updatePerson(user.getPerson());

		List<UserRoleLine> newUserRoleList = translateRoles(user.getId(), user.getRoleList());
		List<UserRoleLine> oldUserRoleList = translateRoles(old.getId(), old.getRoleList());
		// TODO: Do this a little smarter without deleting all.
		//       Improve ModifiedListHelper.
		for (UserRoleLine userRole : oldUserRoleList) {
			this.userRoleDao.deleteUserRole(userRole);
		}
		for (UserRoleLine userRole : newUserRoleList) {
			this.userRoleDao.insertUserRole(userRole);
		}
		this.userDao.updateUser(user);
		return Boolean.TRUE;
	}
	private List<UserRoleLine> translateRoles(Integer id, Role[] roleList){
		List<UserRoleLine> result = new ArrayList<UserRoleLine>();
		for (Role role : roleList) {
			UserRoleLine userRole = new UserRoleLine();
			userRole.setUserRoleId(role.getId());
			userRole.setUserId(id);
			userRole.setLastChanged(new Date());
			result.add(userRole);
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
	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}

}
