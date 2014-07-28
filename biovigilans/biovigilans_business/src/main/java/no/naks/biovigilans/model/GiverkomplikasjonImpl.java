package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


/**
 * En givekomplikasjon er en komplikasjon som oppst�r hos giver etter at hun har gitt blod eller andre organer
 * 
 */

public class GiverkomplikasjonImpl extends AbstractVigilansmelding implements Vigilansmelding,Giverkomplikasjon{

	/**
	 * Hvor skjedde komplikasjonen (venterom, benk)
	 */
	private String stedforkomplikasjon;
	private String tidfratappingtilkompliasjon;
	/**
	 * Nedtrekksvelg
	 */
	private String behandlingssted;
	/**
	 * Tilleggsopplysninger knyttet til denne oppf�lgingen.
	 */
	private String tilleggsopplysninger;
	private String alvorlighetsgrad;
	private String kliniskresultat;
	/**
	 * Varghet kan v�re fra fra minutter til m�neder
	 */
	private String varighetkomplikasjon;
	private Map<String,String> komplikasjonsFields;
	private String[] keys;

	
	public GiverkomplikasjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	
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
	 * setGiverkomplicationfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setGiverkomplicationfieldMaps(String[]userFields){
		keys = userFields;
		for (int i = 0;i<5;i++){
		komplikasjonsFields.put(userFields[i],null);
		}

		
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
	

	public String getStedforkomplikasjon() {
		return stedforkomplikasjon;
	}

	public void setStedforkomplikasjon(String stedforkomplikasjon) {
		this.stedforkomplikasjon = stedforkomplikasjon;
	}

	public String getTidfratappingtilkompliasjon() {
		return tidfratappingtilkompliasjon;
	}

	public void setTidfratappingtilkompliasjon(String tidfratappingtilkompliasjon) {
		this.tidfratappingtilkompliasjon = tidfratappingtilkompliasjon;
	}

	public String getBehandlingssted() {
		return behandlingssted;
	}

	public void setBehandlingssted(String behandlingssted) {
		this.behandlingssted = behandlingssted;
	}

	public String getTilleggsopplysninger() {
		return tilleggsopplysninger;
	}

	public void setTilleggsopplysninger(String tilleggsopplysninger) {
		this.tilleggsopplysninger = tilleggsopplysninger;
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

	public String getVarighetkomplikasjon() {
		return varighetkomplikasjon;
	}

	public void setVarighetkomplikasjon(String varighetkomplikasjon) {
		this.varighetkomplikasjon = varighetkomplikasjon;
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