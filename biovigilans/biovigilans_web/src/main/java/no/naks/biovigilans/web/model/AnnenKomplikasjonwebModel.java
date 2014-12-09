package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.model.AnnenkomplikasjonImpl;

public class AnnenKomplikasjonwebModel extends VigilansModel {
	
	private Annenkomplikasjon annenKomplikasjon;
	protected String[] alvorligHendelse; 
	protected String[] hovedprosesslist;
	protected String[] feilelleravvik;
	protected String[] hendelsenoppdaget;
	
	public AnnenKomplikasjonwebModel(){
		super();
		annenKomplikasjon = new AnnenkomplikasjonImpl();
	}
	
	
	
	
	public String[] getHendelsenoppdaget() {
		return hendelsenoppdaget;
	}

	public void setHendelsenoppdaget(String[] hendelsenoppdaget) {
		this.hendelsenoppdaget = hendelsenoppdaget;
	}

	public String[] getFeilelleravvik() {
		return feilelleravvik;
	}
	public void setFeilelleravvik(String[] feilelleravvik) {
		this.feilelleravvik = feilelleravvik;
	}
	public String[] getHovedprosesslist() {
		return hovedprosesslist;
	}
	public void setHovedprosesslist(String[] hovedprosesslist) {
		this.hovedprosesslist = hovedprosesslist;
	}
	public String[] getAlvorligHendelse() {
		return alvorligHendelse;
	}

	public void setAlvorligHendelse(String[] alvorligHendelse) {
		this.alvorligHendelse = alvorligHendelse;
	}
	
	public Annenkomplikasjon getAnnenKomplikasjon() {
		return annenKomplikasjon;
	}

	public void setAnnenKomplikasjon(Annenkomplikasjon annenKomplikasjon) {
		this.annenKomplikasjon = annenKomplikasjon;
	}
	
	public void distributeTerms(){
		String[] formFields = getFormNames();
		String annenFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5],formFields[6],formFields[7],formFields[8],formFields[9],formFields[10],formFields[11],formFields[12],formFields[13],formFields[14],formFields[15]};
		annenKomplikasjon.setAnnenkomplicationfieldMaps(annenFields); 
	}

	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			annenKomplikasjon.saveField(field, userEntry);
			
		}
		
		annenKomplikasjon.saveToAnnenKomplikasjon();
		
	}
	
}
