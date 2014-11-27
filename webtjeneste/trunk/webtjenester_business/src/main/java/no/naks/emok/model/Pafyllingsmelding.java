

/**
 * 
 */
package no.naks.emok.model;

/**
 * Denne meldingstypen sendes når en av attributtene endres.
 * Dette skjer automatisk.
 */
public class Pafyllingsmelding extends no.naks.emok.model.Leveransemelding implements IPafyllingsmelding{

	/**
	 * Virksomhetens klassifisering(er), som saksbehandler har gjort
	 * Tallkode + medfølgende tekstlig beskrivelse
	 */
	private String klassifikasjonskode;
	/**
	 * Hentes fra saksbehandlingsforløpet. Saksansvarlig sin vurdering.
	 */
	private String saksbehandlersvurdering;
	/**
	 * Tiltak som virksomheten har planlagt.
	 * tiltak: vi kan ha mange tiltak til et avvik. Antar at vi vil slå sammen alt tekstinnhold fra tiltakene til en tekststreng, med linjeskift og tiltakstittel mellom. Men det at det er et enkelt tekstfelt vil gjøre at Kunnskapssenteret ikke ser all relevant informasjon om tiltakene (status, frist, ansvarlig, evt evaluering).
	 */
	private String tiltak;
	/**
	 * Stikkord som virksomheten har gitt saken
	 * saksstikkord: Dette har vi ikke (tror dere har ideen fra Synergi…) Vi har til gjengjeld et "Tittel"-felt som gjør samme nytten.
	 */
	private String saksstikkord;
	/**
	 * Fritekst annen informasjon
	 */
	private String tilleggsbeskrivelse;
	/**
	 * Dette feltet sier status om saken er avsluttet fra MV sin side
	 */
	private String statusmv;
	/**
	 * Beskriver tiltak/endringer som du mener kan/bør gjøres for at noe tilsvarende ikke skal skje igjen. (ikke obligatorisk)
	 */
	private String forslagtiltak;
	/**
	 * Hva er ev. gjort for å begrense den ev. skaden på pasienten/personen. (For eks. gjennomførte strakstiltak)
	 * (Ikke obligatorisk)
	 */
	private String utfortestrakstiltak;
	/**
	 * Hvorfor tror du hendelsen skjedde. (Ikke obligatorisk)
	 */
	private String arsaksbeskrivelse;
	/**
	 * Hvilke umiddelbare konsekvenser for pasienten fikk dette
	 * Finnes det en klassifikasjonskode for dette, så ønsker vi det.
	 * (Kode + tekst)
	 */
	private String konsekvenser;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getKlassifikasjonskode()
	 */
	public String getKlassifikasjonskode() {
		return klassifikasjonskode;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setKlassifikasjonskode(java.lang.String)
	 */
	public void setKlassifikasjonskode(String klassifikasjonskode) {
		this.klassifikasjonskode = klassifikasjonskode;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getSaksbehandlersvurdering()
	 */
	public String getSaksbehandlersvurdering() {
		return saksbehandlersvurdering;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setSaksbehandlersvurdering(java.lang.String)
	 */
	public void setSaksbehandlersvurdering(String saksbehandlersvurdering) {
		this.saksbehandlersvurdering = saksbehandlersvurdering;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getTiltak()
	 */
	public String getTiltak() {
		return tiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setTiltak(java.lang.String)
	 */
	public void setTiltak(String tiltak) {
		this.tiltak = tiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getSaksstikkord()
	 */
	public String getSaksstikkord() {
		return saksstikkord;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setSaksstikkord(java.lang.String)
	 */
	public void setSaksstikkord(String saksstikkord) {
		this.saksstikkord = saksstikkord;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getTilleggsbeskrivelse()
	 */
	public String getTilleggsbeskrivelse() {
		return tilleggsbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setTilleggsbeskrivelse(java.lang.String)
	 */
	public void setTilleggsbeskrivelse(String tilleggsbeskrivelse) {
		this.tilleggsbeskrivelse = tilleggsbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getStatusmv()
	 */
	public String getStatusmv() {
		return statusmv;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setStatusmv(java.lang.String)
	 */
	public void setStatusmv(String statusmv) {
		this.statusmv = statusmv;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getForslagtiltak()
	 */
	public String getForslagtiltak() {
		return forslagtiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setForslagtiltak(java.lang.String)
	 */
	public void setForslagtiltak(String forslagtiltak) {
		this.forslagtiltak = forslagtiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getUtfortestrakstiltak()
	 */
	public String getUtfortestrakstiltak() {
		return utfortestrakstiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setUtfortestrakstiltak(java.lang.String)
	 */
	public void setUtfortestrakstiltak(String utfortestrakstiltak) {
		this.utfortestrakstiltak = utfortestrakstiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getArsaksbeskrivelse()
	 */
	public String getArsaksbeskrivelse() {
		return arsaksbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setArsaksbeskrivelse(java.lang.String)
	 */
	public void setArsaksbeskrivelse(String arsaksbeskrivelse) {
		this.arsaksbeskrivelse = arsaksbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#getKonsekvenser()
	 */
	public String getKonsekvenser() {
		return konsekvenser;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IPafyllingsmelding#setKonsekvenser(java.lang.String)
	 */
	public void setKonsekvenser(String konsekvenser) {
		this.konsekvenser = konsekvenser;
	}
	
}