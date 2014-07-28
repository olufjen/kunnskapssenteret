package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer Giver eller  Donor
 * N�r en giver melder seg til ny avtale, vil eventuelle tidligere komplikasjoner bli vist.
 * 
 */

public abstract class AbtractGiver extends AbstractModel{

	private int kjonn;
	private int alder;
	/**
	 * Vekt angitt i kilo
	 */
	private int vekt;
	/**
	 * Om giver er giver for f�rste gang
	 */
	private int givererfaring;
	/**
	 * Om giver har hatt komplikasjoner tidligere (Ja/Nei)
	 */
	private int tidligerekomlikasjonjanei;
	/**
	 * Dersom ja m� det v�re en forklaring
	 */
	private int tidligerekomplikasjonforklaring;
	/**
	 * Aferese er en egen m�te � tappe blod p�.
	 * Er dette f�rste gang.
	 */
	private int givererfaringaferese;
}