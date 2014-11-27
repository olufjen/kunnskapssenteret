

/**
 * 
 */
package no.naks.emok.model;


/**
 * Representerer alle typer meldinger som Meldepliktig foretak kan sende
 */
public class Leveransemelding extends no.naks.emok.model.Melding implements ILeveransemelding{

	/**
	 * Et flagg for at man ønsker dialog med K. i denne saken
	 */
	private boolean onskerhjelp;
	/**
	 * Dette er et flagg som er satt for å vise at at dette kan andre lære av
	 */
	private boolean kanlareav;
	/**
	 * Dette er et flagg som er satt dersom Helsetilsynet er varslet i denne saken
	 */
	private boolean varslethelsetilsynet;
	/**
	 * Dette beskriver rollen til den som melder; dvs . Kategoriene kan for eksempel være: leder, sykepleier, lege, annet helsepersonell, merkantil, kvalitetsrådgiver, leder og annet.  Vi bør ha to nivåer; at en krysser av for leder eller ikke-leder, og dersom en ikke er leder blir ledet til neste kategoriseringsmulighet.
	 * Her trenger vi både koden og fritekst som beskriver rollen.
	 */
	private String rolle;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#isOnskerhjelp()
	 */
	public boolean isOnskerhjelp() {
		return onskerhjelp;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#setOnskerhjelp(boolean)
	 */
	public void setOnskerhjelp(boolean onskerhjelp) {
		this.onskerhjelp = onskerhjelp;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#isKanlareav()
	 */
	public boolean isKanlareav() {
		return kanlareav;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#setKanlareav(boolean)
	 */
	public void setKanlareav(boolean kanlareav) {
		this.kanlareav = kanlareav;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#isVarslethelsetilsynet()
	 */
	public boolean isVarslethelsetilsynet() {
		return varslethelsetilsynet;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#setVarslethelsetilsynet(boolean)
	 */
	public void setVarslethelsetilsynet(boolean varslethelsetilsynet) {
		this.varslethelsetilsynet = varslethelsetilsynet;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#getRolle()
	 */
	public String getRolle() {
		return rolle;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.ILeveransemelding#setRolle(int)
	 */
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	
}