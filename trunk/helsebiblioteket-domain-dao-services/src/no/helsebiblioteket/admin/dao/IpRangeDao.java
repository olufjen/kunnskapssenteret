package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;

public interface IpRangeDao {
	// Edit
	public void insertIpRange(IpAddressLine addressLine);
	public void updateIpRange(IpAddressLine addressLine);
	public void deleteIpRange(IpAddressLine addressLine);
	
	// Fetch
	public List<IpAddressLine> getIpRangeListByOrganization(MemberOrganization organization);
}
