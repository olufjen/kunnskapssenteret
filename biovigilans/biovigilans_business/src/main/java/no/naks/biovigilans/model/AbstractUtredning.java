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
	private String blodtypeserologisk;
	/**
	 * Hemolyseparametrene er positive  (tekstlig beskrivelse)
	 */
	private String hemolyseparameter;
	/**
	 * lga-mange/anti-lga  (tekstlig beskrivelse)
	 */
	private String lga;
	/**
	 * Dyrking av pose utf�rt
	 */
	private String posedyrking;
	/**
	 * Dersom dyrking av pose er positiv  (tekstlig beskrivelse)
	 */
	private String posedyrkingpositiv;
	
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
	
	public String getBlodtypeserologisk() {
		return blodtypeserologisk;
	}
	public void setBlodtypeserologisk(String blodtypeserologisk) {
		this.blodtypeserologisk = blodtypeserologisk;
	}
	public String getHemolyseparameter() {
		return hemolyseparameter;
	}
	public void setHemolyseparameter(String hemolyseparameter) {
		this.hemolyseparameter = hemolyseparameter;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lga) {
		this.lga = lga;
	}
	public String getPosedyrking() {
		return posedyrking;
	}
	public void setPosedyrking(String posedyrking) {
		this.posedyrking = posedyrking;
	}
	public String getPosedyrkingpositiv() {
		return posedyrkingpositiv;
	}
	public void setPosedyrkingpositiv(String posedyrkingpositiv) {
		this.posedyrkingpositiv = posedyrkingpositiv;
	}
	
	
}