package no.naks.emok.model;

import java.util.Date;

public interface IMVavslutningsmelding {

	public abstract String getKlassifikasjonskode();

	public abstract void setKlassifikasjonskode(String klassifikasjonskode);

	public abstract Date getDatoavsluttet();

	public abstract void setDatoavsluttet(Date datoavsluttet);

}