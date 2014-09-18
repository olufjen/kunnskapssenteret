package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.AbstractVigilansmelding;
import no.naks.biovigilans.model.Giver;
import no.naks.biovigilans.model.GiverImpl;
import no.naks.biovigilans.model.Vigilansmelding;


public class GiverKomplikasjonwebModel extends VigilansModel {
	private Giver giver;
	private Vigilansmelding vigilansmelding;
	
	public GiverKomplikasjonwebModel() {
		super();
		giver = new GiverImpl();
		vigilansmelding = new AbstractVigilansmelding();
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
		}
		
		giver.saveToGiver();
		vigilansmelding.saveToVigilansmelding();
		
	}
	
	
	public void meldingDistributeTerms(){
		String[] formFields = getFormNames();
		String meldingFields[] = {};
		vigilansmelding.setVigilansmeldingfieldMaps(meldingFields);
	}
	
}
