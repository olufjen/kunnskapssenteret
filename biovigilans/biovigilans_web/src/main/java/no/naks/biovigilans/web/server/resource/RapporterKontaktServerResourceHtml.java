package no.naks.biovigilans.web.server.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.web.control.EmailWebService;
import no.naks.biovigilans.web.control.EmailWebServiceImpl;
import no.naks.biovigilans.web.model.MelderwebModel;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonKvitteringWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.AnnenKomplikasjonwebModel;
import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import freemarker.template.SimpleScalar;

public class RapporterKontaktServerResourceHtml extends SessionServerResource {
	private String anonymEpost = "meldeordningen@kunnskapssenteret.no";
	private String[] helseRegioner;
	private String[] hfeneNord;
	private String[]hfeneMidt;
	private String[]hfeneVest;
	private String[]hfeneSor;

	private String[]sykehusHFfinnmark;
	private String[]sykehusHFNord;
	private String[]sykehusHFnordland;
	private String[]sykehusHFHelgeland;
	private String[]sykehusHFNtrond;
	private String[]sykehusHFOlav;
	private String[]sykehusHFMRoms;
	private String[]sykehusHFForde;
	private String[]sykehusHFbergen;
	private String[]sykehusHFFonna;
	private String[]sykehusHFstav;
	private String[]sykehusHFVpriv;
	private String[]sykehusHFsorland;
	private String[]sykehusHFtele;
	private String[]sykehusHFvestf;
	private String[]sykehusHFvviken;
	private String[]sykehusHFostf;
	private String[]sykehusHFoslo;
	private String[]sykehusHFahus;
	private String[]sykehusHFinnl;
	private String[]sykehusHFOpriv;
	
	public RapporterKontaktServerResourceHtml() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	public String[] getSykehusHFfinnmark() {
		return sykehusHFfinnmark;
	}




	public void setSykehusHFfinnmark(String[] sykehusHFfinnmark) {
		this.sykehusHFfinnmark = sykehusHFfinnmark;
	}




	public String[] getSykehusHFNord() {
		return sykehusHFNord;
	}




	public void setSykehusHFNord(String[] sykehusHFNord) {
		this.sykehusHFNord = sykehusHFNord;
	}




	public String[] getSykehusHFnordland() {
		return sykehusHFnordland;
	}




	public void setSykehusHFnordland(String[] sykehusHFnordland) {
		this.sykehusHFnordland = sykehusHFnordland;
	}




	public String[] getSykehusHFHelgeland() {
		return sykehusHFHelgeland;
	}




	public void setSykehusHFHelgeland(String[] sykehusHFHelgeland) {
		this.sykehusHFHelgeland = sykehusHFHelgeland;
	}




	public String[] getSykehusHFNtrond() {
		return sykehusHFNtrond;
	}




	public void setSykehusHFNtrond(String[] sykehusHFNtrond) {
		this.sykehusHFNtrond = sykehusHFNtrond;
	}




	public String[] getSykehusHFOlav() {
		return sykehusHFOlav;
	}




	public void setSykehusHFOlav(String[] sykehusHFOlav) {
		this.sykehusHFOlav = sykehusHFOlav;
	}




	public String[] getSykehusHFMRoms() {
		return sykehusHFMRoms;
	}




	public void setSykehusHFMRoms(String[] sykehusHFMRoms) {
		this.sykehusHFMRoms = sykehusHFMRoms;
	}




	public String[] getSykehusHFForde() {
		return sykehusHFForde;
	}




	public void setSykehusHFForde(String[] sykehusHFForde) {
		this.sykehusHFForde = sykehusHFForde;
	}




	public String[] getSykehusHFbergen() {
		return sykehusHFbergen;
	}




	public void setSykehusHFbergen(String[] sykehusHFbergen) {
		this.sykehusHFbergen = sykehusHFbergen;
	}




	public String[] getSykehusHFFonna() {
		return sykehusHFFonna;
	}




