package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;

public class SymptomerImpl extends AbstractSymptomer implements Symptomer {

	public SymptomerImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		symptomerFields = new HashMap();
	}
	/**
	 * IsInt
	 * Denne rutinen sjekker om en Streng variabel er av typen int
	 * @param str
	 * @return
	 */
	private boolean IsInt(String str)
	{
	    if (str == null) {
	            return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	            return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	            if (length == 1) {
	                    return false;
	            }
	            i = 1;
	    }
	    for (; i < length; i++) {
	            char c = str.charAt(i);
	            if (c <= '/' || c >= ':') {
	                    return false;
	            }
	    }
	    return true;
	}	
	
	public void setParams(){
		Long id = getSymptomId();
		if (id == null){
			params = new Object[]{getSymptomklassifikasjon(),getSymptombeskrivelse(),getTempFor(),getTempetter(),getMeldeId()};
		}else
			params = new Object[]{getSymptomId()};
		
	}
	
	/**
	 * setsymptomerfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setsymptomerfieldMaps(String[]userFields){

		keys = userFields;
		int z = userFields.length;
		
		for (int i = 0; i<z;i++){
			symptomerFields.put(userFields[i],null);
		}
	
		


	}

	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	@Override
	public void saveField(String userField, String userValue) {
		if (symptomerFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			symptomerFields.put(userField,userValue);	

		}	
		
	}
	public void distributeValues(String symptom){
		setSymptombeskrivelse(symptom);
		setSymptomklassifikasjon(symptom);

	}
	public void distributeTemperature(String temp){
		if (getTempFor() >0)
			setTempetter(Integer.parseInt(temp));
		if (getTempFor() == 0)
			setTempFor(Integer.parseInt(temp));
	}

	
}
