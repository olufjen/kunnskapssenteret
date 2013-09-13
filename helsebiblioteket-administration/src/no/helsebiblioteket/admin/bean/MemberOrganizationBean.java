package no.helsebiblioteket.admin.bean;

import javax.faces.component.UIInput;

import org.springframework.security.context.SecurityContextHolder;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

public class MemberOrganizationBean {
	private UserService userService;
	private OrganizationService organizationService;
	private User loggedInUser = null;
	private UIInput logoPicture = null;
	
	public MemberOrganizationBean() {
	}
	
	public User getLoggedInUser() {
		if (this.loggedInUser == null) {
			org.springframework.security.userdetails.User springSecurityUser = 
					(org.springframework.security.userdetails.User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			SingleResultUser lookup = this.userService.findUserByUsername(springSecurityUser.getUsername());
			User foundUser = null;
			if (lookup instanceof ValueResultUser) {
				foundUser = ((ValueResultUser) lookup).getValue();
			} else if (lookup instanceof ValueResultOrganizationUser) {
				foundUser = ((ValueResultOrganizationUser) lookup).getValue().getUser();
			}
			this.loggedInUser = foundUser;
		}
		return this.loggedInUser;
	}
	
	public String actionEditOrganization() {
		return "edit_member_organization";
	}
	
	public String actionSave() {
		organizationService.updateOrganization(getLoggedInUser().getOrgAdminFor());
		return "member_organization";
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	public UIInput getLogoPicture() {
		return logoPicture;
	}
	public void setLogoPicture(UIInput logoPicture) {
		this.logoPicture = logoPicture;
	}
}
