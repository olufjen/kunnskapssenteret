package no.naks.web.server.resource;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.naks.web.control.SessionAdmin;
import no.naks.web.model.Innmelding;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.ext.servlet.ServletUtils;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.data.Parameter;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Resurser blir instansiert for hver kall fra klient
 * 
 * @author olj
 * Denne resursen håndterer all dialog for siden Innmelding (nye prosedyrer)
 * 
 */
public class InnmeldingServerResourceHtml extends ServerResource {
		private Innmelding result = null;
		private SessionAdmin sessionAdmin = null;
	 public InnmeldingServerResourceHtml() {
			super();
			// TODO Auto-generated constructor stub
		}
	 
	public SessionAdmin getSessionAdmin() {
		return sessionAdmin;
	}

	public void setSessionAdmin(SessionAdmin sessionAdmin) {
		this.sessionAdmin = sessionAdmin;
	}

	/**
	 * getInnmelding
	 * Denne rutinen henter inn innmeldingsresurser til å håndtere forespørsel om ny prosedyre og setter opp nettsiden for å ta i mot
	 * opplysninger om ny prosedyre.
	 * @return
	 */
	@Get
	public Representation getInnmelding() {


	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	
	     Request request = getRequest();
	     
//	     HttpServletRequest req = (HttpServletRequest)request;
//	    WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//	     ServletContext servletContext =  context.getServletContext();	    
//         if(session == null){
//        	 session = (HttpSession) facesContext.getExternalContext().getSession(true);
 //            session.invalidate();
 //        }
         // create new session after logout
        

/*
 * En Hashmap benyttes dersom en html side henter data fra flere javaklasser.	
 *      
 */
	 	     
	     result = (Innmelding) sessionAdmin.getSessionObject(request,"innmelding");
	     if (result == null)
	    	 result = new Innmelding();
	     String ref = reference.toString();
	     result.setAccountRef(ref);
	     Map<String, Object> dataModel = new HashMap<String, Object>();
	     dataModel.put("innmelding", result);
	     

	     LocalReference pakke = LocalReference.createClapReference(getClass().getPackage());
	     LocalReference localUri = new LocalReference(reference);
	     LocalReference localFileref = new LocalReference("/no/naks/server/resource");
	     ClientResource clres = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html");
	        // Load the FreeMarker template
	        Representation innmeldingFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	//        Representation innmeldingFtl = new ClientResource("http:///no/naks/server/resource"+"/Innmelding.ftl").get();
	        TemplateRepresentation  templateRep = new TemplateRepresentation(innmeldingFtl, result,
	                MediaType.TEXT_HTML);
		 return templateRep;
	 }
	    @Put
	    public Representation store(Form form) {
	    	
	        for (Parameter entry : form) {
	            System.out.println(entry.getName() + "=" + entry.getValue());
	            result.setValues(entry);
	            
	        }
	        System.out.println("Status = "+result.getStatus());
	        Representation innmeldingFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    	//        Representation innmeldingFtl = new ClientResource("http:///no/naks/server/resource"+"/Innmelding.ftl").get();
	    	        TemplateRepresentation  templateRep = new TemplateRepresentation(innmeldingFtl, result,
	    	                MediaType.TEXT_HTML);
	    	return templateRep;
	      
	    }
	    /**
	     * storeInnmelding
	     * Denne rutinen tar imot alle ny informasjon fra bruker om ny fagprosedyre.
	     * @param form
	     * @return
	     */
	    @Post
	    public Representation storeInnmelding(Form form) {
	    	 result = (Innmelding) sessionAdmin.getSessionObject(getRequest(),"innmelding");
		     if (result == null)
		    	 result = new Innmelding();
	        for (Parameter entry : form) {
	            System.out.println(entry.getName() + "=" + entry.getValue());
	            result.setValues(entry);

	        }
	        sessionAdmin.setSessionObject(getRequest(), result,"innmelding");
	        
	        System.out.println("Status = "+result.getStatus());
	        Representation innmeldingFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    	//        Representation innmeldingFtl = new ClientResource("http:///no/naks/server/resource"+"/Innmelding.ftl").get();
	    	        TemplateRepresentation  templateRep = new TemplateRepresentation(innmeldingFtl, result,
	    	                MediaType.TEXT_HTML);
	    	return templateRep;
	      
	    }
}
