package no.naks.fagprosedyrer.web.server;

import java.util.logging.Logger;

import no.naks.fagprosedyrer.web.server.resource.InnmeldingServerResource;
import no.naks.fagprosedyrer.web.server.resource.InnmeldingServerResourceHtml;
import no.naks.fagprosedyrer.web.server.resource.MailServerResource;
import no.naks.fagprosedyrer.web.server.resource.RootServerResource;
import no.naks.fagprosedyrer.web.server.resource.ToxmlServerresource;
import no.naks.fagprosedyrer.web.server.resource.Tracer;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.LocalReference;
import org.restlet.ext.spring.SpringBeanRouter;
import org.restlet.resource.ClientResource;
import org.restlet.routing.Router;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FagprosedyreApiApplication extends Application {
	private SpringBeanRouter root;
	
	public FagprosedyreApiApplication() {
		// TODO Auto-generated constructor stub
/*		
        setName("RESTful Fagprosedyre Server application");
        setDescription("Example application ");
        setOwner("Kunnskapssenteret.");
        setAuthor("Oluf Jensen");
*/        
        LocalReference pakke = LocalReference.createClapReference(getClass().getPackage());
        String ref = pakke.toString();
/*        
        ApplicationContext appContext = 
      	   new ClassPathXmlApplicationContext(new String[] {"**/
/*        applicationContext.xml"});
        ClientResource clres = new ClientResource(LocalReference.createClapReference(getClass().getPackage())+ "/html/nymeldingfagprosedyre.html");
        Context restContext = (Context)appContext.getBean("componentChildContext");
*/       
//        Logger logger = restContext.getLogger();
       

  
        System.out.println("Fagprosedyre API applikasjon: Startet");
 
	}
    /**
     * Creates a root Router to dispatch call to server resources.
     */
	
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        Context context = getContext();
    	System.out.println("Fagprosedyre API: initialisering Restlet ");
        router.attach("/", RootServerResource.class);
        Tracer tracer = new Tracer(getContext());
        router.attach("/tracer", tracer);
        router.attach("/toxml/{accountId}/mails/{mailId}",
                ToxmlServerresource.class);
//        setInboundRoot(router);
     
        router.attach("/innmelding/{accountId}/mails/{mailId}",InnmeldingServerResource.class);
 //       router.attach("/melding/",InnmeldingServerResourceHtml.class);
        return router;
    }
	public SpringBeanRouter getRoot() {
		return root;
	}
	public void setRoot(SpringBeanRouter root) {
		this.root = root;
	}
}
