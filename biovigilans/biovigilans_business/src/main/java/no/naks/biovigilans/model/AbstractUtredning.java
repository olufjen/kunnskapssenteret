package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * På bakrunn av symptomene gjøres det flere utredninger
 * 
 */

public abstract class AbstractUtredning extends AbstractModel implements Utredning{

	private Long utredningId;
	private Long meldeId;
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
	
	protected String[]keys;
	protected Map<String,String> utredningsFields;
	
	public Long getMeldeId() {
		return meldeId;
	}
	public void setMeldeId(Long meldeId) {
		this.meldeId = meldeId;
	}
	public Long getUtredningId() {
		return utredningId;
	}
	public void setUtredningId(Long utredningId) {
		this.utredningId = utredningId;
	}
	public String getUtredningsklassifikasjon() {
		return utredningsklassifikasjon;
	}
	public void setUtredningsklassifikasjon(String utredningsklassfikasjon) {
		if (utredningsklassfikasjon == null){
			String aProd = null;
	
				aProd = utredningsFields.get(keys[3]);
				if (aProd != null){
					utredningsklassfikasjon = aProd;
				
				}
			
		}
		
		this.utredningsklassifikasjon = utredningsklassfikasjon;
	}
	public String getUtredningbeskrivelse() {
		return utredningbeskrivelse;
	}
	public void setUtredningbeskrivelse(String utredningbeskrvelse) {
		if (utredningbeskrvelse == null){
			String aProd = null;
	
				aProd = utredningsFields.get(keys[3]);
				if (aProd != null){
					utredningbeskrvelse = aProd;
				
				}
			
		}
		this.utredningbeskrivelse = utredningbeskrvelse;
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
	public void setHemolyseparameter(String hemolysparameter) {
		if (hemolysparameter == null){
			String aProd = null;
	
				aProd = utredningsFields.get(keys[7]);
				if (aProd != null){
					hemolysparameter = aProd;
				
				}
			
		}
		this.hemolyseparameter = hemolysparameter;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lgaa) {
		if (lgaa == null){
			String aProd = null;
	
				aProd = utredningsFields.get(keys[10]);
				if (aProd != null){
					lgaa = aProd;
				
				}
			
		}
		this.lga = lgaa;
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
	public void setPosedyrkingpositiv(String posedyrkngpositiv) {
		if (posedyrkngpositiv == null){
			String aProd = null;
	
				aProd = utredningsFields.get(keys[14]);
				if (aProd != null){
					posedyrkngpositiv = aProd;
				
				}
			
		}
		this.posedyrkingpositiv = posedyrkngpositiv;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	public Map<String, String> getUtredningsFields() {
		return utredningsFields;
	}
	public void setUtredningsFields(Map<String, String> utredningsFields) {
		this.utredningsFields = utredningsFields;
	}

	
}