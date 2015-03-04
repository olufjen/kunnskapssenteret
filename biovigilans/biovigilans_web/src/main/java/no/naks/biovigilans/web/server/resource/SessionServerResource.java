package no.naks.biovigilans.web.server.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.restlet.Request;
import org.restlet.data.Reference;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.model.Giverkomplikasjon;
import no.naks.biovigilans.model.Pasientkomplikasjon;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.web.model.AnnenKomplikasjonwebModel;
import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.GiverKvitteringWebModel;
import no.naks.biovigilans.web.model.KomDiagnosegiverwebModel;
import no.naks.biovigilans.web.model.MelderwebModel;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonKvitteringWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.biovigilans.web.model.VigilansModel;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;

/**
 * SessionServerResource
 * Denne klassen inneholder alle Webmodel objekter for en session.
 * Den er felles superklasse for alle ResourceHtml klassene
 * @author olj
 *
 */
public class SessionServerResource extends ProsedyreServerResource {

/*
 * Session objekter for transfusjon	
 */
	protected PasientKomplikasjonWebModel result = null;
	protected TransfusjonWebModel transfusjon = null;
	protected TransfusjonKvitteringWebModel kvittering = null;
	protected String andreKey = "annenKomp"; 		// Nøkkel dersom melding er av type annenkomplikasjon
	protected String pasientKey = "pasientKomp"; // Nøkkel dersom melding er av type pasientkomplikasjon
	protected String giverKey = "giverkomp"; 	// Nøkkel dersom melding er at type giverkomplikasjon
	protected String meldingsId = "meldinger";
	protected String[] avdelinger;
	protected String[] aldergruppe;
	protected String[] kjonnValg; 
	protected String[] blodProdukt; // Plasma blodprodukter for nedtrekk - plasma produkttyper
	protected String[] hemolyseParametre;
	protected String pasientkomplikasjonId = "pasientkomplikasjon"; 	// Benyttes som nøkkel til HTML-sider
	protected String transfusjonId = "transfusjon";					// Benyttes som nøkkel til HTML-sider
	protected String kvitteringsId = "kvittering";					// Benyttes som nøkkel for kvitteringssiden
	protected String kvitteringGiverId = "giverKvittering";
	protected String messageType = "none";
	
/*
 * Til bruk for oppfølgingsmeldinger	
 */
	protected String displayKey = "display";						
	protected String displayPart = "none";
	protected String displaydateKey = "displaydate";
	protected String datePart = "block";
	
	
	List<String> hvagikkgaltList = new ArrayList<String>();
/*
 * Sessiomn objekter for giver	
 */
	protected GiverKomplikasjonwebModel giverModel = null;
	protected DonasjonwebModel donasjon = null;
	protected KomDiagnosegiverwebModel komDiagnosegiver = null;
//	protected GiverKvitteringWebModel giverKvittering = null;

	protected String[] reaksjonengruppe;
	protected String[] utenforBlodbankengruppe;
	protected String[] donasjonsstedgruppe;
	protected String giverkomplikasjonId="giverkomplikasjon";
	protected String donasjonId ="donasjon";
	protected String komDiagnosegiverId = "komDiagnosegiver";
	protected String vigilansmeldingId="vigilansmelding";

	protected String[] systemiskgruppe;
	protected String[] skadeiarmen;
	protected String[] sykemeldinggruppe;
	protected String[] varighetSkadegruppe;
	
	//Rapporter AndreHendelse
	protected AnnenKomplikasjonwebModel annenModel =  null;
	protected String andreHendelseId ="andreHendelse";
	
	protected String[] alvorligHendelse; 
	protected String[] hovedprosesslist;
	protected String[] feilelleravvik;
	protected String[] hendelsenoppdaget;
/*
 * Session objekter for kontakt	
 */
	protected MelderwebModel melderwebModel;
	protected String melderId = "melder";
	
	protected String nokkelId = "nokkel"; // Til bruk leveranseside
	protected String datoId = "dato";		//Til bruk leveranseside
	
/*
 * Disse objektene inneholder tidligere rapporterte meldinger
 * Benyttes når bruker har angitt oppfølgingsmelding	
 */
	protected Annenkomplikasjon annenKomplikasjon = null;
    protected Pasientkomplikasjon pasientKomplikasjon = null;
    protected Giverkomplikasjon giverKomplikasjon = null;

    
    
