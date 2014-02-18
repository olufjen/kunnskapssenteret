package no.naks.fagprosedyrer.web.server.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;










import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

import no.naks.fagprosedyrer.web.control.SessionAdmin;
import no.naks.fagprosedyrer.web.control.TableWebService;
import no.naks.fagprosedyrer.web.model.Innmelding;

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

import edu.unc.ils.mrc.hive2.api.HiveConcept;

/**
 * Resurser blir instansiert for hver kall fra klient
 * 
 * @author olj
 * Denne resursen håndterer all dialog for siden Innmelding (nye prosedyrer)
 * 
 */
public class InnmeldingServerResourceHtml extends ProsedyreServerResource {
		private Innmelding result = null;

		
	 public InnmeldingServerResourceHtml() {
			super();
			// TODO Auto-generated constructor stub
		}
	
	private void checkConcepts(ArrayList<HiveConcept> concepts,ArrayList<HiveConcept> narrower){
		for (HiveConcept concept : concepts){
			ArrayList<String> narrow = (ArrayList) concept.getNarrowerConcepts();
			if (narrow != null && !narrow.isEmpty()){
				for (String name : narrow){
					String namenarrow = name+"n";
					QName qName = new QName(name, "");
					HiveConcept newConcept = null;
					ArrayList<HiveConcept> localConcepts = null;
					try {
						newConcept =  tablewebservice.getHiveService().getVocabulary().findConcept(qName);
						narrower.add(newConcept);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (newConcept != null){
						localConcepts = tablewebservice.getHiveService().findconcepts(newConcept);
						checkConcepts(localConcepts,narrower);
					}
				}
			}
		}
	}
	public SessionAdmin getSessionAdmin() {
		return sessionAdmin;
	}

	public void setSessionAdmin(SessionAdmin sessionAdmin) {
		this.sessionAdmin = sessionAdmin;
	//	this.sessionParams = this.sessionAdmin.getSessionParams();
	}

	public TableWebService getTablewebservice() {
		return tablewebservice;
	}

	public void setTablewebservice(TableWebService tablewebservice) {
		this.tablewebservice = tablewebservice;
	}

	public String[] getSessionParams() {
		return sessionParams;
	}

	public void setSessionParams(String[] sessionParams) {
		this.sessionParams = sessionParams;
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
	     if (result == null){
	    	 result = new Innmelding();
	    	 result.setFormNames(sessionParams);
	     }
	 	QName qnameHelse = result.getqNamehelse();
	 
	 	try {
	 		result.setConceptHelse(tablewebservice.getHiveService().getVocabulary().findConcept(qnameHelse));
	 	} catch (Exception e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	QName qnamePerson = result.getqNameperson();
	 	try {
	 		result.setConceptPerson(tablewebservice.getHiveService().getVocabulary().findConcept(qnamePerson));
	 	} catch (Exception e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	result.setHivehelseConcepts(tablewebservice.getHiveService().findconcepts(result.getConceptHelse()));
	 	result.setHivePersonConcepts(tablewebservice.getHiveService().findconcepts(result.getConceptPerson()));
	 	checkConcepts(result.getHivehelseConcepts(), result.getPersonell());
	 	checkConcepts(result.getHivePersonConcepts(), result.getPasientgrupper());
	 	result.buildGroups();


	     String ref = reference.toString();
	     result.setAccountRef(ref);
	     Map<String, Object> dataModel = new HashMap<String, Object>();
	     dataModel.put("innmelding", result);
	     
	    
	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/innmelding");
	    
	     LocalReference localUri = new LocalReference(reference);
	     sessionAdmin.setSessionObject(getRequest(), result,"innmelding");
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/innmelding/nymeldingfagprosedyre.html"));
	     
	        // Load the FreeMarker template
//	        Representation innmeldingFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
//	        Representation innmeldingFtl = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/innmelding/nymeldingfagprosedyre.html").get();
	        Representation innmeldingFtl = clres2.get();
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
	     * A form must have form elements (Parameters), and they must have names
	     * Otherwise it returns null
	     * @param form
	     * @return
	     */
	    @Post
	    public Representation storeInnmelding(Form form) {
	    	TemplateRepresentation  templateRep = null;
	    	if (form == null){
	    		sessionAdmin.getSession(getRequest(),"innmelding").invalidate();
	    	}
	    	if (form != null){
	    		result = (Innmelding) sessionAdmin.getSessionObject(getRequest(),"innmelding");
	    		Parameter logout = form.getFirst("logout");
	    		if (logout != null){
	    			sessionAdmin.getSession(getRequest(),"innmelding").invalidate();
		    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/innmelding/Logout.html"));
		    		Representation innmeldingFtl = clres2.get();
		    		templateRep = new TemplateRepresentation(innmeldingFtl, result,
		    				MediaType.TEXT_HTML);
	    			return templateRep; // return a new page!!!
	    		}
	    			
	    		if (result == null){
	    			result = new Innmelding();
	    			 result.setFormNames(sessionParams);
	    		}
	    		for (Parameter entry : form) {
	    			System.out.println(entry.getName() + "=" + entry.getValue());
	    			result.setValues(entry);

	    		}
	    		sessionAdmin.setSessionObject(getRequest(), result,"innmelding");

	    		System.out.println("Status = "+result.getStatus());
	    		// Denne client resource forholder seg til src/main/resource katalogen !!!	
	    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/innmelding/nymeldingfagprosedyre.html"));
	    		Representation innmeldingFtl = clres2.get();
	    		//        Representation innmeldingFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
	    		//        Representation innmeldingFtl = new ClientResource("http:///no/naks/server/resource"+"/Innmelding.ftl").get();
	    		templateRep = new TemplateRepresentation(innmeldingFtl, result,
	    				MediaType.TEXT_HTML);
	    	}
	    	return templateRep;
	      
	    }
}
