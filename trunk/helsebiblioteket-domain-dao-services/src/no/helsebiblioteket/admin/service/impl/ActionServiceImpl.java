package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.ActionDao;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Action;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.ActionLine;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultAction;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.service.ActionService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

public class ActionServiceImpl implements ActionService{
	private ActionDao actionDao;
	private UserService userService;
	private OrganizationService organizationService;
	@Override
	public Boolean insertUserAction(User user, Resource resource, AccessType accessType) {
		ActionLine line = new ActionLine();
		line.setAccessTypeId(accessType.getId());
		line.setId(null);
		line.setOrgUnitId(null);
		line.setResourceId(resource.getId());
		line.setUserId(user.getId());
		this.actionDao.insertAction(line);
		return true;
	}
	@Override
	public Boolean insertOrganizationAction(Organization organization, Resource resource, AccessType accessType) {
		ActionLine line = new ActionLine();
		line.setAccessTypeId(accessType.getId());
		line.setId(null);
		line.setOrgUnitId(organization.getId());
		line.setResourceId(resource.getId());
		line.setUserId(null);
		this.actionDao.insertAction(line);
		return true;
	}
	@Override
	public SingleResultUser getUserByAction(Action action) {
		ActionLine actionLine = this.actionDao.getActionLineById(action.getId());
		UserListItem item = new UserListItem();
		item.setId(actionLine.getUserId());
		return this.userService.getUserByUserListItem(item);
	}
	@Override
	public SingleResultOrganization getOrganizationByAction(Action action) {
		ActionLine actionLine = this.actionDao.getActionLineById(action.getId());
		OrganizationListItem item = new OrganizationListItem();
		item.setId(actionLine.getOrgUnitId());
		return this.organizationService.getOrganizationByListItem(item);
	}
	@Override
	public ListResultAction getActionListByUser(User user) {
		List<ActionLine> list = this.actionDao.getActionListByUser(user);
		return new ListResultAction(translateList(list));
	}
	@Override
	public ListResultAction getActionListByOrganization(Organization organization) {
		List<ActionLine> list = this.actionDao.getActionListByOrganization(organization);
		return new ListResultAction(translateList(list));
	}
	@Override
	public ListResultAction getActionListByResource(Resource resource) {
		List<ActionLine> list = this.actionDao.getActionListByResource(resource);
		return new ListResultAction(translateList(list));
	}
	@Override
	public ListResultAction getActionListByAccessType(AccessType accessType) {
		List<ActionLine> list = this.actionDao.getActionListByAccessType(accessType);
		return new ListResultAction(translateList(list));
	}
	private Action[] translateList(List<ActionLine> list){
		Action[] result = new Action[list.size()];
		int i = 0;
		for (ActionLine line : list) {
			Action action = new Action();
			action.setId(line.getId());
//			Not in use yet
//			action.setAccessType(this.accessTypeDao.getAccessTypeById(line.getAccessTypeId()));
//			action.setResource(this.resourceDao.getResourceById(line.getResourceId()).getResource());
			if(line.getOrgUnitId() != null){
				action.setUserOrganizationAccess(Action.ORGANIZATION_ACCESS);
			} else if(line.getUserId() != null){
				action.setUserOrganizationAccess(Action.USER_ACCESS);
			}
			result[i] = action;
			i++;
		}
		return result;
	}

	
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
