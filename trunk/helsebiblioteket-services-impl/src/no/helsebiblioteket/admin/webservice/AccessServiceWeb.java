package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSourceResource;
import no.helsebiblioteket.admin.service.AccessService;

@SuppressWarnings("serial")
public class AccessServiceWeb extends BasicWebService implements AccessService{
	protected static final Log logger = LogFactory.getLog(AccessServiceWeb.class);
	private QName accessListByAll;
	private QName accessListByOrganization;
	private QName accessListByOrganizationType;
	private QName accessListByRole;
	private QName accessListByUser;
	private QName accessTypeByTypeCategory;
	private QName resourceTypeByKey;
	private QName supplierSourceListAll;
	private QName insertSupplierSourceResource;
	private QName deleteSupplierSourceResource;
	private QName deleteResourceAccess;
	private QName insertOrganizationAccess;
	private QName insertOrganizationTypeAccess;
	private QName insertUserAccess;
	private QName insertUserRoleAccess;
	private QName supplierSourceResourceListAll;
	private QName supplierSourceByUrlStartsWithName;
	
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource) {
		Object[] args = new Object[] { resource };
		Class[] returnTypes = new Class[] { SingleResultSupplierSourceResource.class };
		return (SingleResultSupplierSourceResource)invoke(this.insertSupplierSourceResource, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteSupplierSourceResource(SupplierSourceResource resource) {
		Object[] args = new Object[] { resource };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.deleteSupplierSourceResource, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteResourceAccess(ResourceAccessListItem access) {
		Object[] args = new Object[] { access };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.deleteResourceAccess, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListForAll() {
		Object[] args = new Object[] {  };
		Class[] returnTypes = new Class[] { ListResultResourceAccess.class };
		return (ListResultResourceAccessListItem)invoke(this.accessListByAll, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganization(Organization organization) {
		Object[] args = new Object[] { organization };
		Class[] returnTypes = new Class[] { ListResultResourceAccess.class };
		return (ListResultResourceAccessListItem)invoke(this.accessListByOrganization, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganizationType(OrganizationType organizationType) {
		Object[] args = new Object[] { organizationType };
		Class[] returnTypes = new Class[] { ListResultResourceAccess.class };
		return (ListResultResourceAccessListItem)invoke(this.accessListByOrganizationType, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByRole(Role role) {
		Object[] args = new Object[] { role };
		Class[] returnTypes = new Class[] { ListResultResourceAccess.class };
		return (ListResultResourceAccessListItem)invoke(this.accessListByRole, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByUser(User user) {
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { ListResultResourceAccess.class };
		return (ListResultResourceAccessListItem)invoke(this.accessListByUser, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultAccessType getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory) {
		Object[] args = new Object[] { accessTypeKey, accessTypeCategory };
		Class[] returnTypes = new Class[] { SingleResultAccessType.class };
		return (SingleResultAccessType)invoke(this.accessTypeByTypeCategory, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey){
		Object[] args = new Object[] { resourceTypeKey };
		Class[] returnTypes = new Class[] { SingleResultResourceType.class };
		return (SingleResultResourceType)invoke(this.resourceTypeByKey, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultSupplierSource getSupplierSourceListAll(String DUMMY) {
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResultSupplierSource.class };
		return (ListResultSupplierSource)invoke(this.supplierSourceListAll, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertOrganizationResourceAccess(Organization organization, ResourceAccess access) {
		Object[] args = new Object[] { organization, access };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertOrganizationAccess, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertOrganizationTypeResourceAccess(OrganizationType organizationType, ResourceAccess access) {
		Object[] args = new Object[] { organizationType, access };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertOrganizationTypeAccess, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertUserResourceAccess(User user, ResourceAccess access) {
		Object[] args = new Object[] { user, access};
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertUserAccess, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertUserRoleResourceAccess(Role userRole, ResourceAccess access) {
		Object[] args = new Object[] { userRole, access };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertUserRoleAccess, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultSupplierSourceResource getSupplierSourceResourceListAll(String DUMMY) {
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResultSupplierSourceResource.class };
		return (ListResultSupplierSourceResource)invoke(this.supplierSourceResourceListAll, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultSupplierSource getSupplierSourceByUrlStartsWith(Url url) {
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResultSupplierSource.class };
		return (SingleResultSupplierSource) invoke(this.supplierSourceByUrlStartsWithName, args, returnTypes);
	}
	@Override
	public Log getLogger() {
		return logger;
	}
	public void setAccessListByOrganization(QName accessListByOrganization) {
		this.accessListByOrganization = accessListByOrganization;
	}
	public void setAccessListByOrganizationType(QName accessListByOrganizationType) {
		this.accessListByOrganizationType = accessListByOrganizationType;
	}
	public void setAccessListByRole(QName accessListByRole) {
		this.accessListByRole = accessListByRole;
	}
	public void setAccessListByUser(QName accessListByUser) {
		this.accessListByUser = accessListByUser;
	}
	public void setAccessTypeByTypeCategory(QName accessTypeByTypeCategory) {
		this.accessTypeByTypeCategory = accessTypeByTypeCategory;
	}
	public void setSupplierSourceListAll(QName supplierSourceListAll) {
		this.supplierSourceListAll = supplierSourceListAll;
	}
	public void setInsertOrganizationTypeAccess(QName insertOrganizationTypeAccess) {
		this.insertOrganizationTypeAccess = insertOrganizationTypeAccess;
	}
	public void setInsertUserAccess(QName insertUserAccess) {
		this.insertUserAccess = insertUserAccess;
	}
	public void setInsertUserRoleAccess(QName insertUserRoleAccess) {
		this.insertUserRoleAccess = insertUserRoleAccess;
	}
	public void setInsertOrganizationAccess(QName insertOrganizationAccess) {
		this.insertOrganizationAccess = insertOrganizationAccess;
	}
	public void setResourceTypeByKey(QName resourceTypeByKey) {
		this.resourceTypeByKey = resourceTypeByKey;
	}
	public void setInsertSupplierSourceResource(QName insertSupplierSourceResource) {
		this.insertSupplierSourceResource = insertSupplierSourceResource;
	}
	public void setDeleteSupplierSourceResource(QName deleteSupplierSourceResource) {
		this.deleteSupplierSourceResource = deleteSupplierSourceResource;
	}
	public void setDeleteResourceAccess(QName deleteResourceAccess) {
		this.deleteResourceAccess = deleteResourceAccess;
	}
	public void setSupplierSourceResourceListAll(QName supplierSourceResourceListAll) {
		this.supplierSourceResourceListAll = supplierSourceResourceListAll;
	}
	public void setSupplierSourceByUrlStartsWithName(QName supplierSourceByUrlStartsWithName) {
		this.supplierSourceByUrlStartsWithName = supplierSourceByUrlStartsWithName;
	}
}
