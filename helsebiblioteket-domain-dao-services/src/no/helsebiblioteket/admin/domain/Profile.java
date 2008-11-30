package no.helsebiblioteket.admin.domain;

public class Profile {
	private Boolean receiveNewsletter = null;
	private Boolean participateSurvey = null;
	public Boolean getReceiveNewsletter() { return receiveNewsletter; }
	public void setReceiveNewsletter(Boolean receiveNewsletter) { this.receiveNewsletter = receiveNewsletter; }
	public Boolean getParticipateSurvey() { return participateSurvey; }
	public void setParticipateSurvey(Boolean participateSurvey) { this.participateSurvey = participateSurvey; }
}
