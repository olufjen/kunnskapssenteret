package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.IpRange;

public interface IpRangeDao {
	public IpRange getIpRangeById(Integer ipRangeId);
	
	public void deleteIpRange(IpRange ipRange);
	
	public void insertIpRange(IpRange ipRange);
	
	public void updateIpRange(IpRange ipRange);
	
	public void saveIpRange(IpRange changedIpRange, IpRange originalIpRange);
	
	public void saveIpRange(IpRange ipRange);
	
	public List<IpRange> getIpRangeListByOrganizationId(Integer organizationId);
}