	public String getDisplayKey() {
		return displayKey;
	}

	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}

	public String getDisplayPart() {
		return displayPart;
	}

	public void setDisplayPart(String displayPart) {
		this.displayPart = displayPart;
	}

	public String getDisplaydateKey() {
		return displaydateKey;
	}

	public void setDisplaydateKey(String displaydateKey) {
		this.displaydateKey = displaydateKey;
	}

	public String getDatePart() {
		return datePart;
	}

	public void setDatePart(String datePart) {
		this.datePart = datePart;
	}

	public String getMeldingsId() {
		return meldingsId;
	}

	public void setMeldingsId(String meldingsId) {
		this.meldingsId = meldingsId;
	}
	public String getPasientKey() {
		return pasientKey;
	}
	public void setPasientKey(String pasientKey) {
		this.pasientKey = pasientKey;
	}



	public String getGiverKey() {
		return giverKey;
	}



	public void setGiverKey(String giverKey) {
		this.giverKey = giverKey;
	}

	public String getAndreKey() {
		return andreKey;
	}



	public void setAndreKey(String andreKey) {
		this.andreKey = andreKey;
	}

	
	public String[] getVarighetSkadegruppe() {
		return varighetSkadegruppe;
	}
	public void setVarighetSkadegruppe(String[] varighetSkadegruppe) {
		this.varighetSkadegruppe = varighetSkadegruppe;
	}
	public String[] getSykemeldinggruppe() {
		return sykemeldinggruppe;
	}
	public void setSykemeldinggruppe(String[] sykemeldinggruppe) {
		this.sykemeldinggruppe = sykemeldinggruppe;
	}
	public String[] getSystemiskgruppe() {
		return systemiskgruppe;
	}
	public void setSystemiskgruppe(String[] systemiskgruppe) {
		this.systemiskgruppe = systemiskgruppe;
	}
	public String[] getSkadeiarmen() {
		return skadeiarmen;
	}
	public void setSkadeiarmen(String[] skadeiarmen) {
		this.skadeiarmen = skadeiarmen;
	}
	public String[] getHendelsenoppdaget() {
		return hendelsenoppdaget;
	}
	public void setHendelsenoppdaget(String[] hendelsenoppdaget) {
		this.hendelsenoppdaget = hendelsenoppdaget;
	}
	public String[] getFeilelleravvik() {
		return feilelleravvik;
	}
	public void setFeilelleravvik(String[] feilelleravvik) {
		this.feilelleravvik = feilelleravvik;
	}
	public String[] getHovedprosesslist() {
		return hovedprosesslist;
	}
	public void setHovedprosesslist(String[] hovedprosesslist) {
		this.hovedprosesslist = hovedprosesslist;
	}
	public AnnenKomplikasjonwebModel getAnnenModel() {
		return annenModel;
	}
	public void setAnnenModel(AnnenKomplikasjonwebModel annenModel) {
		this.annenModel = annenModel;
	}
	public String getAndreHendelseId() {
		return andreHendelseId;
	}
	public void setAndreHendelseId(String andreHendelseId) {
		this.andreHendelseId = andreHendelseId;
	}
	public String[] getAlvorligHendelse() {
		return alvorligHendelse;
	}
	public void setAlvorligHendelse(String[] alvorligHendelse) {
		this.alvorligHendelse = alvorligHendelse;
	}
	public PasientKomplikasjonWebModel getResult() {
		return result;
	}
	public void setResult(PasientKomplikasjonWebModel result) {
		this.result = result;
	}
	public TransfusjonWebModel getTransfusjon() {
		return transfusjon;
	}
	public void setTransfusjon(TransfusjonWebModel transfusjon) {
		this.transfusjon = transfusjon;
	}
	public TransfusjonKvitteringWebModel getKvittering() {
		return kvittering;
	}
	public void setKvittering(TransfusjonKvitteringWebModel kvittering) {
		this.kvittering = kvittering;
	}
	public String[] getAvdelinger() {
		return avdelinger;
	}
	public void setAvdelinger(String[] avdelinger) {
		this.avdelinger = avdelinger;
	}
	public String[] getAldergruppe() {
		return aldergruppe;
	}
	public void setAldergruppe(String[] aldergruppe) {
		this.aldergruppe = aldergruppe;
	}
	public String[] getKjonnValg() {
		return kjonnValg;
	}
	public void setKjonnValg(String[] kjonnValg) {
		this.kjonnValg = kjonnValg;
	}
	public String[] getBlodProdukt() {
		return blodProdukt;
	}
	public void setBlodProdukt(String[] blodProdukt) {
		this.blodProdukt = blodProdukt;
	}
	public String[] getHemolyseParametre() {
		return hemolyseParametre;
	}
	public void setHemolyseParametre(String[] hemolyseParametre) {
		this.hemolyseParametre = hemolyseParametre;
	}
	public String getPasientkomplikasjonId() {
		return pasientkomplikasjonId;
	}
	public void setPasientkomplikasjonId(String pasientkomplikasjonId) {
		this.pasientkomplikasjonId = pasientkomplikasjonId;
	}
	public String getTransfusjonId() {
		return transfusjonId;
	}
	public void setTransfusjonId(String transfusjonId) {
		this.transfusjonId = transfusjonId;
	}
	public String getKvitteringsId() {
		return kvitteringsId;
	}
	public void setKvitteringsId(String kvitteringsId) {
		this.kvitteringsId = kvitteringsId;
	}
	public GiverKomplikasjonwebModel getGiverModel() {
		return giverModel;
	}
	public void setGiverModel(GiverKomplikasjonwebModel giverModel) {
		this.giverModel = giverModel;
	}
	public DonasjonwebModel getDonasjon() {
		return donasjon;
	}
	public void setDonasjon(DonasjonwebModel donasjon) {
		this.donasjon = donasjon;
	}
	public KomDiagnosegiverwebModel getKomDiagnosegiver() {
		return komDiagnosegiver;
	}
	public void setKomDiagnosegiver(KomDiagnosegiverwebModel komDiagnosegiver) {
		this.komDiagnosegiver = komDiagnosegiver;
	}
	public String[] getReaksjonengruppe() {
		return reaksjonengruppe;
	}
	public void setReaksjonengruppe(String[] reaksjonengruppe) {
		this.reaksjonengruppe = reaksjonengruppe;
	}
	public String[] getUtenforBlodbankengruppe() {
		return utenforBlodbankengruppe;
	}
	public void setUtenforBlodbankengruppe(String[] utenforBlodbankengruppe) {
		this.utenforBlodbankengruppe = utenforBlodbankengruppe;
	}
	public String[] getDonasjonsstedgruppe() {
		return donasjonsstedgruppe;
	}
	public void setDonasjonsstedgruppe(String[] donasjonsstedgruppe) {
		this.donasjonsstedgruppe = donasjonsstedgruppe;
	}
	public String getGiverkomplikasjonId() {
		return giverkomplikasjonId;
	}
	public void setGiverkomplikasjonId(String giverkomplikasjonId) {
		this.giverkomplikasjonId = giverkomplikasjonId;
	}
	public String getDonasjonId() {
		return donasjonId;
	}
	public void setDonasjonId(String donasjonId) {
		this.donasjonId = donasjonId;
	}
	public String getKomDiagnosegiverId() {
		return komDiagnosegiverId;
	}
	public void setKomDiagnosegiverId(String komDiagnosegiverId) {
		this.komDiagnosegiverId = komDiagnosegiverId;
	}
	public String getVigilansmeldingId() {
		return vigilansmeldingId;
	}
	public void setVigilansmeldingId(String vigilansmeldingId) {
		this.vigilansmeldingId = vigilansmeldingId;
	}
	/*public GiverKvitteringWebModel getGiverKvittering() {
		return giverKvittering;
	}
	public void setGiverKvittering(GiverKvitteringWebModel giverKvittering) {
		this.giverKvittering = giverKvittering;
	}*/
	public MelderwebModel getMelderwebModel() {
		return melderwebModel;
	}
	public void setMelderwebModel(MelderwebModel melderwebModel) {
		this.melderwebModel = melderwebModel;
	}
	public String getMelderId() {
		return melderId;
	}
	public void setMelderId(String melderId) {
		this.melderId = melderId;
	}
	
	public List<String> getHvagikkgaltList() {
		return hvagikkgaltList;
	}
	public void setHvagikkgaltList(List<String> hvagikkgaltList) {
		this.hvagikkgaltList = hvagikkgaltList;
	}
	
	
	/**
	 * invalidateSessionobjects
	 * Denne rutinen fjerner alle session objekter
	 */
	public void invalidateSessionobjects(){
		sessionAdmin.getSession(getRequest(),pasientkomplikasjonId).invalidate();
		sessionAdmin.getSession(getRequest(),transfusjonId).invalidate();
		sessionAdmin.getSession(getRequest(),melderId).invalidate();
		sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
		sessionAdmin.getSession(getRequest(), kvitteringGiverId).invalidate();
		sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
		sessionAdmin.getSession(getRequest(), komDiagnosegiverId).invalidate();
		sessionAdmin.getSession(getRequest(), andreHendelseId).invalidate();
		sessionAdmin.getSession(getRequest(), vigilansmeldingId).invalidate();
	}
	/**
	 * setTransfusjonsObjects
	 * Denne rutinene setter opp alle session objekter som er nødvendig for å fylle ut 
	 * et hendelsesskjema
	 */
	public void setTransfusjonsObjects(){
		
/*
 * Trasfusjonsession		
 */
	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	     Request request = getRequest();
	     icd10WebService.readXml();
	     List<Letter> letters = icd10WebService.getLetters();
	     List<MainTerm> terms = new ArrayList();
	     for (Letter letter : letters){
	    	 terms.addAll(letter.getMainTerm());
	     }
	     result = (PasientKomplikasjonWebModel) sessionAdmin.getSessionObject(request,pasientkomplikasjonId);
	     transfusjon = (TransfusjonWebModel) sessionAdmin.getSessionObject(request,transfusjonId);
	     kvittering = (TransfusjonKvitteringWebModel)sessionAdmin.getSessionObject(request,kvitteringsId);
	 	melderwebModel =(MelderwebModel) sessionAdmin.getSessionObject(getRequest(),melderId);
	     if (result == null){
	    	 result = new PasientKomplikasjonWebModel();
	
	    	 result.setAldergruppe(aldergruppe);
	    	 result.setKjonnValg(kjonnValg);
	    	 result.setblodProducts(blodProdukt);
	    	 result.setHemolyseparams(hemolyseParametre);
	    	 result.setAvdelinger(avdelinger);
	
	    
	     }
	     result.setTerms(terms);
	     if (transfusjon == null){
	    	 transfusjon = new TransfusjonWebModel();

	  //  	 transfusjon.setHemolyseParametre(hemolyseParametre);
	     }

	     if ( melderwebModel == null){
	    	 melderwebModel = new MelderwebModel();
	
	     }
	     sessionAdmin.setSessionObject(getRequest(), melderwebModel,melderId);
	   
/*
 * Giver session 	     
 */
	     giverModel = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(request,giverkomplikasjonId);
		 donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(request, donasjonId);  
		 komDiagnosegiver =(KomDiagnosegiverwebModel) sessionAdmin.getSessionObject(request,komDiagnosegiverId );
	//	 giverKvittering = (GiverKvitteringWebModel)sessionAdmin.getSessionObject(request,kvitteringGiverId);
	     if(giverModel==null){
	    	 giverModel = new GiverKomplikasjonwebModel();
	 
	    	 giverModel.setAldergruppe(aldergruppe);
	    	 giverModel.setReaksjonengruppe(reaksjonengruppe);
	    	 giverModel.setUtenforBlodbankengruppe(utenforBlodbankengruppe);  
	    	 giverModel.setDonasjonsstedgruppe(donasjonsstedgruppe);
	    	 giverModel.setSkadeiarmen(skadeiarmen);
	    	 giverModel.setSystemiskgruppe(systemiskgruppe);
	    	 giverModel.setSykemeldinggruppe(sykemeldinggruppe);
	    	 giverModel.setVarighetSkadegruppe(varighetSkadegruppe);
	     }
	     if(donasjon==null){
	    	 donasjon = new DonasjonwebModel();
	
	     }
	     if(komDiagnosegiver == null){
	    	 komDiagnosegiver = new KomDiagnosegiverwebModel();
	   
	     }
	     
	   /*  if (giverKvittering == null){
	    	 giverKvittering = new GiverKvitteringWebModel();
	    
	     }*/
	
	}

    /*
     * Andre Hendelse session
     */
   	
	public void setAndreHendelser(){
		 Request request = getRequest();
		 annenModel = (AnnenKomplikasjonwebModel)sessionAdmin.getSessionObject(request, andreHendelseId);
	     if(annenModel == null){
	    	 annenModel = new AnnenKomplikasjonwebModel();
	    	 annenModel.setAlvorligHendelse(alvorligHendelse);
	    	 annenModel.setHovedprosesslist(hovedprosesslist);
	    	 annenModel.setFeilelleravvik(feilelleravvik);
	    	 annenModel.setHendelsenoppdaget(hendelsenoppdaget);
	    	 annenModel.setHvagikkgaltList(hvagikkgaltList);
	    	 annenModel.setMeldingsNokkel("xx");
	    	 annenModel.setHendelseDato(new Date());
	    	 
	     }
	}
	
	/**
	 * checkMessageType
	 * Denne rutinen sjekker type melding som er sendt inn
	 * Brukes av leveransesiden for å vise riktig informasjon
	 * @return
	 */
	public VigilansModel checkMessageType(){
		  Request request = getRequest();
		  VigilansModel melding = null;
		  transfusjon = (TransfusjonWebModel) sessionAdmin.getSessionObject(request,transfusjonId);
		  annenModel = (AnnenKomplikasjonwebModel)sessionAdmin.getSessionObject(request, andreHendelseId);
		  giverModel = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(request,giverkomplikasjonId);
		  messageType = "none";
		  if (transfusjon != null){
			  messageType = "transfusjon";
			  melding = (VigilansModel)transfusjon;
			  
		  }
		  if (giverModel != null){
			  messageType = "giver";
			  melding = (VigilansModel)giverModel;
		  }
		  if (annenModel != null){
			  messageType = "annen";
			  melding = (VigilansModel)annenModel;
		  }
		return melding;
	}
	public String getPage(){
		String page = "/hemovigilans/leveranse.html";
		return page;
	}
	/**
	 * checkSavedModel
	 * Denne rutinen sjekker om et skjema er lagret
	 * @return true dersom et skjema er lagret.
	 */
	public boolean checkSavedModel(){
		if (giverModel != null){
			return giverModel.isLagret();
		}
		if (result != null){
			return result.isLagret();
		}
		if (transfusjon != null){
			return transfusjon.isLagret();
		}
		return false;
	}
	    /**
     * Save Skjema
     * DEnne rutinen sørger for å lagre melderid til vigilansmelding
     * 
     */
   protected void SaveSkjema(){
		Long melderKey = melderwebModel.getMelder().getMelderId();
		if (melderKey != null && transfusjon != null){
			if (transfusjon.isLagret()){
				transfusjon.getPasientKomplikasjon().setMelderId(melderKey);
				Vigilansmelding melding = (Vigilansmelding)transfusjon.getPasientKomplikasjon();
				melding.setGodkjent("Ja");
				//melding.setKladd("");
				hendelseWebService.saveVigilansMelder(melding);
			}
		}
			if (melderKey != null && giverModel != null){
			if (giverModel.isLagret()){
				giverModel.getGiverKomplikasjon().setMelderId(melderKey);
				Vigilansmelding melding = (Vigilansmelding)giverModel.getGiverKomplikasjon();
				melding.setGodkjent("Ja");
				//melding.setKladd("");
				hendelseWebService.saveVigilansMelder(melding);
			}
		}
		if (melderKey != null && annenModel != null){
			if (annenModel.isLagret()){
				annenModel.getAnnenKomplikasjon().setMelderId(melderKey); 
				Vigilansmelding melding = (Vigilansmelding)annenModel.getAnnenKomplikasjon();
				melding.setGodkjent("Ja");
				//melding.setKladd("");
				hendelseWebService.saveVigilansMelder(melding);
			}
		}  
    }

}
