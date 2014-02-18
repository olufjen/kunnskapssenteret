package no.naks.fagprosedyrer.web.server.resource;

import no.naks.fagprosedyrer.web.control.InnmeldingWebService;
import no.naks.fagprosedyrer.web.control.SessionAdmin;
import no.naks.fagprosedyrer.web.control.TableWebService;

import org.restlet.resource.ServerResource;

public class ProsedyreServerResource extends ServerResource {

	protected SessionAdmin sessionAdmin = null;
	protected TableWebService tablewebservice;
	protected InnmeldingWebService innmeldingWebService;
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
	public InnmeldingWebService getInnmeldingWebService() {
		return innmeldingWebService;
	}
	public void setInnmeldingWebService(InnmeldingWebService innmeldingWebService) {
		this.innmeldingWebService = innmeldingWebService;
	}
	
	

}
