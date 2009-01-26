package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.Profile;

public interface ProfileDao {
	// Edit
	public void insertProfile(Profile profile);
	public void updateProfile(Profile profile);
	public void deleteProfile(Profile profile);

	// Fetch
}
