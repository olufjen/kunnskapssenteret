package no.naks.web.nhn.control;

import javax.xml.bind.JAXBElement;

import no.naks.framework.web.control.MasterWebService;
import no.naks.nhn.service.MelderService;
import no.naks.nhn.service.NHNServiceClient;

/**
 * Denne tjenesten henter inn alle nødvendige lister fra Norsk Helsenett
 * Det skjer enten ved bruk av webtjenester eller ved oppslag mot database
 * Dette bestemmes av TableService
 * @author olj
 *
 */

public class TableWebServiceImpl extends NHNMasterWebServiceImpl implements
		TableWebService,NHNMasterWebService,MasterWebService {
	protected MelderService meldingService;

	
	public TableWebServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NHNServiceClient getNhnClient() {
		return nhnClient;
	}

	public void setNhnClient(NHNServiceClient nhnClient) {
		this.nhnClient = nhnClient;
	
		nhnClient.setNhnFlag(nhnFlag);
		nhnClient.initializeTables();
		organisationName = nhnClient.getOrganisationName();
	}
	


	public MelderService getMeldingService() {
		return meldingService;
	}

	public JAXBElement<String> getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(JAXBElement<String> organisationName) {
		this.organisationName = organisationName;
	}

	public void setMeldingService(MelderService meldingService) {
		this.meldingService = meldingService;
	}

	public boolean isNhnFlag() {
		return nhnFlag;
	}

	public void setNhnFlag(boolean nhnFlag) {
		this.nhnFlag = nhnFlag;
	}
	

	




}
