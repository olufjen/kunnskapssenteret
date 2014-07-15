package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * På bakrunn av symptomene gjøres det flere utredninger
 * 
 */

public abstract class AbstractUtredning extends AbstractModel implements Utredning{

	private Long utredningId;
	/**
	 * Klassifikasjon av utredning, hentes fra AbstractSykdom?
	 */
	private String utredningsklassifikasjon;
	/**
	 * Hva består utredningen i? (klartekst)
	 */
	private String utredningbeskrivelse;
	
	/**
	 * Blodtypeserologisk tyder p� HTR (tekstlig beskrivelse)
	 */
	private java.lang.String blodtypeserologisk;
	/**
	 * Hemolyseparametrene er positive  (tekstlig beskrivelse)
	 */
	private java.lang.String hemolyseparameter;
	/**
	 * lga-mange/anti-lga  (tekstlig beskrivelse)
	 */
	private java.lang.String lga;
	/**
	 * Dyrking av pose utf�rt
	 */
	private java.lang.String posedyrking;
	/**
	 * Dersom dyrking av pose er positiv  (tekstlig beskrivelse)
	 */
	private java.lang.String posedyrkingpositiv;
	
	public Long getUtredningId() {
		return utredningId;
	}
	public void setUtredningId(Long utredningId) {
		this.utredningId = utredningId;
	}
	public String getUtredningsklassifikasjon() {
		return utredningsklassifikasjon;
	}
	public void setUtredningsklassifikasjon(String utredningsklassifikasjon) {
		this.utredningsklassifikasjon = utredningsklassifikasjon;
	}
	public String getUtredningbeskrivelse() {
		return utredningbeskrivelse;
	}
	public void setUtredningbeskrivelse(String utredningbeskrivelse) {
		this.utredningbeskrivelse = utredningbeskrivelse;
	}
	
	
}