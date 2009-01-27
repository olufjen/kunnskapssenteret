package no.helsebiblioteket.admin.daoobjects;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Role;

public class UserRole {
	private Role userRole;
	private Integer userId;
	private Date lastChanged = null;
	
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
