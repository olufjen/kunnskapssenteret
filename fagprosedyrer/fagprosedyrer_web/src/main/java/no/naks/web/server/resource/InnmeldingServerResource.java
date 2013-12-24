package no.naks.web.server.resource;

import no.naks.web.model.Innmelding;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.restlet.engine.header.Header;

public class InnmeldingServerResource extends ServerResource {

    @Get
    public Innmelding represent() throws Exception {
    Innmelding result = null;

    


        result = new Innmelding(); 
        
        Series<Header> headers = new Series<Header>(Header.class);
        headers.set("Access-Control-Expose-Headers", "Link, X-RateLimit-Limit, X-RateLimit-Remaining, X-OAuth-Scopes, X-Accepted-OAuth-Scopes");
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Credentials", "true");
        getResponseAttributes().put("org.restlet.http.headers", headers);

    return result;
    }

    // Define allowed roles for the method "put".
    private static final String[] put730AllowedRoles = new String[] {"cellroledev"};
    // Define denied roles for the method "put".
    private static final String[] put730DeniedRoles = new String[] {};

    @Put
    public Innmelding store(Innmelding bean) throws Exception {
    Innmelding result = null;
 


        result = new Innmelding(); 

    

        Series<Header> headers = new Series<Header>(Header.class);
        headers.set("Access-Control-Expose-Headers", "Link, X-RateLimit-Limit, X-RateLimit-Remaining, X-OAuth-Scopes, X-Accepted-OAuth-Scopes");
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Credentials", "true");
        getResponseAttributes().put("org.restlet.http.headers", headers);

    return result;
    }

}
