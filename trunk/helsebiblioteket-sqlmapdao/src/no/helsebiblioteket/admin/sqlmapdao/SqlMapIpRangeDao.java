package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;

public class SqlMapIpRangeDao extends SqlMapClientDaoSupport implements IpRangeDao {
	// TODO: Go through all!
	public void insertIpRange(IpAddressLine ipRange){
		
		getSqlMapClientTemplate().insert("insertIpRange", ipRange);

	}
	public void updateIpRange(IpAddressLine ipRange){
		
		getSqlMapClientTemplate().update("updateIpRange", ipRange);

	}
	public void deleteIpRange(IpAddressLine ipRange){

		getSqlMapClientTemplate().delete("deleteIpRange", ipRange);

	}
	public List<IpAddressLine> getIpRangeListByOrganization(MemberOrganization organization){
		return (List<IpAddressLine>)getSqlMapClientTemplate().queryForList("", organization.getId());
//		return (List<IpAddressSet>) getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organizationId);
	}
	public List<MemberOrganization> getOrganizationListByIpAdress(IpAddress ipAddress) {
		return (List<MemberOrganization>)getSqlMapClientTemplate().queryForList("", ipAddress);
	}
}
