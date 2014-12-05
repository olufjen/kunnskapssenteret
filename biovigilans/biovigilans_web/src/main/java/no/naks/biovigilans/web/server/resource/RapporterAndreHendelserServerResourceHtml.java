package no.naks.biovigilans.web.server.resource;

import java.util.HashMap;
import java.util.Map;

import no.naks.biovigilans.web.model.AnnenKomplikasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;

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

public class RapporterAndreHendelserServerResourceHtml extends SessionServerResource {
	
	public RapporterAndreHendelserServerResourceHtml (){
		super();
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
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_andrehendelser.html"));
	  //   setAndreHendelser(); // Setter opp andreHendelser session objekter
	     setTransfusjonsObjects(); 
	     annenModel.setFormNames(sessionParams);
	     annenModel.distributeTerms();
	     
	     dataModel.put(andreHendelseId, annenModel);
	     sessionAdmin.setSessionObject(getRequest(), annenModel,andreHendelseId);
	     
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
    	if(form == null){
    		invalidateSessionobjects();
    	}
 
    	if (form != null){
    		Parameter logout = form.getFirst("avbryt3");
    		Parameter lukk = form.getFirst("lukk3");
    	     Map<String, Object> dataModel = new HashMap<String, Object>();

    		if (logout != null || lukk != null){
 
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemivigilans/Logout.html"));
	    		Representation pasientkomplikasjonFtl = clres2.get();
	    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
	    				MediaType.TEXT_HTML);
    			return templateRep; // return a new page!!!
    		}
    		
  		  annenModel = (AnnenKomplikasjonwebModel)sessionAdmin.getSessionObject(getRequest(), andreHendelseId);
  		  if(annenModel == null){
  			  annenModel = new AnnenKomplikasjonwebModel();
  			  annenModel.setFormNames(sessionParams);
  		  }
    		
 
    		for (Parameter entry : form) {
    			if (entry.getValue() != null && !(entry.getValue().equals("")))
    					System.out.println(entry.getName() + "=" + entry.getValue());
    			annenModel.setValues(entry);
    		}
    		
    		sessionAdmin.setSessionObject(getRequest(), annenModel,andreHendelseId);
    		dataModel.put(andreHendelseId,annenModel);
    		Parameter lagre = form.getFirst("lagre3");
    		
    		if(lagre != null){
    			giverModel = new GiverKomplikasjonwebModel();
    			giverModel.getVigilansmelding().saveToVigilansmelding();
    			giverWebService.saveVigilansmelding(giverModel);
    			Long meldeId = giverModel.getVigilansmelding().getMeldeid();
    			annenModel.getAnnenKomplikasjon().setMeldeid(meldeId);
    			annenModel.saveValues();
    			annenKomplikasjonWebService.saveAnnenKomplikasjon(annenModel);
    		}
    		
    		
    		String page = getPage();
    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,page));
    		Representation pasientkomplikasjonFtl = clres2.get();
    		invalidateSessionobjects();
    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
    				MediaType.TEXT_HTML);
    		
    	
    	}
    	return templateRep;
      
    }


}
