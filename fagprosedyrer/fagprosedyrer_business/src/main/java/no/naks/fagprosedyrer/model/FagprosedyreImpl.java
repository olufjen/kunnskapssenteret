package no.naks.fagprosedyrer.model;


/**
 * Fagprosedyrer er prosedyrer som omhandler medisinske og helsefaglige aktiviteter eller prosesser i helsetjenesten. Her er flere definisjoner:
 * Prosedyre: en angitt fremgangsmåte for å utføre en aktivitet eller en prosess.
 * Flerfaglig prosedyrer:  prosedyrer som ivaretar krav 4 i AGREE: «Arbeidsgruppen som har utarbeidet retningslinjen (les: prosedyren) har med personer fra alle relevante faggrupper».
 * Kunnskapsbaserte fagprosedyrer: prosedyrer som er utarbeidet etter minstekrav for fagprosedyrer.
 * 
 */

public class FagprosedyreImpl extends AbstractProsedyre implements Fagprosedyre{

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
}