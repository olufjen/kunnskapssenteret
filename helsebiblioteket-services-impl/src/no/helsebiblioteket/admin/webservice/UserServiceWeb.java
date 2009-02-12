package no.helsebiblioteket.admin.webservice;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.UserListItem;
@SuppressWarnings("serial")

public class UserServiceWeb extends BasicWebService implements UserService {
	protected static final Log logger = LogFactory.getLog(UserServiceWeb.class);
	private QName systemByKey;
	private QName roleListBySystem;
	private QName positionListAllName;
	private QName roleByKeyName;
	private QName userListAllName;
	private QName findUsersBySearchStringRolesName;
	private QName findUserByUsernameName;
	private QName insertUserName;
	private QName updateUserName;
	private QName positionByKey;
	private QName findUser;

	public SingleResult<System> getSystemByKey(SystemKey key) {
		Object[] args = new Object[] { key };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<System>)invoke(this.systemByKey, args, returnTypes);
	}
	public ListResult<Role> getRoleListBySystem(System system) {
		Object[] args = new Object[] { system };
		Class[] returnTypes = new Class[] { ListResult.class };
		return (ListResult<Role>)invoke(this.roleListBySystem, args, returnTypes);
	}
	public ListResult<Position> getPositionListAll(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResult.class };
		Object result = invoke(this.positionListAllName, args, returnTypes);
		return (ListResult<Position>) result;
	}
	public Position[] getPositionListAllWS(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { Position[].class };
		return (Position[]) invoke(this.positionListAllName, args, returnTypes);
	}
	public SingleResult<Role> getRoleByKeySystem(UserRoleKey key, System system) {
		Object[] args = new Object[] { key, system };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Role>)invoke(this.roleByKeyName, args, returnTypes);
	}
	public PageResult<UserListItem> getUserListAll(PageRequest<UserListItem> request){
		Object[] args = new Object[] { request };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<UserListItem>)invoke(this.userListAllName, args, returnTypes);
	}
	public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<UserListItem> request){
		Object[] args = new Object[] { searchString, roles, request};
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<UserListItem>)invoke(this.findUsersBySearchStringRolesName, args, returnTypes);
	}
	public SingleResult<User> findUserByUsername(String username){
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<User>)invoke(this.findUserByUsernameName, args, returnTypes);
	}
	public Boolean insertUser(User user){
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertUserName, args, returnTypes);
	}
	public Boolean updateUser(User user){
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.updateUserName, args, returnTypes);
	}
	public SingleResult<Position> getPositionByKey(PositionTypeKey positionTypeKey) {
		Object[] args = new Object[] { positionTypeKey };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Position>)invoke(this.positionByKey, args, returnTypes);
	}

	
	public Log getLogger() {
		return this.logger;
	}

	public void setPositionListAllName(QName positionListAllName) {
		this.positionListAllName = positionListAllName;
	}

	public void setRoleByKeyName(QName roleByKeyName) {
		this.roleByKeyName = roleByKeyName;
	}

	public void setUserListAllName(QName userListAllName) {
		this.userListAllName = userListAllName;
	}

	public void setFindUsersBySearchStringRolesName(
			QName findUsersBySearchStringRolesName) {
		this.findUsersBySearchStringRolesName = findUsersBySearchStringRolesName;
	}

	public void setFindUserByUsernameName(QName findUserByUsernameName) {
		this.findUserByUsernameName = findUserByUsernameName;
	}

	public void setInsertUserName(QName insertUserName) {
		this.insertUserName = insertUserName;
	}

	public void setUpdateUserName(QName updateUserName) {
		this.updateUserName = updateUserName;
	}
	public void setPositionByKey(QName positionByKey) {
		this.positionByKey = positionByKey;
	}
	public void setFindUser(QName findUser) {
		this.findUser = findUser;
	}
	@Override
	public User findUserByUsernameWS(String username) {
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { User.class };
		return (User) invoke(this.findUserByUsernameName, args, returnTypes);
	}
}
