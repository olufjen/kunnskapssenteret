

/**
 * 
 */
package no.naks.emok.model;

import java.util.Date;

/**
 * Avslutningsmelding fra HF som forteller at HF har avsluttet denne saken
 */
public class MVavslutningsmelding extends no.naks.emok.model.Leveransemelding implements IMVavslutningsmelding{

	/**
	 * Virksomhetens klassifisering(er), som saksbehandler har gjort
	 * Tallkode + medfølgende tekstlig beskrivelse
	 */
	private String klassifikasjonskode;
	/**
	 * Dato og klokkeslett for når saken ble avsluttet.
	 * dd.mm.yyyy hh.mm
	 */
	private Date datoavsluttet;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMVavslutningsmelding#getKlassifikasjonskode()
	 */
	public String getKlassifikasjonskode() {
		return klassifikasjonskode;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMVavslutningsmelding#setKlassifikasjonskode(java.lang.String)
	 */
	public void setKlassifikasjonskode(String klassifikasjonskode) {
		this.klassifikasjonskode = klassifikasjonskode;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMVavslutningsmelding#getDatoavsluttet()
	 */
	public Date getDatoavsluttet() {
		return datoavsluttet;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMVavslutningsmelding#setDatoavsluttet(java.util.Date)
	 */
	public void setDatoavsluttet(Date datoavsluttet) {
		this.datoavsluttet = datoavsluttet;
	}
	
}