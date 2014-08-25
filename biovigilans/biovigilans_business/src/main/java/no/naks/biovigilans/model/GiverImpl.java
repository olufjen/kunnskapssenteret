package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class GiverImpl extends AbstractGiver implements Giver {

	public GiverImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		giverFields = new HashMap<String,String>();
	}

	public void setParams(){
		Long id = getGiverid();
		if (id == null){
			params = new Object[]{getKjonn(),getAlder(),getVekt(),getGivererfaring(),getTidligerekomlikasjonjanei(),getTidligerekomplikasjonforklaring(),getGivererfaringaferese()};
		}else
			params = new Object[]{getKjonn(),getAlder(),getVekt(),getGivererfaring(),getTidligerekomlikasjonjanei(),getTidligerekomplikasjonforklaring(),getGivererfaringaferese(),getGiverid()};
		
	}

	/**
	 * setGiverfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setGiverfieldMaps(String[]userFields){
		keys = userFields;
		for (int i = 0;i<7;i++){
			giverFields.put(userFields[i],null);
		}

		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (giverFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			giverFields.put(userField,userValue);	
	
		}
		
	}
	
	public void saveToGiver(){
		setKjonn(null);
		setAlder(null);
		setVekt(null);
		setGivererfaring(null);
		setTidligerekomlikasjonjanei(null);
		setTidligerekomplikasjonforklaring(null);
		setGivererfaringaferese(null);
		
	}
}
