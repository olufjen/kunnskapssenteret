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
import no.helsebiblioteket.admin.domain.OrganizationType;
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

public class OrganizationTypeBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private OrganizationService organizationService;
	private AccessService accessService;
	private OrganizationType[] organizationTypes;
	private ResourceAccessListItem[] orgTypeAccessList;
	private OrganizationType organizationType;
	private HtmlDataTable organizationTypeTable;
	private HtmlSelectOneMenu supplierSource;
	private HtmlSelectOneMenu accessTypeCategory;

	private ResourceAccessListItem[] newOrgTypeAccessList;
	private ResourceAccessListItem[] oldOrgTypeAccessList;

	public String actionEdit(){
		this.organizationType = (OrganizationType) this.organizationTypeTable.getRowData();
		loadTypes();
		loadAccess();
		return "edit_organization_type";
	}
	public void loadTypes(){
		this.organizationTypes = this.organizationService.getOrganizationTypeListAll("").getList();
	}
	private void loadAccess() {
		if(this.organizationType != null){
			this.orgTypeAccessList = this.accessService.getAccessListByOrganizationType(this.organizationType).getList();
		} else {
			this.orgTypeAccessList = new ResourceAccessListItem[0];
		}
	}
	public String actionCancel(){
		this.organizationType = null;
		return "organization_types_overview";
	}
	public String actionSave() {
		for (ResourceAccessListItem accessItem : this.orgTypeAccessList) {
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
			this.accessService.insertOrganizationTypeResourceAccess(this.organizationType, resourceAccess);
		}
		return "organization_types_overview";
	}
	
	public String actionAddSource(){
		String selectedSourceValue = this.supplierSource.getSubmittedValue().toString();
		String selectedAccessTypeCategoryValue = this.accessTypeCategory.getSubmittedValue().toString();

		SupplierSourceResource addedResource = null;
		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(selectedSourceValue.equals(""+supplierSourceResource.getResource().getId())){
				addedResource = supplierSourceResource;
			}
		}
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.orgTypeAccessList.length + 1];
		for(int i=0; i<this.orgTypeAccessList.length;i++){
			newList[i] = this.orgTypeAccessList[i];
		}
		newList[newList.length-1] = new ResourceAccessListItem();
		newList[newList.length-1].setSupplierSourceName(addedResource.getSupplierSource().getSupplierSourceName());
		newList[newList.length-1].setCategory(new AccessTypeCategory(selectedAccessTypeCategoryValue));
		newList[newList.length-1].setId(addedResource.getResource().getId());
		newList[newList.length-1].setKey(AccessTypeKey.general);
		newList[newList.length-1].setUrl(addedResource.getSupplierSource().getUrl());
		newList[newList.length-1].setProvidedBy(addedResource.getResource().getOfferedBy());
		newList[newList.length-1].setResourceId(addedResource.getResource().getId());
		newList[newList.length-1].setSupplierSourceId(addedResource.getSupplierSource().getId());

		this.orgTypeAccessList = newList;
		return "";
	}
	
	public List<SelectItem> getSupplierSourceList() {
		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (SupplierSourceResource resource : resources) {
			SelectItem item = new SelectItem("" + resource.getResource().getId(), "" +
					resource.getSupplierSource().getSupplierSourceName() + " " +
					resource.getSupplierSource().getUrl().getStringValue());
			list.add(item);
		}
		return list;
	}
	public List<SelectItem> getAccessTypeCategoryList(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(AccessTypeCategory.GRANT.getValue(), "Grant"));
		list.add(new SelectItem(AccessTypeCategory.DENY.getValue(), "Deny"));
		return list;
	}


	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	public OrganizationType[] getOrganizationTypes() {
		if(this.organizationTypes==null) { loadTypes(); }
		return organizationTypes;
	}
	public void setOrganizationTypes(OrganizationType[] organizationTypes) {
		this.organizationTypes = organizationTypes;
	}
	public HtmlDataTable getOrganizationTypeTable() {
		return organizationTypeTable;
	}
	public void setOrganizationTypeTable(HtmlDataTable organizationTypeTable) {
		this.organizationTypeTable = organizationTypeTable;
	}
	public OrganizationType getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}
	public ResourceAccessListItem[] getOrgTypeAccessList() {
		if(this.orgTypeAccessList==null) { loadAccess(); }
		return orgTypeAccessList;
	}
	public void setOrgTypeAccessList(ResourceAccessListItem[] orgTypeAccessList) {
		this.orgTypeAccessList = orgTypeAccessList;
	}
	public HtmlSelectOneMenu getSupplierSource() {
		return supplierSource;
	}
	public void setSupplierSource(HtmlSelectOneMenu supplierSource) {
		this.supplierSource = supplierSource;
	}
	public HtmlSelectOneMenu getAccessTypeCategory() {
		return accessTypeCategory;
	}
	public void setAccessTypeCategory(HtmlSelectOneMenu accessTypeCategory) {
		this.accessTypeCategory = accessTypeCategory;
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
}