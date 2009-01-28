package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapAccessDao extends SqlMapClientDaoSupport implements AccessDao {
	/**
	 * 
	 */
	public void insertAccess(ResourceAccess access){
		// TODO: Go through all!
		
	}
	public void updateAccess(ResourceAccess access){
		
	}
	public void deleteAccess(ResourceAccess access){
		
		getSqlMapClientTemplate().delete("deleteAccess", access);

	}
	public List<ResourceAccess> getAccessListByUser(User user){
		return null;
	}
	public List<ResourceAccess> getAccessListByOrganization(Organization organization){
		return null;
	}
	public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType){
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
