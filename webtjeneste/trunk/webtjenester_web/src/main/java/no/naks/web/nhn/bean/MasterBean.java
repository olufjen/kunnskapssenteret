package no.naks.web.nhn.bean;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MasterBean extends AbstractBean {
	
	protected  Log log = LogFactory.getLog(this.getClass().getName());
	protected boolean hasError=true;
	
	public MasterBean() {
		super();
	}
	

	protected String getFacesParamValue(String name) {
	    return (String) FacesContext
	                        .getCurrentInstance()
	                            .getExternalContext()
	                                .getRequestParameterMap()
	                                    .get(name);
	
	}	

	
	public String getVelkommen(){
	//	actualInformant.setInformantId(null);
	//	actualInformant.setInformantName(null) ;
	//	setSelectedTab("skjemaredigering") ;
		return "";
	}


	public boolean isKontroll_btn() {
		boolean kontroll_btn ;
		Object obj = getSessionObject("kontroll_btn") ;
		if(obj!=null )
			kontroll_btn = (Boolean) obj;
		else
			kontroll_btn = false;
		return kontroll_btn;
	}


	
	public void setKontroll_btn(boolean kontroll_btn) {
		setSessionObject("kontroll_btn", kontroll_btn) ;
	}

	/*public boolean ishasError() {
		boolean  aarHidden=true,klokkeHidden=true,datoHidden=true;
		boolean hendelseHidden =false, blEmailHidden=false, blGjentaHidden=false;
		
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
		
		Object objHendelse = getSessionObject("hendelseHidden");
		if(objHendelse!=null){
			hendelseHidden = (Boolean)objHendelse;
		}
		
		Object objEmail = getSessionObject("blEmailHidden");
		if(objEmail != null){
			blEmailHidden = (Boolean)objEmail;
		}
		
		Object objGjenta = getSessionObject("blGjentaHidden") ;
		if(objGjenta != null){
			blGjentaHidden = (Boolean)objGjenta;
		}
		
		if(aarHidden && klokkeHidden && datoHidden 
			&& hendelseHidden && blEmailHidden && blGjentaHidden){
			hasError = false;
		}else{
			hasError = true;
		}
		
		return hasError;
	}

	public void sethasError(boolean hasError) {
		setSessionObject("hasError", hasError) ;
	}*/
	  

}
