package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;

/**
 * AbstractSykdom beskriver sykdom til pasient som er av betydning for komplikasjonen
 * 
 */

public abstract class AbstractSykdom extends AbstractModel implements Sykdom{

	private long sykdomId;
	/**
	 * Navn på sykdom
	 */
	private String sykdomnsnavn;
	/**
	 * Beskriver symptomer ved denne sykdommen
	 */
	private String symptomer;
	/**
	 * ICD10 diagnosekode
	 */
	private String diagnosekode;
	/**
	 * Inneholder navn på input felt i skjermbildet	
	 */	
	protected Map<String,String> sykdomFields;
	
	protected String[]keys;
	
	public long getSykdomId() {
		return sykdomId;
	}
	public void setSykdomId(long sykdomId) {
		this.sykdomId = sykdomId;
	}
	public String getSykdomnsnavn() {
		return sykdomnsnavn;
	}
	public void setSykdomnsnavn(String sykdmnsnavn) {
		if (sykdmnsnavn == null){
			sykdmnsnavn = sykdomFields.get(keys[0]);
		}
		this.sykdomnsnavn = sykdmnsnavn;
	}
	public String getSymptomer() {
		return symptomer;
	}
	public void setSymptomer(String symptomer) {
		this.symptomer = symptomer;
	}
	public String getDiagnosekode() {
		return diagnosekode;
	}
	public void setDiagnosekode(String diagnosekode) {
		this.diagnosekode = diagnosekode;
	}
	public Map<String, String> getSykdomFields() {
		return sykdomFields;
	}
	public void setSykdomFields(Map<String, String> sykdomFields) {
		this.sykdomFields = sykdomFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	
}