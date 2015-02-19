package no.naks.biovigilans.web.server.resource;

import java.util.HashMap;
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

import freemarker.template.SimpleScalar;

public class RapporterteMeldingerServerResourceHTML extends
		SessionServerResource {

	private String meldingsId = "meldinger";
	private String delMelding = "delmelding";
	private String andreKey = "annenKomp"; 
	private String displayPart = "none";
	private String displayKey = "display";
	private String pasientKey = "pasientKomp"; // Nøkkel dersom melding er av type pasientkomplikasjon
	private String giverKey = "giverkomp"; 	// Nøkkel dersom melding er at type giverkomplikasjon
	
			
	
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



	public String getDisplayPart() {
		return displayPart;
	}



	public void setDisplayPart(String displayPart) {
		this.displayPart = displayPart;
	}



	public String getDisplayKey() {
		return displayKey;
	}



	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}



	public String getAndreKey() {
		return andreKey;
	}



	public void setAndreKey(String andreKey) {
		this.andreKey = andreKey;
	}



	public String getMeldingsId() {
		return meldingsId;
	}



	public void setMeldingsId(String meldingsId) {
		this.meldingsId = meldingsId;
	}



	public String getDelMelding() {
		return delMelding;
	}



	public void setDelMelding(String delMelding) {
		this.delMelding = delMelding;
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
	     Vigilansmelding melding = (Vigilansmelding) sessionAdmin.getSessionObject(request, meldingsId);
	     
	     Annenkomplikasjon annenKomplikasjon = (Annenkomplikasjon)sessionAdmin.getSessionObject(request, andreKey);
	     Pasientkomplikasjon pasientKomplikasjon = (Pasientkomplikasjon)sessionAdmin.getSessionObject(request, pasientKey);
	     Giverkomplikasjon giverKomplikasjon = (Giverkomplikasjon)sessionAdmin.getSessionObject(request,  giverKey);
	     Map<String, Object> dataModel = new HashMap<String, Object>();

	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	     SimpleScalar simple = new SimpleScalar(displayPart);
	     LocalReference localUri = new LocalReference(reference);
	     dataModel.put(meldingsId, melding);
	     dataModel.put(displayKey, simple);
	     if (annenKomplikasjon != null){
	    	 dataModel.put(andreKey,annenKomplikasjon);
	     }
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapportert_melding.html"));

	        Representation pasientkomplikasjonFtl = clres2.get();

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
    	
 	    Map<String, Object> dataModel = new HashMap<String, Object>();
 	    Reference reference = new Reference(getReference(),"..").getTargetRef();
	    Request request = getRequest();
	    Vigilansmelding melding = (Vigilansmelding) sessionAdmin.getSessionObject(request, meldingsId);
	    Annenkomplikasjon annenKomplikasjon = (Annenkomplikasjon)sessionAdmin.getSessionObject(request, andreKey);
	     Pasientkomplikasjon pasientKomplikasjon = (Pasientkomplikasjon)sessionAdmin.getSessionObject(request, pasientKey);
	     Giverkomplikasjon giverKomplikasjon = (Giverkomplikasjon)sessionAdmin.getSessionObject(request,  giverKey);
	
	     LocalReference localUri = new LocalReference(reference);
	     dataModel.put(meldingsId, melding);
	
	     if (annenKomplikasjon != null){
	    	 dataModel.put(andreKey,annenKomplikasjon);
	    	 displayPart = "block";
	   	     SimpleScalar simple = new SimpleScalar(displayPart);
	   	     dataModel.put(displayKey, simple);
	     }
 	    ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapportert_melding.html"));

	        Representation pasientkomplikasjonFtl = clres2.get();

	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
		 return templatemapRep;
    }
}
