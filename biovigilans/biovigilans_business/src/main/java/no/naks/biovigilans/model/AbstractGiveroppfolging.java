package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer oppf�lging av Giver ved en Giverkomplikasjon.
 * 
 */

public abstract class AbstractGiveroppfolging extends AbstractModel implements Giveroppfolging{

	/**
	 * Denne klassifikasjonen kan ha flere ulike verdier:
	 * - Ingen videre oppf�lging
	 * - Avregistering
	 * - Sperring
	 * - annet??
	 */
	private String klassifikasjongiveroppfolging;
	/**
	 * Beskrivelse av oppf�lgingen (klartekst).
	 */
	private String giveroppfolgingbeskrivelse;
	/**
	 * Giver er sperret frem til en gitt dato
	 */
	private String avregistering;
	
	protected Map<String,String> giveroppfolgingFields;
	protected String[]keys;
	
	public String getKlassifikasjongiveroppfolging() {
		return klassifikasjongiveroppfolging;
	}
	public void setKlassifikasjongiveroppfolging(
			String klassifikasjongiveroppfolging) {
		this.klassifikasjongiveroppfolging = klassifikasjongiveroppfolging;
	}
	public String getGiveroppfolgingbeskrivelse() {
		return giveroppfolgingbeskrivelse;
	}
	public void setGiveroppfolgingbeskrivelse(String giveroppfolgingbeskrivelse) {
		this.giveroppfolgingbeskrivelse = giveroppfolgingbeskrivelse;
	}
	public String getAvregistering() {
		return avregistering;
	}
	public void setAvregistering(String avregistering) {
		this.avregistering = avregistering;
	}
	public Map<String, String> getGiveroppfolgingFields() {
		return giveroppfolgingFields;
	}
	public void setGiveroppfolgingFields(Map<String, String> giveroppfolgingFields) {
		this.giveroppfolgingFields = giveroppfolgingFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	
}