package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.LoggedInUser;

public class LoggedInUserResult {
	private LoggedInUser user;
	private boolean success = false;
	public LoggedInUserResult() { }
	public LoggedInUserResult(LoggedInUser value){
		this.user = value;
	}
	public LoggedInUser getUser() {
		return user;
	}
	public void setUser(LoggedInUser user) {
		this.user = user;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
