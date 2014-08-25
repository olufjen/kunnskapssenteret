package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



/**
 * En PasientkomplikasjonImpl er en komplikasjon som har oppstått hos pasienten som følge av en transfusjon (overføring).
 * 
 */

public class PasientkomplikasjonImpl extends AbstractVigilansmelding implements Vigilansmelding,Pasientkomplikasjon {

	private Long transfusjonsId;
	
	/**
	 * Klassifikasjon av komplikasjon, hentes fra AbstractSykdom?
	 */
	private String klassifikasjon;
	/**
	 * Tiden (antall timer) fra påbegynt transfusjon til at komplikasjonen oppstod.
	 */
	private int tidfrapabegynttrasfusjontilkomplikasjon;
	/**
	 * Må beskrives
	 */
	private String alvorlighetsgrad;
	/**
	 * Er det samme som utredning i skjema!?
	 */
	private String kliniskresultat;
	/**
	 * Må beskrives
	 */
	private String arsakssammenheng;
	/**
	 * En pasientkomplikasjon inneholder mange symptomer og komplikasjonsklassifikasjoner
	 */	
	private Map<String,Symptomer> symptomer;
	private Map<String,Komplikasjonsklassifikasjon>  komplikasjonsKlassifikasjoner;
	
	private Map<String,String> komplikasjonsFields;
	private String[] keys;
	
	public PasientkomplikasjonImpl() {
		super();
	
		symptomer = new HashMap();
		komplikasjonsKlassifikasjoner = new HashMap();
		
		komplikasjonsFields = new HashMap();
		setMeldingsdato(Calendar.getInstance().getTime());
		
		// Midlertidig Disse datoer bør komme fra bruker !!
		setDatoforhendelse(Calendar.getInstance().getTime());
		setDatooppdaget(Calendar.getInstance().getTime());
	}
	
	public void setParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{getMeldeid(),getKlassifikasjon(),getTidfrapabegynttrasfusjontilkomplikasjon(),getAlvorlighetsgrad(),getKliniskresultat(),getArsakssammenheng(),getTransfusjonsId()};
		}else
			params = new Object[]{getMeldeid(),getKlassifikasjon(),getTidfrapabegynttrasfusjontilkomplikasjon(),getAlvorlighetsgrad(),getKliniskresultat(),getArsakssammenheng(),getTransfusjonsId()};
		
	}
	public void setKomplikasjonstypes(){
		types = new int[] {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
	}

	public Long getTransfusjonsId() {
		return transfusjonsId;
	}

	public void setTransfusjonsId(Long transfusjonsId) {
		this.transfusjonsId = transfusjonsId;
	}

	/**
	 * setPatientkomplicationfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setPatientkomplicationfieldMaps(String[]userFields){
		keys = userFields;
		
		komplikasjonsFields.put(userFields[0],null);
		komplikasjonsFields.put(userFields[1],null);
		komplikasjonsFields.put(userFields[2],null);
		komplikasjonsFields.put(userFields[3],null);
		komplikasjonsFields.put(userFields[4],null);
		komplikasjonsFields.put(userFields[5],null);
		komplikasjonsFields.put(userFields[6],null);
		komplikasjonsFields.put(userFields[7],null);
		komplikasjonsFields.put(userFields[8],null);
		komplikasjonsFields.put(userFields[9],null);
		komplikasjonsFields.put(userFields[10],null);
		komplikasjonsFields.put(userFields[11],null);
		komplikasjonsFields.put(userFields[12],null);
		komplikasjonsFields.put(userFields[13],null);
		
	}

	public String getKlassifikasjon() {
		return klassifikasjon;
	}
	public void setKlassifikasjon(String klassifikasjon) {
		this.klassifikasjon = klassifikasjon;
	}
	public int getTidfrapabegynttrasfusjontilkomplikasjon() {
		return tidfrapabegynttrasfusjontilkomplikasjon;
	}
	public void setTidfrapabegynttrasfusjontilkomplikasjon(
			int tidfrapabegynttrasfusjontilkomplikasjon) {
		this.tidfrapabegynttrasfusjontilkomplikasjon = tidfrapabegynttrasfusjontilkomplikasjon;
	}
	public String getAlvorlighetsgrad() {
		return alvorlighetsgrad;
	}
	public void setAlvorlighetsgrad(String alvorlighetsgrad) {
		if (alvorlighetsgrad == null){
			String aProd = null;
			for (int i=0;i<=4;i++){
				aProd = komplikasjonsFields.get(keys[i]);
				if (aProd != null){
					alvorlighetsgrad = aProd;
					break;
				}
			}
		}
		this.alvorlighetsgrad = alvorlighetsgrad;
	}
	public String getKliniskresultat() {
		return kliniskresultat;
	}
	public void setKliniskresultat(String kliniskresultat) {
		if (kliniskresultat == null){
			String aProd = null;
			for (int i=5;i<=9;i++){
				aProd = komplikasjonsFields.get(keys[i]);
				if (aProd != null){
					kliniskresultat = aProd;
					break;
				}
			}
		}
		this.kliniskresultat = kliniskresultat;
	}
	public String getArsakssammenheng() {
		return arsakssammenheng;
	}
	public void setArsakssammenheng(String arsakssammenheng) {
		if (arsakssammenheng == null){
			String aProd = null;
			for (int i=10;i<=13;i++){
				aProd = komplikasjonsFields.get(keys[i]);
				if (aProd != null){
					arsakssammenheng = aProd;
					break;
				}
			}
		}
		this.arsakssammenheng = arsakssammenheng;
	}

	public Map<String, Symptomer> getSymptomer() {
		return symptomer;
	}

	public void setSymptomer(Map<String, Symptomer> symptomer) {
		this.symptomer = symptomer;
	}

	public Map<String, Komplikasjonsklassifikasjon> getKomplikasjonsKlassifikasjoner() {
		return komplikasjonsKlassifikasjoner;
	}

	public void setKomplikasjonsKlassifikasjoner(
			Map<String, Komplikasjonsklassifikasjon> komplikasjonsKlassifikasjoner) {
		this.komplikasjonsKlassifikasjoner = komplikasjonsKlassifikasjoner;
	}

	public Map<String, String> getKomplikasjonsFields() {
		return komplikasjonsFields;
	}

	public void setKomplikasjonsFields(Map<String, String> komplikasjonsFields) {
		this.komplikasjonsFields = komplikasjonsFields;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (komplikasjonsFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			komplikasjonsFields.put(userField,userValue);	
	
		}
		
	}

	
	/* produceSymptoms
	 * Denne rutinen lager korrekt antall symptomobjekt etter antall symptomer bruker har angitt
	 * 
	 */
	public void produceSymptoms(Symptomer symptomer) {
		
		for (String symptom : symptomer.getSymptomerFields().values()){
			if (symptom != null && !symptom.equals("")){
				Symptomer lokalsymptomer = new SymptomerImpl();
				lokalsymptomer.setSymptombeskrivelse(symptom);
				lokalsymptomer.setSymptomklassifikasjon(symptom);
				this.symptomer.put(symptom, lokalsymptomer);
				
			}
		}
	}
	
}