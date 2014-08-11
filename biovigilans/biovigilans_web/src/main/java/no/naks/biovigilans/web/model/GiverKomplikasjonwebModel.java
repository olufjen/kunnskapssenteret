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

	private String[] aldergruppe;

	public String[] getAldergruppe() {
		return aldergruppe;
	}

	public void setAldergruppe(String[] aldergruppe) {
		this.aldergruppe = aldergruppe;
	}
	public void saveValues(){
		
	}
	
}
