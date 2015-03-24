package no.naks.biovigilans.model;

import java.util.HashMap;
import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * 
 */

public abstract class AbstractKomplikasjonsdiagnosegiver extends AbstractModel implements Komplikasjonsdiagnosegiver{

	private Long komlikasjonsdiagnoseId;
	
	/**
	 * Dette representerer diagnoseklassifikasjon
	 */
	private String lokalskadearm;
	/**
	 * Dette representerer diagnoseklassifikasjon
	 */
	private String systemiskbivirkning;
	/**
	 * Dette representerer diagnoseklassifikasjon
	 */
	private String annenreaksjon;
	/**
	 * Dette er ogs� nedtrekksvalg
	 */
	private String lokalskadebeskrivelse;
	/**
	 * Dette er ogs� nedtrekksvalg
	 */
	private String bivirkningbeskrivelse;
	/**
	 * Dette er ogs nedtrekksvalg
	 */
	private String annenreaksjonbeskrivelse;
	private String kommentar;
	private Long meldeId;
	
	protected Map<String,String> komplikasjonGiverFields;
	protected String[] keys;
	
	
	public Long getKomlikasjonsdiagnoseId() {
		return komlikasjonsdiagnoseId;
	}
	public void setKomlikasjonsdiagnoseId(Long komlikasjonsdiagnoseId) {
		this.komlikasjonsdiagnoseId = komlikasjonsdiagnoseId;
	}
	public String getLokalskadearm() {
		Map<String,String> userEntries = getKomplikasjonGiverFields();
		String field = "tab-arm";
		lokalskadearm = userEntries.get(field);
		if (lokalskadearm == null ||  lokalskadearm.trim().equalsIgnoreCase("")){
			lokalskadearm = "";
		}
		
		return lokalskadearm;
	}
	public void setLokalskadearm(String lokalskadearm) {
		/*if(lokalskadearm == null){
			lokalskadearm = komplikasjonGiverFields.get(keys[0]);
		}*/
		this.lokalskadearm = lokalskadearm;
	}
	public String getSystemiskbivirkning() {
		Map<String,String> userEntries = getKomplikasjonGiverFields();
		String field = "tab-bivirk";
		systemiskbivirkning = userEntries.get(field);
		if (systemiskbivirkning == null ){
			systemiskbivirkning = "";
		}else{
			if(systemiskbivirkning.trim().equalsIgnoreCase("Systemisk-Ja")){
				String systemValue = userEntries.get("tab-systemiskbivirkning");
				if(systemValue != null || ! systemValue.trim().equalsIgnoreCase("") ){
					systemiskbivirkning = systemiskbivirkning + ";" + systemValue;
				}
			}
		}
		return systemiskbivirkning;
	}
	public void setSystemiskbivirkning(String systemiskbivirkning) {
		/*if(systemiskbivirkning ==  null){
			systemiskbivirkning = komplikasjonGiverFields.get(keys[2]);
		}*/
		this.systemiskbivirkning = systemiskbivirkning;
	}
	public String getAnnenreaksjon() {
		// OLJ 9.03.15
		Map<String,String> userEntries = getKomplikasjonGiverFields();
		String field = "tab-kortbeskr";
		if (annenreaksjon == null) // Dersom verdien er hentet fra db så skal den ikke være null!?
			annenreaksjon = userEntries.get(field);
		if (annenreaksjon == null){
			annenreaksjon = "";
		}
		
		return annenreaksjon;
	}
	public void setAnnenreaksjon(String annenreaksjon) {
		if(annenreaksjon ==  null){ // OLJ 9.03.15
			Map<String,String> userEntries = getKomplikasjonGiverFields();
			String field = "tab-kortbeskr";
			annenreaksjon = userEntries.get(field);
		
		}
		this.annenreaksjon = annenreaksjon;
	}
	public String getLokalskadebeskrivelse() {
		Map<String,String> userEntries = getKomplikasjonGiverFields();
		String field = "tab-tabskadearm";
		lokalskadebeskrivelse = userEntries.get(field);
		if (lokalskadebeskrivelse == null ||  lokalskadebeskrivelse.trim().equalsIgnoreCase("")){
			lokalskadebeskrivelse = "";
		}else{
			
			if(lokalskadebeskrivelse.trim().equalsIgnoreCase("Nerveirritasjon")){
				String imgValue = userEntries.get("tab-imgarea");
				if(imgValue != null){
					lokalskadebeskrivelse = lokalskadebeskrivelse +";"+imgValue;
				}
			}
		}
		
		return lokalskadebeskrivelse;
	}
	public void setLokalskadebeskrivelse(String lokalskadebeskrivelse) {
		/*if(lokalskadebeskrivelse == null){
			lokalskadebeskrivelse = komplikasjonGiverFields.get(keys[1]);
		}*/
		this.lokalskadebeskrivelse = lokalskadebeskrivelse;
	}
	public String getBivirkningbeskrivelse() {
		return bivirkningbeskrivelse;
	}
	public void setBivirkningbeskrivelse(String bivirkningbeskrivelse) {
		/*if(bivirkningbeskrivelse == null){
			bivirkningbeskrivelse = komplikasjonGiverFields.get(keys[3]);
		}*/
		this.bivirkningbeskrivelse = bivirkningbeskrivelse;
	}
	public String getAnnenreaksjonbeskrivelse() {
		return annenreaksjonbeskrivelse;
	}
	public void setAnnenreaksjonbeskrivelse(String annenreaksjonbeskrivelse) {
		/*if(annenreaksjonbeskrivelse == null){
			annenreaksjonbeskrivelse = komplikasjonGiverFields.get(keys[5]);
		}*/
		this.annenreaksjonbeskrivelse = annenreaksjonbeskrivelse;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public Long getMeldeId() {
		return meldeId;
	}
	public void setMeldeId(Long meldeId) {
		this.meldeId = meldeId;
	}
	public Map<String, String> getKomplikasjonGiverFields() {
		if(komplikasjonGiverFields == null){
			komplikasjonGiverFields = new HashMap<String, String>();
		}
		return komplikasjonGiverFields;
	}
	public void setKomplikasjonGiverFields(
			Map<String, String> komplikasjonGiverFields) {
		this.komplikasjonGiverFields = komplikasjonGiverFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	
}