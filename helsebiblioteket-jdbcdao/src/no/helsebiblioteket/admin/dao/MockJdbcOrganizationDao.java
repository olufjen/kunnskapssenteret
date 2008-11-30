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
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;

public class MockJdbcOrganizationDao extends SimpleJdbcDaoSupport implements OrganizationDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	public List<Organization> getAllOrganizations() {
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

	public List<SupplierOrganization> getSupplierList() {
		List<SupplierOrganization> list = new ArrayList<SupplierOrganization>();
		SupplierOrganization org = new SupplierOrganization();
		SupplierSource source = new SupplierSource();
		ResourceType type = new ResourceType();
		OrganizationType orgType = new OrganizationType();
		List<SupplierSource> sourceList = new ArrayList<SupplierSource>();
		
		type.setDescription("Supplier source (subsource)");
		type.setId(1);
		type.setKey("supplierSoruce");
		type.setName("supplierSoruceName");
		
		source.setId(1);
		source.setName("kilde 1");
		source.setResourceType(type);
		source.setUrl("http://www.proquest.com?resource=123)");
		sourceList.add(source);
		source = new SupplierSource();
		source.setId(2);
		source.setName("kilde 2");
		source.setResourceType(type);
		source.setUrl("http://www.proquest.com?resource=456)");
		sourceList.add(source);
		
		orgType.setDescription("Supplier");
		orgType.setId(1);
		orgType.setKey("key");
		orgType.setName("name");
		
		org.setDescription("ProQuest - journal portal");
		org.setId(1);
		org.setName("ProQuest");
		org.setNameShort("PQ");		
		org.setParentOrganization(new Organization());
		org.setSourceList(sourceList);
		org.setType(orgType);
		
		list.add(org);
		
		sourceList = new ArrayList<SupplierSource>();
		source = new SupplierSource();
		source.setId(3);
		source.setName("kilde asdf");
		source.setResourceType(type);
		source.setUrl("http://www.ovid.com?resource=5342)");
		sourceList.add(source);
		source = new SupplierSource();
		source.setId(4);
		source.setName("kilde fdsa");
		source.setResourceType(type);
		source.setUrl("http://www.ovid.com?resource=asds8q2)");
		sourceList.add(source);
		
		org = new SupplierOrganization();
		org.setDescription("Ovid - journal portal");
		org.setId(2);
		org.setName("Ovid");
		org.setNameShort("O");		
		org.setParentOrganization(null);
		org.setSourceList(sourceList);
		org.setType(orgType);
		
		list.add(org);
		
		return list;
	}
}