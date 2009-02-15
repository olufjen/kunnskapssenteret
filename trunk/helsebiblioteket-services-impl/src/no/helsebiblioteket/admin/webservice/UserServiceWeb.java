package no.helsebiblioteket.admin.webservice;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ListResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
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

	public SingleResultSystem getSystemByKey(SystemKey key) {
		Object[] args = new Object[] { key };
		Class[] returnTypes = new Class[] { SingleResultSystem.class };
		return (SingleResultSystem)invoke(this.systemByKey, args, returnTypes);
	}
	public ListResultRole getRoleListBySystem(System system) {
		Object[] args = new Object[] { system };
		Class[] returnTypes = new Class[] { ListResultRole.class };
		return (ListResultRole)invoke(this.roleListBySystem, args, returnTypes);
	}
	public ListResultPosition getPositionListAll(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResultPosition.class };
		Object result = invoke(this.positionListAllName, args, returnTypes);
		return (ListResultPosition) result;
	}
	public Position[] getPositionListAllWS(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { Position[].class };
		Object result = invoke(this.positionListAllName, args, returnTypes);
		return (result != null) ? (Position[]) result : null;
	}
	public SingleResultRole getRoleByKeySystem(UserRoleKey key, System system) {
		Object[] args = new Object[] { key, system };
		Class[] returnTypes = new Class[] { SingleResultRole.class };
		return (SingleResultRole)invoke(this.roleByKeyName, args, returnTypes);
	}
	public PageResult<UserListItem> getUserListAll(PageRequest request){
		Object[] args = new Object[] { request };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<UserListItem>)invoke(this.userListAllName, args, returnTypes);
	}
	public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest request){
		Object[] args = new Object[] { searchString, roles, request};
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<UserListItem>)invoke(this.findUsersBySearchStringRolesName, args, returnTypes);
	}
	public SingleResultUser findUserByUsername(String username){
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { SingleResultUser.class };
		return (SingleResultUser)invoke(this.findUserByUsernameName, args, returnTypes);
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
	public SingleResultPosition getPositionByKey(PositionTypeKey positionTypeKey) {
		Object[] args = new Object[] { positionTypeKey };
		Class[] returnTypes = new Class[] { SingleResultPosition.class };
		return (SingleResultPosition)invoke(this.positionByKey, args, returnTypes);
	}
	public User findUserByUsernameWS(String username) {
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { User.class };
		Object result = invoke(this.findUserByUsernameName, args, returnTypes);
		return (result != null) ? (User) result : null;
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
	public void setSystemByKey(QName systemByKey) {
		this.systemByKey = systemByKey;
	}
	public void setRoleListBySystem(QName roleListBySystem) {
		this.roleListBySystem = roleListBySystem;
	}
}
