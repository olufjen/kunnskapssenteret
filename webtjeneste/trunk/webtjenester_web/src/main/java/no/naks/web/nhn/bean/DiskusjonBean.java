package no.naks.web.nhn.bean;

import no.naks.emok.model.IBasismelding;
import no.naks.web.nhn.control.WebPagesService;

public class DiskusjonBean extends MasterBean{

	protected WebPagesService webPagesService;
	
	public WebPagesService getWebPagesService() {
		return webPagesService;
	}
	public void setWebPagesService(WebPagesService webPagesService) {
		this.webPagesService = webPagesService;
		tableWebService = webPagesService.getTableWebService();

	}

	public IBasismelding getMelding() {
		return webPagesService.getMelding();
	}

	public void setMelding(IBasismelding melding) {
		webPagesService.setMelding(melding); 
	}
	
	public String getLareavHendelse() {
		return webPagesService.getLareavHendelse();
	}
	public void setLareavHendelse(String lareavHendelse) {
		webPagesService.setLareavHendelse(lareavHendelse);
	}
	public String hendelsePage(){
		return "hendelse";
	}
	
	public String innledningPage(){
		return "innledning";
	}
	
	public String hvorHvemPage(){
		return "hvorHvem";
	}
	public String kontaktinfoPage(){
		return "kontaktinfo";
	}
}
