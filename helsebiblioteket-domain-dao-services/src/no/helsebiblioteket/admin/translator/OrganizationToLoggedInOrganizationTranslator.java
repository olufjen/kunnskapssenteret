package no.helsebiblioteket.admin.translator;

import no.helsebiblioteket.admin.domain.LoggedInOrganization;
import no.helsebiblioteket.admin.domain.Organization;

public class OrganizationToLoggedInOrganizationTranslator {
	public LoggedInOrganization translate(Organization organization){
		LoggedInOrganization loggedInOrganization = new LoggedInOrganization();
		loggedInOrganization.setId(organization.getId());
		loggedInOrganization.setNameEnglishNormal(organization.getNameEnglish());
		loggedInOrganization.setNameEnglishShort(organization.getNameShortEnglish());
		loggedInOrganization.setNameNorwegianNormal(organization.getNameNorwegian());
		loggedInOrganization.setNameNorwegianShort(organization.getNameShortNorwegian());
		loggedInOrganization.setTypeKey(organization.getType().getKey().getValue());
		return loggedInOrganization;
	}
}
