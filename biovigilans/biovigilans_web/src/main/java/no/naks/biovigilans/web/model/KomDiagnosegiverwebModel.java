package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.Komplikasjonsdiagnosegiver;
import no.naks.biovigilans.model.KomplikasjonsdiagnosegiverImpl;

public class KomDiagnosegiverwebModel extends VigilansModel {

	private Komplikasjonsdiagnosegiver komDiagnosegiver;

	public KomDiagnosegiverwebModel(){
		super();
		komDiagnosegiver = new KomplikasjonsdiagnosegiverImpl();
	}
	
	public Komplikasjonsdiagnosegiver getKomDiagnosegiver() {
		return komDiagnosegiver;
	}

	public void setKomDiagnosegiver(Komplikasjonsdiagnosegiver komDiagnosegiver) {
		this.komDiagnosegiver = komDiagnosegiver;
	}
	
	public void distributeTerms(){
		String[] formFields = getFormNames();
		String fields[] = {formFields[18],formFields[19],formFields[20],formFields[21],formFields[22],formFields[23]};
		komDiagnosegiver.setKomplikasjonsdiagnoseMaps(fields);
	}
	
	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); 
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			komDiagnosegiver.saveField(field, userEntry);
		}
		
		komDiagnosegiver.saveToField();
		
	}	

	
}
