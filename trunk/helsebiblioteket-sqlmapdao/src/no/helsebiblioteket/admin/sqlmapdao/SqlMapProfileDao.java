package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapProfileDao extends SqlMapClientDaoSupport implements ProfileDao {
	
	public Profile getProfileById(Integer profileId) {
		return (Profile) getSqlMapClientTemplate().queryForObject("getProfileById", profileId);
	}
	
	public void deleteProfile(Profile profile) {
		getSqlMapClientTemplate().delete("deleteProfile", profile);
	}
	
	public void insertProfile(Profile profile) {
		getSqlMapClientTemplate().insert("insertProfile", profile);
	}
	
	public void updateProfile(Profile profile) {
		getSqlMapClientTemplate().update("updatePerson", profile);
	}
	
	public void saveProfile(Profile changedProfile, Profile originalProfile) {
		if (changedProfile.getId() == null) {
			insertProfile(changedProfile);
		} else {
			updateProfile(changedProfile);
		}	
	}
}