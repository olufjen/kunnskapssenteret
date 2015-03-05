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
import org.restlet.data.Parameter;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import freemarker.template.SimpleScalar;

public class RapporterStartServerResourceHTML extends SessionServerResource {

	
	private String delMelding = "delmelding";
	private String meldeTxtId = "melding";



	public String getDelMelding() {
		return delMelding;
	}

	public void setDelMelding(String delMelding) {
		this.delMelding = delMelding;
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

	private void setMeldingsValues(Vigilansmelding lokalMelding,Vigilansmelding melding){
			lokalMelding.setMeldingsnokkel(melding.getMeldingsnokkel());
			lokalMelding.setDatoforhendelse(melding.getDatoforhendelse());
	//		lokalMelding.setMeldeid(melding.getMeldeid());
			lokalMelding.setMelderId(melding.getMelderId());
			lokalMelding.setDatooppdaget(melding.getDatooppdaget());
			lokalMelding.setDonasjonoverforing(melding.getDonasjonoverforing());
			lokalMelding.setSjekklistesaksbehandling(melding.getSjekklistesaksbehandling());
			lokalMelding.setSupplerendeopplysninger(melding.getSupplerendeopplysninger());
			lokalMelding.setMeldingsdato(melding.getMeldingsdato());
			lokalMelding.setKladd(melding.getKladd());
			lokalMelding.setGodkjent(melding.getGodkjent());
			lokalMelding.setMeldeid(null);
	}
	/**
	 * getHemovigilans
	 * Denne rutinen henter inn nødvendige session objekter og  setter opp nettsiden for å ta i mot
	 * en meldingsnøkkel til en oppfølgingsmelding
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
     * Denne rutinen tar imot meldingsnøkkel og henter frem riktig meldingsinformasjon fra
     * databasen basert på meldingsnøkkel
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
 	    List<Vigilansmelding> meldinger = null;
 //	    List delMeldinger = null;
 	    List<Vigilansmelding> andreMeldinger = null;
 	    List<Vigilansmelding> pasientMeldinger = null;
 	    List<Vigilansmelding> giverMeldinger = null;
 	    
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
	    String page = "../hemovigilans/rapportert_melding.html"; 
    	if (formValue != null && meldingsNokkel != null){
    		alleMeldinger = melderWebService.selectMeldinger(meldingsNokkel);
    		meldinger = alleMeldinger.get(meldingsNokkel);
    		andreMeldinger = alleMeldinger.get(andreKey);
    		pasientMeldinger = alleMeldinger.get(pasientKey);
    		giverMeldinger = alleMeldinger.get(giverKey);
     
    		Vigilansmelding melding = null;
    		if (!meldinger.isEmpty())
    			melding = meldinger.get(0);  
    		
    		if (andreMeldinger != null){
    			if (!andreMeldinger.isEmpty()){
        			Annenkomplikasjon annenKomplikasjon = (Annenkomplikasjon)andreMeldinger.get(0);
        			Vigilansmelding lokalMelding = (Vigilansmelding) annenKomplikasjon;
        			setMeldingsValues(lokalMelding, melding);
        		
        			sessionAdmin.setSessionObject(request, annenKomplikasjon,andreKey);
        			sessionAdmin.setSessionObject(request, lokalMelding, meldingsId);
        		} 			
    		}
      		if (pasientMeldinger != null){
    			if (!pasientMeldinger.isEmpty()){
  //  			   page = "../hemovigilans/rapportert_pasient.html";
        			Pasientkomplikasjon pasientKomplikasjon = (Pasientkomplikasjon)pasientMeldinger.get(0);
        			Vigilansmelding lokalMelding = (Vigilansmelding) pasientKomplikasjon;
        			setMeldingsValues(lokalMelding, melding);
        			sessionAdmin.setSessionObject(request, lokalMelding, meldingsId);       			
        			sessionAdmin.setSessionObject(request, pasientKomplikasjon,pasientKey);
        		} 			
    		}
 
      		if (giverMeldinger != null){
    			if (!giverMeldinger.isEmpty()){
  //  				 page = "../hemovigilans/rapportert_giver.html";
        			Giverkomplikasjon giverKomplikasjon = (Giverkomplikasjon)giverMeldinger.get(0);
        			Vigilansmelding lokalMelding = (Vigilansmelding) giverKomplikasjon;
        			setMeldingsValues(lokalMelding, melding);
        			sessionAdmin.setSessionObject(request, giverKomplikasjon,giverKey);
        			sessionAdmin.setSessionObject(request, lokalMelding, meldingsId);
        		} 			
    		}
    	      		
    	}
    	if (meldinger != null){
        	if (meldinger.isEmpty()){
        		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/startside.html"));
        		Representation pasientkomplikasjonFtl = clres2.get();
        		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
        				MediaType.TEXT_HTML);	
        	}
        	
        	if (!meldinger.isEmpty()){
        	//	Vigilansmelding melding = (Vigilansmelding) meldinger.get(0);
        	//	sessionAdmin.setSessionObject(request, melding, meldingsId);
        		redirectPermanent(page);
        	}    		
    	}else{
     		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/startside.html"));
    		Representation pasientkomplikasjonFtl = clres2.get();
    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
    				MediaType.TEXT_HTML);	
    	}


    	return templateRep;
    }
}
