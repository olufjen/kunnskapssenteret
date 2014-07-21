package no.naks.biovigilans.model;

import java.util.Map;

public interface Pasientkomplikasjon {

	public String getKlassifikasjon();
	public void setKlassifikasjon(String klassifikasjon);
	public int getTidfrapabegynttrasfusjontilkomplikasjon();
	public void setTidfrapabegynttrasfusjontilkomplikasjon(
			int tidfrapabegynttrasfusjontilkomplikasjon);
	public String getAlvorlighetsgrad();
	public void setAlvorlighetsgrad(String alvorlighetsgrad);
	public String getKliniskresultat();
	public void setKliniskresultat(String kliniskresultat);
	public String getArsakssammenheng();
	public void setArsakssammenheng(String arsakssammenheng);
	public Map<String, Symptomer> getSymptomer();

	public void setSymptomer(Map<String, Symptomer> symptomer);

	public Map<String, Komplikasjonsklassifikasjon> getKomplikasjonsKlassifikasjoner();

	public void setKomplikasjonsKlassifikasjoner(
			Map<String, Komplikasjonsklassifikasjon> komplikasjonsKlassifikasjoner);

	public Map<String, String> getKomplikasjonsFields();

	public void setKomplikasjonsFields(Map<String, String> komplikasjonsFields);

	public String[] getKeys();

	public void setKeys(String[] keys);
}
