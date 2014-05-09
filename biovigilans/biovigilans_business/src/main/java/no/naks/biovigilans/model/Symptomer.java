package no.naks.biovigilans.model;

public interface Symptomer {

	public long getSymptomId();
	public void setSymptomId(long symptomId);
	public String getSymptomklassifikasjon();
	public void setSymptomklassifikasjon(String symptomklassifikasjon);
	public String getSymptombeskrivelse();
	public void setSymptombeskrivelse(String symptombeskrivelse);
}
