package no.helsebiblioteket.plugin;

import org.junit.Test;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;

/**
 * Aug 13, 2010
 */
public class LinkCheckerPluginTest
{

    @Test
    public void dummy() {
        
    }
    
    //@Test
    public void testX()
    {
        Client client = ClientFactory.getRemoteClient( "http://localhost:18081/rpc/bin" );
        LinkCheckerPlugin linkCheckerPlugin = new LinkCheckerPlugin( client );

        linkCheckerPlugin.execute( null );
    }
}
