package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;

public class GiveroppfolgingImpl extends AbstractGiveroppfolging implements Giveroppfolging {

	public GiveroppfolgingImpl(){
		super();
		types= new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		utypes= new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
		giveroppfolgingFields = new HashMap();
	}
	
	public void setParams() {
	
		Long id = getGiveroppfolgingId();
		if(id == null){
			params = new Object[]{getKlassifikasjongiveroppfolging(), getGiveroppfolgingbeskrivelse(), getAvregistering(),getMeldeid() };
		}else{
			params = new Object[]{getKlassifikasjongiveroppfolging(), getGiveroppfolgingbeskrivelse(), getAvregistering(),getMeldeid(), getGiveroppfolgingId() };

		}
	}
	
	/**
	 * setGiveroppfolgingfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	
	public void setGiveroppfolgingfieldMaps(String[]userFields){
		keys = userFields;
		int size = keys.length;
		for(int i=0; i<size;i++){
			giveroppfolgingFields.put(userFields[i],null);
		}
	}
	
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (giveroppfolgingFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			giveroppfolgingFields.put(userField,userValue);	
	
		}
		
	}
	
	public void saveToField(){
		setKlassifikasjongiveroppfolging(null);
		setGiveroppfolgingbeskrivelse(null);
		setAvregistering(null);
	}

}
