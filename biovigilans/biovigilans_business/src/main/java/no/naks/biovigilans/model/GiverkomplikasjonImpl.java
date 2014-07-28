package no.naks.biovigilans.model;


/**
 * En givekomplikasjon er en komplikasjon som oppst�r hos giver etter at hun har gitt blod eller andre organer
 * 
 */

public class GiverkomplikasjonImpl extends AbstractVigilansmelding implements Vigilansmelding{

	/**
	 * Hvor skjedde komplikasjonen (venterom, benk)
	 */
	private int stedforkomplikasjon;
	private int tidfratappingtilkompliasjon;
	/**
	 * Nedtrekksvelg
	 */
	private int behandlingssted;
	/**
	 * Tilleggsopplysninger knyttet til denne oppf�lgingen.
	 */
	private int tilleggsopplysninger;
	private int alvorlighetsgrad;
	private int kliniskresultat;
	/**
	 * Varghet kan v�re fra fra minutter til m�neder
	 */
	private int varighetkomplikasjon;
}