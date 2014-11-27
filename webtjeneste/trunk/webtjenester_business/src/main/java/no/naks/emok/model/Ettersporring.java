

/**
 * 
 */
package no.naks.emok.model;


/**
 * Kunnskapssenteret ber om utfyllende informasjon. Denne meldingen sendes ved behov og kan sendes flere ganger.
 */
public class Ettersporring extends no.naks.emok.model.Responsmelding implements IEttersporring{

	/**
	 * Fritekst fra saksbehandler ved Kunnskapssenteret. Skrevet i saksbehandlersystemet. Mulighet til å legge inn forhåndsdefinert tekst.
	 */
	private String ettersporringsinfo;

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IEttersporring#getEttersporringsinfo()
	 */
	public String getEttersporringsinfo() {
		return ettersporringsinfo;
	}

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IEttersporring#setEttersporringsinfo(java.lang.String)
	 */
	public void setEttersporringsinfo(String ettersporringsinfo) {
		this.ettersporringsinfo = ettersporringsinfo;
	}
	
}