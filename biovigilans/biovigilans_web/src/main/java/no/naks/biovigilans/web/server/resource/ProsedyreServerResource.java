package no.naks.biovigilans.web.server.resource;

import no.naks.biovigilans.web.control.GiverWebService;
import no.naks.biovigilans.web.control.ICD10WebService;
import no.naks.biovigilans.web.control.HendelseWebService;
import no.naks.biovigilans.web.control.SessionAdmin;
import no.naks.biovigilans.web.control.TableWebService;

import org.restlet.resource.ServerResource;

public class ProsedyreServerResource extends ServerResource {

	protected SessionAdmin sessionAdmin = null;
	protected TableWebService tablewebservice;
	protected HendelseWebService hendelseWebService;
	protected ICD10WebService icd10WebService;
	protected GiverWebService giverWebService; 
	
	protected String[]sessionParams;
	
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
	
	

}
