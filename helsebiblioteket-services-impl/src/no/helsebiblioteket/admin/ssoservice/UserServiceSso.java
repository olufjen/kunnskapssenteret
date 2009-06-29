package no.helsebiblioteket.admin.ssoservice;

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
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ListResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ListResultUser;
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
@SuppressWarnings("serial")

public class UserServiceSso extends SsoService implements UserService {
	protected static final Log logger = LogFactory.getLog(UserServiceSso.class);
	
	private UserService userService;

	@Override
	public SingleResultSystem getSystemByKey(SystemKey systemKey) {
		String key = (
				((systemKey != null) ? (systemKey.getValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.userServiceSsoGetSystemByKeyCache, key);
		if (result == null) {
			result = userService.getSystemByKey(systemKey);
			cacheHelper.addCache(CacheKey.userServiceSsoGetSystemByKeyCache, key, result);
		}
		return (SingleResultSystem) result;
	}
	@Override
	public ListResultRole getRoleListBySystem(System system) {
		return userService.getRoleListBySystem(system);
	}
	@Override
	public ListResultPosition getPositionListAll(String DUMMY) {
		String key = "getPositionListAll";
		Object result = cacheHelper.findCache(CacheKey.staticListCache, key);
		if (result == null) {
			result = userService.getPositionListAll(DUMMY);
			cacheHelper.addCache(CacheKey.staticListCache, key, result);
		}
		return (ListResultPosition) result;
	}
	@Override
	public SingleResultRole getRoleByKeySystem(UserRoleKey roleKey, System system) {
		String key = (
				((roleKey != null) ? (roleKey.getValue() + "-") : "") +
				((system != null && system.getKey() != null) ? (system.getKey().getValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.userServiceSsoGetRoleByKeySystemCache, key);
		if (result == null) {
			result = userService.getRoleByKeySystem(roleKey, system);
			cacheHelper.addCache(CacheKey.userServiceSsoGetRoleByKeySystemCache, key, result);
		}
		return (SingleResultRole) result;
	}
	@Override
	public PageResultUserListItem getUserListAll(PageRequest request){
		return userService.getUserListAll(request);
	}
	@Override
	public PageResultUserListItem findUsersBySearchStringRoles(String searchString, Role[] roles, PageRequest request){
		return userService.findUsersBySearchStringRoles(searchString, roles, request);
	}
	@Override
	public SingleResultUser findUserByUsername(String username){
		return userService.findUserByUsername(username);
	}
	@Override
	public SingleResultUser getUserByUserListItem(UserListItem userListItem) {
		return getUserByUserListItem(userListItem);
	}
	@Override
	public SingleResultUser insertUser(User user){
		// TODO Fase2: This test should not be here.
		if(user.getRoleList() == null) {
			throw new NullPointerException("RoleList should not be null");
		}
		return userService.insertUser(user);
	}
	@Override
	public SingleResultUser insertOrganizationUser(OrganizationUser organizationUser) {
		return userService.insertOrganizationUser(organizationUser);
	}
	@Override
	public Boolean updateUser(User user){
		// TODO Fase2: This test should not be here.
		if(user.getRoleList() == null) {
			throw new NullPointerException("RoleList should not be null");
		}
		return userService.updateUser(user);
	}
	@Override
	public SingleResultPosition getPositionByKey(PositionTypeKey positionTypeKey, OrganizationType organizationType) {
		return userService.getPositionByKey(positionTypeKey, organizationType);
	}
	@Override
	public ListResultUser getUserListByEmailAddress(String emailAddress) {
		return userService.getUserListByEmailAddress(emailAddress);
	}

	public Log getLogger() {
		return logger;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public void deleteUser(User user) {
		this.userService.deleteUser(user);
	}
}