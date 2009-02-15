package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;

public class SqlMapOrganizationDao extends SqlMapClientDaoSupport implements OrganizationDao {
	public MemberOrganization getMemberOrganizationById(Integer id){
		return (MemberOrganization) getSqlMapClientTemplate().queryForObject("getMemberOrganizationById", id);
	}
	public SupplierOrganization getSupplierOrganizationById(Integer id){
		return (SupplierOrganization) getSqlMapClientTemplate().queryForObject("getSupplierOrganizationById", id);
	}
	@Override
	public void deleteMemberOrganization(MemberOrganization organization) {
		getSqlMapClientTemplate().delete("deleteOrganization", organization.getOrganization().getId());
	}
	@Override
	public void deleteSupplierOrganization(SupplierOrganization organization) {
		getSqlMapClientTemplate().delete("deleteOrganization", organization.getOrganization().getId());
	}
	@Override
	public void insertMemberOrganization(MemberOrganization organization) {
		getSqlMapClientTemplate().insert("insertMemberOrganization", organization);
	}
	@Override
	public void insertSupplierOrganization(SupplierOrganization organization) {
		getSqlMapClientTemplate().insert("insertSupplierOrganization", organization);
	}
	@Override
	public void updateMemberOrganization(MemberOrganization organization) {
		getSqlMapClientTemplate().update("updateMemberOrganization", organization);
	}
	@Override
	public void updateSupplierOrganization(SupplierOrganization organization) {
		getSqlMapClientTemplate().update("updateSupplierOrganization", organization);
	}
}
