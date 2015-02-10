package no.naks.biovigilans.web.server.resource;

import org.restlet.Request;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;

/**
 * RapporterLeveranseServerResourceHTML
 * @author olj
 *
 * Denne klassen er knyttet til leveranse.html
 * Det er den endelige kvitteringssiden for en melding til Hemovigilans
 */
public class RapporterLeveranseServerResourceHTML extends SessionServerResource {

	
	
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
	     setTransfusjonsObjects();
	     setAndreHendelser();
    	 melderwebModel.setFormNames(sessionParams);
    	 melderwebModel.distributeTerms();
    	 
    	 
    	 return null;
	}
}
