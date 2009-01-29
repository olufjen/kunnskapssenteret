package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public class SqlMapOrganizationDao extends SqlMapClientDaoSupport implements OrganizationDao {
	public void insertOrganization(Organization organization){
		getSqlMapClientTemplate().insert("insertOrganization", organization);
	}
	public void updateOrganization(Organization organization){
		getSqlMapClientTemplate().update("updateOrganization", organization);
	}
	public void deleteOrganization(Organization organization){
		getSqlMapClientTemplate().delete("deleteOrganization", organization.getId());
	}
	public MemberOrganization getMemberOrganizationById(Integer id){
		return (MemberOrganization) getSqlMapClientTemplate().queryForObject("getMemberOrganizationById", id);
	}
	public SupplierOrganization getSupplierOrganizationById(Integer id){
		return (SupplierOrganization) getSqlMapClientTemplate().queryForObject("getSupplierOrganizationById", id);
	}
}
