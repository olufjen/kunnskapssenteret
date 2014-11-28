package no.naks.biovigilans.web.server.resource;

import java.util.ArrayList;
import java.util.List;

import org.restlet.Request;
import org.restlet.data.Reference;

import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.GiverKvitteringWebModel;
import no.naks.biovigilans.web.model.KomDiagnosegiverwebModel;
import no.naks.biovigilans.web.model.MelderwebModel;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonKvitteringWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
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

	protected PasientKomplikasjonWebModel result = null;
	protected TransfusjonWebModel transfusjon = null;
	protected TransfusjonKvitteringWebModel kvittering = null;

	
	protected String[] avdelinger;
	protected String[] aldergruppe;
	protected String[] kjonnValg; 
	protected String[] blodProdukt;
	protected String[] hemolyseParametre;
	protected String pasientkomplikasjonId = "pasientkomplikasjon"; 	// Benyttes som nøkkel til HTML-sider
	protected String transfusjonId = "transfusjon";					// Benyttes som nøkkel til HTML-sider
	protected String kvitteringsId = "kvittering";					// Benyttes som nøkkel for kvitteringssiden
	protected String kvitteringGiverId = "giverKvittering";
	
	protected GiverKomplikasjonwebModel giverModel = null;
	protected DonasjonwebModel donasjon = null;
	protected KomDiagnosegiverwebModel komDiagnosegiver = null;

	protected String[] reaksjonengruppe;
	protected String[] utenforBlodbankengruppe;
	protected String[] donasjonsstedgruppe;
	protected String giverkomplikasjonId="giverkomplikasjon";
	protected String donasjonId ="donasjon";
	protected String komDiagnosegiverId = "komDiagnosegiver";
	protected String vigilansmeldingId="vigilansmelding";
	protected GiverKvitteringWebModel giverKvittering = null;
	
	
	
	protected MelderwebModel melderwebModel;
	protected String melderId = "melder";
	
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
	public GiverKvitteringWebModel getGiverKvittering() {
		return giverKvittering;
	}
	public void setGiverKvittering(GiverKvitteringWebModel giverKvittering) {
		this.giverKvittering = giverKvittering;
	}
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
	
	public void invalidateSessionobjects(){
		sessionAdmin.getSession(getRequest(),pasientkomplikasjonId).invalidate();
		sessionAdmin.getSession(getRequest(),transfusjonId).invalidate();
		sessionAdmin.getSession(getRequest(),melderId).invalidate();
		sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
		sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
		sessionAdmin.getSession(getRequest(), komDiagnosegiverId).invalidate();
	}
	/**
	 * setTransfusjonsObjects
	 * Denne rutinene setter opp alle session objeter som er nødvendig for å fylle ut 
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
		 giverKvittering = (GiverKvitteringWebModel)sessionAdmin.getSessionObject(request,kvitteringGiverId);
	     if(giverModel==null){
	    	 giverModel = new GiverKomplikasjonwebModel();
	 
	    	 giverModel.setAldergruppe(aldergruppe);
	    	 giverModel.setReaksjonengruppe(reaksjonengruppe);
	    	 giverModel.setUtenforBlodbankengruppe(utenforBlodbankengruppe);  
	    	 giverModel.setDonasjonsstedgruppe(donasjonsstedgruppe);
	     }
	     if(donasjon==null){
	    	 donasjon = new DonasjonwebModel();
	
	     }
	     if(komDiagnosegiver == null){
	    	 komDiagnosegiver = new KomDiagnosegiverwebModel();
	   
	     }
	     
	     if (giverKvittering == null){
	    	 giverKvittering = new GiverKvitteringWebModel();
	    
	     }
	
/*
 * Må også sette opp session objekter for Andre hendelser !!!
 * 	     
 */
	     
	     
	     
	}
	public String getPage(){
		String page = "/hemovigilans/rapporter_hendelse_main.html";
		return page;
	}
}
