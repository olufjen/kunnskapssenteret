package no.naks.biovigilans.model;

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

	
	
}