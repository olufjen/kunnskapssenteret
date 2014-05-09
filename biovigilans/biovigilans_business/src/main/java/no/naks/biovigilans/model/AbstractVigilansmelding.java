package no.naks.biovigilans.model;

import java.util.Date;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * En AbstractVigilansmelding er en melding om uønskede hendelser eller komplikasjoner ved blodoverføringer, celler og vev, eller organsdonasjoner. 
 * En AbstractVigilansmelding er enten en Giverkomplikasjon eller en PasientkomplikasjonImpl.
 * 
 */

public class AbstractVigilansmelding extends AbstractModel{

	/**
	 * Id til meldingen
	 */
	private long meldeid;
	/**
	 * Dato for hendelsen
	 */
	private Date datoforhendelse;
	/**
	 * Klokkeslett for hendelsen
	 */
	private java.lang.String klokkesletthendelse;
	/**
	 * Dato når komplikasjonen ble oppdaget
	 */
	private Date datooppdaget;
	/**
	 * Tidspunkt for donasjon eller overføring
	 */
	private java.lang.String donasjonoverforing;
	/**
	 * Denne sjekklisten omfatter følgende definisjoner:
	 * Skal meldes videre til HDIR
	 * Skal diskuteres på neste møte
	 * Egnet som eksempler i rapport
	 * Egnet som oppgave på seminar
	 * Trenger ytterligere opplysninger
	 * Trenger å følges opp
	 * Ferdig behandlet
	 */
	private java.lang.String sjekklistesaksbehandling;
	/**
	 * Eventuelle supplerende opplysninger ved meldingen.
	 */
	private java.lang.String supplerendeopplysninger;
	/**
	 * Dato meldingen er mottatt
	 */
	private Date meldingsdato;
	
	public long getMeldeid() {
		return meldeid;
	}
	public void setMeldeid(long meldeid) {
		this.meldeid = meldeid;
	}
	public Date getDatoforhendelse() {
		return datoforhendelse;
	}
	public void setDatoforhendelse(Date datoforhendelse) {
		this.datoforhendelse = datoforhendelse;
	}
	public java.lang.String getKlokkesletthendelse() {
		return klokkesletthendelse;
	}
	public void setKlokkesletthendelse(java.lang.String klokkesletthendelse) {
		this.klokkesletthendelse = klokkesletthendelse;
	}
	public Date getDatooppdaget() {
		return datooppdaget;
	}
	public void setDatooppdaget(Date datooppdaget) {
		this.datooppdaget = datooppdaget;
	}
	public java.lang.String getDonasjonoverforing() {
		return donasjonoverforing;
	}
	public void setDonasjonoverforing(java.lang.String donasjonoverforing) {
		this.donasjonoverforing = donasjonoverforing;
	}
	public java.lang.String getSjekklistesaksbehandling() {
		return sjekklistesaksbehandling;
	}
	public void setSjekklistesaksbehandling(
			java.lang.String sjekklistesaksbehandling) {
		this.sjekklistesaksbehandling = sjekklistesaksbehandling;
	}
	public java.lang.String getSupplerendeopplysninger() {
		return supplerendeopplysninger;
	}
	public void setSupplerendeopplysninger(java.lang.String supplerendeopplysninger) {
		this.supplerendeopplysninger = supplerendeopplysninger;
	}
	public Date getMeldingsdato() {
		return meldingsdato;
	}
	public void setMeldingsdato(Date meldingsdato) {
		this.meldingsdato = meldingsdato;
	}
	
	
	
}