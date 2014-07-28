package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DonasjonImpl extends AbstractDonasjon implements Donasjon {

	public DonasjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		donasjonsFields = new HashMap();
		
	}

	public void setParams(){
		Long id = getDonasjonsId();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getDonasjonsId()};
		
	}	

	/**
	 * setDonasjonsfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setDonasjonsfieldMaps(String[]userFields){
		keys = userFields;
		for (int i = 0;i<6;i++){
			donasjonsFields.put(userFields[i],null);
		}
	
		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (donasjonsFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			donasjonsFields.put(userField,userValue);	
	
		}
		
	}

}
