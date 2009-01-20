package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.Profile;

public interface ProfileDao {
	public Profile getProfileById(Integer profileId);
	
	public void deleteProfile(Profile profile);
	
	public void insertProfile(Profile profile);
	
	public void updateProfile(Profile profile);
	
	public void saveProfile(Profile changedProfile, Profile originalProfile);
}