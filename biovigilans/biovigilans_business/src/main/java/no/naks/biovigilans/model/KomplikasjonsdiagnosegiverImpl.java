package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;

public class KomplikasjonsdiagnosegiverImpl extends
		AbstractKomplikasjonsdiagnosegiver implements Komplikasjonsdiagnosegiver {

	public KomplikasjonsdiagnosegiverImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		komplikasjonGiverFields = new HashMap();
		
	}

	public void setParams(){
		Long id = getKomlikasjonsdiagnoseId();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getKomlikasjonsdiagnoseId()};
		
	}	

	/**
	 * setKomplikasjonsdiagnoseMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setKomplikasjonsdiagnoseMaps(String[]userFields){
		keys = userFields;
		for (int i = 0;i<5;i++){
			komplikasjonGiverFields.put(userFields[i],null);
		}

		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (komplikasjonGiverFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			komplikasjonGiverFields.put(userField,userValue);	
	
		}
		
	}
}
