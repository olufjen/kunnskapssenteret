package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.dao.ActionDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Action;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.ActionLine;
import no.helsebiblioteket.admin.domain.requestresult.ListResultAction;
import no.helsebiblioteket.admin.service.ActionService;

public class ActionServiceImpl implements ActionService{
	private ActionDao actionDao;
	private AccessTypeDao accessTypeDao;
	private ResourceDao resourceDao;
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

			// TODO: Use caching when loading access type and resource!
			action.setAccessType(this.accessTypeDao.getAccessTypeById(line.getAccessTypeId()));
			
			// TODO: This is not working!
			action.setResource(this.resourceDao.getResourceById(line.getResourceId()).getResource());
			
			// TODO: Return user or organization here or load when needed?
			result[i] = action;
			i++;
		}
		return result;
	}
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	public void setAccessTypeDao(AccessTypeDao accessTypeDao) {
		this.accessTypeDao = accessTypeDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}