	public void setSykehusHFFonna(String[] sykehusHFFonna) {
		this.sykehusHFFonna = sykehusHFFonna;
	}




	public String[] getSykehusHFstav() {
		return sykehusHFstav;
	}




	public void setSykehusHFstav(String[] sykehusHFstav) {
		this.sykehusHFstav = sykehusHFstav;
	}




	public String[] getSykehusHFVpriv() {
		return sykehusHFVpriv;
	}




	public void setSykehusHFVpriv(String[] sykehusHFVpriv) {
		this.sykehusHFVpriv = sykehusHFVpriv;
	}




	public String[] getSykehusHFsorland() {
		return sykehusHFsorland;
	}




	public void setSykehusHFsorland(String[] sykehusHFsorland) {
		this.sykehusHFsorland = sykehusHFsorland;
	}




	public String[] getSykehusHFtele() {
		return sykehusHFtele;
	}




	public void setSykehusHFtele(String[] sykehusHFtele) {
		this.sykehusHFtele = sykehusHFtele;
	}




	public String[] getSykehusHFvestf() {
		return sykehusHFvestf;
	}




	public void setSykehusHFvestf(String[] sykehusHFvestf) {
		this.sykehusHFvestf = sykehusHFvestf;
	}




	public String[] getSykehusHFvviken() {
		return sykehusHFvviken;
	}




	public void setSykehusHFvviken(String[] sykehusHFvviken) {
		this.sykehusHFvviken = sykehusHFvviken;
	}




	public String[] getSykehusHFostf() {
		return sykehusHFostf;
	}




	public void setSykehusHFostf(String[] sykehusHFostf) {
		this.sykehusHFostf = sykehusHFostf;
	}




	public String[] getSykehusHFoslo() {
		return sykehusHFoslo;
	}




	public void setSykehusHFoslo(String[] sykehusHFoslo) {
		this.sykehusHFoslo = sykehusHFoslo;
	}




	public String[] getSykehusHFahus() {
		return sykehusHFahus;
	}




	public void setSykehusHFahus(String[] sykehusHFahus) {
		this.sykehusHFahus = sykehusHFahus;
	}




	public String[] getSykehusHFinnl() {
		return sykehusHFinnl;
	}




	public void setSykehusHFinnl(String[] sykehusHFinnl) {
		this.sykehusHFinnl = sykehusHFinnl;
	}




	public String[] getSykehusHFOpriv() {
		return sykehusHFOpriv;
	}




	public void setSykehusHFOpriv(String[] sykehusHFOpriv) {
		this.sykehusHFOpriv = sykehusHFOpriv;
	}




	public String[] getHelseRegioner() {
		return helseRegioner;
	}


	public void setHelseRegioner(String[] helseRegioner) {
		this.helseRegioner = helseRegioner;
	}


	public String[] getHfeneNord() {
		return hfeneNord;
	}


	public void setHfeneNord(String[] hfeneNord) {
		this.hfeneNord = hfeneNord;
	}


	public String[] getHfeneMidt() {
		return hfeneMidt;
	}


	public void setHfeneMidt(String[] hfeneMidt) {
		this.hfeneMidt = hfeneMidt;
	}


	public String[] getHfeneVest() {
		return hfeneVest;
	}


	public void setHfeneVest(String[] hfeneVest) {
		this.hfeneVest = hfeneVest;
	}


	public String[] getHfeneSor() {
		return hfeneSor;
	}


	public void setHfeneSor(String[] hfeneSor) {
		this.hfeneSor = hfeneSor;
	}


