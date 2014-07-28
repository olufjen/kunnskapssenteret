package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer oppfølging av Giver ved en Giverkomplikasjon.
 * 
 */

public abstract class AbstractGiveroppfolging extends AbstractModel{

	/**
	 * Denne klassifikasjonen kan ha flere ulike verdier:
	 * - Ingen videre oppfølging
	 * - Avregistering
	 * - Sperring
	 * - annet??
	 */
	private int klassifikasjongiveroppfolging;
	/**
	 * Beskrivelse av oppfølgingen (klartekst).
	 */
	private int giveroppfolgingbeskrivelse;
	/**
	 * Giver er sperret frem til en gitt dato
	 */
	private int avregistering;
}