package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;

import no.helsebiblioteket.domain.Organization;
import no.helsebiblioteket.domain.User;

import com.enonic.cms.api.plugin.HttpResponseFilterPlugin;

public final class HttpResponseFilterHelsebiblioteket extends HttpResponseFilterPlugin {
    private final static String TAG_TO_REPLACE = "helse";
	private String sessionVarName;
    
    public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {
    	Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
    	String result = "ERROR";
    	
    	// TODO: Complete this!
    	
    	if(sessionVar instanceof Organization){
    		Organization organization = (Organization) sessionVar;
    		result = response.replaceAll(TAG_TO_REPLACE, "ORG: " + organization.getName());    		
    	} else if (sessionVar instanceof User){
    		User user = (User) sessionVar;
    		result = response.replaceAll(TAG_TO_REPLACE, "USR: " + user.getName());
    	}
//        String remoteAddr = request.getRemoteAddr();
        
        return result;
    }

	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
}
