package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Profile {
	private Integer id = null;
	private Boolean receiveNewsletter = false;
	private Boolean participateSurvey = false;
	private Person person;
	private Date lastChanged = null;
	
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
