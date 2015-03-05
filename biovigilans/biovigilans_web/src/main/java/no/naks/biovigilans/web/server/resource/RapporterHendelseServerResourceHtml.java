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

import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.web.client.ICD10;
import no.naks.biovigilans.web.client.ICD10Soap;
import no.naks.biovigilans.web.control.SessionAdmin;
import no.naks.biovigilans.web.control.TableWebService;
import no.naks.biovigilans.web.model.MelderwebModel;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonKvitteringWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
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

import freemarker.template.SimpleScalar;

//import edu.unc.ils.mrc.hive2.api.HiveConcept;

/**
 * Resurser blir instansiert for hver kall fra klient
 * 
 * @author olj
 * Denne resursen håndterer all dialog for rapportering av transfusjonskomplikasjoner hemovigilans
 * 
 */
public class RapporterHendelseServerResourceHtml extends SessionServerResource {

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

	public String[] getAvdelinger() {
		return avdelinger;
	}

	public void setAvdelinger(String[] avdelinger) {
		this.avdelinger = avdelinger;
	}

	public String[] getBlodProdukt() {
		return blodProdukt;
	}

	public void setBlodProdukt(String[] blodProdukt) {
		this.blodProdukt = blodProdukt;
	}

	public String[] getHemolyseParametre() {
		return hemolyseParametre;
	}

