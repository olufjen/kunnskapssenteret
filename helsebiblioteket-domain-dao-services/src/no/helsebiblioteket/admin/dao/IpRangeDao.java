package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;

public interface IpRangeDao {
	// Edit
	public void insertIpRange(IpAddressLine ipRange);
	public void updateIpRange(IpAddressLine ipRange);
	public void deleteIpRange(IpAddressLine ipRange);
	
	// Fetch
	public List<IpAddressLine> getIpRangeListByOrganization(MemberOrganization organization);
	public List<MemberOrganization> getOrganizationListByIpAdress(IpAddress ipAddress);
}
