package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapIpRangeDao extends IbatisSqlMapClientDaoSupport implements IpRangeDao {
	public void insertIpRange(IpAddressLine ipRange){
		getSqlMapClientTemplate().insert("insertIpRange", ipRange);
		IpAddressLine tmp = (IpAddressLine) getSqlMapClientTemplate().queryForObject("getIpRangeById", ipRange.getId());
		ipRange.setLastChanged(tmp.getLastChanged());
	}
	public void updateIpRange(IpAddressLine ipRange){
		getSqlMapClientTemplate().update("updateIpRange", ipRange);
		IpAddressLine tmp = (IpAddressLine) getSqlMapClientTemplate().queryForObject("getIpRangeById", ipRange.getId());
		ipRange.setLastChanged(tmp.getLastChanged());
	}
	public void deleteIpRange(IpAddressLine ipRange){
		getSqlMapClientTemplate().delete("deleteIpRange", ipRange);
	}
	@SuppressWarnings("unchecked")
	public List<IpAddressLine> getIpRangeListByOrganization(MemberOrganization organization){
		return (List<IpAddressLine>)getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organization.getOrganization().getId());
	}
}
