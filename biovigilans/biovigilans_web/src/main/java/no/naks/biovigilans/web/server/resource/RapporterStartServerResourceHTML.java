package no.naks.biovigilans.web.server.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.model.Vigilansmelding;

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

public class RapporterStartServerResourceHTML extends SessionServerResource {

	private String meldingsId = "meldinger";
	private String delMelding = "delmelding";
	private String meldeTxtId = "melding";
	private String andreKey = "annenKomp"; 		// Nøkkel dersom melding er av type annenkomplikasjon
	private String pasientKey = "pasientKomp"; // Nøkkel dersom melding er av type pasientkomplikasjon
	private String giverKey = "giverkomp"; 	// Nøkkel dersom melding er at type giverkomplikasjon
	
	
	public String getDelMelding() {
		return delMelding;
	}

	public void setDelMelding(String delMelding) {
		this.delMelding = delMelding;
	}

	public String getMeldingsId() {
		return meldingsId;
	}

	public void setMeldingsId(String meldingsId) {
		this.meldingsId = meldingsId;
	}

	public String getMeldeTxtId() {
		return meldeTxtId;
	}

	public void setMeldeTxtId(String meldeTxtId) {
		this.meldeTxtId = meldeTxtId;
	}

	public String getAndreKey() {
		return andreKey;
	}

	public void setAndreKey(String andreKey) {
		this.andreKey = andreKey;
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
	     Map<String, Object> dataModel = new HashMap<String, Object>();
	     String meldingsText = " ";
	     SimpleScalar simple = new SimpleScalar(meldingsText);
		    dataModel.put(meldeTxtId,simple );
	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	    
	     LocalReference localUri = new LocalReference(reference);
	
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/startside.html"));

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
    	TemplateRepresentation  templateRep = null;
 	    Map<String, Object> dataModel = new HashMap<String, Object>();
 	    String meldingsText = "Meldingsnøkkel finnes ikke, prøv igjen";
	    dataModel.put( meldeTxtId, meldingsText);
	    Request request = getRequest();
	    Map<String,List> alleMeldinger = new HashMap<String,List>();
 	    List meldinger = null;
 	    List delMeldinger = null;
    	if(form == null){
    		invalidateSessionobjects();
    	}
    	String meldingsNokkel = null;
    	for (Parameter entry : form) {
			if (entry.getValue() != null && !(entry.getValue().equals(""))){
					System.out.println(entry.getName() + "=" + entry.getValue());
					if (entry.getName().equals("k-meldingsnokkel")){
						meldingsNokkel = entry.getValue();
					}
			}
			
    	}
		Parameter formValue = form.getFirst("formValue"); // Bruker oppgir meldingsnøkkel
    	if (formValue != null && meldingsNokkel != null){
    		alleMeldinger = melderWebService.selectMeldinger(meldingsNokkel);
    		meldinger = alleMeldinger.get(meldingsNokkel);
    		delMeldinger = alleMeldinger.get(andreKey);
    		if (!delMeldinger.isEmpty()){
    			Annenkomplikasjon annenKomplikasjon = (Annenkomplikasjon)delMeldinger.get(0);
    			sessionAdmin.setSessionObject(request, annenKomplikasjon,andreKey);
    		}
    	}
    	
    	if (meldinger.isEmpty()){
    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/startside.html"));
    		Representation pasientkomplikasjonFtl = clres2.get();
    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
    				MediaType.TEXT_HTML);	
    	}
    	
    	if (!meldinger.isEmpty()){
    		Vigilansmelding melding = (Vigilansmelding) meldinger.get(0);
    		sessionAdmin.setSessionObject(request, melding, meldingsId);
    		redirectPermanent("../hemovigilans/rapportert_melding.html");
    	}

    	return templateRep;
    }
}
