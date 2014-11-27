package no.naks.emok.model;

public interface IPafyllingsmelding {

	public abstract String getKlassifikasjonskode();

	public abstract void setKlassifikasjonskode(String klassifikasjonskode);

	public abstract String getSaksbehandlersvurdering();

	public abstract void setSaksbehandlersvurdering(
			String saksbehandlersvurdering);

	public abstract String getTiltak();

	public abstract void setTiltak(String tiltak);

	public abstract String getSaksstikkord();

	public abstract void setSaksstikkord(String saksstikkord);

	public abstract String getTilleggsbeskrivelse();

	public abstract void setTilleggsbeskrivelse(String tilleggsbeskrivelse);

	public abstract String getStatusmv();

	public abstract void setStatusmv(String statusmv);

	public abstract String getForslagtiltak();

	public abstract void setForslagtiltak(String forslagtiltak);

	public abstract String getUtfortestrakstiltak();

	public abstract void setUtfortestrakstiltak(String utfortestrakstiltak);

	public abstract String getArsaksbeskrivelse();

	public abstract void setArsaksbeskrivelse(String arsaksbeskrivelse);

	public abstract String getKonsekvenser();

	public abstract void setKonsekvenser(String konsekvenser);

}