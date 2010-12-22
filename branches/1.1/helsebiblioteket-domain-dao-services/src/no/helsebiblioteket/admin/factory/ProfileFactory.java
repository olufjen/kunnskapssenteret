package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Profile;

public class ProfileFactory {
	public static ProfileFactory factory = new ProfileFactory();
	private ProfileFactory(){}
	public Profile createProfile(){
		Profile profile = new Profile();
		profile.setLastChanged(new Date());
		profile.setParticipateSurvey(false);
		profile.setReceiveNewsletter(false);
		return profile;
	}
	public Profile completeProfile(){
		return createProfile();
	}
}
