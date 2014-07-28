package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * 
 */

public abstract class AbstractDonasjon extends AbstractModel{

	/**
	 * M� beskrives
	 */
	private int donasjonssted;
	/**
	 * Ja nei, vet ikke
	 */
	private int komplisertvenepunksjon;
	/**
	 * Tappetype vil v�re en av f�lgende:
	 * Fullblods
	 */
	private int tappetype;
	/**
	 * Tappevariighet
	 */
	private int tappevarighet;
	/**
	 * M� beskrives
	 */
	private int lokalisasjonvenepunksjon;
	/**
	 * M�ltid innen 3 timer f�r tapping (Ja, nei Vet ikke)
	 */
	private int maltidfortapping;
}