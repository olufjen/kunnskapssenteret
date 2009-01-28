package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;

public class SqlMapIpRangeDao extends SqlMapClientDaoSupport implements IpRangeDao {
	// TODO: Go through all!
	public void insertIpRange(IpAddressSet ipRange){
		
		getSqlMapClientTemplate().insert("insertIpRange", ipRange);

	}
	public void updateIpRange(IpAddressSet ipRange){
		
		getSqlMapClientTemplate().update("updateIpRange", ipRange);

	}
	public void deleteIpRange(IpAddressSet ipRange){

		getSqlMapClientTemplate().delete("deleteIpRange", ipRange);

	}
	public List<IpAddressSet> getIpRangeListByOrganization(MemberOrganization organization){
		return null;
	}
	public List<MemberOrganization> getOrganizationListByIpAdress(IpAddress ipAddress) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public IpAddressSet getIpRangeById(Integer ipRangeId) {
		return (IpAddressSet) getSqlMapClientTemplate().queryForObject("getProfileById", ipRangeId);
	}
	
	
	
	
	public void saveIpRange(IpAddressSet ipRange) {
		if (ipRange.getId() == null) {
			insertIpRange(ipRange);
		} else {
			updateIpRange(ipRange);
		}
	}
	
	public void saveIpRange(IpAddressSet changedIpRange, IpAddressSet originalIpRange) {
		if (changedIpRange.getId() == null) {
			insertIpRange(changedIpRange);
		} else {
			updateIpRange(changedIpRange);
		}	
	}
	
	public List<IpAddressSet> getIpRangeListByOrganizationId(Integer organizationId) {
		return (List<IpAddressSet>) getSqlMapClientTemplate().queryForList("getIpRangeListByOrganizationId", organizationId);
	}
}
