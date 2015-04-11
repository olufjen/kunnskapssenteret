package no.naks.biovigilans.felles.server.resource;

import no.naks.biovigilans.felles.control.AnnenKomplikasjonWebService;
import no.naks.biovigilans.felles.control.DonasjonWebService;
import no.naks.biovigilans.felles.control.GiverWebService;
import no.naks.biovigilans.felles.control.ICD10WebService;
import no.naks.biovigilans.felles.control.HendelseWebService;
import no.naks.biovigilans.felles.control.KomDiagnosegiverWebService;
import no.naks.biovigilans.felles.control.KomplikasjonsklassifikasjonWebService;
import no.naks.biovigilans.felles.control.MelderWebService;
import no.naks.biovigilans.felles.control.SessionAdmin;
import no.naks.biovigilans.felles.control.TableWebService;

import org.restlet.resource.ServerResource;

public class ProsedyreServerResource extends ServerResource {

	protected SessionAdmin sessionAdmin = null;
	protected TableWebService tablewebservice;
	protected HendelseWebService hendelseWebService;
	protected ICD10WebService icd10WebService;
	protected GiverWebService giverWebService; 
	protected DonasjonWebService donasjonWebService;
	protected MelderWebService melderWebService;
	protected KomDiagnosegiverWebService komDiagnosegiverWebService;
	protected AnnenKomplikasjonWebService annenKomplikasjonWebService;
	protected KomplikasjonsklassifikasjonWebService komplikasjonsklassifikasjonWebService;
	
	
	protected String[]sessionParams;
	
	
	
	
	public KomplikasjonsklassifikasjonWebService getKomplikasjonsklassifikasjonWebService() {
		return komplikasjonsklassifikasjonWebService;
	}
	public void setKomplikasjonsklassifikasjonWebService(
			KomplikasjonsklassifikasjonWebService komplikasjonsklassifikasjonWebService) {
		this.komplikasjonsklassifikasjonWebService = komplikasjonsklassifikasjonWebService;
	}
	public AnnenKomplikasjonWebService getAnnenKomplikasjonWebService() {
		return annenKomplikasjonWebService;
	}
	public void setAnnenKomplikasjonWebService(
			AnnenKomplikasjonWebService annenKomplikasjonWebService) {
		this.annenKomplikasjonWebService = annenKomplikasjonWebService;
	}
	public SessionAdmin getSessionAdmin() {
		return sessionAdmin;
	}
	public void setSessionAdmin(SessionAdmin sessionAdmin) {
		this.sessionAdmin = sessionAdmin;
	}
	public TableWebService getTablewebservice() {
		return tablewebservice;
	}
	public void setTablewebservice(TableWebService tablewebservice) {
		this.tablewebservice = tablewebservice;
	}
	public String[] getSessionParams() {
		return sessionParams;
	}
	public void setSessionParams(String[] sessionParams) {
		this.sessionParams = sessionParams;
	}
	public HendelseWebService getInnmeldingWebService() {
		return hendelseWebService;
	}
	public void setInnmeldingWebService(HendelseWebService hendelseWebService) {
		this.hendelseWebService = hendelseWebService;
	}
	public ICD10WebService getIcd10WebService() {
		return icd10WebService;
	}
	public void setIcd10WebService(ICD10WebService icd10WebService) {
		this.icd10WebService = icd10WebService;
	}
	public GiverWebService getGiverWebService() {
		return giverWebService;
	}
	public void setGiverWebService(GiverWebService giverWebService) {
		this.giverWebService = giverWebService;
	}
	public DonasjonWebService getDonasjonWebService() {
		return donasjonWebService;
	}
	public void setDonasjonWebService(DonasjonWebService donasjonWebService) {
		this.donasjonWebService = donasjonWebService;
	}
	public MelderWebService getMelderWebService() {
		return melderWebService;
	}
	public void setMelderWebService(MelderWebService melderWebService) {
		this.melderWebService = melderWebService;
	}
	public KomDiagnosegiverWebService getKomDiagnosegiverWebService() {
		return komDiagnosegiverWebService;
	}
	public void setKomDiagnosegiverWebService(
			KomDiagnosegiverWebService komDiagnosegiverWebService) {
		this.komDiagnosegiverWebService = komDiagnosegiverWebService;
	}
	
	

}
