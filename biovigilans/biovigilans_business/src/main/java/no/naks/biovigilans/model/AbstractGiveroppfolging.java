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
	private Long giveroppfolgingId;
	private Long meldeid;
	protected Map<String,String> giveroppfolgingFields;
	protected String[]keys;
	protected Map formMap; // Inneholder brukers input verdier fra skjermbildet
	
	
	public Long getGiveroppfolgingId() {
		return giveroppfolgingId;
	}
	public void setGiveroppfolgingId(Long giveroppfolgingId) {
		this.giveroppfolgingId = giveroppfolgingId;
	}
	
	public Long getMeldeid() {
		return meldeid;
	}
	public void setMeldeid(Long meldeid) {
		this.meldeid = meldeid;
	}
	public String getKlassifikasjongiveroppfolging() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-annenreaksjon";
		klassifikasjongiveroppfolging = userEntries.get(field);
		if (klassifikasjongiveroppfolging == null){
			klassifikasjongiveroppfolging = "";
		}
		return klassifikasjongiveroppfolging;
	}
	public void setKlassifikasjongiveroppfolging(
			String klassifikasjongiveroppfolging) {
		/*if(klassifikasjongiveroppfolging == null){
			klassifikasjongiveroppfolging = giveroppfolgingFields.get(keys[0]);
		}*/
		this.klassifikasjongiveroppfolging = klassifikasjongiveroppfolging;
	}
	public String getGiveroppfolgingbeskrivelse() {
		
		Map<String,String> userEntries = getFormMap();
		String field1 = "tab-alvorlighetgrad";
		String field2="tab-forbedringstiltak";
		String alvorlighetgrad = userEntries.get(field1);
		String forbedringstiltak = userEntries.get(field2);
		if(alvorlighetgrad == null){
			giveroppfolgingbeskrivelse="";
		}else{
			giveroppfolgingbeskrivelse = alvorlighetgrad + "; ";
		}
		if(forbedringstiltak!=null){
			giveroppfolgingbeskrivelse = giveroppfolgingbeskrivelse + forbedringstiltak;
		}
		return giveroppfolgingbeskrivelse;
	}
	public void setGiveroppfolgingbeskrivelse(String giveroppfolgingbeskrivelse) {
		/*if(giveroppfolgingbeskrivelse == null){
			String value="";
			if(giveroppfolgingFields.get(keys[1])!=null){
				value = giveroppfolgingFields.get(keys[1]) + " ";
			}
			if(giveroppfolgingFields.get(keys[2]) != null){
				value= value + giveroppfolgingFields.get(keys[2]);
			}
			giveroppfolgingbeskrivelse = value;
		}*/
		this.giveroppfolgingbeskrivelse = giveroppfolgingbeskrivelse;
	}
	public String getAvregistering() {
		Map<String,String> userEntries = getFormMap();
		String field1 = "tab-avreg";
		String field2 ="tab-onske";
		String avreg = userEntries.get(field1);
		String onske = userEntries.get(field2);
		if(avreg != null){
			avregistering = avreg;
		}else{
			avregistering = "";
		}
		
		if (onske != null && avregistering.equalsIgnoreCase("avregistrert-Ja") ){
			avregistering = avregistering +"; " + onske;
		}
		return avregistering;
	}
	public void setAvregistering(String avregistering) {
		/*if(avregistering == null){
			avregistering  = giveroppfolgingFields.get(keys[3]);
			if(giveroppfolgingFields.get(keys[4])!= null && avregistering.equalsIgnoreCase("avregistrert-Ja")){
				avregistering = avregistering +"; " + giveroppfolgingFields.get(keys[4]);
			}
		}*/
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
	public Map getFormMap() {
		return formMap;
	}
	public void setFormMap(Map formMap) {
		this.formMap = formMap;
	}
	
	
}