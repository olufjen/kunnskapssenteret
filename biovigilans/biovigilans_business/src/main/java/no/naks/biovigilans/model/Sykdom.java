package no.naks.biovigilans.model;

import java.util.Map;

public interface Sykdom {

	public long getSykdomId();
	public void setSykdomId(long sykdomId);
	public String getSykdomnsnavn();
	public void setSykdomnsnavn(String sykdomnsnavn);
	public String getSymptomer();
	public void setSymptomer(String symptomer);
	public String getDiagnosekode();
	public void setDiagnosekode(String diagnosekode);
	public void setSykdomFields(Map<String, String> sykdomFields);
	public void setsykdomfieldMaps(String[]userFields);
	public void saveField(String userField,String userValue);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public void saveSykdom();
		
}
