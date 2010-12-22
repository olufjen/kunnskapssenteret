package no.helsebiblioteket.admin.bean;

/**
 * @author Leif Torger, kunnskapssenteret.no
 * Class provides access to static values and lists like dropdownlists etc.
 */
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class AdminBean {
	private static final String organizationTypeKeyBundle = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_organizationtypekey";
	private static final String accessTypeCategoryBundle = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_accesstypecategory";
	private static final String accessTypeKeyBundle = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_accesstypekey";

	private OrganizationService organizationService;
	private UserService userService;

	private OrganizationType[] organizationTypeList;
	
	public List<SelectItem> getRoleSelectItemList() {
		List<SelectItem> rolesSelectItemList = new ArrayList<SelectItem>();
		Role[] roles = this.userService.getRoleListBySystem(
				((ValueResultSystem)
						this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue()).getList();
		for (Role role : roles) {
			SelectItem option = new SelectItem(role.getKey().getValue(), role.getName(), "", false);
			rolesSelectItemList.add(option);
		}
		return rolesSelectItemList;
	}
	
	public List<SelectItem> getOrganizationTypeSelectItemList() {
		List<SelectItem> organizationTypeSelectItemList = new ArrayList<SelectItem>();
		if (this.organizationTypeList == null) {
			organizationTypeList = this.organizationService.getOrganizationTypeListAll("").getList();
		}
		SelectItem selectItem = null;
		for (OrganizationType orgType: organizationTypeList) {
			selectItem = new SelectItem(orgType.getKey().getValue(), MessageResourceReader.getMessageResourceString(organizationTypeKeyBundle, orgType.getKey().getValue()));
			organizationTypeSelectItemList.add(selectItem);
		}
		return organizationTypeSelectItemList;
	}
	
	public List<SelectItem> getAccessTypeCategoryKeySelectItemList(){
		List<SelectItem> accessTypeSelectItemList = new ArrayList<SelectItem>();
		SelectItem selectItem = null;
		selectItem = new SelectItem(
				AccessTypeCategory.GRANT.getValue() + System.getProperty("path.separator") + AccessTypeKey.proxy_include.getValue(), 
				MessageResourceReader.getMessageResourceString(accessTypeCategoryBundle, AccessTypeCategory.GRANT.getValue()) + " - " +
				MessageResourceReader.getMessageResourceString(accessTypeKeyBundle, AccessTypeKey.proxy_include.getValue())
				);
		accessTypeSelectItemList.add(selectItem);
		selectItem = new SelectItem(
				AccessTypeCategory.GRANT.getValue() + System.getProperty("path.separator") + AccessTypeKey.proxy_exclude.getValue(), 
				MessageResourceReader.getMessageResourceString(accessTypeCategoryBundle, AccessTypeCategory.GRANT.getValue()) + " - " +
				MessageResourceReader.getMessageResourceString(accessTypeKeyBundle, AccessTypeKey.proxy_exclude.getValue())
				);
		accessTypeSelectItemList.add(selectItem);
		selectItem = new SelectItem(
				AccessTypeCategory.DENY.getValue() + System.getProperty("path.separator") + AccessTypeKey.general.getValue(), 
				MessageResourceReader.getMessageResourceString(accessTypeCategoryBundle, AccessTypeCategory.DENY.getValue()) + " - " +
				MessageResourceReader.getMessageResourceString(accessTypeKeyBundle, AccessTypeKey.general.getValue())
				);
		accessTypeSelectItemList.add(selectItem);
		return accessTypeSelectItemList;
	}

	public static String subStringMax(String from, int max) {
		if(from.length() >= max){ return from.substring(0, max) + " ..."; } else { return from; }
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
