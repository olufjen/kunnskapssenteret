package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.ListResultAction;
import no.helsebiblioteket.admin.service.ActionService;

@SuppressWarnings("serial")
public class ActionServiceWeb extends BasicWebService implements ActionService{
	protected static final Log logger = LogFactory.getLog(ActionServiceWeb.class);
	private QName insertUserActionName;
	private QName insertOrganizationActionName;
	private QName getActionListByUserName;
	private QName getActionListByOrganizationName;
	private QName getActionListByResourceName;
	private QName getActionListByAccessTypeName;
	@Override
	public Log getLogger() { return logger; }
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertUserAction(User user, Resource resource, AccessType accessType) {
		Object[] args = new Object[] { user, resource, accessType };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertUserActionName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertOrganizationAction(Organization organization, Resource resource, AccessType accessType) {
		Object[] args = new Object[] { organization, resource, accessType };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertOrganizationActionName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultAction getActionListByUser(User user) {
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { ListResultAction.class };
		return (ListResultAction)invoke(this.getActionListByUserName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultAction getActionListByOrganization(Organization organization) {
		Object[] args = new Object[] { organization };
		Class[] returnTypes = new Class[] { ListResultAction.class };
		return (ListResultAction)invoke(this.getActionListByOrganizationName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultAction getActionListByResource(Resource resource) {
		Object[] args = new Object[] { resource };
		Class[] returnTypes = new Class[] { ListResultAction.class };
		return (ListResultAction)invoke(this.getActionListByResourceName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultAction getActionListByAccessType(AccessType accessType) {
		Object[] args = new Object[] { accessType };
		Class[] returnTypes = new Class[] { ListResultAction.class };
		return (ListResultAction)invoke(this.getActionListByAccessTypeName, args, returnTypes);
	}
	public void setInsertUserActionName(QName insertUserActionName) {
		this.insertUserActionName = insertUserActionName;
	}
	public void setInsertOrganizationActionName(QName insertOrganizationActionName) {
		this.insertOrganizationActionName = insertOrganizationActionName;
	}
	public void setGetActionListByUserName(QName getActionListByUserName) {
		this.getActionListByUserName = getActionListByUserName;
	}
	public void setGetActionListByOrganizationName(
			QName getActionListByOrganizationName) {
		this.getActionListByOrganizationName = getActionListByOrganizationName;
	}
	public void setGetActionListByResourceName(QName getActionListByResourceName) {
		this.getActionListByResourceName = getActionListByResourceName;
	}
	public void setGetActionListByAccessTypeName(QName getActionListByAccessTypeName) {
		this.getActionListByAccessTypeName = getActionListByAccessTypeName;
	}
}
