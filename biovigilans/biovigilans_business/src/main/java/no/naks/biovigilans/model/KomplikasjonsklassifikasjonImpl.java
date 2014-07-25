package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;

public class KomplikasjonsklassifikasjonImpl extends
		AbstractKomplikasjonsklassifikasjon implements Komplikasjonsklassifikasjon {

	public KomplikasjonsklassifikasjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		komplikasjonklassifikasjonFields = new HashMap();
	}

	public void setParams(){
		Long id = getKlassifikasjonsid();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getKlassifikasjonsid()};
		
	}

	/**
	 * setkomplikasjonklassifikasjonFieldsMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter 
	 */
	public void setkomplikasjonklassifikasjonFieldsMaps(String[]userFields){
		keys = userFields;
		for (int i = 0; i<22;i++){
			komplikasjonklassifikasjonFields.put(userFields[i],null);
		}
	
		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (komplikasjonklassifikasjonFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			komplikasjonklassifikasjonFields.put(userField,userValue);	

		}	
		
		
	}

}
