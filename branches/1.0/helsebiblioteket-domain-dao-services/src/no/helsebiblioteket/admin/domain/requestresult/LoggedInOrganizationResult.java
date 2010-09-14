package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;

public class LoggedInOrganizationResult implements Serializable {
	private LoggedInOrganization organization;
	private boolean success = false;
	public LoggedInOrganizationResult() { }
	public LoggedInOrganizationResult(LoggedInOrganization organization){
		this.organization = organization;
	}
	public LoggedInOrganization getOrganization() {
		return organization;
	}
	public void setOrganization(LoggedInOrganization organization) {
		this.organization = organization;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
