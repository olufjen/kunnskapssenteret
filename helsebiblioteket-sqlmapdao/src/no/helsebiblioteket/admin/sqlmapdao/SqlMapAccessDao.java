package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.domain.Access;

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
		getSqlMapClientTemplate().delete("deleteAccess", access);
	}

	@Override
	public void insertAccess(Access access) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccess(Access access) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Access> getAccessListByOrganizationId(Integer organizationId) {
		return (List<Access>) getSqlMapClientTemplate().queryForList("getAccessListByOrganizationId", organizationId);
	}
}