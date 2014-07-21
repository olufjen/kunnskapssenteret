package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;


public class TransfusjonImpl extends AbstractTransfusjon implements Transfusjon {

	public TransfusjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		
		transfusjonsFields = new HashMap();
		pasientKomplikasjoner = new HashMap();
	}
	public void setParams(){
		Long id = getTransfusjonsId();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getTransfusjonsId()};
	

	}	
	/**
	 * settransfusjonsFieldsMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void settransfusjonsFieldsMaps(String[]userFields){

		keys = userFields;
		
		transfusjonsFields.put(userFields[0],getHastegrad());

	}	
}
