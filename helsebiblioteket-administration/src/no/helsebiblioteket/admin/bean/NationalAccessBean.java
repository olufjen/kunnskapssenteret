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
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

public class NationalAccessBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private UserService userService;
	private OrganizationService organizationService;
	private AccessService accessService;
	private ResourceAccessListItem[] nationalAccessList;
	private HtmlSelectOneMenu supplierSource;
	private HtmlDataTable nationalAccessTable;
	private List<ResourceAccessListItem> deltetedResources;

	private void loadAccess() {
		this.deltetedResources = new ArrayList<ResourceAccessListItem>();
		this.nationalAccessList = this.accessService.getAccessListNational().getList();
	}
	public String actionCancel(){
		this.supplierSource = null;
		this.deltetedResources = new ArrayList<ResourceAccessListItem>();
		this.nationalAccessList = null;
		this.nationalAccessTable = null;
		return "cancel_national_access";
	}
	public String actionSave() {
		if(this.deltetedResources == null){ this.deltetedResources = new ArrayList<ResourceAccessListItem>(); }
		for (ResourceAccessListItem accessItem : this.deltetedResources) {
			if(accessItem.getId() == null){ continue; }
			this.accessService.deleteResourceAccess(accessItem);
		}
		for (ResourceAccessListItem accessItem : this.getNationalAccessList()) {
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
			supplierSourceResource.setResource(resource);
			supplierSourceResource.setSupplierSource(supplierSource);
			
			resourceAccess.setResource(supplierSourceResource);
			this.accessService.insertNationalResourceAccess(resourceAccess);
		}
		this.supplierSource = null;
		this.deltetedResources = new ArrayList<ResourceAccessListItem>();
		this.nationalAccessList = null;
		this.nationalAccessTable = null;
		return "save_national_access";
	}
	
	public String actionAddSource(){
		String selectedSourceValue = this.supplierSource.getSubmittedValue().toString();
		AccessType accessTypeGrantProxyInclude = ((ValueResultAccessType)this.accessService.getAccessTypeByTypeCategory(AccessTypeKey.proxy_include, AccessTypeCategory.GRANT)).getValue();
		
		SupplierSourceResource addedResource = null;
		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(selectedSourceValue.equals(""+supplierSourceResource.getResource().getId())){
				addedResource = supplierSourceResource;
			}
		}
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.getNationalAccessList().length + 1];
		for(int i=0; i<this.nationalAccessList.length;i++){
			newList[i] = this.nationalAccessList[i];
		}
		newList[newList.length-1] = new ResourceAccessListItem();
		newList[newList.length-1].setSupplierSourceName(addedResource.getSupplierSource().getSupplierSourceName());
		newList[newList.length-1].setCategory(accessTypeGrantProxyInclude.getCategory());
		newList[newList.length-1].setKey(accessTypeGrantProxyInclude.getKey());
		newList[newList.length-1].setUrl(addedResource.getSupplierSource().getUrl());
		newList[newList.length-1].setProvidedBy(addedResource.getResource().getOfferedBy());
		newList[newList.length-1].setResourceId(addedResource.getResource().getId());
		newList[newList.length-1].setSupplierSourceId(addedResource.getSupplierSource().getId());

		this.nationalAccessList = newList;
		return "";
	}
	
	public void actionDeleteSource(){
		int index = this.nationalAccessTable.getRowIndex();
		this.deltetedResources.add(this.getNationalAccessList()[index]);
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.nationalAccessList.length - 1];
		int j=0; int i=0;
		for (ResourceAccessListItem access : this.nationalAccessList) {
			if(j != index){ newList[i++] = access; } j++;
		}
		this.nationalAccessList = newList;
	}
	
	public List<SelectItem> getSupplierSourceList() {
		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (SupplierSourceResource resource : resources) {
			SelectItem item = new SelectItem("" + resource.getResource().getId(), "" +
					resource.getSupplierSource().getSupplierSourceName() + " " +
					resource.getSupplierSource().getUrl().getDomain());
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
	public ResourceAccessListItem[] getNationalAccessList() {
		if(this.nationalAccessList==null) { loadAccess(); }
		return nationalAccessList;
	}
	public void setNationalAccessList(ResourceAccessListItem[] nationalAccessList) {
		this.nationalAccessList = nationalAccessList;
	}
	public HtmlDataTable getNationalAccessTable() {
		return nationalAccessTable;
	}
	public void setNationalAccessTable(HtmlDataTable nationalAccessTable) {
		this.nationalAccessTable = nationalAccessTable;
	}
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
