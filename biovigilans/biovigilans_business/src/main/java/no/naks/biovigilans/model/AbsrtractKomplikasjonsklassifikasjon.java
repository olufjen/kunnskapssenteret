package no.naks.biovigilans.model;


/**
 * Denne klassen representerer en klassifikasjon av pasientkomplikasjonen. En pasientkomplikasjon kan ha flere klassifikasjoner.
 * 
 */

public abstract class AbsrtractKomplikasjonsklassifikasjon{

	/**
	 * Klassifikasjon av komplikasjon, hentes fra Sykdom?
	 */
	private java.lang.String klassifikasjon;
	/**
	 * Beskrvelse av klassifikasjonen til komplikasjonen
	 */
	private java.lang.String klassifikasjonsbeskrivelse;
	private int klassifikasjonsid;
}