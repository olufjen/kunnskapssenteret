package no.helsebiblioteket.admin.ssoservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Action;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.ListResultAction;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.service.ActionService;

@SuppressWarnings("serial")
public class ActionServiceSso extends SsoService implements ActionService{
	protected static final Log logger = LogFactory.getLog(ActionServiceSso.class);	
	private ActionService actionService;
	
	@Override
	public Log getLogger() { return logger; }
	@Override
	public Boolean insertUserAction(User user, Resource resource, AccessType accessType) {
		return actionService.insertUserAction(user, resource, accessType);
	}
	@Override
	public Boolean insertOrganizationAction(Organization organization, Resource resource, AccessType accessType) {
		return actionService.insertOrganizationAction(organization, resource, accessType);
	}
	@Override
	public SingleResultUser getUserByAction(Action action) {
		return actionService.getUserByAction(action);
	}
	@Override
	public SingleResultOrganization getOrganizationByAction(Action action) {
		return actionService.getOrganizationByAction(action);
	}
	@Override
	public ListResultAction getActionListByUser(User user) {
		return actionService.getActionListByUser(user);
	}
	@Override
	public ListResultAction getActionListByOrganization(Organization organization) {
		return actionService.getActionListByOrganization(organization);
	}
	@Override
	public ListResultAction getActionListByResource(Resource resource) {
		return actionService.getActionListByResource(resource);
	}
	@Override
	public ListResultAction getActionListByAccessType(AccessType accessType) {
		return actionService.getActionListByAccessType(accessType);
	}
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
}