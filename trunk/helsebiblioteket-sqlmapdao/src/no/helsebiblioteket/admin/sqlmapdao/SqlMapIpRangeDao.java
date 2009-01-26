package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.domain.IpRange;

public class SqlMapIpRangeDao extends SqlMapClientDaoSupport implements IpRangeDao {
	
	public IpRange getIpRangeById(Integer ipRangeId) {
		return (IpRange) getSqlMapClientTemplate().queryForObject("getProfileById", ipRangeId);
	}
	
	public void deleteIpRange(IpRange ipRange) {
		getSqlMapClientTemplate().delete("deleteIpRange", ipRange);
	}
	
	public void insertIpRange(IpRange ipRange) {
		getSqlMapClientTemplate().insert("insertIpRange", ipRange);
	}
	
	public void updateIpRange(IpRange ipRange) {
		getSqlMapClientTemplate().update("updateIpRange", ipRange);
	}
	
	public void saveIpRange(IpRange ipRange) {
		if (ipRange.getId() == null) {
			insertIpRange(ipRange);
		} else {
			updateIpRange(ipRange);
		}
	}
	
	public void saveIpRange(IpRange changedIpRange, IpRange originalIpRange) {
		if (changedIpRange.getId() == null) {
			insertIpRange(changedIpRange);
		} else {
			updateIpRange(changedIpRange);
		}	
	}
	
	public List<IpRange> getIpRangeListByOrganizationId(Integer organizationId) {
		return (List<IpRange>) getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organizationId);
	}
}