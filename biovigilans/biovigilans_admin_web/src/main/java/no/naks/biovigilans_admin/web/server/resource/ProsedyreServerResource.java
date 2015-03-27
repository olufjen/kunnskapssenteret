package no.naks.biovigilans_admin.web.server.resource;

//import no.naks.biovigilans_admin.web.control.AnnenKomplikasjonWebService;
//import no.naks.biovigilans_admin.web.control.DonasjonWebService;
//import no.naks.biovigilans.web.control.GiverWebService;
import no.naks.biovigilans_admin.web.control.ICD10WebService;
import no.naks.biovigilans_admin.web.control.HendelseWebService;
import no.naks.biovigilans_admin.web.control.SaksbehandlingWebService;
import no.naks.biovigilans_admin.web.control.SessionAdmin;
//import no.naks.biovigilans.web.control.TableWebService;


import org.restlet.resource.ServerResource;

public class ProsedyreServerResource extends ServerResource {

	protected SessionAdmin sessionAdmin = null;
//	protected TableWebService tablewebservice;
	protected HendelseWebService hendelseWebService;
	protected ICD10WebService icd10WebService;
//	protected GiverWebService giverWebService; 
//	protected DonasjonWebService donasjonWebService;
//	protected MelderWebService melderWebService;
//	protected KomDiagnosegiverWebService komDiagnosegiverWebService;
//	protected AnnenKomplikasjonWebService annenKomplikasjonWebService;
//	protected KomplikasjonsklassifikasjonWebService komplikasjonsklassifikasjonWebService;
	
	protected SaksbehandlingWebService saksbehandlingWebservice;
	
	protected String[]sessionParams;
	
	
	

	public HendelseWebService getHendelseWebService() {
		return hendelseWebService;
	}
	public void setHendelseWebService(HendelseWebService hendelseWebService) {
		this.hendelseWebService = hendelseWebService;
	}
	public SaksbehandlingWebService getSaksbehandlingWebservice() {
		return saksbehandlingWebservice;
	}
	public void setSaksbehandlingWebservice(
			SaksbehandlingWebService saksbehandlingWebservice) {
		this.saksbehandlingWebservice = saksbehandlingWebservice;
	}
	public SessionAdmin getSessionAdmin() {
		return sessionAdmin;
	}
	public void setSessionAdmin(SessionAdmin sessionAdmin) {
		this.sessionAdmin = sessionAdmin;
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

	
	

}
