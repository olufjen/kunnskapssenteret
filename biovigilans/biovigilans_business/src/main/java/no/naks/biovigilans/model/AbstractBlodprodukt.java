package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * Denne klassen beskriver blodprodukt som er benyttet i overføringen.
 * 
 */

public abstract class AbstractBlodprodukt extends AbstractModel implements Blodprodukt {

	private long blodProduktId;
	/**
	 * Hvilken bodtype som er benyttet
	 */
	private String blodtype;
	/**
	 * Alder angis som en tappedato
	 */
	private int alderprodukt;
	/**
	 * Må beskrives
	 */
	private String tappetype;
	/**
	 * Må beskrives
	 */
	private String blodprodukt;
	/**
	 * Må beskrives
	 */
	private String produktetsegenskap;
	
	
	public long getBlodProduktId() {
		return blodProduktId;
	}
	public void setBlodProduktId(long blodProduktId) {
		this.blodProduktId = blodProduktId;
	}
	public String getBlodtype() {
		return blodtype;
	}
	public void setBlodtype(String blodtype) {
		this.blodtype = blodtype;
	}
	public int getAlderprodukt() {
		return alderprodukt;
	}
	public void setAlderprodukt(int alderprodukt) {
		this.alderprodukt = alderprodukt;
	}
	public String getTappetype() {
		return tappetype;
	}
	public void setTappetype(String tappetype) {
		this.tappetype = tappetype;
	}
	public String getBlodprodukt() {
		return blodprodukt;
	}
	public void setBlodprodukt(String blodprodukt) {
		this.blodprodukt = blodprodukt;
	}
	public String getProduktetsegenskap() {
		return produktetsegenskap;
	}
	public void setProduktetsegenskap(String produktetsegenskap) {
		this.produktetsegenskap = produktetsegenskap;
	}
	
}