package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer Giver eller  Donor
 * Når en giver melder seg til ny avtale, vil eventuelle tidligere komplikasjoner bli vist.
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
	 * Om giver er giver for første gang
	 */
	private int givererfaring;
	/**
	 * Om giver har hatt komplikasjoner tidligere (Ja/Nei)
	 */
	private int tidligerekomlikasjonjanei;
	/**
	 * Dersom ja må det være en forklaring
	 */
	private int tidligerekomplikasjonforklaring;
	/**
	 * Aferese er en egen måte å tappe blod på.
	 * Er dette første gang.
	 */
	private int givererfaringaferese;
}