package no.naks.biovigilans.model;

import java.util.Map;

public interface Giverkomplikasjon {
	
	public String getStedforkomplikasjon();

	public void setStedforkomplikasjon(String stedforkomplikasjon);

	public String getTidfratappingtilkompliasjon();

	public void setTidfratappingtilkompliasjon(String tidfratappingtilkompliasjon);

	public String getBehandlingssted();

	public void setBehandlingssted(String behandlingssted);

	public String getTilleggsopplysninger();

	public void setTilleggsopplysninger(String tilleggsopplysninger);

	public String getAlvorlighetsgrad();

	public void setAlvorlighetsgrad(String alvorlighetsgrad);

	public String getKliniskresultat();

	public void setKliniskresultat(String kliniskresultat);

	public String getVarighetkomplikasjon();

	public void setVarighetkomplikasjon(String varighetkomplikasjon);

	public Map<String, String> getKomplikasjonsFields();
	public void setKomplikasjonsFields(Map<String, String> komplikasjonsFields);
	public String[] getKeys();
	public void setKeys(String[] keys);
	public void setGiverkomplicationfieldMaps(String[]userFields);
	public void saveField(String userField, String userValue); 

}
