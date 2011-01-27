package no.helsebiblioteket.evs.plugin;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * Util class to just get all request headers in a string of name:value pairs 
 *
 */

public class RequestPrinter {
	
	/**
	 * get all request headers in a string of name:value pairs 
	 * @param request
	 * @return all request headers in a string of name:value pairs
	 */
	
	public static String getRequestHeaders(HttpServletRequest request) {
		
    		StringBuffer strBufHeadersNameValues = new StringBuffer();
		
		if (request != null) {
			Enumeration enumerationHeaderNames = request.getHeaderNames();
			while (enumerationHeaderNames.hasMoreElements()) {
				String headerName = (String) enumerationHeaderNames.nextElement();
				strBufHeadersNameValues.append("(" + headerName);strBufHeadersNameValues.append(":");
				strBufHeadersNameValues.append(request.getHeader(headerName) + ")");
			}
		}
		
		return strBufHeadersNameValues.toString();
	}
}