	public void setHemolyseParametre(String[] hemolyseParametre) {
		this.hemolyseParametre = hemolyseParametre;
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
  
	     setTransfusjonsObjects(); // Setter opp alle session objekter
	     Map<String, Object> dataModel = new HashMap<String, Object>();
	     if (transfusjon.getVigilansmelding().getMeldingsnokkel() != null){
	    	 displayPart = "block";
	    	 datePart = "none";
	    	 Vigilansmelding melding = (Vigilansmelding)transfusjon.getPasientKomplikasjon();
	    	 transfusjon.setHendelseDato(melding.getDatoforhendelse());
	    	 transfusjon.setMeldingsNokkel(melding.getMeldingsnokkel());
	
	     }
    	 SimpleScalar simple = new SimpleScalar(displayPart);
    	 SimpleScalar hendelseDate = new SimpleScalar(datePart);
    	 dataModel.put(displayKey, simple);
    	 dataModel.put(displaydateKey, hendelseDate);

	     
	     
    	 result.setFormNames(sessionParams);
    	 transfusjon.setFormNames(sessionParams);
    	 transfusjon.setPlasmaEgenskaper(blodProdukt); // Setter plasma produkttyper
	     if (kvittering == null){
	    	 kvittering = new TransfusjonKvitteringWebModel(sessionParams);
	  //  	 kvittering.setFormNames(sessionParams);
	     }
	
		 result.distributeTerms();
	     String ref = reference.toString();
	     result.setAccountRef(ref);
	     transfusjon.setAccountRef(ref);
	     transfusjon.distributeTerms();
	 
/*
 * En Hashmap benyttes dersom en html side henter data fra flere javaklasser.	
 * Hver javaklasse får en id (ex pasientkomplikasjonId) som er tilgjengelig for html
 *      
*/	     
	
	     dataModel.put(pasientkomplikasjonId, result);
	     dataModel.put(transfusjonId,transfusjon);
	     dataModel.put(kvitteringsId,kvittering);
	     dataModel.put(melderId, melderwebModel);
	     LocalReference pakke = LocalReference.createClapReference(LocalReference.CLAP_CLASS,
                 "/hemovigilans");
	    
	     LocalReference localUri = new LocalReference(reference);
	     sessionAdmin.setSessionObject(getRequest(), result,pasientkomplikasjonId);
	     sessionAdmin.setSessionObject(getRequest(), transfusjon,transfusjonId);
	     sessionAdmin.setSessionObject(request,kvittering, kvitteringsId);
	     sessionAdmin.setSessionObject(getRequest(), melderwebModel,melderId);
// Denne client resource forholder seg til src/main/resource katalogen !!!	
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_transfusjon.html"));
	     
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
	    		invalidateSessionobjects();
	      	}
	    	if (form != null){
	    		Representation webTransfusjon = form.getWebRepresentation();
	    		result = (PasientKomplikasjonWebModel) sessionAdmin.getSessionObject(getRequest(),pasientkomplikasjonId);
	    		transfusjon = (TransfusjonWebModel) sessionAdmin.getSessionObject(getRequest(),transfusjonId);
	    	    kvittering = (TransfusjonKvitteringWebModel)sessionAdmin.getSessionObject(getRequest(),kvitteringsId);
	    	     melderwebModel = ( MelderwebModel)sessionAdmin.getSessionObject(getRequest(),melderId);
	    		Parameter logout = form.getFirst("avbryt4");
	    		Parameter lukk = form.getFirst("lukk4");
	    		if (logout != null || lukk != null){
	    			invalidateSessionobjects();
		    		ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemivigilans/Logout.html"));
		    		Representation pasientkomplikasjonFtl = clres2.get();
		    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, result,
		    				MediaType.TEXT_HTML);
	    			return templateRep; // return a new page!!!
	    		}
	    		List<MainTerm> terms = new ArrayList();	
	    		if (result == null){
	    		     icd10WebService.readXml();
	    		     List<Letter> letters = icd10WebService.getLetters();
	    		     for (Letter letter : letters){
	    		    	 terms.addAll(letter.getMainTerm());
	    		     }
	    			result = new PasientKomplikasjonWebModel();
	    			 result.setFormNames(sessionParams);
	    	    	 result.setAldergruppe(aldergruppe);
	    	    	 result.setKjonnValg(kjonnValg);
	    	    	 result.setblodProducts(blodProdukt);
	    	    	 result.setHemolyseparams(hemolyseParametre);
	    	    	 result.setAvdelinger(avdelinger);
	    		     result.setTerms(terms);
	    			 result.distributeTerms();
	    		   
	    	
	    		}
	   	     	if (transfusjon == null){
		    	 transfusjon = new TransfusjonWebModel();
		    	 transfusjon.setFormNames(sessionParams);
		    	 transfusjon.setPlasmaEgenskaper(blodProdukt); // Setter plasma produkttyper
	   	     	}
	   	     if (kvittering == null){
		    	 kvittering = new TransfusjonKvitteringWebModel(sessionParams);
		    	 //kvittering.setFormNames(sessionParams);
		     }
	   	     if (melderwebModel == null){
	   	    	 melderwebModel = new MelderwebModel();
	   	    	 melderwebModel.setFormNames(sessionParams);
	   	     }
	    		for (Parameter entry : form) {
	    			if (entry.getValue() != null && !(entry.getValue().equals("")))
	    					System.out.println(entry.getName() + "=" + entry.getValue());
	    			result.setValues(entry);
	    			transfusjon.setValues(entry);
	    			kvittering.setValues(entry);
	    			melderwebModel.setValues(entry);

	    		}
	    		sessionAdmin.setSessionObject(getRequest(), result,pasientkomplikasjonId);
	    	    sessionAdmin.setSessionObject(getRequest(), transfusjon,transfusjonId);
	    	    sessionAdmin.setSessionObject(getRequest(), melderwebModel,melderId);
	    	    Map<String, Object> dataModel = new HashMap<String, Object>();
	    	    dataModel.put(pasientkomplikasjonId, result);
	    	    dataModel.put(transfusjonId,transfusjon);
	    	    dataModel.put(kvitteringsId,kvittering);
	    	    dataModel.put(melderId, melderwebModel);
/*	
 * IKKE I BRUK    	    
	    	    Parameter hemolyse = form.getFirst("p_hemolyseleggtil");
	    	    if (hemolyse != null){
	    	        ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_transfusjon.html"));
	    	        clres2.put(form);
	    	        Representation pasientkomplikasjonFtl = clres2.get();

	    	        TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	    	                MediaType.TEXT_HTML);
	    	        return  templatemapRep;
	    	    }
*/	    	    
	    		Parameter lagre = form.getFirst("btnSendinn");
	    		if (lagre != null){
	    			result.saveValues();
	    		
	    			transfusjon.saveValues();
	    			result.getPasient().getTransfusjoner().put(transfusjon.getTransfusjon().getTransDato(), transfusjon.getTransfusjon());
	    			Vigilansmelding melding = (Vigilansmelding)transfusjon.getPasientKomplikasjon();
	    			melding.setDatoforhendelse(transfusjon.getTransfusjon().getTransfusionDate());
	    			hendelseWebService.saveHendelse(result);
	    			hendelseWebService.saveTransfusjon(transfusjon,result);
	    			transfusjon.setTransfusjonsFlag(kvittering); //Setter opp ulike feltverdier for TransfusjonKvitteringWebModel.
	    			result.setLagret(true);
	    			transfusjon.setLagret(true);
//	    			klassifikasjon.setMeldeidannen(meldeid);

	    			transfusjon.getKomplikasjonsklassifikasjon().setMeldeidpasient(transfusjon.getPasientKomplikasjon().getMeldeid());
	    			komplikasjonsklassifikasjonWebService.saveKomplikasjonsklassifikasjon(transfusjon.getKomplikasjonsklassifikasjon());
	    			Long melderKey = melderwebModel.getMelder().getMelderId();
	    			if (melderKey != null){
	    				transfusjon.getPasientKomplikasjon().setMelderId(melderKey);
	    				melding = (Vigilansmelding)transfusjon.getPasientKomplikasjon();
	    				hendelseWebService.saveVigilansMelder(melding);
	    			}
	    			///ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_transfusjonkvittering.html"));
		    		///Representation pasientkomplikasjonFtl = clres2.get();
		    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
		    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
		    		
		    		///templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,MediaType.TEXT_HTML);
	    			redirectPermanent("../hemovigilans/rapporter_kontakt.html");
	    		}else{
	    		

					ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/rapporter_kontakt.html"));
		    		Representation pasientkomplikasjonFtl = clres2.get();
		    		//        Representation pasientkomplikasjonFtl = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html").get();
		    		//        Representation pasientkomplikasjonFtl = new ClientResource("http:///no/naks/server/resource"+"/pasientkomplikasjon.ftl").get();
		    		templateRep = new TemplateRepresentation(pasientkomplikasjonFtl, dataModel,
				    				MediaType.TEXT_HTML);
	    		}
	    		
	    
	    	}
	    	return templateRep;
	      
	    }
}
