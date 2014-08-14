package no.naks.biovigilans.model;

import java.util.Map;

public interface Giver {

	
	public long getGiverid();
	public void setGiverid(long giverid);
	public String getKjonn();
	public void setKjonn(String kjonn);
	public String getAlder();
	public void setAlder(String alder);
	public String getVekt();
	public void setVekt(String vekt);
	public String getGivererfaring();
	public void setGivererfaring(String givererfaring);
	public String getTidligerekomlikasjonjanei();
	public void setTidligerekomlikasjonjanei(String tidligerekomlikasjonjanei);
	public String getTidligerekomplikasjonforklaring();
	public void setTidligerekomplikasjonforklaring(
			String tidligerekomplikasjonforklaring);
	public String getGivererfaringaferese();
	public void setGivererfaringaferese(String givererfaringaferese);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public Map<String, String> getGiverFields();
	public void setGiverFields(Map<String, String> giverFields);
	public void setGiverfieldMaps(String[]userFields);
	public void saveField(String userField, String userValue);
	public void setParams();
	public int[] getTypes();
	public Object[] getParams();
	public int[] getUtypes();
	
}
