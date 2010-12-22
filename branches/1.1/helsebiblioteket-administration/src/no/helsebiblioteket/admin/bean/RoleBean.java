package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

public class RoleBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private UserService userService;
	private OrganizationService organizationService;
	private AccessService accessService;
	private Role[] roles;
	private ResourceAccessListItem[] roleAccessList;
	private Role role;
	private HtmlDataTable roleTable;
	private HtmlSelectOneMenu supplierSource;
	private HtmlSelectOneMenu accessTypeCategoryKey;
	private HtmlDataTable roleAccessTable;
	private List<ResourceAccessListItem> deltetedResources;

	public String actionEdit(){
		this.role = (Role) this.roleTable.getRowData();
		loadRoles();
		loadAccess();
		this.deltetedResources = new ArrayList<ResourceAccessListItem>();
		return "edit_role";
	}
	public void loadRoles(){
		this.roles = this.userService.getRoleListBySystem(((ValueResultSystem)this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue()).getList();
	}
	private void loadAccess() {
		if(this.role != null){
			this.roleAccessList = this.accessService.getAccessListByRole(this.role.getKey()).getList();
		} else {
			this.roleAccessList = new ResourceAccessListItem[0];
		}
	}
	public String actionCancel(){
		this.role = null;
		this.supplierSource = null;
		this.accessTypeCategoryKey = null;
		return "roles_overview";
	}
	public String actionSave() {
		for (ResourceAccessListItem accessItem : this.deltetedResources) {
			if(accessItem.getId() == null){ continue; }
			this.accessService.deleteResourceAccess(accessItem);
		}
		for (ResourceAccessListItem accessItem : this.roleAccessList) {
			if(accessItem.getId() != null){ continue; }

			ResourceAccess resourceAccess = new ResourceAccess();
			Access access = new Access();
			AccessType accessType = ((ValueResultAccessType)this.accessService.getAccessTypeByTypeCategory(accessItem.getKey(), accessItem.getCategory())).getValue();
			access.setAccessType(accessType);
			OrganizationListItem organizationListItem = new OrganizationListItem();
			organizationListItem.setId(accessItem.getProvidedBy());
			SupplierOrganization supplier = ((ValueResultSupplierOrganization)this.organizationService.getOrganizationByListItem(organizationListItem)).getValue();
			access.setProvidedBy(supplier);
			Calendar calendar = Calendar.getInstance();
			access.setValidFrom(calendar.getTime());
			calendar.add(Calendar.YEAR, 1);
			access.setValidTo(calendar.getTime());
			resourceAccess.setAccess(access);

			SupplierSourceResource supplierSourceResource = new SupplierSourceResource();
			Resource resource = new Resource();
			resource.setId(accessItem.getResourceId());
			resource.setOfferedBy(supplier.getOrganization().getId());
			ResourceType resourceType = ((ValueResultResourceType)accessService.getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();
			resource.setResourceType(resourceType);
						
			SupplierSource supplierSource = new SupplierSource();
			supplierSource.setId(accessItem.getSupplierSourceId());
			supplierSource.setSupplierSourceName(accessItem.getSupplierSourceName());
			supplierSource.setUrl(accessItem.getUrl());
			supplierSource.setHost(accessItem.getHost());
			supplierSourceResource.setResource(resource);
			supplierSourceResource.setSupplierSource(supplierSource);
			
			resourceAccess.setResource(supplierSourceResource);
			this.accessService.insertUserRoleResourceAccess(this.role, resourceAccess);
		}
		this.role = null;
		this.supplierSource = null;
		this.accessTypeCategoryKey = null;
		return "roles_overview";
	}
	
	public String actionAddSource(){
		String selectedSourceValue = this.supplierSource.getSubmittedValue().toString();
		String selectedAccessTypeCategoryKeyValue = this.accessTypeCategoryKey.getSubmittedValue().toString();
		String selectedAccessTypeCategoryKeyValueArray[] = selectedAccessTypeCategoryKeyValue.split(System.getProperty("path.separator"));
		String selectedAccessTypeCategoryValue = selectedAccessTypeCategoryKeyValueArray[0];
		String selectedAccessTypeKeyValue = selectedAccessTypeCategoryKeyValueArray[1];

		SupplierSourceResource addedResource = null;
		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(selectedSourceValue.equals(""+supplierSourceResource.getResource().getId())){
				addedResource = supplierSourceResource;
			}
		}
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.roleAccessList.length + 1];
		for(int i=0; i<this.roleAccessList.length;i++){
			newList[i] = this.roleAccessList[i];
		}
		newList[newList.length-1] = new ResourceAccessListItem();
		newList[newList.length-1].setSupplierSourceName(addedResource.getSupplierSource().getSupplierSourceName());
		newList[newList.length-1].setCategory(new AccessTypeCategory(selectedAccessTypeCategoryValue));
		newList[newList.length-1].setKey(new AccessTypeKey(selectedAccessTypeKeyValue));
		newList[newList.length-1].setUrl(addedResource.getSupplierSource().getUrl());
		newList[newList.length-1].setHost(addedResource.getSupplierSource().getHost());
		newList[newList.length-1].setProvidedBy(addedResource.getResource().getOfferedBy());
		newList[newList.length-1].setResourceId(addedResource.getResource().getId());
		newList[newList.length-1].setSupplierSourceId(addedResource.getSupplierSource().getId());

		this.roleAccessList = newList;
		return "";
	}
	
	public void actionDeleteSource(){
		int index = this.roleAccessTable.getRowIndex();
		this.deltetedResources.add(this.roleAccessList[index]);
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.roleAccessList.length - 1];
		int j=0; int i=0;
		for (ResourceAccessListItem access : this.roleAccessList) {
			if(j != index){ newList[i++] = access; } j++;
		}
		this.roleAccessList = newList;
	}
	
	public List<SelectItem> getSupplierSourceList() {
		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (SupplierSourceResource resource : resources) {
			SelectItem item = new SelectItem("" + resource.getResource().getId(), "" +
					resource.getSupplierSource().getSupplierSourceName() + " " +
					resource.getSupplierSource().getHost());
			list.add(item);
		}
		return list;
	}
	
	public HtmlSelectOneMenu getSupplierSource() {
		return supplierSource;
	}
	public void setSupplierSource(HtmlSelectOneMenu supplierSource) {
		this.supplierSource = supplierSource;
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Role[] getRoles() {
		if(this.roles==null){ loadRoles(); }
		return roles;
	}
	public void setRoles(Role[] roles) {
		this.roles = roles;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public HtmlDataTable getRoleTable() {
		return roleTable;
	}
	public void setRoleTable(HtmlDataTable roleTable) {
		this.roleTable = roleTable;
	}
	public ResourceAccessListItem[] getRoleAccessList() {
		if(this.roleAccessList==null) { loadAccess(); }
		return roleAccessList;
	}
	public void setRoleAccessList(ResourceAccessListItem[] roleAccessList) {
		this.roleAccessList = roleAccessList;
	}
	public HtmlDataTable getRoleAccessTable() {
		return roleAccessTable;
	}
	public void setRoleAccessTable(HtmlDataTable roleAccessTable) {
		this.roleAccessTable = roleAccessTable;
	}
	public HtmlSelectOneMenu getAccessTypeCategoryKey() {
		return accessTypeCategoryKey;
	}
	public void setAccessTypeCategoryKey(HtmlSelectOneMenu accessTypeCategoryKey) {
		this.accessTypeCategoryKey = accessTypeCategoryKey;
	}
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
