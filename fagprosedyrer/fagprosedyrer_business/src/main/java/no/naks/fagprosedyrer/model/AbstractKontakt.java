package no.naks.fagprosedyrer.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Kontaktperson for prosedyren
 * 
 */

public abstract class AbstractKontakt extends AbstractModel implements Kontakt{

	/**
	 * Navn på kontaktperson
	 */
	private java.lang.String navn;
	/**
	 * Yrkestittel
	 */
	private int tittel;
	/**
	 * Epost til kontaktperson
	 */
	private java.lang.String epost;
}