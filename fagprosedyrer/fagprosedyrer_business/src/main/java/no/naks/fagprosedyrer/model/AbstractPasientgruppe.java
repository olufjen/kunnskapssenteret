package no.naks.fagprosedyrer.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Pasientgrupper som denne prosedyren er relevant for. (3)
 * 
 */

public abstract class AbstractPasientgruppe extends AbstractModel implements Pasientgruppe{

	/**
	 * MesH term
	 */
	private java.lang.String pasientgruppe;
	/**
	 * Entydig MeSH identifikator
	 */
	private java.lang.String meshindeks;
}