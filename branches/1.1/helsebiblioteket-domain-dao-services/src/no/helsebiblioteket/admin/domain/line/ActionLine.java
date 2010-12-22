package no.helsebiblioteket.admin.domain.line;

import java.util.Date;

public class ActionLine {
	private Integer id;
	private Integer userId;
	private Integer resourceId;
	private Integer orgUnitId;
	private Integer accessTypeId;
	private boolean deleted = false;
	private Date actionPerformed;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public Integer getAccessTypeId() {
		return accessTypeId;
	}
	public void setAccessTypeId(Integer accessTypeId) {
		this.accessTypeId = accessTypeId;
	}
	public Date getActionPerformed() {
		return actionPerformed;
	}
	public void setActionPerformed(Date actionPerformed) {
		this.actionPerformed = actionPerformed;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
