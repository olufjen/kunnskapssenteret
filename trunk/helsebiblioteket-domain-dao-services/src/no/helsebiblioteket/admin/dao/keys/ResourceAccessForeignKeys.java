package no.helsebiblioteket.admin.dao.keys;

import java.util.Date;

import no.helsebiblioteket.admin.domain.ResourceAccess;

public class ResourceAccessForeignKeys {
	private Integer userId;
	private Integer userRoleId;
	private Integer orgUnitId;
	private Integer orgTypeId;
	private ResourceAccess resourceAccess;

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public ResourceAccess getResourceAccess() {
		return resourceAccess;
	}

	public void setResourceAccess(ResourceAccess resourceAccess) {
		this.resourceAccess = resourceAccess;
	}
}
