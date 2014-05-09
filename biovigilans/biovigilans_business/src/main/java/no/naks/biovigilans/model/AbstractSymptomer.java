package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * En PasientkomplikasjonImpl viser flere ulike symptomer.
 * 
 */

public abstract class AbstractSymptomer extends AbstractModel implements Symptomer{

	private long symptomId;
	/**
	 * Klassifikasjon av komplikasjon, hentes fra AbstractSykdom?
	 */
	private String symptomklassifikasjon;
	/**
	 * Her beskrives symptomene i klartekst
	 */
	private String symptombeskrivelse;
	
	public long getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(long symptomId) {
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
	
	
	
}