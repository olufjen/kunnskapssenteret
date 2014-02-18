package no.naks.fagprosedyrer.model;

import java.sql.Types;


/**
 * Fagprosedyrer er prosedyrer som omhandler medisinske og helsefaglige aktiviteter eller prosesser i helsetjenesten. Her er flere definisjoner:
 * Prosedyre: en angitt fremgangsmåte for å utføre en aktivitet eller en prosess.
 * Flerfaglig prosedyrer:  prosedyrer som ivaretar krav 4 i AGREE: «Arbeidsgruppen som har utarbeidet retningslinjen (les: prosedyren) har med personer fra alle relevante faggrupper».
 * Kunnskapsbaserte fagprosedyrer: prosedyrer som er utarbeidet etter minstekrav for fagprosedyrer.
 * 
 */

public class FagprosedyreImpl extends AbstractProsedyre implements Fagprosedyre{

	private Long fagprosedyre_Id;
	/**
	 * Hensikt med prosedyren
	 */
	private String hensikt;
	/**
	 * Spørsmålsformulering fra pico. Problemstillingen bør formuleres som et presist spørsmål. På den måten kan man søke frem relevant forskning og finne svar på problemstillingen.
	 * Det bør inneholde informasjon om hvem vi er interessert i, hvilke tiltak vi er interessert i, og hvilke utfall som er av interesse.
	 */
	private String sporsmalformulering;
	/**
	 * Hvilke kliniske og andre spørsmål fagprosedyren skal svare på
	 */
	private String kliniskesporsmal;
	/**
	 * Om det er aktuelt med søk i lovdata etter relevante lover og forskrifter
	 */
	private String lovdata;
	
	
	
	public FagprosedyreImpl() {
		super();
		
		types = new int[] {Types.VARCHAR,Types.INTEGER,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.INTEGER,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
				Types.INTEGER,Types.INTEGER};

		
	}
	public void setParams(){
		Long id = getProsedyre_Id();
		Long fId = getFagprosedyre_Id();
		if (id == null){
			params = new Object[]{getTittel(),getProsedyrestatus(),getLitterartursok(),getNestelitteratursok(),getVersjonsnr(),getHensikt(),
					getSporsmalformulering(),getKliniskesporsmal(),getLovdata()};
		}else
			params = new Object[]{getTittel(),getProsedyrestatus(),getLitterartursok(),getNestelitteratursok(),getVersjonsnr(),getHensikt(),
				getSporsmalformulering(),getKliniskesporsmal(),getLovdata(),getProsedyre_Id(),getFagprosedyre_Id()};


	}	
	
	public Long getFagprosedyre_Id() {
		return fagprosedyre_Id;
	}
	public void setFagprosedyre_Id(Long fagprosedyre_Id) {
		this.fagprosedyre_Id = fagprosedyre_Id;
	}
	public String getHensikt() {
		return hensikt;
	}
	public void setHensikt(String hensikt) {
		this.hensikt = hensikt;
	}
	public String getSporsmalformulering() {
		return sporsmalformulering;
	}
	public void setSporsmalformulering(String sporsmalformulering) {
		this.sporsmalformulering = sporsmalformulering;
	}
	public String getKliniskesporsmal() {
		return kliniskesporsmal;
	}
	public void setKliniskesporsmal(String kliniskesporsmal) {
		this.kliniskesporsmal = kliniskesporsmal;
	}
	public String getLovdata() {
		return lovdata;
	}
	public void setLovdata(String lovdata) {
		this.lovdata = lovdata;
	}
	
}