package no.naks.biovigilans.web.server.resource;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.naks.biovigilans.model.DonasjonImpl;
import no.naks.biovigilans.web.model.DonasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
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

public class RapporterGiverServerResourceHtml extends ProsedyreServerResource {
	private GiverKomplikasjonwebModel result=null;
	private DonasjonwebModel donasjon = null;
	private String[] aldergruppe;
	private String[] kjonnValg; 
	private String giverkomplikasjonId="giverkomplikasjon";
	private String donasjonId ="donasjon";
	private String vigilansmeldingId="vigilansmelding";
	
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
	
	     if(result==null){
	    	 result = new GiverKomplikasjonwebModel();
	    	 result.setFormNames(sessionParams);
	    	 result.setAldergruppe(aldergruppe);
	    	 
	    	 
	     }
	     if(donasjon==null){
	    	 donasjon = new DonasjonwebModel();
	    	 donasjon.setFormNames(sessionParams);
	     }
	     
	     result.distributeTerms();
	     result.giverKomplikasjonDistribute();
	     result.giveroppfolgingDistribute();
	     donasjon.distributeTerms();
	     
	     dataModel.put(giverkomplikasjonId, result);
	     dataModel.put(donasjonId, donasjon);
	     
	     sessionAdmin.setSessionObject(getRequest(), result,giverkomplikasjonId);
	     
	     sessionAdmin.setSessionObject(getRequest(), donasjon,donasjonId);
	     
	     // Load the FreeMarker template
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
//	        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/pasientkomplikasjon/nymeldingfagprosedyre.html").get();
	        Representation givertkomplikasjonFtl = clres2.get();
	//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
	        
//	        TemplateRepresentation  templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, result,
//	                MediaType.TEXT_HTML);
	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(givertkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
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
    	}
 
    	if (form != null){
    		result = (GiverKomplikasjonwebModel) sessionAdmin.getSessionObject(getRequest(),giverkomplikasjonId);
    		donasjon = (DonasjonwebModel) sessionAdmin.getSessionObject(getRequest(), donasjonId);
    		Parameter logout = form.getFirst("avbryt4");
    		Parameter lukk = form.getFirst("lukk4");
    	     Map<String, Object> dataModel = new HashMap<String, Object>();

    		if (logout != null || lukk != null){
    			sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
    			sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
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
    		
    		for (Parameter entry : form) {
    			if (entry.getValue() != null && !(entry.getValue().equals("")))
    					System.out.println(entry.getName() + "=" + entry.getValue());
    			result.setValues(entry);
    			donasjon.setValues(entry);
    		}
    		
    		sessionAdmin.setSessionObject(getRequest(), result,giverkomplikasjonId);
    		sessionAdmin.setSessionObject(getRequest(), donasjon, donasjonId);
    		dataModel.put(giverkomplikasjonId, result);
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
    			
    			result.getGiveroppfolging().setMeldeid(meldeId);
    			giverWebService.saveGiveroppfolging(result);
    		    //lagre i vigiansmelding
    		    
    		   
    		}
    		sessionAdmin.getSession(getRequest(),giverkomplikasjonId).invalidate();
    		sessionAdmin.getSession(getRequest(), donasjonId).invalidate();
//    		System.out.println("Status = "+result.getStatus());
    		// Denne client resource forholder seg til src/main/resource katalogen !!!	
    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/hemovigilans.html"));
    		Representation pasientkomplikasjonFtl = clres2.get();
    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
    				MediaType.TEXT_HTML);
    	}
    	return templateRep;
      
    }

}
