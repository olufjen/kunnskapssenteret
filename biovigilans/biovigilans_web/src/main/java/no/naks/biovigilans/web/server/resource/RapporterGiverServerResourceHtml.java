package no.naks.biovigilans.web.server.resource;

import java.util.HashMap;
import java.util.Map;

import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.KomDiagnosegiverwebModel;
import no.naks.biovigilans.web.model.MelderwebModel;

import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public class RapporterGiverServerResourceHtml extends SessionServerResource {

	
	public RapporterGiverServerResourceHtml() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * getInnmelding
	 * Denne rutinen henter inn nødvendige session objekter og  setter opp nettsiden for å ta i mot
	 * en rapportert hendelse
	 * @return
	 */
	@Get
	public Representation getHemovigilans() {

		//invalidateSessionobjects();
	    // Reference reference = new Reference(getReference(),"..").getTargetRef();
	

/*
 * En Hashmap benyttes dersom en html side henter data fra flere javaklasser.	
 * Hver javaklasse får en id (ex pasientkomplikasjonId) som er tilgjengelig for html
 *      
*/	     
	     Map<String, Object> dataModel = new HashMap<String, Object>();

	    /* LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	    
	     LocalReference localUri = new LocalReference(reference);
	*/
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_giver.html"));
	    
	     setTransfusjonsObjects(); // Setter opp alle session objekter
	//   	 giverModel.setFormNames(sessionParams);
    //	 donasjon.setFormNames(sessionParams);
    // 	 komDiagnosegiver.setFormNames(sessionParams);
    //	 giverKvittering.setFormNames(sessionParams);
         
	 //    giverModel.distributeTerms();
	 //    giverModel.giverKomplikasjonDistribute();
	 //    giverModel.giveroppfolgingDistribute();
	 //    donasjon.distributeTerms();
	//     komDiagnosegiver.distributeTerms();
	     
	     dataModel.put(giverkomplikasjonId, giverModel);
	     dataModel.put(donasjonId, donasjon);
	     dataModel.put(komDiagnosegiverId, komDiagnosegiver);
	  //   dataModel.put(kvitteringGiverId,giverKvittering);
	     
	     sessionAdmin.setSessionObject(getRequest(), giverModel,giverkomplikasjonId);
	     sessionAdmin.setSessionObject(getRequest(), donasjon,donasjonId);
	     sessionAdmin.setSessionObject(getRequest(), komDiagnosegiver, komDiagnosegiverId);
	//     sessionAdmin.setSessionObject(request,giverKvittering, kvitteringGiverId);
	     
	     // Load the FreeMarker template
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/pasientkomplikasjon/nymeldingfagprosedyre.html").get();
	        Representation givertkomplikasjonFtl = clres2.get();
	   
	//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	        
//	        TemplateRepresentation  templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, giverModel,
//	                MediaType.TEXT_HTML);
	        
	     /*
	        Directory directory = new Directory(getContext(), "file:///hemovigilans/img/");
	        Router router = new Router(getContext());
	        router.attach("/", directory);*/
	        
	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(givertkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML ); 
		 return templatemapRep;
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

	/**
     * storeHemovigilans
     * Denne rutinen tar imot alle ny informasjon fra bruker om den rapporterte hendelsen
     * @param form
     * @return
     */
    @Post
    public Representation storeHemovigilans(Form form) {
    	TemplateRepresentation  templateRep = null;
    	
    	if (form == null){
    		sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
    		sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
    		sessionAdmin.getSession(getRequest(), komDiagnosegiverId).invalidate();
    	}
 
    	if (form != null){
    		giverModel = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),giverkomplikasjonId);
    		donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(getRequest(), donasjonId);
    		komDiagnosegiver = (KomDiagnosegiverwebModel) sessionAdmin.getSessionObject(getRequest(),komDiagnosegiverId );
    //		giverKvittering = (GiverKvitteringWebModel)sessionAdmin.getSessionObject(getRequest(),kvitteringGiverId);
   	     	melderwebModel = ( MelderwebModel)sessionAdmin.getSessionObject(getRequest(),melderId);
    		Parameter logout = form.getFirst("avbryt4");
    		Parameter lukk = form.getFirst("lukk4");
    	     Map<String, Object> dataModel = new HashMap<String, Object>();

    		if (logout != null || lukk != null){
    			sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
    			sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
    			sessionAdmin.getSession(getRequest(), komDiagnosegiverId).invalidate();
    			sessionAdmin.getSession(getRequest(),kvitteringGiverId).invalidate();
    			
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemivigilans/Logout.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    	/*	templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
	    				MediaType.TEXT_HTML);
	    	*/	
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, giverModel,
	    				MediaType.TEXT_HTML);
    			return templateRep; // return a new page!!!
    		}
    		
    		if (giverModel == null){
    			giverModel = new GiverKomplikasjonwebModel();
    			// giverModel.setFormNames(sessionParams);
    		}
 
    		if(donasjon==null){
    			donasjon = new DonasjonwebModel();
    			donasjon.setFormNames(sessionParams);
    		}
    		
    		if(komDiagnosegiver == null){
    	    	 komDiagnosegiver = new KomDiagnosegiverwebModel();
    	    	 komDiagnosegiver.setFormNames(sessionParams);
    	     }
    		
    	/*	if (giverKvittering == null){
    			giverKvittering = new GiverKvitteringWebModel();
    			giverKvittering.setFormNames(sessionParams);
		     }*/
    		
    		for (Parameter entry : form) {
    			if (entry.getValue() != null && !(entry.getValue().equals("")))
    					System.out.println(entry.getName() + "=" + entry.getValue());
    			giverModel.setValues(entry);
    			donasjon.setValues(entry);
    			komDiagnosegiver.setValues(entry);
    			/*giverKvittering.setValues(entry);
    			if(entry.getName().equalsIgnoreCase("tab-annenreak")){
    				giverKvittering.annenreakList.add(entry.getValue()) ;
    			}*/
    		}
    		
    		sessionAdmin.setSessionObject(getRequest(), giverModel,giverkomplikasjonId);
    		sessionAdmin.setSessionObject(getRequest(), donasjon, donasjonId);
    		sessionAdmin.setSessionObject(getRequest(), komDiagnosegiver, komDiagnosegiverId);
    	    sessionAdmin.setSessionObject(getRequest(), melderwebModel,melderId);
    		dataModel.put(giverkomplikasjonId, giverModel);
    	//	dataModel.put(kvitteringGiverId, giverKvittering);
    		
    		Parameter lagre = form.getFirst("btnSendinn");
    		if(lagre!=null){
    			giverModel.saveValues();
    			giverWebService.saveGiver(giverModel);
    			giverWebService.saveVigilansmelding(giverModel);
    			
    			Long giverId=	giverModel.getGiver().getGiverid();
    			if(giverId != null){
    				donasjon.getDonasjon().setGiveId(giverId.intValue());
    			}
    		    donasjon.saveValues();
    		    donasjonWebService.saveDonasjon(donasjon);
    		    
    		    Long donasjonId = donasjon.getDonasjon().getDonasjonsId();
    		    giverModel.getGiverKomplikasjon().setDonasjonid(donasjonId);
    		    
    		    Long meldeId = giverModel.getVigilansmelding().getMeldeid();
    			giverModel.getGiverKomplikasjon().setMeldeid(meldeId);
    			giverWebService.saveGiverkomplikasjon(giverModel);
    			
    			komDiagnosegiver.getKomDiagnosegiver().setMeldeId(meldeId);
    			komDiagnosegiver.saveValues();
    			komDiagnosegiverWebService.saveKomDiagnosegiver(komDiagnosegiver);
    			
    			giverModel.getGiveroppfolging().setMeldeid(meldeId);
    			giverWebService.saveGiveroppfolging(giverModel);
    			giverModel.setLagret(true);
    	//		giverKvittering.setReadonlyFlag("true");
    			
    			//lagre i vigiansmelding
    			//dataModel.put(melderId, melderwebModel);
    			//ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
	    		//Representation pasientkomplikasjonFtl = clres2.get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	    		//templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,MediaType.TEXT_HTML);
	    		redirectPermanent("../hemovigilans/rapporter_kontakt.html");
    		}else{
    			ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_giver.html"));
    			Representation pasientkomplikasjonFtl = clres2.get();
        		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
        				MediaType.TEXT_HTML);
			}
    		
    	}
    
    	return templateRep;
      
    }

}
