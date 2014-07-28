package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * 
 */

public abstract class AbstractKomplikasjonsdiagnosegiver extends AbstractModel{

	/**
	 * Dette representerer diagnoseklassifikasjon
	 */
	private int lokalskadearm;
	/**
	 * Dette representerer diagnoseklassifikasjon
	 */
	private int systemiskbivirkning;
	/**
	 * Dette representerer diagnoseklassifikasjon
	 */
	private int annenreaksjon;
	/**
	 * Dette er også nedtrekksvalg
	 */
	private int lokalskadebeskrivelse;
	/**
	 * Dette er også nedtrekksvalg
	 */
	private int bivirkningbeskrivelse;
	/**
	 * Dette er ogs nedtrekksvalg
	 */
	private int annenreaksjonbeskrivelse;
	private int kommentar;
}