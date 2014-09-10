package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class GiverImpl extends AbstractGiver implements Giver {

	public GiverImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		giverFields = new HashMap<String,String>();
	}

	public void setParams(){
		Long id = getGiverid();
		int vekt=0;
		if(getVekt()!= null)
			vekt = Integer.parseInt(getVekt());
		if (id == null){
			params = new Object[]{getKjonn(),getAlder(),getGivererfaring(),getTidligerekomlikasjonjanei(),getTidligerekomplikasjonforklaring(),getGivererfaringaferese(),vekt};
		}else
			params = new Object[]{getKjonn(),getAlder(),getGivererfaring(),getTidligerekomlikasjonjanei(),getTidligerekomplikasjonforklaring(),getGivererfaringaferese(),vekt,getGiverid()};
		
	}

	/**
	 * setGiverfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setGiverfieldMaps(String[]userFields){
		keys = userFields;
		for (int i = 0;i<6;i++){
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