	/**
	 * getHemovigilans
	 * Denne rutinen henter inn nødvendige session objekter og  setter opp nettsiden for å ta i mot
	 * en rapportert hendelse
	 * @return
	 */
	@Get
	public Representation getHemovigilans() {


	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	     Request request = getRequest();


	     setTransfusjonsObjects();
    	 melderwebModel.setFormNames(sessionParams);
    	 setMelderparams();
    	 melderwebModel.distributeTerms();
/*
 * En Hashmap benyttes dersom en html side henter data fra flere javaklasser.	
 * Hver javaklasse får en id (ex pasientkomplikasjonId) som er tilgjengelig for html
 *      
*/	     
	     Map<String, Object> dataModel = new HashMap<String, Object>();

	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	    
	     LocalReference localUri = new LocalReference(reference);
	
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
	     melderwebModel =(MelderwebModel) sessionAdmin.getSessionObject(request,melderId);
	     
			transfusjon = (TransfusjonWebModel) sessionAdmin.getSessionObject(getRequest(),transfusjonId);
    		giverModel = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),giverkomplikasjonId);
    		annenModel = (AnnenKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),andreHendelseId);
    		donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(getRequest(),donasjonId);
/*
 * 	     
 */

	 
/*
 * En Hashmap benyttes dersom en html side henter data fra flere javaklasser.	
 * Hver javaklasse får en id (ex pasientkomplikasjonId) som er tilgjengelig for html
 *      
*/	     

	     dataModel.put(pasientkomplikasjonId, result);
	     dataModel.put(transfusjonId,transfusjon);
	     dataModel.put(kvitteringsId,kvittering);
	     dataModel.put(melderId, melderwebModel);
	     
//=======================	     

	     dataModel.put(melderId, melderwebModel);
	     sessionAdmin.setSessionObject(getRequest(), melderwebModel,melderId);
	        // Load the FreeMarker template
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/pasientkomplikasjon/nymeldingfagprosedyre.html").get();
	        Representation pasientkomplikasjonFtl = clres2.get();
	//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	        
//	        TemplateRepresentation  templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, result,
//	                MediaType.TEXT_HTML);
	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
		 return templatemapRep;
	 }
    /**
     * storeHemovigilans
     * Denne rutinen tar imot alle ny informasjon fra bruker om den rapporterte hendelsen
     * @param form
     * @return
     */
    @Post
    public Representation storeHemovigilans(Form form) {
    	TemplateRepresentation  templateRep = null;
        String meldingsNokkel = null;
	     String datoLevert = null;
    	if(form == null){
    		invalidateSessionobjects();
    	}
    	GiverKomplikasjonwebModel model = null;
    	if (form != null){
    		melderwebModel =(MelderwebModel) sessionAdmin.getSessionObject(getRequest(),melderId);
    		transfusjon = (TransfusjonWebModel) sessionAdmin.getSessionObject(getRequest(),transfusjonId);
    		giverModel = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),giverkomplikasjonId);
    		annenModel = (AnnenKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),andreHendelseId);
    		donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(getRequest(),donasjonId);
    		Parameter logout = form.getFirst("avbrytkontakt");
    		Parameter lukk = form.getFirst("lukkkontakt");
    	     Map<String, Object> dataModel = new HashMap<String, Object>();

    		if (logout != null || lukk != null){
    			invalidateSessionobjects();
    			
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemivigilans/Logout.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
	    				MediaType.TEXT_HTML);
    			return templateRep; // return a new page!!!
    		}
    			
    		 if(melderwebModel == null){
    	    	 melderwebModel = new MelderwebModel();
    	    	 melderwebModel.setFormNames(sessionParams);
    	    	 setMelderparams();
    	    	 melderwebModel.distributeTerms();
    	     }
 
    		for (Parameter entry : form) {
    			if (entry.getValue() != null && !(entry.getValue().equals("")))
    					System.out.println(entry.getName() + "=" + entry.getValue());
    			melderwebModel.setValues(entry);
    		}

    		sessionAdmin.setSessionObject(getRequest(), melderwebModel, melderId);
    		dataModel.put(melderId, melderwebModel);
    		
    		Parameter formValue = form.getFirst("formValue"); // Fill kontaktform on the base of epost 
    		Parameter lagre = form.getFirst("lagrekontakt");
    		Parameter lagreAnonymt = form.getFirst("lagreanonymt"); // Bruker ønsker å melde anonymt
    		Parameter valgtRegion = form.getFirst("regionValue");   //Bruker har valgt region
    		Parameter valgtForetak = form.getFirst("foretakValue"); //Bruker har valgt HF
    		if(lagre != null){								// Lagre kontaktskjema
    			melderwebModel.saveValues();
    			melderWebService.saveMelder(melderwebModel);
    			SaveSkjema();
    			sessionAdmin.setSessionObject(getRequest(), melderwebModel, melderId);
        		dataModel.put(melderId, melderwebModel);
        		String page = getPage();
    			ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,page));
	    		Representation pasientkomplikasjonFtl = clres2.get();
