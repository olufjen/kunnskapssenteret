package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;

public interface OrganizationListDao {
	public List<Organization> getOrganizationListAll();
}
