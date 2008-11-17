package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.service.AdminService;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class AdminBean {
	AdminService adminService;
	
	private static final String organizationTypesBundle = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_organization_type";
	List<SelectItem> organizationTypeSelectItemList;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public List<SelectItem> getOrganizationTypeSelectItemList() {
		if (this.organizationTypeSelectItemList == null) {
			organizationTypeSelectItemList = new ArrayList<SelectItem>();
			List<OrganizationType> organizationTypeList = adminService.getOrganizationTypeList();
			SelectItem selectItem = null;
			for (OrganizationType orgType: organizationTypeList) {
				selectItem = new SelectItem(String.valueOf(orgType.getId()), MessageResourceReader.getMessageResourceString(organizationTypesBundle, orgType.getKey(), orgType.getName()));
				organizationTypeSelectItemList.add(selectItem);
			}
		}
		return this.organizationTypeSelectItemList;
	}
}