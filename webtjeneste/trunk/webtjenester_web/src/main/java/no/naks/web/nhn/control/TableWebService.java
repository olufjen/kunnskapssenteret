
package no.naks.web.nhn.control;

import javax.xml.bind.JAXBElement;

import no.naks.nhn.service.MelderService;
import no.naks.nhn.service.NHNServiceClient;
import no.naks.services.nhn.client.Organization;

/**
 * Dette grensesnitt implementeres av TableWebServiceImpl som
 * gjør tjenester for TableMaintenanceBean
 * @author ojn
 * TODO: remove?
 */
public interface TableWebService extends NHNMasterWebService {

	public NHNServiceClient getNhnClient();

	public void setNhnClient(NHNServiceClient nhnClient);

	public void collectMasterTables();
	public String getforetakKey();
	public MelderService getMeldingService();

	public JAXBElement<String> getOrganisationName();

	public void setOrganisationName(JAXBElement<String> organisationName);
	

	/**
	 * @param foretakKey the foretakKey to set
	 */
	public void setforetakKey(String foretakKey);
	public boolean isNhnFlag();

	public void setNhnFlag(boolean nhnFlag);
}
