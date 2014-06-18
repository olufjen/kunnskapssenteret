package no.naks.biovigilans.model;

import java.util.Map;

public interface Pasient {
	public Long getPasient_Id();
	public void setPasient_Id(Long pasient_Id);
	public String getKjonn();
	public void setKjonn(String kjonn);
	public String getAldersGruppe();
	public void setAldersGruppe(String aldersGruppe);
	public String[] getAntiStoff();
	public void setAntiStoff(String antiStoff[]);
	public String getInneliggendePoli();
	public void setInneliggendePoli(String inneliggendePoli);
	public String getAvdeling();
	public void setAvdeling(String avdeling);
	public Map<String, String> getPatientFields();

	public void setPatientFields(Map<String, String> patientFields);

	/**
	 * setPatientfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setPatientfieldMaps(String[]userFields);
	/**
	 * saveField
	 * Denne rutinen lagrer skrembildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField,String userValue);
	public void savetoPatient();
	public String[] getKeys();
	public void setKeys(String[] keys);
	
}
