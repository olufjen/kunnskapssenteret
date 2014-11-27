

/**
 * 
 */
package no.naks.emok.model;


/**
 * Denne meldingstypen sendes automatisk når saken avsluttes i Kunnskapssenteret. Den går til melder og foretakets saksbehandler gjennom foretakets meldesystem.
 * Meldingen inneholder takk for melding saksgang.
 * avslutningsmelding: hvis denne alltid kommer, vil jeg tro at det er nyttig at den jobben Kunnskapssenteret har gjort på WHO-klassifisering, følger med tilbake til oss. Så kan vi ta det inn hvis systemene kan.
 */
public class Avslutningsmelding extends no.naks.emok.model.Responsmelding implements IAvslutningsmelding{

	/**
	 * Vi takker for at de har sendt denne meldingen. Vi forklarer hvordan meldingen brukes videre og hvor de finner rapporten
	 * takkformeldingtekst1: "hvor de finner rapporten"? Hos oss får melder automatisk kvittering med lenke til saken når den lukkes hos oss. Vi kan ikke sende avslutningsmail flere ganger. Og "rapporten" er jo saken i avvikssystemet og skal ikke finnes andre steder. Kunne evt tenke at dette ble gjort i saker der avdelingen har merket av for at de ønsker tilbakemelding?
	 */
	private String takkformeldingtekst1;
	/**
	 * Kunnskapssenterets klassifikasjon av meldingen (som tekst og kode) 
	 * Normalt er denne med, men den kan velges bort.
	 */
	private String klassifikasjonskodeK;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAvslutningsmelding#getTakkformeldingtekst1()
	 */
	public String getTakkformeldingtekst1() {
		return takkformeldingtekst1;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAvslutningsmelding#setTakkformeldingtekst1(java.lang.String)
	 */
	public void setTakkformeldingtekst1(String takkformeldingtekst1) {
		this.takkformeldingtekst1 = takkformeldingtekst1;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAvslutningsmelding#getKlassifikasjonskodeK()
	 */
	public String getKlassifikasjonskodeK() {
		return klassifikasjonskodeK;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAvslutningsmelding#setKlassifikasjonskodeK(java.lang.String)
	 */
	public void setKlassifikasjonskodeK(String klassifikasjonskodeK) {
		this.klassifikasjonskodeK = klassifikasjonskodeK;
	}
	
}