package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

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
