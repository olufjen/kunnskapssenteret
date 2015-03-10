package no.naks.biovigilans.web.server.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.model.Giverkomplikasjon;
import no.naks.biovigilans.model.Pasientkomplikasjon;
import no.naks.biovigilans.model.Vigilansmelding;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public class MelderRapportServerResourceHTML extends SessionServerResource {

	private String meldeKey = "meldinger";
	/**
	 * getHemovigilans
	 * Denne rutinen henter inn nødvendige session objekter og  
	 * henter frem siden for oppfølgingsmeldinger
	 * @return
	 */
	@Get
	public Representation getHemovigilans() {
		
		 List<Vigilansmelding> meldinger = null;
	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	     Request request = getRequest();
	     Vigilansmelding melding = (Vigilansmelding) sessionAdmin.getSessionObject(request, meldingsId);
	   	 String page = "/hemovigilans/melder_rapport.html";
	     annenKomplikasjon = (Annenkomplikasjon)sessionAdmin.getSessionObject(request, andreKey);
	     pasientKomplikasjon = (Pasientkomplikasjon)sessionAdmin.getSessionObject(request, pasientKey);
	     giverKomplikasjon = (Giverkomplikasjon)sessionAdmin.getSessionObject(request,  giverKey);
	     Map<String, List> dataModel = new HashMap<String, List>();
	     Long melderId = null;
	     if (annenKomplikasjon != null){
	    	melderId = annenKomplikasjon.getMelderId();
	    	 
	     }
	     if (giverKomplikasjon != null){
	    	melderId = giverKomplikasjon.getMelderId();
	     }
	     if (pasientKomplikasjon != null){
	    	 melderId = pasientKomplikasjon.getMelderId();
	     }
	     if (melderId != null){
	    	 meldinger = hendelseWebService.collectMeldinger(melderId);
	    	 
		     Vigilansmelding[] meldingene = new Vigilansmelding[meldinger.size()];
		     int index = 0;
			 for (Vigilansmelding lokalmelding : meldinger){
				 meldingene[index] = lokalmelding;
				 index++;
			 }
	     }

	   
	     dataModel.put(meldeKey,meldinger);
//	     meldingene = (Vigilansmelding) meldinger.toArray();
	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	     LocalReference localUri = new LocalReference(reference);
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,page));

	        Representation pasientkomplikasjonFtl = clres2.get();

	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
		 return templatemapRep;
	}
	
	  /**
     * storeHemovigilans
     * Denne rutinen tar imot meldingsnøkkel fra bruker og henter frem meidngsinformasjon basert på 
     * oppgitt meldingsnøkkel
     * @param form
     * @return
     */
    @Post
    public Representation storeHemovigilans(Form form) {
    	
 	    Map<String, Object> dataModel = new HashMap<String, Object>();
 	    Reference reference = new Reference(getReference(),"..").getTargetRef();
	    Request request = getRequest();
	    Vigilansmelding melding = (Vigilansmelding) sessionAdmin.getSessionObject(request, meldingsId);
	    annenKomplikasjon = (Annenkomplikasjon)sessionAdmin.getSessionObject(request, andreKey);
	    pasientKomplikasjon = (Pasientkomplikasjon)sessionAdmin.getSessionObject(request, pasientKey);
	    giverKomplikasjon = (Giverkomplikasjon)sessionAdmin.getSessionObject(request,  giverKey);
	
	     LocalReference localUri = new LocalReference(reference);
	     dataModel.put(meldingsId, melding);
	   	 String page = "/hemovigilans/melder_rapport.html";
		    ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,page));

	        Representation pasientkomplikasjonFtl = clres2.get();

	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
		 return templatemapRep;
    }
}
