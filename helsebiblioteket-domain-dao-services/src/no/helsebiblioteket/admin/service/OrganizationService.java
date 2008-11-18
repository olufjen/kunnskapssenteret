package no.helsebiblioteket.admin.service;

/**
 * Purpose of this class is to retrieve and store static values used for 
 * drop down lists, checkbox groups etc.
 */

import java.io.Serializable;
import java.util.List;

import no.helsebiblioteket.admin.domain.Contract;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;

public interface OrganizationService extends Serializable {
	public List<Organization> getOrganizationList();
	public Organization getOrganization(Integer organizationId);
	public List<Contract> getContractList(OrganizationType organizationType);
}
