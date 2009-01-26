package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapAccessDao extends SqlMapClientDaoSupport implements AccessDao {
	// TODO: Go through all!
	public void insertAccess(Access access){
		
	}
	public void updateAccess(Access access){
		
	}
	public void deleteAccess(Access access){
		
		getSqlMapClientTemplate().delete("deleteAccess", access);

	}
	public List<Access> getAccessListByUser(User user){
		return null;
	}
	public List<Access> getAccessListByOrganization(Organization organization){
		return null;
	}
	public List<Access> getAccessListByOrganizationType(OrganizationType organizationType){
		return null;
	}

	
	
	
	

	
	
	
	
	
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


	public List<Access> getAccessListByOrganizationId(Integer organizationId) {
		return (List<Access>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organizationId);
	}
}
