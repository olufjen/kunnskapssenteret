

/**
 * 
 */
package no.naks.emok.model;


/**
 * Kunnskapssenterets tilbakemelding til virksomheten, inneholdende forskjellig typer innhold tilpasset den enkelte melding. Kunnskapstilbakmelding vil kunne inneholde forskningsbasert kunnskap, erfaringsbasert kunnskap, statistikk fra meldeordningen eller andre kunnskapskilder, metoder og verktøy som kan være aktuelle å bruke mv. Meldingen vil kunne inneholde fritekst med aktive lenker, vedlegg i form av pdf-filer, word-filer, ppt-filer og muligens excelfiler. Kunnskapstilbakemeldingen kan sendes flere ganger på samme melding, men vil ikke nødvendigvis bli sendt for alle meldinger.
 */
public class Kunnskapstilbakemelding extends no.naks.emok.model.Responsmelding implements IKunnskapstilbakemelding{

	/**
	 * Kunnskapssenterets råd og forslag til videre håndtering til virksomheten ved kontaktperson. (fritekst).
	 */
	private String tilbakemeldingsinfo;
	/**
	 * Kunnskapssenterets klassifikasjon av meldingen (som tekst og kode) 
	 * Normalt er denne med, men den kan velges bort.
	 */
	private String klassifikasjonskodeK;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IKunnskapstilbakemelding#getTilbakemeldingsinfo()
	 */
	public String getTilbakemeldingsinfo() {
		return tilbakemeldingsinfo;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IKunnskapstilbakemelding#setTilbakemeldingsinfo(java.lang.String)
	 */
	public void setTilbakemeldingsinfo(String tilbakemeldingsinfo) {
		this.tilbakemeldingsinfo = tilbakemeldingsinfo;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IKunnskapstilbakemelding#getKlassifikasjonskodeK()
	 */
	public String getKlassifikasjonskodeK() {
		return klassifikasjonskodeK;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IKunnskapstilbakemelding#setKlassifikasjonskodeK(java.lang.String)
	 */
	public void setKlassifikasjonskodeK(String klassifikasjonskodeK) {
		this.klassifikasjonskodeK = klassifikasjonskodeK;
	}
	
}