package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author olj
 *
 */
public class BlodproduktImpl extends AbstractBlodprodukt implements Blodprodukt {

	private List<String> userFields;
	private Map<String,Produktegenskap> produktEgenskaper;
	private String[] produkter = {"blod-erytrocytt","blod-trombocytt","Octaplas","Plasma fra enkeltgiver patogeninaktivert",
			"Plasma fra flere givere patogeninaktivert","Plasma fra enkeltgiver karantene","Plasma fra enkeltgiver frysetørret",
			"Plasma fra flere givere frysetørret","Plasma fra enkeltgiver ferskt (ikke frosset)","Ferskfrosset plasma",
			"Uniplas","Annet plasma"};
	public BlodproduktImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		blodproduktFields = new HashMap<String,String>();
		antallFields = new HashMap<String,String>();
		egenskaperFields = new HashMap<String,String>();
		produktEgenskaper = new HashMap();
		userFields = new ArrayList();
		 
	}

	public void setParams(){
		Long id = getBlodProduktId();
		if (id == null){
			params = new Object[]{getBlodtype(),getAlderProdukt(),getTappetype(),getBlodprodukt(),getProduktetsegenskap(),getAntallEnheter(),getAntallenheterpakke(),getSuspensjon(), getAntallTromb(),getAntallPlasma(),getTransfusjonsId()};
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
		egenskaperFields.put(userFields[7], null);
		egenskaperFields.put(userFields[8], null);
//		ArrayList egenskaper = (ArrayList) egenskaperFields.values();
//		String first = (String) egenskaper.get(0);
	}
	
	public String[] getProdukter() {
		return produkter;
	}

	public void setProdukter(String[] produkter) {
		this.produkter = produkter;
	}

	public void setAntallfieldMaps(String[]userFields){
		int l = userFields.length;
		for (String field : userFields){
			this.userFields.add(field);
		}
		for (int i = 0;i<l;i++){
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
	public void saveToBlodprodukt(){
		setAntallEnheter(-1);
		setBlodprodukt(null);// Produktet settes fast!
		setProduktetsegenskap(null);
		setTappetype(null);
		setSuspensjon(null);
		setAntallenheterpakke(-1);
		setAntallTromb(-1);
		setAntallPlasma(-1);
	}
	public void saveblodproduktName(int pos){
		
	}
	/**
	 * produceProduktegeskaper
	 * Denne rutinen lager korrekt antall produktegenskaper etter hvor mange bruker har oppgitt
	 * @param egenskap 
	 */
	public void produceProduktegenskaper(Produktegenskap egenskap) {
		Produktegenskap lokalEgenskap = null;
		boolean noTemp = false;
		// Så lenge det finnes produktegenskaper:
		for (String produkt : egenskap.getProduktegenskapFields().values() ){
			if (produkt != null && !produkt.equals("")){
				lokalEgenskap = new ProduktegenskapImpl();
				lokalEgenskap.distributeValues(produkt);
				produktEgenskaper.put(produkt, lokalEgenskap);
			}
		}
	}

	public Map<String, Produktegenskap> getProduktEgenskaper() {
		return produktEgenskaper;
	}

	public void setProduktEgenskaper(Map<String, Produktegenskap> produktEgenskaper) {
		this.produktEgenskaper = produktEgenskaper;
	}
	/* distributeValues
	 * Denne rutinen plasserer gitt egenskap til riktig felt
	 * 
	 */
	public void distributeValues(String key, String produkt){
		
		System.out.println("Blod Key: "+ key + " Innhold = " + produkt);
	}	
	
}
