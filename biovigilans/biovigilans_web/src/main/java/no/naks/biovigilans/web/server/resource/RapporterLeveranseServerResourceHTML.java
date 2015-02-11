package no.naks.biovigilans.web.server.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import no.naks.biovigilans.web.model.AnnenKomplikasjonwebModel;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.biovigilans.web.model.VigilansModel;

import org.restlet.Request;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;

/**
 * RapporterLeveranseServerResourceHTML
 * @author olj
 *
 * Denne klassen er knyttet til leveranse.html
 * Det er den endelige kvitteringssiden for en melding til Hemovigilans
 */
public class RapporterLeveranseServerResourceHTML extends SessionServerResource {

	private String meldingsId = "melding";
	private Date dato = null;
	
	
	public String getMeldingsId() {
		return meldingsId;
	}


	public void setMeldingsId(String meldingsId) {
		this.meldingsId = meldingsId;
	}


	public Date getDato() {
		return dato;
	}


	public void setDato(Date dato) {
		this.dato = dato;
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

/*
 * Hent alle session objekter
 */
	//     String messageType = checkMessageType();
	     VigilansModel melding = checkMessageType();
	     TransfusjonWebModel transfusjon = null;
	     GiverKomplikasjonwebModel giver = null;
	     AnnenKomplikasjonwebModel annen = null;
	     
//	     setTransfusjonsObjects();
//	     setAndreHendelser();
	     Map<String, Object> dataModel = new HashMap<String, Object>();
//    	 melderwebModel.setFormNames(sessionParams);
//    	 melderwebModel.distributeTerms();
    	 if (messageType.equals("transfusjon")){
    		 transfusjon = (TransfusjonWebModel)melding;
    		 dataModel.put(meldingsId, transfusjon);
    	 }
    	 if (messageType.equals("giver")){
    		 giver = (GiverKomplikasjonwebModel)melding;
    		 dataModel.put(meldingsId, giver);
    	 }
    	 if (messageType.equals("annen")){
    		 annen = (AnnenKomplikasjonwebModel)melding;
    		 dataModel.put(meldingsId, annen);
    	 }
    	 dato = melding.getVigilans().getDatoforhendelse();
     
	     ClientResource clres2 = new ClientResource(LocalReference.createClapReference(LocalReference.CLAP_CLASS,"/hemovigilans/leveranse.html"));
	     Representation pasientkomplikasjonFtl = clres2.get();
	     TemplateRepresentation  templatemapRep = new TemplateRepresentation(pasientkomplikasjonFtl,dataModel,
	                MediaType.TEXT_HTML);
    	 return templatemapRep;
	}
}
