package no.helsebiblioteket.admin.test.dao;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.factory.ProfileFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ProfileDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testProfile(){
		Profile profile = ProfileFactory.factory.completeProfile();
		
		// INSERT
		this.insertProfile(profile);

		ProfileDao profileDao = beanFactory.getProfileDao();

		// GET
		Profile found = profileDao.getProfileById(profile.getId());
		boolean was = found.getParticipateSurvey();
		found.setParticipateSurvey( ! was);
		
		// UPDATE
		profileDao.updateProfile(found);
		found = profileDao.getProfileById(found.getId());
		Assert.isTrue(found.getParticipateSurvey() != was, "Not updated");
		
		// DELETE
		this.removeProfile(found);
	}
	public void insertProfile(Profile profile){
		ProfileDao profileDao = beanFactory.getProfileDao();
		profileDao.insertProfile(profile);
	}
	public void removeProfile(Profile profile){
		ProfileDao profileDao = beanFactory.getProfileDao();
		profileDao.deleteProfile(profile);
	}
}
