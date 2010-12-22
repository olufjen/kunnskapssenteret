package no.helsebiblioteket.admin.domain.parameter;

import java.util.List;


public class UserExportParameter {
	private String roleKeyListAsString;
	private List<String> roleKeyList;
	private Boolean receiveNewsletter;
	private Boolean participateSurvey;
	private Boolean randomize;
	private Integer limit;
	
	public UserExportParameter() {
	}
	
	public void setRoleKeyListAsString(String roleKeyListAsString) {
		this.roleKeyListAsString = roleKeyListAsString;
	}
	public String getRoleKeyListAsString() {
		return this.roleKeyListAsString;
	}
	
	public List<String> getRoleKeyList() {
		return roleKeyList;
	}

	public void setRoleKeyList(List<String> roleKeyList) {
		this.roleKeyList = roleKeyList;
	}
	
	public Boolean getReceiveNewsletter() {
		return receiveNewsletter;
	}
	public void setReceiveNewsletter(Boolean receiveNewsletter) {
		this.receiveNewsletter = receiveNewsletter;
	}
	public Boolean getParticipateSurvey() {
		return participateSurvey;
	}
	public void setParticipateSurvey(Boolean participateSurvey) {
		this.participateSurvey = participateSurvey;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Boolean getRandomize() {
		return randomize;
	}
	public void setRandomize(Boolean randomize) {
		this.randomize = randomize;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result
			.append("roleKeyListAsString: " + roleKeyListAsString).append("\n")
			.append("receiveNewsletter: " + receiveNewsletter).append("\n")
			.append("participateSurvey: " + participateSurvey).append("\n")
			.append("randomize: " + randomize).append("\n")
			.append("limit: " + limit)
			;
		return result.toString();
	}
}