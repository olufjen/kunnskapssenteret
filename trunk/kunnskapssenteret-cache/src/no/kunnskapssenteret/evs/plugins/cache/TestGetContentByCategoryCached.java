package no.kunnskapssenteret.evs.plugins.cache;

import com.enonic.cms.api.client.ClientFactory;
import java.io.IOException;

import no.kunnskapssenteret.evs.cache.Utilities;

public class TestGetContentByCategoryCached {

    public TestGetContentByCategoryCached()
    {
    }

    public static void main(String args[])
        throws IOException, Exception
    {
        GetContentByCategoryCached getcontentbycategorycached = new GetContentByCategoryCached();
        getcontentbycategorycached.setClient(ClientFactory.getRemoteClient("http://localhost:8080/cms/rpc/bin"));
        System.out.println("Starter, henter fra tidsskrifter..");
        long l = System.currentTimeMillis();
        org.jdom.Document document = getcontentbycategorycached.getCachedContent(
        		new int[] { 479 }, 
        		0, 
        		null, 
        		null, 
        		0, 
        		3000,
        		true, 
        		0, 
        		0, 
        		"tidsskrifter", 
        		"APPLICATION", 
        		15);
        System.out.println((new StringBuilder()).append("Første hent tok ").append(System.currentTimeMillis() - l).toString());
        System.out.println("\nHenter for mesh..");
        l = System.currentTimeMillis();
        document = getcontentbycategorycached.getCachedContent(
        		new int[] { 478 }, 
        		0, 
        		null, 
        		null, 
        		0, 
        		3000,
        		true, 
        		0, 
        		0, 
        		"tidsskrifter-mesh", 
        		"APPLICATION", 
        		15);
        System.out.println((new StringBuilder()).append("Andre hent tok ").append(System.currentTimeMillis() - l).toString());
        System.out.println("\nHenter fra tidsskrifter igjen (n\345 fra cache)..");
        l = System.currentTimeMillis();
        document = getcontentbycategorycached.getCachedContent(
        		new int[] { 479 }, 
        		0, 
        		null, 
        		null, 
        		0, 
        		3000,
        		true, 
        		0, 
        		0, 
        		"tidsskrifter", 
        		"APPLICATION", 
        		15);
        Utilities.prettyPrint(document);
        System.out.println((new StringBuilder()).append("tredje hent tok ").append(System.currentTimeMillis() - l).toString());
    }
}