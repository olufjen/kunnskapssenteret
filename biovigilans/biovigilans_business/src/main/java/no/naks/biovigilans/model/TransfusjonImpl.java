package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;


public class TransfusjonImpl extends AbstractTransfusjon implements Transfusjon {

	public TransfusjonImpl() {
		super();
		types = new int[] {Types.DATE,Types.TIME,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		utypes = new int[] {Types.DATE,Types.TIME,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		
		transfusjonsFields = new HashMap();
		pasientKomplikasjoner = new HashMap();
		blodProdukter = new HashMap();
	}
	public void setParams(){
		Long id = getTransfusjonsId();
		if (id == null){
			params = new Object[]{getTransfusionDate(),getTransfusjonsklokkeslett(),getHastegrad(),getFeiltranfudert(),getIndikasjon(),getAntalenheter(),getPasient_Id()};
		}else
			params = new Object[]{getTransfusionDate(),getTransfusjonsklokkeslett(),getHastegrad(),getFeiltranfudert(),getIndikasjon(),getAntalenheter(),getPasient_Id(),getTransfusjonsId()};
	

	}	
	/**
	 * settransfusjonsFieldsMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	
	public void settransfusjonsFieldsMaps(String[]userFields){

		keys = userFields;
		
		transfusjonsFields.put(userFields[0],null);
		transfusjonsFields.put(userFields[1],null);
		transfusjonsFields.put(userFields[2],null);
		transfusjonsFields.put(userFields[3],null);
		transfusjonsFields.put(userFields[4],null);
		transfusjonsFields.put(userFields[5],null);
		transfusjonsFields.put(userFields[6],null);
		transfusjonsFields.put(userFields[7],null);
		transfusjonsFields.put(userFields[8],null);

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
