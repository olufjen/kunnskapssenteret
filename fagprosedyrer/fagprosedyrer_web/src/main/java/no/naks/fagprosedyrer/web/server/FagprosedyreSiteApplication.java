package no.naks.fagprosedyrer.web.server;

import no.naks.fagprosedyrer.web.server.resource.MailServerResource;
import no.naks.fagprosedyrer.web.server.resource.RootServerResource;
import no.naks.fagprosedyrer.web.server.resource.Tracer;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.routing.Router;

public class FagprosedyreSiteApplication extends Application {

    /**
     * Constructor.
     */
    public FagprosedyreSiteApplication() {
        setName("RESTful Fagprosedyre Server application");
        setDescription("Example application ");
        setOwner("Kunnskapssenteret.");
        setAuthor("Oluf Jensen");
        

        System.out.println("Fagprosedyre SITE applikasjon: Startet");
    }

    /**
     * Creates a root Router to dispatch call to server resources.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        Context context = getContext();
    	System.out.println("Fagprosedyre applikasjon: initialisering Restlet ");
        router.attach("/", RootServerResource.class);
        Tracer tracer = new Tracer(getContext());
        router.attach("/tracer", tracer);
        router.attach("/mail/{accountId}/mails/{mailId}",  MailServerResource.class);
     //   router.
    //    setInboundRoot(router);
        return router;
   /*     
        return new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                String entity = "Method       : " + request.getMethod()
                        + "\nResource URI : " + request.getResourceRef()
                        + "\nIP address   : "
                        + request.getClientInfo().getAddress()
                        + "\nAgent name   : "
                        + request.getClientInfo().getAgentName()
                        + "\nAgent version: "
                        + request.getClientInfo().getAgentVersion();
                response.setEntity(entity, MediaType.TEXT_PLAIN);
            }
      
        
    };
    */
    }
}
