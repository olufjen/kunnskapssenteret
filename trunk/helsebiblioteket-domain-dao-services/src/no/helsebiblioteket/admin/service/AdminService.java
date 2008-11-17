package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.OrganizationType;

public interface AdminService extends Serializable {
    public List<OrganizationType> getOrganizationTypeList();
}
