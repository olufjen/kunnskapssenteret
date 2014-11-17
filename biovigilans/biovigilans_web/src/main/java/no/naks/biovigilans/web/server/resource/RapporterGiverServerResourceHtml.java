package no.naks.biovigilans.web.server.resource;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.naks.biovigilans.model.DonasjonImpl;
import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.GiverKvitteringWebModel;
import no.naks.biovigilans.web.model.KomDiagnosegiverwebModel;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonKvitteringWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;

import org.restlet.Request;
import org.restlet.Restlet;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Directory;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.routing.Router;

public class RapporterGiverServerResourceHtml extends ProsedyreServerResource {
	private GiverKomplikasjonwebModel result=null;
	private DonasjonwebModel donasjon = null;
	private KomDiagnosegiverwebModel komDiagnosegiver = null;
	private String[] aldergruppe;
	private String[] kjonnValg; 
	private String[] reaksjonengruppe;
	private String[] utenforBlodbankengruppe;
	private String[] donasjonsstedgruppe;
	private String giverkomplikasjonId="giverkomplikasjon";
	private String donasjonId ="donasjon";
	private String komDiagnosegiverId = "komDiagnosegiver";
	private String vigilansmeldingId="vigilansmelding";
	private GiverKvitteringWebModel giverKvittering = null;
	private String kvitteringsId="giverKvittering";

	
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


	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	
	     Request request = getRequest();


	    

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
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_giver.html"));
	    
	     result = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(request,giverkomplikasjonId);
		 donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(request, donasjonId);  
		 komDiagnosegiver =(KomDiagnosegiverwebModel) sessionAdmin.getSessionObject(request,komDiagnosegiverId );
		 giverKvittering = (GiverKvitteringWebModel)sessionAdmin.getSessionObject(request,kvitteringsId);
	     if(result==null){
	    	 result = new GiverKomplikasjonwebModel();
	    	 result.setFormNames(sessionParams);
	    	 result.setAldergruppe(aldergruppe);
	    	 result.setReaksjonengruppe(reaksjonengruppe);
	    	 result.setUtenforBlodbankengruppe(utenforBlodbankengruppe);  
	    	 result.setDonasjonsstedgruppe(donasjonsstedgruppe);
	     }
	     if(donasjon==null){
	    	 donasjon = new DonasjonwebModel();
	    	 donasjon.setFormNames(sessionParams);
	     }
	     if(komDiagnosegiver == null){
	    	 komDiagnosegiver = new KomDiagnosegiverwebModel();
	    	 komDiagnosegiver.setFormNames(sessionParams);
	     }
	     
	     if (giverKvittering == null){
	    	 giverKvittering = new GiverKvitteringWebModel();
	    	 giverKvittering.setFormNames(sessionParams);
	     }
	     
	     result.distributeTerms();
	     result.giverKomplikasjonDistribute();
	     result.giveroppfolgingDistribute();
	     donasjon.distributeTerms();
	     komDiagnosegiver.distributeTerms();
	     
	     dataModel.put(giverkomplikasjonId, result);
	     dataModel.put(donasjonId, donasjon);
	     dataModel.put(komDiagnosegiverId, komDiagnosegiver);
	     dataModel.put(kvitteringsId,giverKvittering);
	     
	     sessionAdmin.setSessionObject(getRequest(), result,giverkomplikasjonId);
	     sessionAdmin.setSessionObject(getRequest(), donasjon,donasjonId);
	     sessionAdmin.setSessionObject(getRequest(), komDiagnosegiver, komDiagnosegiverId);
	     sessionAdmin.setSessionObject(request,giverKvittering, kvitteringsId);
	     
	     // Load the FreeMarker template
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/pasientkomplikasjon/nymeldingfagprosedyre.html").get();
	        Representation givertkomplikasjonFtl = clres2.get();
	   
