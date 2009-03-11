package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.ActionLine;

public interface ActionDao {
	// Edit
	public void insertAction(ActionLine actionLine);
	public void updateAction(ActionLine actionLine);
	public void deleteAction(ActionLine actionLine);

	// Fetch
	public ActionLine getActionLineById(Integer id);
	
	public List<ActionLine> getActionListByUser(User user);
	public List<ActionLine> getActionListByOrganization(Organization organization);
	public List<ActionLine> getActionListByResource(Resource resource);
	public List<ActionLine> getActionListByAccessType(AccessType accessType);
}
