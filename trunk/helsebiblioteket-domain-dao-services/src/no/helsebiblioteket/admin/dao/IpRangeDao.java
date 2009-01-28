package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;

public interface IpRangeDao {
	// Edit
	public void insertIpRange(IpAddressSet ipRange);
	public void updateIpRange(IpAddressSet ipRange);
	public void deleteIpRange(IpAddressSet ipRange);
	
	// Fetch
	public List<IpAddressSet> getIpRangeListByOrganization(MemberOrganization organization);
	public List<MemberOrganization> getOrganizationListByIpAdress(IpAddress ipAddress);
}
