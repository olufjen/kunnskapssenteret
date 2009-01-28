package no.helsebiblioteket.admin.domain.line;

import java.util.Date;

public class UserRoleLine {
	// Primary key
	private Integer userRoleId;
	private Integer userId;

	// Local value
	private Date lastChanged;
	
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
