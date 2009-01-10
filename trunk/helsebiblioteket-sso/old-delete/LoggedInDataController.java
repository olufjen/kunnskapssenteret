package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public final class LoggedInDataController extends HttpControllerPlugin {
	private String userKey = "hbloggedinuser";
	private String keyName = "hbkey";
	private LoggedInFunction loggedInFunction;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		http://localhost:8080/cms/site/2/Databaser?hbkey=hbproxyresult
		
		// FIXME: Move everything to LoggedInFunction?
//    	StringBuffer buffer = new StringBuffer();
		
		String key = request.getParameter(keyName);
    	Document document;
    	if(userKey.equals(key)){
    		document = loggedInFunction.getUserAsXML();
    	} else {
        	document = loggedInFunction.getResult(key);
    	}
//    	document.toString();
    	
    	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

    	// TODO: Is this the right heading?
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
//		response.setStatus(HttpServletResponse.SC_OK);

		outputter.output(document, response.getWriter());
		
//		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
//		writer.append(buffer);
//		writer.close();
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}
