

/**
 * 
 */
package no.naks.emok.model;

/**
 * Representerer alle typer meldinger som Kunnskapssenteret kan sende
 */
public class Responsmelding extends no.naks.emok.model.Melding implements IResponsmelding{

	/**
	 * Henter tekst fra et sett av standardtekster tilgjengelig
	 */
	private String standardtekst;

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IResponsmelding#getStandardtekst()
	 */
	public String getStandardtekst() {
		return standardtekst;
	}

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IResponsmelding#setStandardtekst(java.lang.String)
	 */
	public void setStandardtekst(String standardtekst) {
		this.standardtekst = standardtekst;
	}
	
}