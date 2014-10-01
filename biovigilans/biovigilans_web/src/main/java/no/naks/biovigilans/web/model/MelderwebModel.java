package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.Melder;
import no.naks.biovigilans.model.MelderImpl;

public class MelderwebModel extends VigilansModel {
	
	private Melder melder;
	
	public MelderwebModel(){
		super();
		melder = new MelderImpl();
	}

	public Melder getMelder() {
		return melder;
	}

	public void setMelder(Melder melder) {
		this.melder = melder;
	}

	public void distributeTerms(){
		String[] formFields = getFormNames();
		String melderFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5]};
		melder.setMelderfieldMaps(melderFields);
	}
	
	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			melder.saveField(field, userEntry);
		}
		
		melder.saveToMelder();
		
		
	}
	
}
