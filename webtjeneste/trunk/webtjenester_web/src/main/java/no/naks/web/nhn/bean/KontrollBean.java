package no.naks.web.nhn.bean;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.myfaces.webapp.filter.ExtensionsResponseWrapper;

import no.naks.web.nhn.control.WebPagesService;

public class KontrollBean extends MasterBean {
	
	protected boolean hvorHvemErro=false;
	protected boolean hendelse = false;
	protected boolean kontaktinfo=false;
	protected WebPagesService webPagesService;

	public boolean ishvorHvemErro() {
		boolean  aarHidden=true,klokkeHidden=true,datoHidden=true;
		boolean spesialistHidden=true, sykehusHidden=false;
		Object objAar = getSessionObject("aarHidden");
		if(objAar != null){
			aarHidden =(Boolean) objAar;
		}
		
		Object objKlokke = getSessionObject("klokkeHidden");
		if(objKlokke != null){
			klokkeHidden = (Boolean)objKlokke;
		}
		
		Object objDato = getSessionObject("datoHidden");
		if(objDato != null){
			datoHidden = (Boolean)objDato;
		}
		
		Object objSpesialist = getSessionObject("spesialistHidden");
		if(objSpesialist != null){
			spesialistHidden = (Boolean)objSpesialist;
		}
		
		Object objSykehusHidden = getSessionObject("sykehusHidden");
		if(objSykehusHidden != null){
			sykehusHidden = (Boolean)objSykehusHidden;
		}
		
		
		if(aarHidden && klokkeHidden && datoHidden
				&& spesialistHidden && sykehusHidden){
			hvorHvemErro=  false;
		}else{
			hvorHvemErro= true;
		}
		return hvorHvemErro;
	}
	

	public WebPagesService getWebPagesService() {
		return webPagesService;
	}


	public void setWebPagesService(WebPagesService webPagesService) {
		this.webPagesService = webPagesService;
		tableWebService = webPagesService.getTableWebService();
	}


	public void setHvorHvemErro(boolean hvorHvemErro) {
		this.hvorHvemErro = hvorHvemErro;
	}

	public boolean isHendelse() {
		
		boolean hendelseHidden =false;
		Object objHendelse = getSessionObject("hendelseHidden");
		if(objHendelse!=null){
			hendelseHidden = (Boolean)objHendelse;
		}
		if( hendelseHidden){
			hendelse = false;
		}else{
			hendelse = true;
		}
		
		return hendelse;
	}

	public void setHendelse(boolean hendelse) {
		this.hendelse = hendelse;
	}

	public boolean isKontaktinfo() {
		boolean blEmailHidden=false, blGjentaHidden=false;
		boolean blPhone = true, blLederPhone=true;
		
		Object objEmail = getSessionObject("blEmailHidden");
		if(objEmail != null){
			blEmailHidden = (Boolean)objEmail;
		}
		
		Object objGjenta = getSessionObject("blGjentaHidden") ;
		if(objGjenta != null){
			blGjentaHidden = (Boolean)objGjenta;
		}
		
		Object objPhone = getSessionObject("blPhone") ;
		if(objPhone != null){
			blPhone = (Boolean)objPhone;
		}
		Object objLederPhone = getSessionObject("blLederPhone") ;
		if(objLederPhone != null){
			blLederPhone = (Boolean)objLederPhone;
		}
		
		
		if(blEmailHidden && blGjentaHidden
		 	&& blPhone && blLederPhone){
			kontaktinfo = false;
		}else{
			kontaktinfo = true;
		}
		return kontaktinfo;
	}

	public void setKontaktinfo(boolean kontaktinfo) {
		this.kontaktinfo = kontaktinfo;
	}

	public String send(){
		tableWebService.getMeldingService().sendMelding(webPagesService.getMelding(),webPagesService.getPerson(),webPagesService.getLeder());
		System.out.println("getKvittering Session "+ webPagesService.getSessionId()+" "+webPagesService.getClientIP()+ " har sent en melding");
	
		return "";
	}
	
	public String getKvittering(){
		tableWebService.getMeldingService().sendMelding(webPagesService.getMelding(),webPagesService.getPerson(),webPagesService.getLeder());
		System.out.println("getKvittering Session "+ webPagesService.getSessionId()+" "+webPagesService.getClientIP()+ " har sent en melding");
		WebPagesService webService ;
		webService= webPagesService;
		setSessionObject("webService", webService);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("webPagesService");
		int timeoutSeconds = 600; // 10 min. sesjon !!
		session.setMaxInactiveInterval(timeoutSeconds);

		
		return "kvittering";
	}
	
/*
	public void sessionExpired(ValueChangeEvent val){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	}
	
*/	
	public String sessionKiller(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ExtensionsResponseWrapper response = (ExtensionsResponseWrapper) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setHeader("Cache-control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires", -1); 
		session.invalidate();
		return "innledning";
	}
	
	public boolean ishasError() {
		boolean  blHvorHvem, blHendelse, blKontaktinfo;
		blHvorHvem = ishvorHvemErro();
		blHendelse = isHendelse();
		blKontaktinfo = isKontaktinfo();
		
		if(!blHendelse && !blHvorHvem && !blKontaktinfo){
			hasError = false;
		}else{
			hasError = true;
		}
		
		return hasError;
	}
	
}