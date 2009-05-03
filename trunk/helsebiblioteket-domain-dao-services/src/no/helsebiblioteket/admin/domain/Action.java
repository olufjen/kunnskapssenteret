package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

public class Action implements Serializable {
	public static final String USER_ACCESS = "U";
	public static final String ORGANIZATION_ACCESS = "O";
	private Integer id;
	
	// Not in use yet
//	private Resource resource;
//	private AccessType accessType;

	private String userOrganizationAccess = "";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserOrganizationAccess() {
		return userOrganizationAccess;
	}
	public void setUserOrganizationAccess(String userOrganizationAccess) {
		this.userOrganizationAccess = userOrganizationAccess;
	}
}
