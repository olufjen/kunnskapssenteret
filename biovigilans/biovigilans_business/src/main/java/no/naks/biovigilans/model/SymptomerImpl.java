package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;

public class SymptomerImpl extends AbstractSymptomer implements Symptomer {

	public SymptomerImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		symptomerFields = new HashMap();
	}
	public void setParams(){
		Long id = getSymptomId();
		if (id == null){
			params = new Object[]{};
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
		
		for (int i = 0; i<20;i++){
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

	
}
