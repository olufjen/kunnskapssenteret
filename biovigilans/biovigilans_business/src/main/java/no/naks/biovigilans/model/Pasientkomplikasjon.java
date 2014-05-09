package no.naks.biovigilans.model;

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
}
