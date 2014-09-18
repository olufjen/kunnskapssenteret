package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.Donasjon;
import no.naks.biovigilans.model.DonasjonImpl;

public class DonasjonwebModel extends VigilansModel {
	
	private Donasjon donasjon;
	
	
	public DonasjonwebModel(){
		super();
		donasjon = new DonasjonImpl();
	}


	public Donasjon getDonasjon() {
		return donasjon;
	}


	public void setDonasjon(Donasjon donasjon) {
		this.donasjon = donasjon;
	}
	
	public void distributeTerms(){
		String[] formFields = getFormNames();
		String donasjonFields[] = {formFields[6],formFields[7],formFields[8],formFields[9],formFields[10],formFields[11],formFields[12],formFields[25]};
		donasjon.setDonasjonsfieldMaps(donasjonFields);
	}
	
	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			donasjon.saveField(field, userEntry);
		}
		
		donasjon.saveToField();
		
	}	

	
}
