package no.naks.biovigilans.model;

import java.util.Map;

public interface Blodprodukt {

	
	public long getBlodProduktId();
	
	public int getAntallEnheter();
	public void setAntallEnheter(int antallEnheter);
	public void setBlodProduktId(long blodProduktId);
	public String getBlodtype();
	public void setBlodtype(String blodtype);
	public int getAlderprodukt();
	public void setAlderprodukt(int alderprodukt);
	public String getTappetype();
	public void setTappetype(String tappetype);
	public String getBlodprodukt();
	public void setBlodprodukt(String blodprodukt);
	public String getProduktetsegenskap();
	public void setProduktetsegenskap(String produktetsegenskap);
	public void setBlodProduktfieldMaps(String[]userFields);
	public void saveField(String userField,String userValue);
	public void setEgenskaperfieldMaps(String[]userFields);
	public void setKeyvalues();
	
}