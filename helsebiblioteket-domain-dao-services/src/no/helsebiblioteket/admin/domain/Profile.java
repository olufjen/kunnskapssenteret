package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Profile {
	// Primary key
	private Integer profileId;
	
	// Local values
	private Boolean receiveNewsletter;
	private Boolean participateSurvey;
	private Date lastChanged;

	// GET/SET
	public Boolean getReceiveNewsletter() { return receiveNewsletter; }
	public void setReceiveNewsletter(Boolean receiveNewsletter) { this.receiveNewsletter = receiveNewsletter; }
	public Boolean getParticipateSurvey() { return participateSurvey; }
	public void setParticipateSurvey(Boolean participateSurvey) { this.participateSurvey = participateSurvey; }
	
	public void setId(Integer id) {
		this.profileId = id;
	}
	public Integer getId() {
		return this.profileId;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
