package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.RoleDao;
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
import no.helsebiblioteket.admin.domain.UserRoleLine;

public class SqlMapRoleDao extends SqlMapClientDaoSupport implements RoleDao {
	
	public List<Role> getUserRoleListByUserId(Integer userId) {
		return (List<Role>) getSqlMapClientTemplate().queryForObject("getUserRoleListByUserId", userId);
	}
	
	public void insertUserRoleLine(UserRoleLine userRoleLine) {
		getSqlMapClientTemplate().insert("insertUserRoleLine", userRoleLine);	
	}
	
	public void deleteUserRoleLine(UserRoleLine userRoleLine) {
		getSqlMapClientTemplate().delete("deleteUserRoleLine", userRoleLine);		
	}

	public List<Role> getAllRoles() {
		return (List<Role>) getSqlMapClientTemplate().queryForList("getUserRoleList");
	}

	public Role getUserRoleById(Integer userRoleId) {
		return (Role) getSqlMapClientTemplate().queryForObject("getUserRoleById", userRoleId);
	}
}