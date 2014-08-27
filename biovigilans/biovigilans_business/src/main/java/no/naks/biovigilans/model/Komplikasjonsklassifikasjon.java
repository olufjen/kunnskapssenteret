package no.naks.biovigilans.model;

import java.util.Map;

public interface Komplikasjonsklassifikasjon {

	public String getKlassifikasjon();
	public void setKlassifikasjon(String klassifikasjon);
	public String getKlassifikasjonsbeskrivelse();
	public void setKlassifikasjonsbeskrivelse(String klassifikasjonsbeskrivelse);
	public Long getKlassifikasjonsid();
	public void setKlassifikasjonsid(Long klassifikasjonsid);
	
	public Long getMeldeid();
	public void setMeldeid(Long meldeid);	
	
	public Map<String,String> getKomplikasjonklassifikasjonFields();
	public void setKomplikasjonklassifikasjonFields(
			Map<String,String> komplikasjonklassifikasjonFields);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public void setkomplikasjonklassifikasjonFieldsMaps(String[]userFields);
	public void saveField(String userField, String userValue);
	public void setParams();
	public int[] getTypes();
	public Object[] getParams();
	public int[] getUtypes();
	
}
