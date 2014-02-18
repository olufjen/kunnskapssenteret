package no.naks.fagprosedyrer.model;

import java.sql.Types;
import java.util.Date;


/**
 * Denne klassen representerer prosedyrer som er meldt inn til nettverket som forslag og som er under arbeid.
 * 
 */

public class InnmeldtprosedyreImpl extends AbstractProsedyre implements Innmeldtprosedyre{

	Long innmeldtprosedyre_Id;
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
	
	
	public InnmeldtprosedyreImpl() {
		super();
		
		types = new int[] {Types.VARCHAR,Types.INTEGER,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.DATE};
		utypes = new int[] {Types.VARCHAR,Types.INTEGER,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.DATE,
				Types.INTEGER,Types.INTEGER};

	}
	public void setParams(){
		Long id = getProsedyre_Id();
		Long fId = getInnmeldtprosedyre_Id();
		if (id == null){
			params = new Object[]{getTittel(),getProsedyrestatus(),getLitterartursok(),getNestelitteratursok(),getVersjonsnr(),getFormal(),
					getOppstartdato(),getFerdigdato()};
		}else
			params = new Object[]{getTittel(),getProsedyrestatus(),getLitterartursok(),getNestelitteratursok(),getVersjonsnr(),getFormal(),
				getOppstartdato(),getFerdigdato(),getProsedyre_Id(),getInnmeldtprosedyre_Id()};


	}	
	
	public Long getInnmeldtprosedyre_Id() {
		return innmeldtprosedyre_Id;
	}
	public void setInnmeldtprosedyre_Id(Long innmeldtprosedyre_Id) {
		this.innmeldtprosedyre_Id = innmeldtprosedyre_Id;
	}
	public String getFormal() {
		return formal;
	}
	public void setFormal(String formal) {
		this.formal = formal;
	}
	public Date getOppstartdato() {
		return oppstartdato;
	}
	public void setOppstartdato(Date oppstartdato) {
		this.oppstartdato = oppstartdato;
	}
	public Date getFerdigdato() {
		return ferdigdato;
	}
	public void setFerdigdato(Date ferdigdato) {
		this.ferdigdato = ferdigdato;
	}
	
}