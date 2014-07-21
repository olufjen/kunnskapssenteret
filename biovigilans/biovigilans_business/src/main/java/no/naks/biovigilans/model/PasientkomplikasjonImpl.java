package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;



/**
 * En PasientkomplikasjonImpl er en komplikasjon som har oppstått hos pasienten som følge av en transfusjon (overføring).
 * 
 */

public class PasientkomplikasjonImpl extends AbstractVigilansmelding implements Pasientkomplikasjon,Vigilansmelding {

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
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		symptomer = new HashMap();
		komplikasjonsKlassifikasjoner = new HashMap();
		
		komplikasjonsFields = new HashMap();
		
	}
	
	public void setParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getMeldeid()};
		
	}

	/**
	 * setPatientkomplicationfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setPatientkomplicationfieldMaps(String[]userFields){
		keys = userFields;
		
		komplikasjonsFields.put(userFields[0],getAlvorlighetsgrad());
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
		this.alvorlighetsgrad = alvorlighetsgrad;
	}
	public String getKliniskresultat() {
		return kliniskresultat;
	}
	public void setKliniskresultat(String kliniskresultat) {
		this.kliniskresultat = kliniskresultat;
	}
	public String getArsakssammenheng() {
		return arsakssammenheng;
	}
	public void setArsakssammenheng(String arsakssammenheng) {
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
	
}