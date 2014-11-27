

/**
 * 
 */
package no.naks.emok.model;


/**
 * Kvitteringsmeldingen går til meldepliktig virksomhets avvikssystem.
 * Avvikssystemet sørger for at saksansvarlig og melder får epost som inneholder kvitteringsmelding.
 * Kvitteringsmeldingen bør om mulig inneholde en kopi av informasjonen som Kunnskapssenteret har mottatt.
 * Denne meldingen sendes kun etter å ha mottatt basismelding.
 */
public class Automatiskkvittering extends no.naks.emok.model.Responsmelding implements IAutomatiskkvittering{

	/**
	 * Tekst og bilder(?) som velges automatisk fra en liste av mulig utvalg.
	 */
	private String takkformeldingtekst2;
	/**
	 * Standard tekst for hvordan saken går + lenke?
	 * saksgangAK: "hvordan saken går videre": det viktigste er den interne saksbehandling. Detaljer i hvem og hvilke saksbehandlingsgrupper som finnes internt i Kunnskapssenteret bør ikke være for omfattende. Tenk smart og effektivt byråkrati, informasjonsmengden som skal forutsettes lest av mange må veies på gullvekt
	 */
	private String saksgangAK;
	/**
	 * Inneholder en kopi av informasjonen som Kunnskapssenteret har mottatt.
	 */
	private String kopiavmottattmelding;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAutomatiskkvittering#getTakkformeldingtekst2()
	 */
	public String getTakkformeldingtekst2() {
		return takkformeldingtekst2;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAutomatiskkvittering#setTakkformeldingtekst2(java.lang.String)
	 */
	public void setTakkformeldingtekst2(String takkformeldingtekst2) {
		this.takkformeldingtekst2 = takkformeldingtekst2;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAutomatiskkvittering#getSaksgangAK()
	 */
	public String getSaksgangAK() {
		return saksgangAK;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAutomatiskkvittering#setSaksgangAK(java.lang.String)
	 */
	public void setSaksgangAK(String saksgangAK) {
		this.saksgangAK = saksgangAK;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAutomatiskkvittering#getKopiavmottattmelding()
	 */
	public String getKopiavmottattmelding() {
		return kopiavmottattmelding;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IAutomatiskkvittering#setKopiavmottattmelding(java.lang.String)
	 */
	public void setKopiavmottattmelding(String kopiavmottattmelding) {
		this.kopiavmottattmelding = kopiavmottattmelding;
	}
	
}
