package no.helsebiblioteket.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import no.helsebiblioteket.admin.domain.OrganizationType;

public class MockJdbcOrganizationTypeDao extends SimpleJdbcDaoSupport implements OrganizationTypeDao {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	public List<OrganizationType> getOrganizationTypeList() {
		logger.info("Mock: fetching all organization types");
        
        List<OrganizationType> orgTypes = new ArrayList<OrganizationType>();
        OrganizationType orgType = null;
        orgType = new OrganizationType();
        orgType.setId(1);
        orgType.setKey("health_institution");
        orgType.setName("fallbackname for org_type_health_institution");
        orgTypes.add(orgType);
        orgType = new OrganizationType();
        orgType.setId(2);
        orgType.setKey("health_education_institution");
        orgType.setName("fallbackname for org_type_health_education_institution");
        orgTypes.add(orgType);
        return orgTypes;
	}
	
	public OrganizationType getOrganizationType(String organizationTypeKey) {
		return null;
	}
}
