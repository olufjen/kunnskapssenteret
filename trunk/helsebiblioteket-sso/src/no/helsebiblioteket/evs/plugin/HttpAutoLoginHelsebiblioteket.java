package no.helsebiblioteket.evs.plugin;

import java.util.Enumeration;
import java.util.StringTokenizer;

import com.enonic.cms.api.plugin.HttpAutoLoginPlugin;
import com.sun.mail.iap.Response;

import javax.servlet.http.HttpServletRequest;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.domain.IpAddress;
import no.helsebiblioteket.domain.Organization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class HttpAutoLoginHelsebiblioteket extends HttpAutoLoginPlugin {
    public String getAuthenticatedUser(HttpServletRequest request) throws Exception {
    	return null;
    }
}
