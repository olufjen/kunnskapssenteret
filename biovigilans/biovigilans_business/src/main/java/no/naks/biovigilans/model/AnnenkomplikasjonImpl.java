package no.naks.biovigilans.model;

import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Vigilansmelding som annen komplikasjon
 */

public class AnnenkomplikasjonImpl extends AbstractVigilansmelding implements Vigilansmelding,Annenkomplikasjon{

	
	/**
	 * Klassifikasjon av hendelsen (beskrivelse)
	 */
	private String klassifikasjon;
	/**
	 * Klassifikasjonskode
	 */
	private String Klassifikasjonkode;
	/**
	 * Beskrivelse av hendelsen
	 */
	private String komplikasjonbeskrivelse;
	/**
	 * Hva slags hendelse er dette?
	 */
	private String komplikasjondefinisjon;
	/**
	 * Årsak til avviket
	 */
	private String avvikarsak;
	/**
	 * Under hvilken prosess skjedde hendelsen?
	 */
	private String hovedprosess;
	/**
	 * Gjennomførte eller planlagte tiltak
	 */
	private String tiltak;
	/**
	 * Kommentarer til hendelsen
	 */
	private String kommentar;
	/**
	 * Hvordan ble hendelsen oppdaget
	 */
	private String oppdaget;
	/**
	 * Dato når komplikasjonen ble oppdaget for kvittering
	 */
	private String datoforhendelseKvittering;

	private Long meldeid;
	private Map<String,String> annenKomplikasjonsFields;
	private String[] keys;
	
	
	public AnnenkomplikasjonImpl() {
		super();
		types = new int[]  {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		annenKomplikasjonsFields = new HashMap<String,String>();
	}
	public String getKlassifikasjon() {
		return klassifikasjon;
	}
	public void setKlassifikasjon(String klassifikasjon) {
		if(klassifikasjon == null){
			klassifikasjon = (annenKomplikasjonsFields.get(keys[3])==null ? "" : annenKomplikasjonsFields.get(keys[3])) ;
			for(int i=9;i<16;i++){
				if(annenKomplikasjonsFields.get(keys[i])!=null){
					klassifikasjon = klassifikasjon +" ; " + annenKomplikasjonsFields.get(keys[i]);
					i=16;
				}
			}
		}
		this.klassifikasjon = klassifikasjon;
	}
	
	
	public String getDatoforhendelseKvittering() {
		return datoforhendelseKvittering;
	}
	public void setDatoforhendelseKvittering(String datoforhendelseKvittering) {
		this.datoforhendelseKvittering = datoforhendelseKvittering;
	}
	public String getKlassifikasjonkode() {
		return Klassifikasjonkode;
	}
	public void setKlassifikasjonkode(String klassifikasjonkode) {
		
		Klassifikasjonkode = klassifikasjonkode;
	}
	public String getKomplikasjonbeskrivelse() {
		return komplikasjonbeskrivelse;
	}
	public void setKomplikasjonbeskrivelse(String komplikasjonbeskrivelse) {
		if(komplikasjonbeskrivelse == null){
			komplikasjonbeskrivelse = (annenKomplikasjonsFields.get(keys[1])==null ? "" : annenKomplikasjonsFields.get(keys[1])) ;
		}
		this.komplikasjonbeskrivelse = komplikasjonbeskrivelse;
	}
	public String getKomplikasjondefinisjon() {
		return komplikasjondefinisjon;
	}
	public void setKomplikasjondefinisjon(String komplikasjondefinisjon) {
		if(komplikasjondefinisjon == null){
			komplikasjondefinisjon = (annenKomplikasjonsFields.get(keys[2])==null ? "" : annenKomplikasjonsFields.get(keys[2])) ;
		}
		this.komplikasjondefinisjon = komplikasjondefinisjon;
	}
	public String getAvvikarsak() {
		return avvikarsak;
	}
	public void setAvvikarsak(String avvikarsak) {
		if(avvikarsak == null){
			avvikarsak = (annenKomplikasjonsFields.get(keys[4])==null ? "" : annenKomplikasjonsFields.get(keys[4])) ;
			if(avvikarsak.equalsIgnoreCase("--- Select ---")){
				avvikarsak=null;
			}
		}
		this.avvikarsak = avvikarsak;
	}
	public String getHovedprosess() {
		return hovedprosess;
	}
	public void setHovedprosess(String hovedprosess) {
		if(hovedprosess == null){
			hovedprosess = (annenKomplikasjonsFields.get(keys[5])==null ? "" : annenKomplikasjonsFields.get(keys[5])) ;
			if(hovedprosess.equalsIgnoreCase("--- Select ---")){
				hovedprosess=null;
			}
		}
		this.hovedprosess = hovedprosess;
	}
	public String getTiltak() {
		return tiltak;
	}
	public void setTiltak(String tiltak) {
		if(tiltak == null){
			tiltak = (annenKomplikasjonsFields.get(keys[7])==null ? "" : annenKomplikasjonsFields.get(keys[7])) ;
		}
		this.tiltak = tiltak;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		if(kommentar == null){
			kommentar = (annenKomplikasjonsFields.get(keys[8])==null ? "" : annenKomplikasjonsFields.get(keys[8])) ;
		}
		this.kommentar = kommentar;
	}
	public String getOppdaget() {
		return oppdaget;
	}
	public void setOppdaget(String oppdaget) {
		if(oppdaget == null){
			oppdaget = (annenKomplikasjonsFields.get(keys[6])==null ? "" : annenKomplikasjonsFields.get(keys[6])) ;
			if(oppdaget.equalsIgnoreCase("--- Select ---")){
				oppdaget=null;
			}
		}
		this.oppdaget = oppdaget;
	}
	public Map<String, String> getAnnenKomplikasjonsFields() {
		return annenKomplikasjonsFields;
	}
	public void setAnnenKomplikasjonsFields(
			Map<String, String> annenKomplikasjonsFields) {
		this.annenKomplikasjonsFields = annenKomplikasjonsFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	public Long getMeldeid() {
		return meldeid;
	}
	public void setMeldeid(Long meldeid) {
		this.meldeid = meldeid;
	}
	
	public void setParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{this.getMeldeid(),getKlassifikasjon(),getKlassifikasjonkode(),getKomplikasjonbeskrivelse(),getKomplikasjondefinisjon(),getAvvikarsak(),getHovedprosess(),getTiltak(),getKommentar(),getOppdaget()};
		}else
			params = new Object[]{this.getMeldeid(),getKlassifikasjon(),getKlassifikasjonkode(),getKomplikasjonbeskrivelse(),getKomplikasjondefinisjon(),getAvvikarsak(),getHovedprosess(),getTiltak(),getKommentar(),getOppdaget()};
		}
	/**
	 * setAnnenkomplicationfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setAnnenkomplicationfieldMaps(String[]userFields){
		keys = userFields;
		int size = keys.length;
		for (int i = 0; i<size;i++){
			annenKomplikasjonsFields.put(userFields[i],null);
		}
	
		
	}
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (annenKomplikasjonsFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			annenKomplikasjonsFields.put(userField,userValue);	
	
		}
		
	}

	public void saveToAnnenKomplikasjon(){
		setTiltak(null);
		setKomplikasjonbeskrivelse(null);
		setKlassifikasjonkode(null);
		setKlassifikasjon(null);
		setAvvikarsak(null);
		setHovedprosess(null);
		setOppdaget(null);
		setKomplikasjondefinisjon(null);
		setKommentar(null);
	
	
	}
	
}

