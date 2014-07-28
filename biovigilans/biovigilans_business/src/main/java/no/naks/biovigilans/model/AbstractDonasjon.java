package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * 
 */

public abstract class AbstractDonasjon extends AbstractModel{

	/**
	 * Må beskrives
	 */
	private int donasjonssted;
	/**
	 * Ja nei, vet ikke
	 */
	private int komplisertvenepunksjon;
	/**
	 * Tappetype vil være en av følgende:
	 * Fullblods
	 */
	private int tappetype;
	/**
	 * Tappevariighet
	 */
	private int tappevarighet;
	/**
	 * Må beskrives
	 */
	private int lokalisasjonvenepunksjon;
	/**
	 * Måltid innen 3 timer før tapping (Ja, nei Vet ikke)
	 */
	private int maltidfortapping;
}