/*
 * Invalidate session objects kun dersom et meldeskjema er fylt ut	 
 * Dette er nå irrelevant Dette skjer i leveranseskjema olj 23.02.15   		
 
	    		if (checkSavedModel()){
	    			invalidateSessionobjects();
	    		}
*/	    		
//	    		sessionAdmin.getSession(getRequest(),melderId).invalidate();
/*
 */
/*
 * Disse verdiene må settes opp for å unngå feilemelding i kvitteringsskjema !!	    		
 */
	     	    SimpleScalar simple = new SimpleScalar(meldingsNokkel);
	    	    dataModel.put(nokkelId,simple);
	    	    SimpleScalar datoSimple = new SimpleScalar(datoLevert);
	    	    dataModel.put(datoId, datoSimple);
	    		
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
	    				MediaType.TEXT_HTML);
	    		redirectPermanent("../hemovigilans/leveranse.html");
	    		
    		}else if(formValue != null){					// Sjekk epostadresse oppgitt
				String epost = melderwebModel.getMelderepost();
				if(!epost.equalsIgnoreCase("")){
					List<Map<String, Object>> rows = melderWebService.selectMelder(epost);
					if(rows.size() > 0){
						melderwebModel.kontaktValues( rows);
						melderwebModel.saveValues();
						
						//sessionAdmin.getSession(getRequest(),melderId).invalidate();
			    	}
				}
				// Denne client resource forholder seg til src/main/resource katalogen !!!	
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
			    				MediaType.TEXT_HTML);
    		}else if(valgtRegion != null){   // Bruker har valgt en helseregion
    			melderwebModel.setValgtregion();
    			melderwebModel.saveValues();
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
			    				MediaType.TEXT_HTML);    			
    	
       		}else if(valgtForetak != null){   // Bruker har valgt et HF
       			melderwebModel.setValgtsykehus();
       			melderwebModel.saveValues();
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
			    				MediaType.TEXT_HTML);    			
    	
    	 	}else if(lagreAnonymt != null){					// Bruker velger å lagre skjema anonymt.
    			melderwebModel.setMelderepost(anonymEpost);
    			melderwebModel.setAnonymEpost(anonymEpost);
    			String epost = melderwebModel.getMelderepost();
    			if(!epost.equalsIgnoreCase("")){
					List<Map<String, Object>> rows = melderWebService.selectMelder(epost);
					if(rows.size() > 0){
						melderwebModel.kontaktValues( rows);
						melderwebModel.saveAnonym();
						melderwebModel.saveValues();
						
						//sessionAdmin.getSession(getRequest(),melderId).invalidate();
			    	}else{
						melderwebModel.setMelderepost(anonymEpost);
						melderwebModel.saveAnonym();
					}
			    		
				}else{
					melderwebModel.setMelderepost(anonymEpost);
					melderwebModel.saveAnonym();
				}
    			melderWebService.saveMelder(melderwebModel);
				SaveSkjema();
    			sessionAdmin.setSessionObject(getRequest(), melderwebModel, melderId);
        		dataModel.put(melderId, melderwebModel);
        		String page = getPage(); //Hent neste side 
    			ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
/*
 * Invalidate session objects kun dersom et meldeskjema er fylt ut	    		
 */
	    		if (checkSavedModel()){
	    			invalidateSessionobjects();
	    		}
//	    		sessionAdmin.getSession(getRequest(),melderId).invalidate();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
	    				MediaType.TEXT_HTML);
	    		redirectPermanent("../hemovigilans/leveranse.html");
    		}else{

    			sessionAdmin.setSessionObject(getRequest(), melderwebModel, melderId);
        		dataModel.put(melderId, melderwebModel);
    			ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
    			invalidateSessionobjects();
    			Representation pasientkomplikasjonFtl = clres2.get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
			    				MediaType.TEXT_HTML);
    		}
    	}
    	emailWebService.sendEmail();
    	
    	return templateRep;
      
    }
    /**
     * setMelderparams
     * Denne rutinen setter opp alle helseregioner og HF etc for kontaktperson
     */
    private void setMelderparams(){
     	 melderwebModel.setHelseRegioner(helseRegioner);
    	 melderwebModel.setHfeneNord(hfeneNord);
    	 melderwebModel.setHfeneMidt(hfeneMidt);
    	 melderwebModel.setHfeneVest(hfeneVest);
    	 melderwebModel.setHfeneSor(hfeneSor);
    	 melderwebModel.setSykehusHFahus(sykehusHFahus);
    	 melderwebModel.setSykehusHFbergen(sykehusHFbergen);
    	 melderwebModel.setSykehusHFfinnmark(sykehusHFfinnmark);
    	 melderwebModel.setSykehusHFFonna(sykehusHFFonna);
    	 melderwebModel.setSykehusHFForde(sykehusHFForde);
    	 melderwebModel.setSykehusHFHelgeland(sykehusHFHelgeland);
    	 melderwebModel.setSykehusHFinnl(sykehusHFinnl);
    	 melderwebModel.setSykehusHFMRoms(sykehusHFMRoms);
    	 melderwebModel.setSykehusHFNord(sykehusHFNord);
    	 melderwebModel.setSykehusHFnordland(sykehusHFnordland);
    	 melderwebModel.setSykehusHFNtrond(sykehusHFNtrond);
    	 melderwebModel.setSykehusHFOlav(sykehusHFOlav);
    	 melderwebModel.setSykehusHFOpriv(sykehusHFOpriv);
    	 melderwebModel.setSykehusHFoslo(sykehusHFoslo);
    	 melderwebModel.setSykehusHFostf(sykehusHFostf);
    	 melderwebModel.setSykehusHFsorland(sykehusHFsorland);
    	 melderwebModel.setSykehusHFstav(sykehusHFstav);
    	 melderwebModel.setSykehusHFtele(sykehusHFtele);
    	 melderwebModel.setSykehusHFvestf(sykehusHFvestf);
    	 melderwebModel.setSykehusHFVpriv(sykehusHFVpriv);
    	 melderwebModel.setSykehusHFvviken(sykehusHFvviken);
    	
    	 
    }

    
    public void sendEmail(){
    	
    	// Recipient's email ID needs to be mentioned.
        String to = "qadeeralvi@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "qadeer.ahmad.alvi@kunnskapssenteret.no";
        final String user = "qadeer.ahmad.alvi@kunnskapssenteret.no";
        final String pwd ="osl#1689";

        // Assuming you are sending email from smtp office 365
        String host = "smtp.office365.com";
        String port ="587";
        Properties props = new Properties();
        // Setup mail server
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
       

        // Get the  Session object.
        Session session = Session.getInstance(props,
        		new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,pwd);
            }
    });

        try{
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO,
                                    new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Now set the actual message
           message.setText("This is actual message");

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        }catch (Exception mex) {
           mex.printStackTrace();
        }
    	
    	
    }
}
