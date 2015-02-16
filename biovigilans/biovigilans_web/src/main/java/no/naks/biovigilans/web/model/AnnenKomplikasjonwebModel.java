package no.naks.biovigilans.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sun.xml.bind.v2.schemagen.episode.Klass;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.model.AnnenkomplikasjonImpl;
import no.naks.biovigilans.model.Komplikasjonsklassifikasjon;
import no.naks.biovigilans.model.KomplikasjonsklassifikasjonImpl;
import no.naks.biovigilans.model.Vigilansmelding;

public class AnnenKomplikasjonwebModel extends VigilansModel {
	
	private Annenkomplikasjon annenKomplikasjon;
	private Vigilansmelding vigilansmelding;
	private Komplikasjonsklassifikasjon komplikasjonsklassifikasjon;
	protected String[] alvorligHendelse; 
	protected String[] hovedprosesslist;
	protected String[] feilelleravvik;
	protected String[] hendelsenoppdaget;
	List<String> hvagikkgaltList ;
	
	
	public AnnenKomplikasjonwebModel(){
		super();
		annenKomplikasjon = new AnnenkomplikasjonImpl();
		komplikasjonsklassifikasjon =  new KomplikasjonsklassifikasjonImpl();
		vigilansmelding = (Vigilansmelding) annenKomplikasjon;
		//hvagikkgaltList = new ArrayList<String>();
	}

	public Vigilansmelding getVigilansmelding() {
		return vigilansmelding;
	}

	public void setVigilansmelding(Vigilansmelding vigilansmelding) {
		this.vigilansmelding = vigilansmelding;
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
	
	
	
	public Komplikasjonsklassifikasjon getKomplikasjonsklassifikasjon() {
		return komplikasjonsklassifikasjon;
	}




	public void setKomplikasjonsklassifikasjon(
			Komplikasjonsklassifikasjon komplikasjonsklassifikasjon) {
		this.komplikasjonsklassifikasjon = komplikasjonsklassifikasjon;
	}

	public List<String> getHvagikkgaltList() {
		return hvagikkgaltList;
	}

	public void setHvagikkgaltList(List<String> hvagikkgaltList) {
		this.hvagikkgaltList = hvagikkgaltList;
	}

	public void distributeTerms(){
		String[] formFields = getFormNames();
		String annenFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5],formFields[6],formFields[7],formFields[8],formFields[9],
				formFields[11]};
		annenKomplikasjon.setAnnenkomplicationfieldMaps(annenFields); 
		
		String komplikasjonFields[] = {formFields[10]};
		komplikasjonsklassifikasjon.setkomplikasjonklassifikasjonFieldsMaps(komplikasjonFields);

	}

	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			annenKomplikasjon.saveField(field, userEntry);
			komplikasjonsklassifikasjon.saveField(field, userEntry);
			
		}
		
		annenKomplikasjon.saveToAnnenKomplikasjon();
	//	komplikasjonsklassifikasjon.savetoKomplikasjonklassifikasjon();
		
	}
	
}
