

/**
 * 
 */
package no.naks.emok.model;

import java.io.Serializable;
import java.util.Date;

import no.naks.nhn.model.Person;
import no.naks.rammeverk.kildelag.model.AbstractModel;
import no.naks.services.nhn.client.Organization;

/**
 * Dette representerer alle meldingstyper i meldeordningen.
 * Denne klassen respresenterer hodemeldingen beskrevet i KITH standard. Inneholder også alle ev. vedlegg.
 * Felter som står som "ikke opbligatorisk". Feltet skal være med i meldinger til oss, men trenger ikke 
 * være utfylt.
 */
public abstract class Melding extends AbstractModel implements IMelding,Serializable {

	/**
	 * Dato for når meldingen ble sendt
	 */
	private Date datoformeldingen;
	/**
	 * En entydig id fra Meldepliktig virksomhet (hentet fra Avvikssystem + NHN adresseregister). Dette er returadressen slik at responsmeldinger havner på riktig sted
	 */
	private Person entydigidmelder;
	/**
	 * En entydig id som blir påført meldingen fra Kunnskapssnteret når merldingen er mottatt.
	 * Settes av saksbehanlingssystem
	 */
	private String entydigidk;
	/**
	 * Fra NHN adresseregister
	 */
	private int melder_enhet;
	/**
	 * Adresse for virksomheten hentet fra Norsk Helsenett
	 */
	private Organization nhnadresse;
	/**
	 * Alle typer vanlige vedlegg
	 */
	private int vedlegg;
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#getDatoformeldingen()
	 */
	public Date getDatoformeldingen() {
		return datoformeldingen;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#setDatoformeldingen(java.util.Date)
	 */
	public void setDatoformeldingen(Date datoformeldingen) {
		this.datoformeldingen = datoformeldingen;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#getEntydigidmelder()
	 */
	public Person getEntydigidmelder() {
		return entydigidmelder;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#setEntydigidmelder(no.naks.nhn.model.Person)
	 */
	public void setEntydigidmelder(Person entydigidmelder) {
		this.entydigidmelder = entydigidmelder;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#getEntydigidk()
	 */
	public String getEntydigidk() {
		return entydigidk;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#setEntydigidk(int)
	 */
	public void setEntydigidk(String entydigidk) {
		this.entydigidk = entydigidk;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#getMelder_enhet()
	 */
	public int getMelder_enhet() {
		return melder_enhet;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#setMelder_enhet(int)
	 */
	public void setMelder_enhet(int melder_enhet) {
		this.melder_enhet = melder_enhet;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#getNhnadresse()
	 */
	public Organization getNhnadresse() {
		return nhnadresse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#setNhnadresse(no.naks.services.nhn.client.Organization)
	 */
	public void setNhnadresse(Organization nhnadresse) {
		this.nhnadresse = nhnadresse;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#getVedlegg()
	 */
	public int getVedlegg() {
		return vedlegg;
	}
	/* (non-Javadoc)
	 * @see no.naks.emok.model.IMelding#setVedlegg(int)
	 */
	public void setVedlegg(int vedlegg) {
		this.vedlegg = vedlegg;
	}
	
}