	//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	        
//	        TemplateRepresentation  templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, result,
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
    		result = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),giverkomplikasjonId);
    		donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(getRequest(), donasjonId);
    		komDiagnosegiver = (KomDiagnosegiverwebModel) sessionAdmin.getSessionObject(getRequest(),komDiagnosegiverId );
    		giverKvittering = (GiverKvitteringWebModel)sessionAdmin.getSessionObject(getRequest(),kvitteringsId);
    		
    		Parameter logout = form.getFirst("avbryt4");
    		Parameter lukk = form.getFirst("lukk4");
    	     Map<String, Object> dataModel = new HashMap<String, Object>();

    		if (logout != null || lukk != null){
    			sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
    			sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
    			sessionAdmin.getSession(getRequest(), komDiagnosegiverId).invalidate();
    			sessionAdmin.getSession(getRequest(),kvitteringsId).invalidate();
    			
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemivigilans/Logout.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    	/*	templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
	    				MediaType.TEXT_HTML);
	    	*/	
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, result,
	    				MediaType.TEXT_HTML);
    			return templateRep; // return a new page!!!
    		}
    		
    		if (result == null){
    			result = new GiverKomplikasjonwebModel();
    			 result.setFormNames(sessionParams);
    		}
 
    		if(donasjon==null){
    			donasjon = new DonasjonwebModel();
    			donasjon.setFormNames(sessionParams);
    		}
    		
    		if(komDiagnosegiver == null){
    	    	 komDiagnosegiver = new KomDiagnosegiverwebModel();
    	    	 komDiagnosegiver.setFormNames(sessionParams);
    	     }
    		
    		if (giverKvittering == null){
    			giverKvittering = new GiverKvitteringWebModel();
    			giverKvittering.setFormNames(sessionParams);
		     }
    		
    		for (Parameter entry : form) {
    			if (entry.getValue() != null && !(entry.getValue().equals("")))
    					System.out.println(entry.getName() + "=" + entry.getValue());
    			result.setValues(entry);
    			donasjon.setValues(entry);
    			komDiagnosegiver.setValues(entry);
    			giverKvittering.setValues(entry);
    		}
    		
    		sessionAdmin.setSessionObject(getRequest(), result,giverkomplikasjonId);
    		sessionAdmin.setSessionObject(getRequest(), donasjon, donasjonId);
    		sessionAdmin.setSessionObject(getRequest(), komDiagnosegiver, komDiagnosegiverId);
    		dataModel.put(giverkomplikasjonId, result);
    		dataModel.put(kvitteringsId, giverKvittering);
    		
    		Parameter lagre = form.getFirst("lagre4");
    		if(lagre!=null){
    			result.saveValues();
    			giverWebService.saveGiver(result);
    			giverWebService.saveVigilansmelding(result);
    			
    			Long giverId=	result.getGiver().getGiverid();
    			if(giverId != null){
    				donasjon.getDonasjon().setGiveId(giverId.intValue());
    			}
    		    donasjon.saveValues();
    		    donasjonWebService.saveDonasjon(donasjon);
    		    
    		    Long donasjonId = donasjon.getDonasjon().getDonasjonsId();
    		    result.getGiverKomplikasjon().setDonasjonid(donasjonId);
    		    
    		    Long meldeId = result.getVigilansmelding().getMeldeid();
    			result.getGiverKomplikasjon().setMeldeId(meldeId);
    			giverWebService.saveGiverkomplikasjon(result);
    			
    			komDiagnosegiver.getKomDiagnosegiver().setMeldeId(meldeId);
    			komDiagnosegiver.saveValues();
    			komDiagnosegiverWebService.saveKomDiagnosegiver(komDiagnosegiver);
    			
    			result.getGiveroppfolging().setMeldeid(meldeId);
    			giverWebService.saveGiveroppfolging(result);
    		    //lagre i vigiansmelding
    		}
     		ClientResource clres2  ;
  		
    		Parameter ikkegodkjet = form.getFirst("ikkegodkjent");
    		/*if(godkjent!= null){
         		// clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));

    		}else */
    		if(ikkegodkjet != null){
         		 clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_giver.html"));
    		}else{
	    		
	    		sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
	    		sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
	    		sessionAdmin.getSession(getRequest(), komDiagnosegiverId).invalidate();
	    		clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_giverkvittering.html"));
	//    		System.out.println("Status = "+result.getStatus());
	    		// Denne client resource forholder seg til src/main/resource katalogen !!!	
	   // 		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
    		}
	     	Representation pasientkomplikasjonFtl = clres2.get();
    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
    				MediaType.TEXT_HTML);
    	}
    	return templateRep;
      
    }

}
