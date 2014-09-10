package no.naks.biovigilans.model;

import java.sql.Time;
import java.sql.Types;
import java.util.HashMap;


public class TransfusjonImpl extends AbstractTransfusjon implements Transfusjon {

	public TransfusjonImpl() {
		super();
		types = new int[] {Types.DATE,Types.TIME,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER};
		utypes = new int[] {Types.DATE,Types.TIME,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		
		transfusjonsFields = new HashMap();
		pasientKomplikasjoner = new HashMap();
		blodProdukter = new HashMap();
	}
	public void setParams(){
		Long id = getTransfusjonsId();
		if (id == null){
			params = new Object[]{getTransfusionDate(),getTransklokke(),getHastegrad(),getFeiltranfudert(),getIndikasjon(),getAntalenheter(),getTildigerKomplikasjon(),getPasient_Id()};
		}else
			params = new Object[]{getTransfusionDate(),getTransklokke(),getHastegrad(),getFeiltranfudert(),getIndikasjon(),getAntalenheter(),getTildigerKomplikasjon(),getPasient_Id(),getTransfusjonsId()};
	

	}	
	/**
	 * settransfusjonsFieldsMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	
	public void settransfusjonsFieldsMaps(String[]userFields){

		keys = userFields;
		for (int i = 0;i<36;i++){
			transfusjonsFields.put(userFields[i],null);
		}
		
	

	}	
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	
	public void saveField(String userField,String userValue){
		
		if (transfusjonsFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			transfusjonsFields.put(userField,userValue);	
	
		}
	}


	
}
