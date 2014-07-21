package no.naks.biovigilans.model;

import java.util.Map;

public interface Komplikasjonsklassifikasjon {

	public String getKlassifikasjon();
	public void setKlassifikasjon(String klassifikasjon);
	public String getKlassifikasjonsbeskrivelse();
	public void setKlassifikasjonsbeskrivelse(String klassifikasjonsbeskrivelse);
	public Long getKlassifikasjonsid();
	public void setKlassifikasjonsid(Long klassifikasjonsid);
	
	
	public Map getKomplikasjonklassifikasjonFields();
	public void setKomplikasjonklassifikasjonFields(
			Map komplikasjonklassifikasjonFields);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public void setkomplikasjonklassifikasjonFieldsMaps(String[]userFields);
}
