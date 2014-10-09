package no.naks.biovigilans.model;

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
		return lokalskadearm;
	}
	public void setLokalskadearm(String lokalskadearm) {
		if(lokalskadearm == null){
			lokalskadearm = komplikasjonGiverFields.get(keys[0]);
		}
		this.lokalskadearm = lokalskadearm;
	}
	public String getSystemiskbivirkning() {
		return systemiskbivirkning;
	}
	public void setSystemiskbivirkning(String systemiskbivirkning) {
		if(systemiskbivirkning ==  null){
			systemiskbivirkning = komplikasjonGiverFields.get(keys[2]);
		}
		this.systemiskbivirkning = systemiskbivirkning;
	}
	public String getAnnenreaksjon() {
		return annenreaksjon;
	}
	public void setAnnenreaksjon(String annenreaksjon) {
		if(annenreaksjon ==  null){
			annenreaksjon = komplikasjonGiverFields.get(keys[4]);
		}
		this.annenreaksjon = annenreaksjon;
	}
	public String getLokalskadebeskrivelse() {
		return lokalskadebeskrivelse;
	}
	public void setLokalskadebeskrivelse(String lokalskadebeskrivelse) {
		if(lokalskadebeskrivelse == null){
			lokalskadebeskrivelse = komplikasjonGiverFields.get(keys[1]);
		}
		this.lokalskadebeskrivelse = lokalskadebeskrivelse;
	}
	public String getBivirkningbeskrivelse() {
		return bivirkningbeskrivelse;
	}
	public void setBivirkningbeskrivelse(String bivirkningbeskrivelse) {
		if(bivirkningbeskrivelse == null){
			bivirkningbeskrivelse = komplikasjonGiverFields.get(keys[3]);
		}
		this.bivirkningbeskrivelse = bivirkningbeskrivelse;
	}
	public String getAnnenreaksjonbeskrivelse() {
		return annenreaksjonbeskrivelse;
	}
	public void setAnnenreaksjonbeskrivelse(String annenreaksjonbeskrivelse) {
		if(annenreaksjonbeskrivelse == null){
			annenreaksjonbeskrivelse = komplikasjonGiverFields.get(keys[5]);
		}
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