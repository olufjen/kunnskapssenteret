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
		this.lokalskadearm = lokalskadearm;
	}
	public String getSystemiskbivirkning() {
		return systemiskbivirkning;
	}
	public void setSystemiskbivirkning(String systemiskbivirkning) {
		this.systemiskbivirkning = systemiskbivirkning;
	}
	public String getAnnenreaksjon() {
		return annenreaksjon;
	}
	public void setAnnenreaksjon(String annenreaksjon) {
		this.annenreaksjon = annenreaksjon;
	}
	public String getLokalskadebeskrivelse() {
		return lokalskadebeskrivelse;
	}
	public void setLokalskadebeskrivelse(String lokalskadebeskrivelse) {
		this.lokalskadebeskrivelse = lokalskadebeskrivelse;
	}
	public String getBivirkningbeskrivelse() {
		return bivirkningbeskrivelse;
	}
	public void setBivirkningbeskrivelse(String bivirkningbeskrivelse) {
		this.bivirkningbeskrivelse = bivirkningbeskrivelse;
	}
	public String getAnnenreaksjonbeskrivelse() {
		return annenreaksjonbeskrivelse;
	}
	public void setAnnenreaksjonbeskrivelse(String annenreaksjonbeskrivelse) {
		this.annenreaksjonbeskrivelse = annenreaksjonbeskrivelse;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
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