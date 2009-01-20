package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapAccessDao extends SqlMapClientDaoSupport implements AccessDao {

	public Access getAccessById(Integer accessId) {
		return (Access) getSqlMapClientTemplate().queryForObject("getAccessById", accessId);
	}

	public List<Access> getAccessListByOrganizationTypeId(Integer organizationTypeId) {
		return (List<Access>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationTypeId", organizationTypeId);
	}

	public List<Access> getAccessListByUserId(Integer userId) {
		return (List<Access>) getSqlMapClientTemplate().queryForList("getAccessListByUserId", userId);
	}
	
	public List<Access> getAccessListByUserRoleId(Integer userRoleId) {
		return (List<Access>) getSqlMapClientTemplate().queryForList("getAccessListByRoleId", userRoleId);
	}

	@Override
	public void deleteAccess(Access access) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAccess(Access access) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccess(Access access) {
		// TODO Auto-generated method stub
		
	}
}