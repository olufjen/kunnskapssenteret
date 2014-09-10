package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlodproduktImpl extends AbstractBlodprodukt implements Blodprodukt {

	private List<String> userFields;
	public BlodproduktImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		blodproduktFields = new HashMap<String,String>();
		antallFields = new HashMap<String,String>();
		egenskaperFields = new HashMap<String,String>();
		userFields = new ArrayList();
	}

	public void setParams(){
		Long id = getBlodProduktId();
		if (id == null){
			params = new Object[]{getBlodtype(),getAlderProdukt(),getTappetype(),getBlodprodukt(),getProduktetsegenskap(),getAntallEnheter(),getTransfusjonsId()};
		}else
			params = new Object[]{getBlodtype(),getAlderProdukt(),getTappetype(),getBlodprodukt(),getProduktetsegenskap(),getAntallEnheter(),getTransfusjonsId(),getBlodProduktId()};
		
	}	
	/**
	 * setBlodProduktfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setBlodProduktfieldMaps(String[]userFields){
	//	keys = userFields;
		for (String field : userFields){
			this.userFields.add(field);
		}
		for (int i = 0;i<12;i++){
			blodproduktFields.put(userFields[i],null);
		}

	}

	/**
	 * setEgenskaperfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setEgenskaperfieldMaps(String[]userFields){
		for (String field : userFields){
			this.userFields.add(field);
		}	
		
		egenskaperFields.put(userFields[0], null);
		egenskaperFields.put(userFields[1], null);
		egenskaperFields.put(userFields[2], null);
		egenskaperFields.put(userFields[3], null);
		egenskaperFields.put(userFields[4], null);
		egenskaperFields.put(userFields[5], null);
		egenskaperFields.put(userFields[6], null);
//		ArrayList egenskaper = (ArrayList) egenskaperFields.values();
//		String first = (String) egenskaper.get(0);
	}
	public void setAntallfieldMaps(String[]userFields){
		for (String field : userFields){
			this.userFields.add(field);
		}
		for (int i = 0;i<7;i++){
			antallFields.put(userFields[i],null);
		}
	
	}
	/* 
	 * setKeyvalues
	 * Denne rutinen setter opp streng rekken keys med verdier fra userFields
	 */
	public void setKeyvalues(){
		keys = new String[this.userFields.size()];
		this.userFields.toArray(keys);
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField,String userValue){
		if (blodproduktFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			blodproduktFields.put(userField,userValue);	
	
		}
		if (antallFields.containsKey(userField)  && userValue != null && !userValue.equals("")){
			antallFields.put(userField,userValue);	
		}
		if (egenskaperFields.containsKey(userField)  && userValue != null && !userValue.equals("")){
			egenskaperFields.put(userField,userValue);	
		}
//		ArrayList<String> egenskaper = new ArrayList<String>(egenskaperFields.values());
		
		
	}


	
}
