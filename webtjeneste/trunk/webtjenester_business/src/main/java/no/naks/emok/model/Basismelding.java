

/**
 * 
 */
package no.naks.emok.model;

import java.io.Serializable;
import java.util.Date;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.CV;


/**
 * Basismelding er den første meldingen som sendes til Kunnskapssenteret når en ny uønsket pasienthendelse blir registrert
 * Ved behov (endringer) kan denne meldingen sendes flere ganger. 
 */
public class Basismelding extends no.naks.emok.model.Leveransemelding implements IBasismelding,Serializable {

	/**
	 * Fritekst svar på Kunnskapssenterets etterspørring
	 * Dette er felt som må legges til i Avvikssystemene.
	 * svarpaettersporring: Dette er ikke et felt som finnes. Det blir ulogisk i forhold til den interne saksgang om innspill fra Kunnskapssenteret skal avstedkomme svar fra våre ledere i et eget felt for Kunnskapssenteret. Hvis det er slik at evt spm fra Kunnskapssenteret skal understøtte den interne årsaksanalyse og forbedring, må utfallet legges i våre interne standardfelter for vurderinger/konklusjon, tiltak osv. Dette feltet antyder at Kunnskapssenteret legger opp til at saksbehandlingen skal være en kommunikasjon mellom vår saksbehandler og Kunnskapssenteret som to parter med likt eierskap til saken. Slik er det ikke.
	 */
	private boolean svarpaettersporring;
	/**
	 * Sykehus, Sykehusavdeling, post  (laveste enhet) der pasienten var da hendelsen inntraff (obligatorisk)
	 * Dersom det er utfyllende opplysninger om hvor hendelsen inntraff så ønsker vi dette.
	 */
	private String stedforhendelsen;
	/**
	 * Kort beskrivelse av hendelsen, hva, hvordan (OBLIGATORISK)
	 */
	private String hendelsesbeskrivelse;
	/**
	 * Hva er ev. gjort for å begrense den ev. skaden på pasienten/personen. (For eks. gjennomførte strakstiltak)
	 */
	private String utfortestrakstiltak;
	/**
	 * Beskriver tiltak/endringer som du mener kan/bør gjøres for at noe tilsvarende ikke skal skje igjen.
	 */
	private String forslagtiltak;
	/**
	 * Hvorfor tror du hendelsen skjedde.
	 * Finnes det en klassifikasjonskode for dette så ønsker vi det.
	 * (Kode + tekst)
	 */
	private String arsaksbeskrivelse;
	/**
	 * Hvilke umiddelbare faktiske og potensielle konsekvenser for pasienten fikk dette
	 * Finnes det en klassifikasjonskode for dette, så ønsker vi det.
	 * (Kode + tekst)
	 */
	private String konsekvenser;
	/**
	 * Hvordan ble hendelsen oppdaget?
	 */
	private String hvordanoppdaget;
	/**
	 * Fødselsår (NB ikke fødselsdato) Oversettes fra personnummer, hvis mulig
	 */
	private int arfodt;
	/**
	 * Pasientens kjønn. Oversettes fra fødselsnummer hvis mulig
	 */
	private CS kjonn;
	/**
	 * En kode for når på døgnet hendelsen inntraff. (Morgen,Ettermiddag,Kveld, Natt).
	 */
	private CS dognkode;
	/**
	 * Tidspunkt (dato og klokkeslett) for når hendelsen inntraff (Dersom klokkeslett ikke finnes, angi tid på døgnet f. eks. dag, kveld natt)
	 */
	private Date tidforhendelsen;
	/**
	 * Type uhell. Om dette er et nesten uhell (med postensiell pasientskade) eller et uhell med pasientskade. Dette viser alvorlighetsgrad av konsekvensen for pasienten av hendelsen.
	 * Her trenger vi kode+fritekst for type uhell.
	 */
	private String typeuhell;
	/**
	 * . Melders vurdering av alvorlighetsgrad
	 * Finnes det en klassifikasjonskode for dette, så ønsker vi det.
	 * (Kode + tekst)
	 */
	private CS alvorlighetsgrad;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#isSvarpaettersporring()
	 */
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#isSvarpaettersporring()
	 */
	public boolean isSvarpaettersporring() {
		return svarpaettersporring;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setSvarpaettersporring(boolean)
	 */
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setSvarpaettersporring(boolean)
	 */
	public void setSvarpaettersporring(boolean svarpaettersporring) {
		this.svarpaettersporring = svarpaettersporring;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getStedforhendelsen()
	 */
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getStedforhendelsen()
	 */
	public String getStedforhendelsen() {
		return stedforhendelsen;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setStedforhendelsen(java.lang.String)
	 */
	public void setStedforhendelsen(String stedforhendelsen) {
		this.stedforhendelsen = stedforhendelsen;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getHendelsesbeskrivelse()
	 */
	public String getHendelsesbeskrivelse() {
		return hendelsesbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setHendelsesbeskrivelse(java.lang.String)
	 */
	public void setHendelsesbeskrivelse(String hendelsesbeskrivelse) {
		this.hendelsesbeskrivelse = hendelsesbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getUtfortestrakstiltak()
	 */
	public String getUtfortestrakstiltak() {
		return utfortestrakstiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setUtfortestrakstiltak(java.lang.String)
	 */
	public void setUtfortestrakstiltak(String utfortestrakstiltak) {
		this.utfortestrakstiltak = utfortestrakstiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getForslagtiltak()
	 */
	public String getForslagtiltak() {
		return forslagtiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setForslagtiltak(java.lang.String)
	 */
	public void setForslagtiltak(String forslagtiltak) {
		this.forslagtiltak = forslagtiltak;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getArsaksbeskrivelse()
	 */
	public String getArsaksbeskrivelse() {
		return arsaksbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setArsaksbeskrivelse(java.lang.String)
	 */
	public void setArsaksbeskrivelse(String arsaksbeskrivelse) {
		this.arsaksbeskrivelse = arsaksbeskrivelse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getKonsekvenser()
	 */
	public String getKonsekvenser() {
		return konsekvenser;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setKonsekvenser(java.lang.String)
	 */
	public void setKonsekvenser(String konsekvenser) {
		this.konsekvenser = konsekvenser;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getHvordanoppdaget()
	 */
	public String getHvordanoppdaget() {
		return hvordanoppdaget;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setHvordanoppdaget(java.lang.String)
	 */
	public void setHvordanoppdaget(String hvordanoppdaget) {
		this.hvordanoppdaget = hvordanoppdaget;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getArfodt()
	 */
	public int getArfodt() {
		return arfodt;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setArfodt(int)
	 */
	public void setArfodt(int arfodt) {
		this.arfodt = arfodt;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getKjonn()
	 */
	public CS getKjonn() {
		if (kjonn.getV() != null && kjonn.getV().equals("2")){
			kjonn.setDN("Kvinne");
		}
		if (kjonn.getV() != null && kjonn.getV().equals("1")){
			kjonn.setDN("Mann");
		}
		if (kjonn.getV() != null && kjonn.getV().equals("3")){
			kjonn.setDN("Flere");
		}
		if (kjonn.getV() != null && kjonn.getV().equals("0")){
			kjonn.setDN("Ingen");
		}
		return kjonn;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setKjonn(no.kith.xmlstds.emok.codes.CV)
	 */
	public void setKjonn(CS kjonn) {
		this.kjonn = kjonn;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getDognkode()
	 */
	public CS getDognkode() {
		return dognkode;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setDognkode(java.lang.String)
	 */
	public void setDognkode(CS dognkode) {
		this.dognkode = dognkode;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getTidforhendelsen()
	 */
	public Date getTidforhendelsen() {
		return tidforhendelsen;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setTidforhendelsen(java.util.Date)
	 */
	public void setTidforhendelsen(Date tidforhendelsen) {
		this.tidforhendelsen = tidforhendelsen;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getTypeuhell()
	 */
	public String getTypeuhell() {
		return typeuhell;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setTypeuhell(java.lang.String)
	 */
	public void setTypeuhell(String typeuhell) {
		this.typeuhell = typeuhell;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getAlvorlighetsgrad()
	 */
	public CS getAlvorlighetsgrad() {
		return alvorlighetsgrad;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setAlvorlighetsgrad(java.lang.String)
	 */
	public void setAlvorlighetsgrad(CS alvorlighetsgrad) {
		this.alvorlighetsgrad = alvorlighetsgrad;
	}
	public Basismelding() {
		super();
		kjonn = new CS();
		dognkode = new CS();
		alvorlighetsgrad = new CS();
		arfodt = 0;
		System.out.println("Melding created");
	}
	

}