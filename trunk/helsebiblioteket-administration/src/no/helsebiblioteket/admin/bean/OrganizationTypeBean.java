package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
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
			ResourceAccess access = new ResourceAccess();
			access.setAccess(null);
			access.setResource(null);
//			this.accessService.insertOrganizationTypeResourceAccess(this.organizationType, access);
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
