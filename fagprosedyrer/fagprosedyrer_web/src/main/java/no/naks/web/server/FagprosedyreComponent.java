package no.naks.web.server;

import org.restlet.Application;
import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;

public class FagprosedyreComponent extends Component {

	  /**
     * Launches the mail server component.
     * 
     * @param args
     *            The arguments.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new FagprosedyreComponent().start();
    }

    /**
     * Constructor.
     * 
     * @throws Exception
     */
    public FagprosedyreComponent() throws Exception {
        // Set basic properties
        setName("RESTful Fagprosedyre Server component");
        setDescription("Example for 'Restlet in Action' book");
        setOwner("Kunnskapssenteret.");
        setAuthor("Oluf Jensen");

        // Add connectors
/*        
        getClients().add(new Client(Protocol.CLAP));
        Server server = new Server(new Context(), Protocol.HTTP, 8111);
        server.getContext().getParameters().set("tracing", "true");
        getServers().add(server);
*/
        // Configure the default virtual host
        VirtualHost host = getDefaultHost();
        // host.setHostDomain("www\\.rmep\\.com|www\\.rmep\\.net|www\\.rmep\\.org");
        // host.setServerAddress("1\\.2\\.3\\.10|1\\.2\\.3\\.20");
        // host.setServerPort("80");

        // Attach the application to the default virtual host
        
        //Z:\prosjekter\restlet\org.restlet.example\src\org\restlet\example\book\restlet\ch03\sec3\server
        
//        Application fagprosedyre = new FagprosedyreApplication();
        
        host.attachDefault(new FagprosedyreSiteApplication());
  //      host.attach("/", fagprosedyre);
        // Configure the log service
 /*      
        getLogService().setLoggerName("Fagprosedyre.AccessLog");
        getLogService()
                .setLogPropertiesRef(
                        "clap://system/no/naks/web/server/log.properties");
*/                        
    	System.out.println("Fagprosedyre Component: initialisering");
    }	
}
