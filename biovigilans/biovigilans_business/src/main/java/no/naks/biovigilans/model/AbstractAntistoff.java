package no.naks.biovigilans.model;

import java.util.Map;


/**
 * Viser type antistoff for pasienten.
 * 
 */

public abstract class AbstractAntistoff{

	private java.lang.String antistoffbeskrivelse;
	private java.lang.String antistoffKode;
	private long antistoffId;
	protected Map<String,String> antistoffFields;
	protected String[] keys;
	public java.lang.String getAntistoffbeskrivelse() {
		return antistoffbeskrivelse;
	}
	public void setAntistoffbeskrivelse(java.lang.String antistoffbeskrivelse) {
		this.antistoffbeskrivelse = antistoffbeskrivelse;
	}
	public java.lang.String getAntistoffKode() {
		return antistoffKode;
	}
	public void setAntistoffKode(java.lang.String antistoffKode) {
		this.antistoffKode = antistoffKode;
	}
	public long getAntistoffId() {
		return antistoffId;
	}
	public void setAntistoffId(long antistoffId) {
		this.antistoffId = antistoffId;
	}
	public Map<String, String> getAntistoffFields() {
		return antistoffFields;
	}
	public void setAntistoffFields(Map<String, String> antistoffFields) {
		this.antistoffFields = antistoffFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	
}