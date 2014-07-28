package no.naks.biovigilans.model;

import java.util.Map;

/**
 * Representerer antistoff for pasienten
 * @author olj
 *
 */
public interface Antistoff {

	public String getAntistoffbeskrivelse();
	public void setAntistoffbeskrivelse(String antistoffbeskrivelse);
	public String getAntistoffKode();
	public void setAntistoffKode(String antistoffKode);
	public long getAntistoffId();
	public void setAntistoffId(long antistoffId);
	public Map<String, String> getAntistoffFields();
	public void setAntistoffFields(Map<String, String> antistoffFields);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public void setantistofffieldMaps(String[]userFields);
	public void saveField(String userField, String userValue);
}
