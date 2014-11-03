package no.naks.biovigilans.model;

import java.sql.Time;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


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

	/**
	 * produceBlodprodukt
	 * Denne rutinen lager korrekt antall blodprodukter etter hvor mange bruker har oppgitt
	 * @param hemolyse
	 */
	public void produceBlodprodukt(Blodprodukt blodprodukt) {
		Blodprodukt lokalBlodprodukt = null;
		boolean noTemp = false;
		Set pKeys = blodprodukt.getBlodproduktFields().keySet();
		Iterator ppItarator = pKeys.iterator();
		while(ppItarator.hasNext()){
		String bKey = (String) ppItarator.next();
		System.out.println("Transfusjon Key: "+ bKey );
		Set keys = blodprodukt.getEgenskaperFields().keySet();
		Iterator produktIterator = keys.iterator();
		while(produktIterator.hasNext()){
			String pKey = (String) produktIterator.next();
			String egenskap = (String)blodprodukt.getEgenskaperFields().get(pKey);
			if (egenskap != null && !egenskap.equals("")){
				lokalBlodprodukt = new BlodproduktImpl();
				lokalBlodprodukt.distributeValues(pKey,egenskap);
			}

		}
		}
		
	}
	
}
