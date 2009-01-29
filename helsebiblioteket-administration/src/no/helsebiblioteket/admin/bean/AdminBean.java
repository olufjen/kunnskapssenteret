package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class AdminBean {
	private static final String organizationTypesBundle = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_organization_type";

	private OrganizationService organizationService;
	private List<SelectItem> organizationTypeSelectItemList;
	
	
	public List<SelectItem> getOrganizationTypeSelectItemList() {
		if (this.organizationTypeSelectItemList == null) {
			organizationTypeSelectItemList = new ArrayList<SelectItem>();
			OrganizationType[] organizationTypeList = this.organizationService.getOrganizationTypeListAll("").getList();
			SelectItem selectItem = null;
			for (OrganizationType orgType: organizationTypeList) {
				selectItem = new SelectItem(String.valueOf(orgType.getId()),
						MessageResourceReader.getMessageResourceString(organizationTypesBundle, orgType.getKey().toString(), orgType.getName()));
				organizationTypeSelectItemList.add(selectItem);
			}
		}
		return this.organizationTypeSelectItemList;
	}


	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
