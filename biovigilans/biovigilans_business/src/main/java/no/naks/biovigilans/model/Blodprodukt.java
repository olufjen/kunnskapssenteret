package no.naks.biovigilans.model;

import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;

public interface Blodprodukt {

	

	public Long getBlodProduktId();
	public void setBlodProduktId(Long blodProduktId);
	public Long getTransfusjonsId();
	public void setTransfusjonsId(Long transfusjonsId);
	
	public int getAntallEnheter();
	public void setAntallEnheter(int antallEnheter);
	
	public String getBlodtype();
	public void setBlodtype(String blodtype);
	
	public Date getAlderProdukt();
	public void setAlderProdukt(Date alderProdukt);
	public String getAldersProdukt();
	public void setAldersProdukt(String aldersProdukt);
	public int getAntallenheterpakke();
	public void setAntallenheterpakke(int antallenhetrpakke);
	public String getTappetype();
	public void setTappetype(String tappetype);
	public String getBlodprodukt();
	public void setBlodprodukt(String blodprodukt);
	public String getProduktetsegenskap();
	public void setSuspensjon(String suspnsjon);
	public String getSuspensjon();
	
	public int getAntallTromb();
	public void setAntallTromb(int antalTromb);
	public int getAntallPlasma();
	public void setAntallPlasma(int antalPlasma);
	public void saveToBlodprodukt();
	public void produceProduktegenskaper(Produktegenskap egenskap);
	public void setProduktetsegenskap(String produktetsegenskap);

	public Map<String, Produktegenskap> getProduktEgenskaper();

	public void setProduktEgenskaper(Map<String, Produktegenskap> produktEgenskaper);
	public void setBlodProduktfieldMaps(String[]userFields);
	public void saveField(String userField,String userValue);
	public void setEgenskaperfieldMaps(String[]userFields);
	public void setAntallfieldMaps(String[]userFields);
	public void setKeyvalues();
	public void setParams();
	public int[] getTypes();
	public Object[] getParams();
	public int[] getUtypes();
	public DateTime getProduktdato();
	public void setProduktdato(DateTime produktdato);
	public Map<String, String> getAntallFields();
	public void setAntallFields(Map<String, String> antallFields);
	public Map<String, String> getEgenskaperFields();
	public void setEgenskaperFields(Map<String, String> egenskaperFields);
	public Map<String, String> getTappetypeFields();
	public void setTappetypeFields(Map<String, String> tappetypeFields);
	public void distributeValues(String key, String produkt);
	public Map<String, String> getBlodproduktFields();

	public void setBlodproduktFields(Map<String, String> blodproduktFields);
}