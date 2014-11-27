package no.naks.web.nhn.bean;

import javax.faces.event.ValueChangeEvent;

import no.naks.emok.model.IBasismelding;
import no.naks.web.nhn.control.WebPagesService;

public class HendelseBean extends MasterBean{
	
	protected WebPagesService webPagesService;
	private boolean blHendelseHidden;
	private String orgHendelse;
	
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

	public String getOrgHendelse() {
		return webPagesService.getOrgHendelse();
	}
	public void setOrgHendelse(String orgHendelse) {
		this.orgHendelse = orgHendelse;
		webPagesService.setOrgHendelse(orgHendelse);
	}
	public void setMelding(IBasismelding melding) {
		webPagesService.setMelding(melding); 
	}
	public String hvorHvemPage(){
		return "hvorHvem";
	}
	
	public String innledningPage(){
		return "innledning";
	}

	public String diskusjonPage(){
	//	tableWebService.getMeldingService().sendMelding(webPagesService.getMelding(),webPagesService.getPerson());
		return "diskusjon";
	}
	public String kontaktinfoPage(){
		return "kontaktinfo";
	}
	
	public boolean isHendelseHidden() {
		blHendelseHidden = false;
		Object obj = getSessionObject("hendelseHidden");
		if(obj!=null){
			blHendelseHidden = (Boolean)obj;
		}
		return blHendelseHidden;
	}
	public void setHendelseHidden(boolean hendelseHidden) {
		setSessionObject("hendelseHidden", hendelseHidden);
	}
	
	public void hendelseHiddenValue(ValueChangeEvent val){
		String strHendelse =(String) val.getNewValue();
		blHendelseHidden = false;
		if(strHendelse != null && strHendelse !=""){
			blHendelseHidden=Boolean.parseBoolean(strHendelse);
		}
		setHendelseHidden(blHendelseHidden) ;
	}
}
