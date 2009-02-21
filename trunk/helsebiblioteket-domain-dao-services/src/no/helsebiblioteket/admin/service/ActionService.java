package no.helsebiblioteket.admin.service;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.ListResultAction;

public interface ActionService {
	public Boolean insertUserAction(User user, Resource resource, AccessType accessType);
	public Boolean insertOrganizationAction(Organization organization, Resource resource, AccessType accessType);

	public ListResultAction getActionListByUser(User user);
	public ListResultAction getActionListByOrganization(Organization organization);
	public ListResultAction getActionListByResource(Resource resource);
	public ListResultAction getActionListByAccessType(AccessType accessType);
}
