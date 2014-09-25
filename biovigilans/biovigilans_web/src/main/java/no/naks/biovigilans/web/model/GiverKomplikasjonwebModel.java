package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.AbstractVigilansmelding;
import no.naks.biovigilans.model.Giver;
import no.naks.biovigilans.model.GiverImpl;
import no.naks.biovigilans.model.Giverkomplikasjon;
import no.naks.biovigilans.model.GiverkomplikasjonImpl;
import no.naks.biovigilans.model.Vigilansmelding;


public class GiverKomplikasjonwebModel extends VigilansModel {
	private Giver giver;
	private Vigilansmelding vigilansmelding;
	private Giverkomplikasjon giverKomplikasjon;
	
	public GiverKomplikasjonwebModel() {
		super();
		giver = new GiverImpl();
		vigilansmelding = new AbstractVigilansmelding();
		giverKomplikasjon = new GiverkomplikasjonImpl();
	//	giver.setGiverfieldMaps(userFields);
		
	}
	
	

	public Vigilansmelding getVigilansmelding() {
		return vigilansmelding;
	}



	public void setVigilansmelding(Vigilansmelding vigilansmelding) {
		this.vigilansmelding = vigilansmelding;
	}



	public Giver getGiver() {
		return giver;
	}

	public void setGiver(Giver giver) {
		this.giver = giver;
	}

	public Giverkomplikasjon getGiverKomplikasjon() {
		return giverKomplikasjon;
	}
	public void setGiverKomplikasjon(Giverkomplikasjon giverKomplikasjon) {
		this.giverKomplikasjon = giverKomplikasjon;
	}



	private String[] aldergruppe;

	public String[] getAldergruppe() {
		return aldergruppe;
	}

	public void setAldergruppe(String[] aldergruppe) {
		this.aldergruppe = aldergruppe;
	}
	
	public void distributeTerms(){
			String[] formFields = getFormNames();
			String giverFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5],formFields[11]};
			giver.setGiverfieldMaps(giverFields);
	}
	
	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			giver.saveField(field, userEntry);
			
			giverKomplikasjon.saveField(field, userEntry);
		}
		
		giver.saveToGiver();
		vigilansmelding.saveToVigilansmelding();
		giverKomplikasjon.saveToGiverkomplikasjon();
		
	}
	
	
	public void meldingDistributeTerms(){
		/*
		String[] formFields = getFormNames();
		String meldingFields[] = {formFields[6],formFields[7],formFields[8],formFields[9],formFields[10],formFields[12],formFields[13]};
		vigilansmelding.setVigilansmeldingfieldMaps(meldingFields);
		*/
	}
	
	public void giverKomplikasjonDistribute(){
		String[] formFields = getFormNames();
		String meldingFields[] = {formFields[13],formFields[14],formFields[15],formFields[16],formFields[17],formFields[18],formFields[19],formFields[20],formFields[21]};
		giverKomplikasjon.setGiverkomplicationfieldMaps(meldingFields);
	}
	
}
