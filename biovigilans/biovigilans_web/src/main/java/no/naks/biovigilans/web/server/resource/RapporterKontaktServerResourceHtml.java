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

public class RapporterKontaktServerResourceHtml extends SessionServerResource {

	public RapporterKontaktServerResourceHtml() {
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


	     setTransfusjonsObjects();
    	 melderwebModel.setFormNames(sessionParams);
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
    		
    		if(lagre != null){
    			melderwebModel.saveValues();
    			melderWebService.saveMelder(melderwebModel);
    			Long melderKey = melderwebModel.getMelder().getMelderId();
    			if (melderKey != null && transfusjon != null){
    				if (transfusjon.isLagret()){
    					transfusjon.getPasientKomplikasjon().setMelderId(melderKey);
    					Vigilansmelding melding = (Vigilansmelding)transfusjon.getPasientKomplikasjon();
    					hendelseWebService.saveVigilansMelder(melding);
    				}
    			}
       			if (melderKey != null && giverModel != null){
    				if (giverModel.isLagret()){
    					giverModel.getGiverKomplikasjon().setMelderId(melderKey);
    					Vigilansmelding melding = (Vigilansmelding)giverModel.getGiverKomplikasjon();
    					hendelseWebService.saveVigilansMelder(melding);
    				}
    			}
    			if (melderKey != null && annenModel != null){
    				if (annenModel.isLagret()){
    					annenModel.getAnnenKomplikasjon().setMelderId(melderKey); 
    					Vigilansmelding melding = (Vigilansmelding)giverModel.getGiverKomplikasjon();
    					hendelseWebService.saveVigilansMelder(melding);
    				}
    			}   			
    			sessionAdmin.setSessionObject(getRequest(), melderwebModel, melderId);
        		dataModel.put(melderId, melderwebModel);
        		String page = getPage();
    			ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,page));
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
	    		
    		}else if(formValue != null){
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
    	return templateRep;
      
    }

/*
    public void sendEmail(){
    	Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("qadeeralvi@gmail.com","XXXX");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("qadeeralvi@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("qadeeralvi@yahoo.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
    */
}
