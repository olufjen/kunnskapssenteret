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
//	private Long meldeId; Meldeid finnes i AbstractVigelansmelding !! Olj 16.01.15
	private Long donasjonid;
	/**
	 * Varghet kan v�re fra fra minutter til m�neder
	 */
	private String varighetkomplikasjon;
	private Map<String,String> komplikasjonsFields;
	private String[] keys;

	
	public GiverkomplikasjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.INTEGER};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.INTEGER};
		komplikasjonsFields = new HashMap();
	}
	
	public void setParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{getStedforkomplikasjon(),getBehandlingssted(),getTidfratappingtilkompliasjon(),getTilleggsopplysninger(),getAlvorlighetsgrad(),getKliniskresultat(),getVarighetkomplikasjon(),getMeldeid(), getDonasjonid()};
		}else
			params = new Object[]{getStedforkomplikasjon(),getBehandlingssted(),getTidfratappingtilkompliasjon(),getTilleggsopplysninger(),getAlvorlighetsgrad(),getKliniskresultat(),getVarighetkomplikasjon(),getMeldeid(), getDonasjonid()};
	}
	/**
	 * setGiverkomplicationfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setGiverkomplicationfieldMaps(String[]userFields){
		keys = userFields;
		int size = keys.length;
		for (int i = 0;i<size;i++){
		komplikasjonsFields.put(userFields[i],null);
		}
	}
	
	public void saveToGiverkomplikasjon(){
		setStedforkomplikasjon(null);
		setTidfratappingtilkompliasjon(null);
		setVarighetkomplikasjon(null);
		setAlvorlighetsgrad(null);
		setTilleggsopplysninger(null);
		setKliniskresultat(null);
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
		if(stedforkomplikasjon == null){
			stedforkomplikasjon = komplikasjonsFields.get(keys[0]);
		}
		this.stedforkomplikasjon = stedforkomplikasjon;
	}

	public String getTidfratappingtilkompliasjon() {
		return tidfratappingtilkompliasjon;
	}

	public void setTidfratappingtilkompliasjon(String tidfratappingtilkompliasjon) {
		if(tidfratappingtilkompliasjon==null){
			tidfratappingtilkompliasjon = komplikasjonsFields.get(keys[1]);
		}
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
		if(tilleggsopplysninger == null){
			tilleggsopplysninger =  komplikasjonsFields.get(keys[10]);
			
			
			
			/*String value="";
			for(int i=4; i<9; i++){
				if(komplikasjonsFields.get(keys[i]) != null){
					value=value + komplikasjonsFields.get(keys[i]) + " ";
				}
			}
			tilleggsopplysninger = value;
			*/
		}
		this.tilleggsopplysninger = tilleggsopplysninger;
	}

	public String getAlvorlighetsgrad() {
		return alvorlighetsgrad;
	}

	public void setAlvorlighetsgrad(String alvorlighetsgrad) {
		if(alvorlighetsgrad==null){
			alvorlighetsgrad = komplikasjonsFields.get(keys[3]);
		}
		this.alvorlighetsgrad = alvorlighetsgrad;
	}

	public String getKliniskresultat() {
		return kliniskresultat;
	}

	public void setKliniskresultat(String kliniskresultat) {
		if(kliniskresultat == null){
			kliniskresultat = komplikasjonsFields.get(keys[4]) ;
		}
		this.kliniskresultat = kliniskresultat;
	}

	public String getVarighetkomplikasjon() {
		return varighetkomplikasjon;
	}

	public void setVarighetkomplikasjon(String varighetkomplikasjon) {
		if(varighetkomplikasjon==null){
			varighetkomplikasjon = komplikasjonsFields.get(keys[2]);
		}
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



	public Long getDonasjonid() {
		return donasjonid;
	}

	public void setDonasjonid(Long donasjonid) {
		this.donasjonid = donasjonid;
	}
	

}