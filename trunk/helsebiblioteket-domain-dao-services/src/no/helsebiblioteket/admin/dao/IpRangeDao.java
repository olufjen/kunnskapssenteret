package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;

public interface IpRangeDao {
	// Edit
	public void insertIpRange(IpRange ipRange);
	public void updateIpRange(IpRange ipRange);
	public void deleteIpRange(IpRange ipRange);
	
	// Fetch
	public List<IpRange> getIpRangeListByOrganization(Organization organization);
}
