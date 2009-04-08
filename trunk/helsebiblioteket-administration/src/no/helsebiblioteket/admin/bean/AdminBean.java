package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class AdminBean {
	private static final String organizationTypeKeyBundle = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_organizationtypekey";

	private OrganizationService organizationService;
	private OrganizationType[] organizationTypeList;
	
	
	public List<SelectItem> getOrganizationTypeSelectItemList() {
		List<SelectItem> organizationTypeSelectItemList = new ArrayList<SelectItem>();
		if (this.organizationTypeList == null) {
			organizationTypeList = this.organizationService.getOrganizationTypeListAll("").getList();
		}
		SelectItem selectItem = null;
		for (OrganizationType orgType: organizationTypeList) {
			selectItem = new SelectItem(orgType.getKey().getValue(), MessageResourceReader.getMessageResourceString(organizationTypeKeyBundle, orgType.getKey().getValue(), orgType.getName()));
			organizationTypeSelectItemList.add(selectItem);
		}
		return organizationTypeSelectItemList;
	}


	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
