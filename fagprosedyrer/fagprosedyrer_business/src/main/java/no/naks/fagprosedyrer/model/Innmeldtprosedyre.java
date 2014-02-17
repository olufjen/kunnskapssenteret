package no.naks.fagprosedyrer.model;


/**
 * Denne klassen representerer prosedyrer som er meldt inn til nettverket som forslag og som er under arbeid.
 * 
 */

public class Innmeldtprosedyre extends Prosedyre{

	/**
	 * Formål med prosedyren Hvorfor er det viktig å utvikle denne prosedyren.
	 */
	private java.lang.String formal;
	/**
	 * Tentativ oppstartsdato for utvikling av prosedyren
	 */
	private Date oppstartdato;
	/**
	 * Tentativ ferdigdato for utvikling av prosedyren
	 */
	private Date ferdigdato;
}