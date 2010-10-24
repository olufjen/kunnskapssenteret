package no.helsebiblioteket.admin.sqlmapdao;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.domain.Profile;

public class SqlMapProfileDao extends IbatisSqlMapClientDaoSupport implements ProfileDao {
	public void insertProfile(Profile profile) {
		getSqlMapClientTemplate().insert("insertProfile", profile);
		Profile tmp = (Profile) getSqlMapClientTemplate().queryForObject("getProfileById", profile.getId());
		profile.setLastChanged(tmp.getLastChanged());
	}
	public void updateProfile(Profile profile) {
		getSqlMapClientTemplate().update("updateProfile", profile);
		Profile tmp = (Profile) getSqlMapClientTemplate().queryForObject("getProfileById", profile.getId());
		profile.setLastChanged(tmp.getLastChanged());
	}
	public void deleteProfile(Profile profile) {
		getSqlMapClientTemplate().delete("deleteProfile", profile);
	}
	public Profile getProfileById(Integer profileId) {
		return (Profile) getSqlMapClientTemplate().queryForObject("getProfileById", profileId);
	}
	public Profile getProfileBySubscriptionKey(String subscriptionKey) {
		return (Profile) getSqlMapClientTemplate().queryForObject("getProfileBySubscriptionKey", subscriptionKey);
	}
}
