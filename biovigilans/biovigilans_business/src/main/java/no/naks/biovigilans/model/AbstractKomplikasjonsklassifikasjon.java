package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer en klassifikasjon av pasientkomplikasjonen. En pasientkomplikasjon kan ha flere klassifikasjoner.
 * 
 */

public abstract class AbstractKomplikasjonsklassifikasjon extends AbstractModel implements Komplikasjonsklassifikasjon{

	/**
	 * Klassifikasjon av komplikasjon, hentes fra Sykdom?
	 */
	private String klassifikasjon;
	/**
	 * Beskrivelse av klassifikasjonen til komplikasjonen
	 */
	private String klassifikasjonsbeskrivelse;
	
	private Long klassifikasjonsid;
	private Long meldeid;
	
	protected Map komplikasjonklassifikasjonFields;
	protected String[] keys;
	
	public String getKlassifikasjon() {
		return klassifikasjon;
	}
	public void setKlassifikasjon(String klassifikasjon) {
		this.klassifikasjon = klassifikasjon;
	}
	public String getKlassifikasjonsbeskrivelse() {
		return klassifikasjonsbeskrivelse;
	}
	public void setKlassifikasjonsbeskrivelse(String klassifikasjonsbeskrivelse) {
		this.klassifikasjonsbeskrivelse = klassifikasjonsbeskrivelse;
	}
	public Long getKlassifikasjonsid() {
		return klassifikasjonsid;
	}
	public void setKlassifikasjonsid(Long klassifikasjonsid) {
		this.klassifikasjonsid = klassifikasjonsid;
	}
	
	public Long getMeldeid() {
		return meldeid;
	}
	public void setMeldeid(Long meldeid) {
		this.meldeid = meldeid;
	}
	public Map getKomplikasjonklassifikasjonFields() {
		return komplikasjonklassifikasjonFields;
	}

	public void setKomplikasjonklassifikasjonFields(
			Map komplikasjonklassifikasjonFields) {
		this.komplikasjonklassifikasjonFields = komplikasjonklassifikasjonFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	
	
}