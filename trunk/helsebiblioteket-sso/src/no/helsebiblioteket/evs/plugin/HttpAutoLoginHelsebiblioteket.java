package no.helsebiblioteket.evs.plugin;

import com.enonic.cms.api.plugin.HttpAutoLoginPlugin;
import javax.servlet.http.HttpServletRequest;

/**
 * This class illustrates a simple http auto login example. It returns the name of who should be
 * logged in when first entering the path where it resides.
 */
public final class HttpAutoLoginHelsebiblioteket
    extends HttpAutoLoginPlugin
{
    /**
     * Return the qualified user name of who should be logged in.
     */
    public String getAuthenticatedUser(HttpServletRequest request)
        throws Exception
    {
        return "myuserstore\\someuser";
    }
}
