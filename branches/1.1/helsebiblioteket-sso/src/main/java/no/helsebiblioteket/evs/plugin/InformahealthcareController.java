package no.helsebiblioteket.evs.plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enonic.cms.api.plugin.PluginEnvironment;
import com.enonic.cms.api.plugin.ext.http.HttpController;

public class InformahealthcareController extends HttpController {
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestTicketUrl = "http://informahealthcare.com/tps/requestticket?domain=helsebiblioteket.no&debug=true";
		String targetUrl = "http://informahealthcare.com/action/showPublications?display=byAlphabet&pubType=journal";
		String encodedTarget = URLEncoder.encode(targetUrl);
		requestTicketUrl += "&ru="+encodedTarget;
		String str;
		URL url = new URL(requestTicketUrl);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder result = new StringBuilder();
		while ((str = in.readLine()) != null) {
			result.append(str);
		} 
		in.close(); 
		String returnedUrl = result.toString();
		response.sendRedirect(returnedUrl);
	}
}