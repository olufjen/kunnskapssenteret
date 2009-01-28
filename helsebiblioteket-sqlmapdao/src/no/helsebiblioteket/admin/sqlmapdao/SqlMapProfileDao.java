package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.domain.Profile;


public class SqlMapProfileDao extends SqlMapClientDaoSupport implements ProfileDao {
	// TODO: Go through all!
	public void insertProfile(Profile profile) {
		getSqlMapClientTemplate().insert("insertProfile", profile);
	}
	public void updateProfile(Profile profile) {
		getSqlMapClientTemplate().update("updatePerson", profile);
	}
	public void deleteProfile(Profile profile) {
		getSqlMapClientTemplate().delete("deleteProfile", profile);
	}

	

	
	
	public Profile getProfileById(Integer profileId) {
		return (Profile) getSqlMapClientTemplate().queryForObject("getProfileById", profileId);
	}
	public void saveProfile(Profile changedProfile, Profile originalProfile) {
		if (changedProfile.getProfileId() == null) {
			insertProfile(changedProfile);
		} else {
			updateProfile(changedProfile);
		}	
	}
}
