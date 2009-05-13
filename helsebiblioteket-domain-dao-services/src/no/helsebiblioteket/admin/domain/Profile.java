package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Profile implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private Boolean receiveNewsletter;
	private Boolean participateSurvey;
	private Date lastChanged;

	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + receiveNewsletter + "; " + participateSurvey + "]";
	}

	// GET/SET
	public Boolean getReceiveNewsletter() { return receiveNewsletter; }
	public void setReceiveNewsletter(Boolean receiveNewsletter) { this.receiveNewsletter = receiveNewsletter; }
	public Boolean getParticipateSurvey() { return participateSurvey; }
	public void setParticipateSurvey(Boolean participateSurvey) { this.participateSurvey = participateSurvey; }
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
