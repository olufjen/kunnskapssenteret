package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * En PasientkomplikasjonImpl viser flere ulike symptomer.
 * 
 */

public abstract class AbstractSymptomer extends AbstractModel implements Symptomer{

	private Long symptomId;
	private Long meldeId; 
	/**
	 * Klassifikasjon av komplikasjon, hentes fra AbstractSykdom?
	 */
	private String symptomklassifikasjon;
	/**
	 * Her beskrives symptomene i klartekst
	 */
	private String symptombeskrivelse;
/**
 * Inneholder navn på input felt i skjermbildet	
 */
	protected Map<String,String> symptomerFields;
	
	protected String[]keys;
	

	public Long getMeldeId() {
		return meldeId;
	}
	public void setMeldeId(Long meldeId) {
		this.meldeId = meldeId;
	}
	public Long getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(Long symptomId) {
		this.symptomId = symptomId;
	}
	public String getSymptomklassifikasjon() {
		return symptomklassifikasjon;
	}
	public void setSymptomklassifikasjon(String symptomklassifikasjon) {
		this.symptomklassifikasjon = symptomklassifikasjon;
	}
	public String getSymptombeskrivelse() {
		return symptombeskrivelse;
	}
	public void setSymptombeskrivelse(String symptombeskrivelse) {
		this.symptombeskrivelse = symptombeskrivelse;
	}
	public Map<String, String> getSymptomerFields() {
		return symptomerFields;
	}
	public void setSymptomerFields(Map<String, String> symptomerFields) {
		this.symptomerFields = symptomerFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	
	
}