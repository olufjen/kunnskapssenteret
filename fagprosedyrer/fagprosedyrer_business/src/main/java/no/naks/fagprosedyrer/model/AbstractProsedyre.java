package no.naks.fagprosedyrer.model;

import java.util.Date;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Prosedyre: en angitt fremgangsmåte for å utføre en aktivitet eller en prosess.
 * 
 */

public abstract class AbstractProsedyre extends AbstractModel implements Prosedyre {

	/**
	 * Tittel på prosedyren. Så kort tittel som mulig. Beste "mest aktive" søkeord først.
	 */
	private java.lang.String tittel;
	int prosedyrestatus;
	Date litterartursok;
	Date nestelitteratursok;
	String versjonsnr;
}