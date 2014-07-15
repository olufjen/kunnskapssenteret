package no.naks.biovigilans.model;
import java.util.Date;



/**
 * Denne klassen representerer transfusjonene som er gjort ved en gitt pasientkomplikasjonen.
 * 
 */

public abstract class AbstractTransfusjon{

	/**
	 * Dato for transfusjonen
	 */
	private Date transfusjondato;
	/**
	 * Klokkeslett for transfusjonen
	 */
	private java.lang.String transfusjonsklokkeslett;
	/**
	 * En verdi for hastegrad ved transfusjonen. Den er en av følgende:
	 * - Planlagt
	 * - Øyeblikkelig hjelp
	 * - Vet ikke
	 */
	private java.lang.String hastegrad;
	private java.lang.String feiltranfudert;
	/**
	 * Dette er grunnen til at pasienten får en transfusjon, for eksempel jernmangelanemi, blødning fra tarm… De samme betraktninger som under Sykdom av betydning for komplikasjonen (ovenfor). Må diskuteres.
	 */
	private java.lang.String indikasjon;
	/**
	 * Totalt antall poser som er benyttet ved transfusjonen.
	 */
	private int antalenheter;
}