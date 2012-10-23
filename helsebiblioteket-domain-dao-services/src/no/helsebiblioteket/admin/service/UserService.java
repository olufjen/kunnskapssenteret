package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.parameter.UserExportParameter;
import no.helsebiblioteket.admin.domain.requestresult.ListResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ListResultRole;
import no.helsebiblioteket.admin.domain.requestresult.ListResultUser;
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultRole;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.requestresult.PageRequest;

public interface UserService extends Serializable {
	public SingleResultSystem getSystemByKey(SystemKey key);
	public ListResultRole getRoleListBySystem(System system);
	public ListResultPosition getPositionListAll(String DUMMY);
	/**
     * Fetches all the positions from the database. Delegates the task to
	 * PositionDao. The variable DUMMY is never used.
	 * Only for webservice client calls
     */
	public SingleResultPosition getPositionByKey(PositionTypeKey positionTypeKey, OrganizationType organizationType);
	public SingleResultRole getRoleByKeySystem(UserRoleKey key, System system);

	public PageResultUserListItem getUserListAll(PageRequest request);
	public PageResultUserListItem findUsersBySearchStringRoles(String searchString, Role[] roles, PageRequest request);

	public ListResultUser getUserListByEmailAddress(String emailAddress);
	public SingleResultUser getUserByUserListItem(UserListItem userListItem);
	public SingleResultUser findUserByUsername(String username);
	public Boolean usernameTaken(String username, Integer userId);
	
	public SingleResultUser insertUser(User user);
	public SingleResultUser insertOrganizationUser(OrganizationUser organizationUser);
	public Boolean updateUser(User user);
	public void deleteUser(User user);

//	public ListResultUser getAdminUserByOrganization(Organization organization);

	public List<User> getUserExportList(UserExportParameter parameter);
	public String getUserExportCsv(UserExportParameter parameter);
	public boolean unsubscribeNewsletter(String subscriptionKey);
	public boolean unsubscribeSurvey(String subscriptionKey);
	public boolean isEmailAddressTaken(String emailAddress);
}
