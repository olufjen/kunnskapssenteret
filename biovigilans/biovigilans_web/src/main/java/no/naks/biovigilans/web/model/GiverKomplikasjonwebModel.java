package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.Giver;
import no.naks.biovigilans.model.GiverImpl;


public class GiverKomplikasjonwebModel extends VigilansModel {
	private Giver giver;
	
	public GiverKomplikasjonwebModel() {
		super();
		giver = new GiverImpl();
	//	giver.setGiverfieldMaps(userFields);
		
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
			String giverFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5],formFields[6]};
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
		
	}
	
}
