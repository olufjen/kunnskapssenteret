package no.naks.biovigilans.model;

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
	
	public long getSykdomId() {
		return sykdomId;
	}
	public void setSykdomId(long sykdomId) {
		this.sykdomId = sykdomId;
	}
	public String getSykdomnsnavn() {
		return sykdomnsnavn;
	}
	public void setSykdomnsnavn(String sykdomnsnavn) {
		this.sykdomnsnavn = sykdomnsnavn;
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
	
	
}