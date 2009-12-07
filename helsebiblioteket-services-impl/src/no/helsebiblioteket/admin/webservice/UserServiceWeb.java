package no.helsebiblioteket.admin.webservice;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.parameter.UserExportParameter;
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ListResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ListResultUser;
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
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
	private QName userByUserListItemName;
	private QName insertUserName;
	private QName insertOrganizationUserName;
	private QName updateUserName;
	private QName positionByKey;
	private QName deleteUserName;

	@SuppressWarnings("unchecked")
	@Override
	public SingleResultSystem getSystemByKey(SystemKey systemKey) {
		Object[] args = new Object[] { systemKey };
		Class[] returnTypes = new Class[] { SingleResultSystem.class };
		String key = (
				((systemKey != null) ? (systemKey.getValue()) : "")
				);
		return (SingleResultSystem)invokeCached(CacheKey.userServiceWebGetSystemByKeyCache, key, this.systemByKey, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultRole getRoleListBySystem(System system) {
		Object[] args = new Object[] { system };
		Class[] returnTypes = new Class[] { ListResultRole.class };
		return (ListResultRole)invoke(this.roleListBySystem, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultPosition getPositionListAll(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResultPosition.class };
		Object result = invokeCached(CacheKey.staticListCache, "getPositionListAll", this.positionListAllName, args, returnTypes);
		return (ListResultPosition) result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultRole getRoleByKeySystem(UserRoleKey roleKey, System system) {
		Object[] args = new Object[] { roleKey, system };
		Class[] returnTypes = new Class[] { SingleResultRole.class };
		String key = (
				((roleKey != null) ? (roleKey.getValue() + "-") : "") +
				((system != null && system.getKey() != null) ? (system.getKey().getValue()) : "")
				);
		return (SingleResultRole)invokeCached(CacheKey.userServiceWebGetRoleByKeySystemCache, key, this.roleByKeyName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResultUserListItem getUserListAll(PageRequest request){
		Object[] args = new Object[] { request };
		Class[] returnTypes = new Class[] { PageResultUserListItem.class };
		return (PageResultUserListItem)invoke(this.userListAllName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResultUserListItem findUsersBySearchStringRoles(String searchString, Role[] roles, PageRequest request){
		Object[] args = new Object[] { searchString, roles, request};
		Class[] returnTypes = new Class[] { PageResultUserListItem.class };
		return (PageResultUserListItem)invoke(this.findUsersBySearchStringRolesName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultUser findUserByUsername(String username){
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { SingleResultUser.class };
		return (SingleResultUser)invoke(this.findUserByUsernameName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultUser getUserByUserListItem(UserListItem userListItem) {
		Object[] args = new Object[] { userListItem };
		Class[] returnTypes = new Class[] { SingleResultUser.class };
		return (SingleResultUser)invoke(this.userByUserListItemName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultUser insertUser(User user){
		// TODO Fase2: This test should not be here.
		if(user.getRoleList() == null) {
			throw new NullPointerException("RoleList should not be null");
		}
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { SingleResultUser.class };
		return (SingleResultUser)invoke(this.insertUserName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultUser insertOrganizationUser(OrganizationUser organizationUser) {
		Object[] args = new Object[] { organizationUser };
		Class[] returnTypes = new Class[] { SingleResultUser.class };
		return (SingleResultUser)invoke(this.insertOrganizationUserName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean updateUser(User user){
		// TODO Fase2: This test should not be here.
		if(user.getRoleList() == null) {
			throw new NullPointerException("RoleList should not be null");
		}
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.updateUserName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultPosition getPositionByKey(PositionTypeKey positionTypeKey, OrganizationType organizationType) {
		Object[] args = new Object[] { positionTypeKey, organizationType };
		Class[] returnTypes = new Class[] { SingleResultPosition.class };
		return (SingleResultPosition)invoke(this.positionByKey, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultUser getUserListByEmailAddress(String emailAddress) {
		Object[] args = new Object[] { emailAddress };
		Class[] returnTypes = new Class[] { ListResultUser.class };
		return (ListResultUser)invoke(this.positionByKey, args, returnTypes);
	}
	@Override
	public void deleteUser(User user) {
		Object[] args = new Object[] { user };
		invoke(this.deleteUserName, args, null);
	}
	
	@Override
	public boolean unsubscribeNewsletter(String subscriptionKey) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean unsubscribeSurvey(String subscriptionKey) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Log getLogger() {
		return logger;
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
	public void setFindUsersBySearchStringRolesName(QName findUsersBySearchStringRolesName) {
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
	public void setpositionByKey(QName positionByKey) {
		this.positionByKey = positionByKey;
	}
	public void setSystemByKey(QName systemByKey) {
		this.systemByKey = systemByKey;
	}
	public void setRoleListBySystem(QName roleListBySystem) {
		this.roleListBySystem = roleListBySystem;
	}
	public void setUserByUserListItemName(QName userByUserListItemName) {
		this.userByUserListItemName = userByUserListItemName;
	}
	public void setInsertOrganizationUserName(QName insertOrganizationUserName) {
		this.insertOrganizationUserName = insertOrganizationUserName;
	}
	public void setDeleteUserName(QName deleteUserName) {
		this.deleteUserName = deleteUserName;
	}
	@Override
	public String getUserExportCsv(UserExportParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getUserExportList(UserExportParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}
