package no.naks.fagprosedyrer.model;


/**
 * Prosedyre: en angitt fremgangsmåte for å utføre en aktivitet eller en prosess.
 * 
 */

public class Prosedyre{

	/**
	 * Tittel på prosedyren. Så kort tittel som mulig. Beste "mest aktive" søkeord først.
	 */
	private java.lang.String tittel;
	int prosedyrestatus;
	Date litterartursok;
	Date nestelitteratursok;
	java.lang.String versjonsnr;
}