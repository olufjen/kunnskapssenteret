package no.naks.biovigilans.model;


/**
 * Vigilansmelding som annen komplikasjon
 */

public class AnnenkomplikasjonImpl extends AbstractVigilansmelding implements Vigilansmelding{

	/**
	 * Klassifikasjon av hendelsen (beskrivelse)
	 */
	private String klassifikasjon;
	/**
	 * Klassifikasjonskode
	 */
	private String Klassifikasjonkode;
	/**
	 * Beskrivelse av hendelsen
	 */
	private String komplikasjonbeskrivelse;
	/**
	 * Hva slags hendelse er dette?
	 */
	private String komplikasjondefinisjon;
	/**
	 * Årsak til avviket
	 */
	private String avvikarsak;
	/**
	 * Under hvilken prosess skjedde hendelsen?
	 */
	private String hovedprosess;
	/**
	 * Gjennomførte eller planlagte tiltak
	 */
	private String tiltak;
	/**
	 * Kommentarer til hendelsen
	 */
	private String kommentar;
	/**
	 * Hvordan ble hendelsen oppdaget
	 */
	private String oppdaget;
	public String getKlassifikasjon() {
		return klassifikasjon;
	}
	public void setKlassifikasjon(String klassifikasjon) {
		this.klassifikasjon = klassifikasjon;
	}
	public String getKlassifikasjonkode() {
		return Klassifikasjonkode;
	}
	public void setKlassifikasjonkode(String klassifikasjonkode) {
		Klassifikasjonkode = klassifikasjonkode;
	}
	public String getKomplikasjonbeskrivelse() {
		return komplikasjonbeskrivelse;
	}
	public void setKomplikasjonbeskrivelse(String komplikasjonbeskrivelse) {
		this.komplikasjonbeskrivelse = komplikasjonbeskrivelse;
	}
	public String getKomplikasjondefinisjon() {
		return komplikasjondefinisjon;
	}
	public void setKomplikasjondefinisjon(String komplikasjondefinisjon) {
		this.komplikasjondefinisjon = komplikasjondefinisjon;
	}
	public String getAvvikarsak() {
		return avvikarsak;
	}
	public void setAvvikarsak(String avvikarsak) {
		this.avvikarsak = avvikarsak;
	}
	public String getHovedprosess() {
		return hovedprosess;
	}
	public void setHovedprosess(String hovedprosess) {
		this.hovedprosess = hovedprosess;
	}
	public String getTiltak() {
		return tiltak;
	}
	public void setTiltak(String tiltak) {
		this.tiltak = tiltak;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public String getOppdaget() {
		return oppdaget;
	}
	public void setOppdaget(String oppdaget) {
		this.oppdaget = oppdaget;
	}
	
	
}

