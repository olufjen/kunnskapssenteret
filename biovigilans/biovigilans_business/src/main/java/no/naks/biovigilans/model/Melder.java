package no.naks.biovigilans.model;

import java.util.Map;

public interface Melder {

	
	public Long getMelderId();
	public void setMelderId(Long melderId);
	public String getMeldernavn();
	public void setMeldernavn(String meldernavn);
	public String getMelderepost();
	public void setMelderepost(String melderepost);
	public String getMeldertlf();
	public void setMeldertlf(String meldertlf);
	public Map<String, String> getMelderFields();
	public void setMelderFields(Map<String, String> melderFields);
	public String[] getKeys();
	public void setKeys(String[] keys);
	
}
