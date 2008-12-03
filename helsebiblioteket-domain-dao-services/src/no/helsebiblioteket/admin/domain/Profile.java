package no.helsebiblioteket.admin.domain;

public class Profile {
	private Boolean receiveNewsletter = false;
	private Boolean participateSurvey = false;
	private Person person;
	public Boolean getReceiveNewsletter() { return receiveNewsletter; }
	public void setReceiveNewsletter(Boolean receiveNewsletter) { this.receiveNewsletter = receiveNewsletter; }
	public Boolean getParticipateSurvey() { return participateSurvey; }
	public void setParticipateSurvey(Boolean participateSurvey) { this.participateSurvey = participateSurvey; }
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
