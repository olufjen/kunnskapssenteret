package no.helsebiblioteket.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.Contract;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;

public class MockJdbcOrganizationDao extends SimpleJdbcDaoSupport implements OrganizationDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	public List<Organization> getOrganizationList() {
		logger.debug("Mock: fetching all organization types");

        List<Organization> orgs = new ArrayList<Organization>();
        
        IpRange ipRange = new IpRange();
        ipRange.setIpAddressFrom("1.2.3.4");
        ipRange.setIpAddressTo("1.2.3.10");
        List<IpRange> ipRangeList = new ArrayList<IpRange>();
        ipRangeList.add(ipRange);
        ipRange = new IpRange();
        ipRange.setIpAddressFrom("1.2.3.41");
        ipRange.setIpAddressTo("1.2.3.88");
        ipRangeList.add(ipRange);
        
        Organization org = null;
        org = new Organization();
        org.setId(1);
        org.setParentOrganization(new Organization());
        org.setIpRangeList(ipRangeList);
        org.setName("Organisajonsnavn 1");
        org.setNameShort("org 1");
        
        OrganizationType orgType = new OrganizationType();
        orgType.setDescription("Helseforetak");
        orgType.setId(1);
        orgType.setKey("orgtypekey");
        orgType.setName("orgTypeNavn");
        org.setType(orgType);
        
        orgs.add(org);
        
        
        ipRange.setIpAddressFrom("4.3.2.1");
        ipRange.setIpAddressTo("4.3.2.123");
        ipRangeList = new ArrayList<IpRange>();
        ipRangeList.add(ipRange);
        ipRange = new IpRange();
        ipRange.setIpAddressFrom("4.3.2.111");
        ipRange.setIpAddressTo("4.3.2.222");
        ipRangeList.add(ipRange);
        
        org = new Organization();
        org.setId(1);
        org.setParentOrganization(new Organization());
        org.setIpRangeList(ipRangeList);
        org.setName("Organisajonsnavn 2");
        org.setNameShort("org 2");
        
        orgType = new OrganizationType();
        orgType.setDescription("Utdanningsinstitusjon");
        orgType.setId(1);
        orgType.setKey("orgtypekey2");
        orgType.setName("orgTypeNavn2");
        org.setType(orgType);
        
        orgs.add(org);
        
        return orgs;
	}

	public Organization getOrganization(Integer organizationId) {
		return null;
	}
	
	public List<Contract> getContractList(OrganizationType organizationType) {
		return null;
	}
}