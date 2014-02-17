package no.naks.fagprosedyrer.model;

import java.util.Date;


/**
 * Denne klassen representerer prosedyrer som er meldt inn til nettverket som forslag og som er under arbeid.
 * 
 */

public class InnmeldtprosedyreImpl extends AbstractProsedyre implements Innmeldtprosedyre{

	/**
	 * Formål med prosedyren Hvorfor er det viktig å utvikle denne prosedyren.
	 */
	private String formal;
	/**
	 * Tentativ oppstartsdato for utvikling av prosedyren
	 */
	private Date oppstartdato;
	/**
	 * Tentativ ferdigdato for utvikling av prosedyren
	 */
	private Date ferdigdato;
}