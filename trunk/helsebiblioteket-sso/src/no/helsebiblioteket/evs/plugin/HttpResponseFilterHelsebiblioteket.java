package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;

import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.plugin.HttpResponseFilterPlugin;

/**
 * This class illustrates a simple http response filter example. This filter adds the remote
 * address of the user to a predefined tag in the response. To configure this plugin place the
 * <b>example-plugin.xml</b> file into <b>CMS_HOME/plugins</b> directory with the jar file(s).
 */
public final class HttpResponseFilterHelsebiblioteket
    extends HttpResponseFilterPlugin
{
    /** Tag to replace. */
    private final static String TAG_TO_REPLACE = "##ip##";
    
    /**
     * Filters the textural response.
     */
    public String filterResponse(HttpServletRequest request, String response, String contentType)
        throws Exception
    {
    	String username = null;
    	
    	username = "HOW???";
    	
    	ClientFactory.getLocalClient().getUserName();
    	
//    	request.getSession()
        String remoteAddr = request.getRemoteAddr();
        return response.replaceAll(TAG_TO_REPLACE, remoteAddr);
    }
}
