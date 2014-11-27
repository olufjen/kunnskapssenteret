package no.naks.nhn.service;

import java.util.List;

import javax.xml.bind.JAXBElement;


import no.naks.services.nhn.client.ArrayOfDepartment;
import no.naks.services.nhn.client.AxisClient;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationParty;
import no.naks.services.nhn.client.Organization;

public interface NHNServiceClient {

	public List collectNHNParties();
	public boolean isNhnFlag();
	public void setNhnFlag(boolean nhnFlag);
	public String getAuthenticatorUser();

	public void setAuthenticatorUser(String authenticatorUser);

	public String getAuthenticatorPassword();

	public void setAuthenticatorPassword(String authenticatorPassword);
	
	public boolean isProduction();

	public void setProduction(boolean production);
	public JAXBElement<String> getOrganisationName();

	public JAXBElement<ArrayOfDepartment> getValgteAvdelinger();

	public void setValgteAvdelinger(JAXBElement<ArrayOfDepartment> valgteAvdelinger);


	public void setOrganisationName(JAXBElement<String> organisationName);
	public List<CommunicationParty> getAllOrganisations();
	public void initializeTables();

	public void setAllOrganisations(List<CommunicationParty> allOrganisations);
	

}
