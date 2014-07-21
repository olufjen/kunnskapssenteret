package no.naks.biovigilans.model;

import java.util.Map;

public interface Symptomer {

	public long getSymptomId();
	public void setSymptomId(long symptomId);
	public String getSymptomklassifikasjon();
	public void setSymptomklassifikasjon(String symptomklassifikasjon);
	public String getSymptombeskrivelse();
	public void setSymptombeskrivelse(String symptombeskrivelse);
	public Map<String, String> getSymptomerFields();
	public void setSymptomerFields(Map<String, String> symptomerFields);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public void setsymptomerfieldMaps(String[]userFields);
}
