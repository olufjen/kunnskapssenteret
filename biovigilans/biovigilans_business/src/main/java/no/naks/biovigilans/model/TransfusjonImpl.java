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
		
		
		for (String produkt : blodprodukt.getBlodproduktFields().values() ){
			
			if (produkt != null && !produkt.equals("")){
				for (String produktnavn : blodprodukt.getProdukter()){
					if (produkt.equals(produktnavn)){
						lokalBlodprodukt = new BlodproduktImpl();
						lokalBlodprodukt.setBlodprodukt(produkt);
					
						break;
					}
				}
				if (lokalBlodprodukt == null){
					for (String produktnavn : blodprodukt.getPlasmaProdukter()){
						if (produkt.equals(produktnavn)){
							lokalBlodprodukt = new BlodproduktImpl();
							lokalBlodprodukt.setBlodprodukt(produkt);
						}
					}
				}
				if (lokalBlodprodukt == null){
					String produktvalue = (String) blodprodukt.getBlodproduktFields().get(blodprodukt.getUserFields().get(5)); // Userfield index 5 er annet blodprodukt !!
					if (produktvalue != null && !produktvalue.equals("")){
						lokalBlodprodukt = new BlodproduktImpl();
						lokalBlodprodukt.setBlodprodukt(produktvalue);
					}
				}
//				lokalBlodprodukt.saveToBlodprodukt();
				if (lokalBlodprodukt != null)
					getBlodProdukter().put(lokalBlodprodukt.getBlodprodukt(), lokalBlodprodukt);
			
				lokalBlodprodukt = null;
				
			}
			
			
		}
		SetblodProductValues(blodprodukt);

	
	}
	private void SetblodProductValues(Blodprodukt blodprodukt){
		Iterator blodIterator = getBlodProdukter().keySet().iterator();
		while (blodIterator.hasNext()){
			String key = (String) blodIterator.next();
			Blodprodukt lokalBlodprodukt = (Blodprodukt)getBlodProdukter().get(key);
			lokalBlodprodukt.setAntallFields(blodprodukt.getAntallFields());
			lokalBlodprodukt.setUserFields(blodprodukt.getUserFields());
			lokalBlodprodukt.setKeyvalues();
			if (lokalBlodprodukt.getBlodprodukt().equals("blod-trombocytt")){
				for (String produkt : blodprodukt.getBlodproduktFields().values() ){
					if (produkt != null && !produkt.equals("")){
						chooseTapping(lokalBlodprodukt, produkt);
						chooseSuspensjon(lokalBlodprodukt, produkt);
					}
				}
			}

			lokalBlodprodukt.setAntallkeyProdukt();
			if (lokalBlodprodukt.getAntallEnheter() < 0)
				lokalBlodprodukt.setAntallEnheter(-1);
		}
	}
	private void chooseTapping(Blodprodukt blodprodukt,String egenskap){
		for (String tapping : blodprodukt.getTapping()){
			if (tapping.equals(egenskap)){
				blodprodukt.setTappetype(egenskap);
			}
		}
	}
	private void chooseSuspensjon(Blodprodukt blodprodukt,String suspensjon){
		for (String susp : blodprodukt.getSuspensjonsValg()){
			if (susp.equals(suspensjon)){
				blodprodukt.setSuspensjon(suspensjon);
			}
		}
	}
	
}
