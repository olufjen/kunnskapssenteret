package no.naks.biovigilans.model;

import java.sql.Types;

public class SymptomerImpl extends AbstractSymptomer implements Symptomer {

	public SymptomerImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
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
		
		
		symptomerFields.put(userFields[0],getSymptombeskrivelse());


	}
	
}
