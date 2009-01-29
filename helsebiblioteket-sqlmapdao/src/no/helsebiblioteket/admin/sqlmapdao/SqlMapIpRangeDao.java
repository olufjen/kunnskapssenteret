package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;

public class SqlMapIpRangeDao extends SqlMapClientDaoSupport implements IpRangeDao {
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
		return (List<IpAddressLine>)getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organization.getId());
	}
}
