package no.naks.biovigilans.web.server.resource;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;

import no.naks.biovigilans.web.client.ICD10;
import no.naks.biovigilans.web.client.ICD10Soap;
import no.naks.biovigilans.web.control.SessionAdmin;
import no.naks.biovigilans.web.control.TableWebService;
import no.naks.biovigilans.web.model.PasientKomplikasjon;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;

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

//import edu.unc.ils.mrc.hive2.api.HiveConcept;

/**
 * Resurser blir instansiert for hver kall fra klient
 * 
 * @author olj
 * Denne resursen håndterer all dialog for rapporter hendelse hemovigilans
 * 
 */
public class RapporterHendelseServerResourceHtml extends ProsedyreServerResource {
		private PasientKomplikasjon result = null;
		private String[] aldergruppe;
		private String[] kjonnValg; 
		private String[] blodProdukt;
		
	 public RapporterHendelseServerResourceHtml() {
			super();
			// TODO Auto-generated constructor stub
		}

/*	 
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
	
*/	
	 
	public String[] getAldergruppe() {
		return aldergruppe;
	}

	public String[] getBlodProdukt() {
		return blodProdukt;
	}

	public void setBlodProdukt(String[] blodProdukt) {
		this.blodProdukt = blodProdukt;
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
	 * Denne rutinen henter inn nødvendige session objekter og  setter opp nettsiden for å ta i mot
	 * en rapportert hendelse
	 * @return
	 */
	@Get
	public Representation getHemovigilans() {


	     Reference reference = new Reference(getReference(),"..").getTargetRef();
	
	     Request request = getRequest();

	     FileInputStream adrFile = null;
  

	     icd10WebService.readXml();
	     List<Letter> letters = icd10WebService.getLetters();
	     List<MainTerm> terms = new ArrayList();
	     for (Letter letter : letters){
	    	 terms.addAll(letter.getMainTerm());
	     }
	    
//	 	 List<MainTerm> terms = icd10WebService.getTerms();    
	     result = (PasientKomplikasjon) sessionAdmin.getSessionObject(request,"pasientkomplikasjon");
	     if (result == null){
	    	 result = new PasientKomplikasjon();
	    	 result.setFormNames(sessionParams);
	    	 result.setAldergruppe(aldergruppe);
	    	 result.setKjonnValg(kjonnValg);
	    	 result.setblodProducts(blodProdukt);
	
	    
	     }
	     result.setTerms(terms);
		 result.distributeTerms();
	     String ref = reference.toString();
	     result.setAccountRef(ref);
/*
 * En Hashmap benyttes dersom en html side henter data fra flere javaklasser.	
 * Hver javaklasse får en id (ex "pasientkomplikasjon") som er tilgjengelig for html
 *      
*/	     
	     Map<String, Object> dataModel = new HashMap<String, Object>();
	     dataModel.put("pasientkomplikasjon", result);
	     
	    
	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	    
	     LocalReference localUri = new LocalReference(reference);
	     sessionAdmin.setSessionObject(getRequest(), result,"pasientkomplikasjon");
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_hendelse.html"));
	     
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
	    	
	    	if (form == null){
	    		sessionAdmin.getSession(getRequest(),"pasientkomplikasjon").invalidate();
	    	}
	    	if (form != null){
	    		result = (PasientKomplikasjon) sessionAdmin.getSessionObject(getRequest(),"pasientkomplikasjon");
	    		Parameter logout = form.getFirst("avbryt4");
	    		Parameter lukk = form.getFirst("lukk4");
	    		if (logout != null || lukk != null){
	    			sessionAdmin.getSession(getRequest(),"pasientkomplikasjon").invalidate();
		    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemivigilans/Logout.html"));
		    		Representation pasientkomplikasjonFtl = clres2.get();
		    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, result,
		    				MediaType.TEXT_HTML);
	    			return templateRep; // return a new page!!!
	    		}
	    			
	    		if (result == null){
	    			result = new PasientKomplikasjon();
	    			 result.setFormNames(sessionParams);
	    		}
	    		for (Parameter entry : form) {
	    			System.out.println(entry.getName() + "=" + entry.getValue());
	    			result.setValues(entry);

	    		}
	    		sessionAdmin.setSessionObject(getRequest(), result,"pasientkomplikasjon");
	    	     Map<String, Object> dataModel = new HashMap<String, Object>();
	    	     dataModel.put("pasientkomplikasjon", result);
	    		Parameter lagre = form.getFirst("lagre4");
	    		if (lagre != null){
	    			result.saveValues();
	    			hendelseWebService.saveHendelse(result);
	    		}
//	    		System.out.println("Status = "+result.getStatus());
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
