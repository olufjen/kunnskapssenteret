package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * Denne klassen beskriver blodprodukt som er benyttet i overføringen.
 * 
 */

public abstract class AbstractBlodprodukt extends AbstractModel implements Blodprodukt {

	private long blodProduktId;
	/**
	 * Hvilken blodtype som er benyttet
	 */
	private String blodtype = null;
	/**
	 * Alder angis som en tappedato
	 */
	private int alderprodukt = 0;
	/**
	 * Må beskrives
	 */
	private String tappetype = null;
	/**
	 * Må beskrives
	 */
	private String blodprodukt = null;
	/**
	 * Må beskrives
	 */
	private String produktetsegenskap = null;
	
	/**
	 * Antall poser som er benyttet ved transfusjonen.
	 */
	private int antallEnheter = 0;
/**
* Inneholder navn på input felt i skjermbildet	
*/
	protected Map<String,String> blodproduktFields; // Inneholder blodproduktene
	protected  Map<String,String> antallFields; // Inneholder antall poser
	protected  Map<String,String> egenskaperFields; // Inneholder egenskaper
	
	protected String[]keys;	

	
	public int getAntallEnheter() {
		return antallEnheter;
	}
	public void setAntallEnheter(int antallEnheter) {
		if (antallEnheter == -1){
			String aProd = null;
			for (int i=6;i<11;i++){
				aProd = antallFields.get(keys[i]);
				if (aProd != null){
					antallEnheter = Integer.parseInt(aProd);
					break;
				}
			}
		}
		this.antallEnheter = antallEnheter;
	}
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
		if (blodprodukt == null){
			String aProd = null;
			for (int i=0;i<6;i++){
				aProd = blodproduktFields.get(keys[i]);
				if (aProd != null){
					blodprodukt = aProd;
					break;
				}
			}
		}
		this.blodprodukt = blodprodukt;
	}
	public String getProduktetsegenskap() {
		return produktetsegenskap;
	}
	public void setProduktetsegenskap(String produktetsegenskap) {
		if (produktetsegenskap == null){
			String aProd = null;
			for (int i=11;i<=17;i++){
				aProd = egenskaperFields.get(keys[i]);
				if (aProd != null){
					produktetsegenskap = aProd;
					break;
				}
			}
		}
		
		this.produktetsegenskap = produktetsegenskap;
	}
	public Map<String, String> getBlodproduktFields() {
		return blodproduktFields;
	}
	public void setBlodproduktFields(Map<String, String> blodproduktFields) {
		this.blodproduktFields = blodproduktFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	
}