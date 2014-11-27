package no.naks.web.nhn.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;
import no.naks.web.nhn.control.TableWebService;
import no.naks.web.nhn.control.WebPagesService;

public class KontaktinfoBean extends MasterBean{

	protected WebPagesService webPagesService;
	protected boolean kontrollHidden;
	private boolean blEmailHidden;
	private boolean blGjentaHidden;
	private boolean blPhone;
	private boolean blLederPhone;
	protected String Onskerhjelp;
	private String gjentaEpost;
	private String lederGjentaEpost;
	
	
	
	public String getGjentaEpost() {
		gjentaEpost =(String) getSessionObject("gjentaEpost");
		if(gjentaEpost == null && gjentaEpost ==""){
			gjentaEpost="";
		}
		return gjentaEpost;
	}
	public void setGjentaEpost(String gjentaEpost) {
		setSessionObject("gjentaEpost", gjentaEpost);
	}
	public String getLederGjentaEpost() {
		lederGjentaEpost =(String) getSessionObject("lederGjentaEpost");
		if(lederGjentaEpost == null && lederGjentaEpost ==""){
			lederGjentaEpost="";
		}
		return lederGjentaEpost;
	}
	public void setLederGjentaEpost(String lederGjentaEpost) {
		setSessionObject("lederGjentaEpost", lederGjentaEpost) ;
	}
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
	
	public Person getPerson(){
		return webPagesService.getPerson();
	}
	public void setPerson(Person person){
		webPagesService.setPerson(person) ;
	}
	
	public Person getLeder(){
		return webPagesService.getLeder();
	}
	public void setLeder(Person leder){
		webPagesService.setLeder(leder) ;
		
	}
	public String diskusjonPage(){
		return "diskusjon";
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
	public String controllPage(){
			setKontroll_btn(true) ;
			setKontrollHidden(true);
	//		tableWebService.getMeldingService().sendMelding(webPagesService.getMelding(),webPagesService.getPerson());
			return "kontroll";
	}
	public boolean isKontrollHidden() {
		Object obj = getSessionObject("kontrollHidden") ;
		if(obj != null){
			kontrollHidden =(Boolean) obj;
		}else{
			kontrollHidden=false;
		}
		return kontrollHidden;
	}
	public void setKontrollHidden(boolean kontrollHidden) {
		setSessionObject("kontrollHidden", kontrollHidden);
	}
	
	public boolean isBlEmailHidden() {
		Object obj = getSessionObject("blEmailHidden");
		blEmailHidden = false;
		if(obj!=null){
			blEmailHidden = (Boolean) obj;
		}
		
		return blEmailHidden;
	}
	public void setBlEmailHidden(boolean blEmailHidden) {
		setSessionObject("blEmailHidden", blEmailHidden);
	}
	
	public void emailHiddenValue(ValueChangeEvent val){
		blEmailHidden = false;
		String strEmail = (String)val.getNewValue();
		if(strEmail != null && strEmail !=""){
			blEmailHidden = Boolean.parseBoolean(strEmail);
		}
		setBlEmailHidden(blEmailHidden) ;
	}
	
	public boolean isBlGjentaHidden() {
		Object obj = getSessionObject("blGjentaHidden");
		blGjentaHidden = false;
		if(obj!=null){
			blGjentaHidden = (Boolean)obj;
		}
		
		return blGjentaHidden;
	}
	public void setBlGjentaHidden(boolean blGjentaHidden) {
		setSessionObject("blGjentaHidden", blGjentaHidden);
	}
	
	public void gjentaHiddenValue(ValueChangeEvent val){
		blGjentaHidden=false;
		String strGjentaHidden = (String)val.getNewValue();
		if(strGjentaHidden != null && strGjentaHidden !=""){
			blGjentaHidden = Boolean.parseBoolean(strGjentaHidden) ;
		}
		setBlGjentaHidden(blGjentaHidden) ;
	}
	
	public boolean isBlPhone() {
		Object obj = getSessionObject("blPhone");
		blPhone = true;
		if(obj!=null){
			blPhone = (Boolean)obj;
		}
		return blPhone;
	}
	public void setBlPhone(boolean blPhone) {
		setSessionObject("blPhone", blPhone);
	}
	
	public void phHiddenValue(ValueChangeEvent val){
		blPhone = true;
		String strPhone = (String)val.getNewValue();
		if(strPhone != null && strPhone !=""){
			blPhone = Boolean.parseBoolean(strPhone);
		}
		setBlPhone(blPhone) ;
	}
	
	public boolean isBlLederPhone() {
		Object obj = getSessionObject("blLederPhone");
		blLederPhone = true;
		if(obj!=null){
			blLederPhone = (Boolean)obj;
		}
		
		return blLederPhone;
	}
	public void setBlLederPhone(boolean blLederPhone) {
		setSessionObject("blLederPhone", blLederPhone);
	}
	public void lederPhHiddenValue(ValueChangeEvent val){
		blLederPhone = true;
		String strLederPhone = (String)val.getNewValue();
		if(strLederPhone != null && strLederPhone !=""){
			blLederPhone = Boolean.parseBoolean(strLederPhone);
		}
		setBlLederPhone(blLederPhone) ;
	}
	
	public String getOnskerhjelp(){
		IBasismelding melding = getMelding();
		boolean blOnskerhjelp = melding.isOnskerhjelp();
		
		if(blOnskerhjelp){
			return "Ja";
		}else{
			return "Nei";
		}
		
	}
	public void setOnskerhjelp(String onskerhjelp) {
		Onskerhjelp = onskerhjelp;
	}
	
	
}
