package no.helsebiblioteket.evs.plugin;

import java.io.OutputStreamWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LoggedInDataController extends HttpControllerPlugin {
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO: Remove this plugin!
		// FIXME: Move everything to LoggedInFunction!
    	StringBuffer buffer = new StringBuffer();
    	
    	String sessionVarName = "hbloggedin";
    	Object object = request.getSession().getAttribute(sessionVarName);
    	
    	
    	
    	
    	// TODO: Is this the right heading?
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
//		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		writer.append(buffer);
		writer.close();
	}
}
