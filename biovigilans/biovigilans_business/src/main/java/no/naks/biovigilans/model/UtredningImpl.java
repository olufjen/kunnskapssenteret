package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;

public class UtredningImpl extends AbstractUtredning implements Utredning {

	public UtredningImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		utredningsFields = new HashMap();
	}
	
	public void setParams(){
		Long id = getUtredningId();
		if (id == null){
			params = new Object[]{getUtredningsklassifikasjon(),getUtredningbeskrivelse(),getBlodtypeserologisk(),getHemolyseparameter(),getLga(),getPosedyrking(),getPosedyrkingpositiv(),getMeldeId()};
		}else
			params = new Object[]{getUtredningsklassifikasjon(),getUtredningbeskrivelse(),getBlodtypeserologisk(),getHemolyseparameter(),getLga(),getPosedyrking(),getPosedyrkingpositiv(),getMeldeId(),getUtredningId()};
		
	}	
	/**
	 * setutredningfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setutredningfieldMaps(String[]userFields){
		keys = userFields;
		for (int i = 0;i<15;i++){
			utredningsFields.put(userFields[i],null);
		}
		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */

	public void saveField(String userField, String userValue) {
		if (utredningsFields.containsKey(userField) && userValue != null){
			utredningsFields.put(userField,userValue);	
	
		}
		
		
	}
	
	/**
	 * saveUtredning
	 * Denne rutinen lagrer alle brukerverider til riktig felt
	 * 
	 */
	public void saveUtredning(){
		setBlodtypeserologisk(null);
		setHemolyseparameter(null);
		setLga(null);
		setPosedyrking(null);
		setPosedyrkingpositiv(null);
		setUtredningbeskrivelse(null);
		setUtredningsklassifikasjon(null);
		
	}
}
