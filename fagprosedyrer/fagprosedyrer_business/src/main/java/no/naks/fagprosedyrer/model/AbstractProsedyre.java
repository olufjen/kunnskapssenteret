package no.naks.fagprosedyrer.model;

import java.util.Date;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Prosedyre: en angitt fremgangsmåte for å utføre en aktivitet eller en prosess.
 * 
 */

public abstract class AbstractProsedyre extends AbstractModel implements Prosedyre {

	private Long prosedyre_Id;
	
	/**
	 * Tittel på prosedyren. Så kort tittel som mulig. Beste "mest aktive" søkeord først.
	 */
	private String tittel;
	/*
	 * Status på prosedyren er følgende: 
		Når den er innmeldt.
		Når den er under arbeid
		Når den er levert
		Når det er gjennomført en kvalitetsjekk og metodesjekk 
		Når den er ferdig klar til bruk.
		Når den er ferdig og tilpasset
	 */
	private int prosedyrestatus;
	/*
	 * Dato for når siste litteratursøk ble gjennomført. Dette er dato for når prosedyren ble oppdatert.
	 */
	private Date litterartursok;
	/*
	 * Dato for når neste litteratursøk skal gjennomføres. Dette er dato for neste revisjon.
	 */
	private Date nestelitteratursok;
	/*
	 * Versjonsnummer er knyttet til et gjennomført litteratursøk.
		Versjon 1.0 er første versjon med første litteratursøk.
		Versjon 1.x er første versjon med neste litteratursøk uten endringer
		Versjon 2.0 er versjon 2 med neste litteratursøk som førte til endringer.

	 */
	private String versjonsnr;
	
	
	public Long getProsedyre_Id() {
		return prosedyre_Id;
	}
	public void setProsedyre_Id(Long prosedyre_Id) {
		this.prosedyre_Id = prosedyre_Id;
	}
	public String getTittel() {
		return tittel;
	}
	public void setTittel(String tittel) {
		this.tittel = tittel;
	}
	public int getProsedyrestatus() {
		return prosedyrestatus;
	}
	public void setProsedyrestatus(int prosedyrestatus) {
		this.prosedyrestatus = prosedyrestatus;
	}
	public Date getLitterartursok() {
		return litterartursok;
	}
	public void setLitterartursok(Date litterartursok) {
		this.litterartursok = litterartursok;
	}
	public Date getNestelitteratursok() {
		return nestelitteratursok;
	}
	public void setNestelitteratursok(Date nestelitteratursok) {
		this.nestelitteratursok = nestelitteratursok;
	}
	public String getVersjonsnr() {
		return versjonsnr;
	}
	public void setVersjonsnr(String versjonsnr) {
		this.versjonsnr = versjonsnr;
	}
	
}