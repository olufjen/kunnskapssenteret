package no.naks.biovigilans.model;

import java.sql.Types;
import java.util.Date;
import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * En AbstractVigilansmelding er en melding om uønskede hendelser eller komplikasjoner ved blodoverføringer, celler og vev, eller organsdonasjoner. 
 * En AbstractVigilansmelding er enten en Giverkomplikasjon eller en PasientkomplikasjonImpl.
 * 
 */

public class AbstractVigilansmelding extends AbstractModel implements Vigilansmelding{

	/**
	 * Id til meldingen
	 */
	private Long meldeid;
	/**
	 * Dato for hendelsen
	 */
	private Date datoforhendelse;
	/**
	 * Klokkeslett for hendelsen
	 */
	private String klokkesletthendelse;
	/**
	 * Dato når komplikasjonen ble oppdaget
	 */
	private Date datooppdaget;
	/**
	 * Tidspunkt for donasjon eller overføring
	 */
	private String donasjonoverforing;
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
	private String sjekklistesaksbehandling;
	/**
	 * Eventuelle supplerende opplysninger ved meldingen.
	 */
	private String supplerendeopplysninger;
	/**
	 * Dato meldingen er mottatt
	 */
	private Date meldingsdato;

	
	protected String[]vigilansKeys;	
	protected Map<String,String>vigilansFields;


	public Long getMeldeid() {
		return meldeid;
	}
	public void setMeldeid(Long meldeid) {
		this.meldeid = meldeid;
	}
	public Date getDatoforhendelse() {
		return datoforhendelse;
	}
	public void setDatoforhendelse(Date datoforhendelse) {
		this.datoforhendelse = datoforhendelse;
	}
	public String getKlokkesletthendelse() {
		return klokkesletthendelse;
	}
	public void setKlokkesletthendelse(String klokkesletthendelse) {
		this.klokkesletthendelse = klokkesletthendelse;
	}
	public Date getDatooppdaget() {
		return datooppdaget;
	}
	public void setDatooppdaget(Date datooppdaget) {
		this.datooppdaget = datooppdaget;
	}
	public String getDonasjonoverforing() {
		return donasjonoverforing;
	}
	public void setDonasjonoverforing(String donasjonoverforing) {
		this.donasjonoverforing = donasjonoverforing;
	}
	public String getSjekklistesaksbehandling() {
		return sjekklistesaksbehandling;
	}
	public void setSjekklistesaksbehandling(
			String sjekklistesaksbehandling) {
		this.sjekklistesaksbehandling = sjekklistesaksbehandling;
	}
	public String getSupplerendeopplysninger() {
		return supplerendeopplysninger;
	}
	public void setSupplerendeopplysninger(String supplerendeopplysninger) {
		this.supplerendeopplysninger = supplerendeopplysninger;
	}
	public Date getMeldingsdato() {
		return meldingsdato;
	}
	public void setMeldingsdato(Date meldingsdato) {
		this.meldingsdato = meldingsdato;
	}
	
	public void setMeldingTypes(){
		types = new int[] {Types.DATE,Types.TIME,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.DATE};
		utypes = new int[] {Types.DATE,Types.TIME,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.INTEGER};
	}
	public void setMeldingParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{getDatoforhendelse(),getKlokkesletthendelse(),getDatooppdaget(),getDonasjonoverforing(),getSjekklistesaksbehandling(),getSupplerendeopplysninger(),getMeldingsdato()};
		}else
			params = new Object[]{getDatoforhendelse(),getKlokkesletthendelse(),getDatooppdaget(),getDonasjonoverforing(),getSjekklistesaksbehandling(),getSupplerendeopplysninger(),getMeldingsdato(),getMeldeid()};
	}
	
}