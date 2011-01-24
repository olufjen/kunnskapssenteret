package no.helsebiblioteket.evs.plugin;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestPrinter {
	
	public static String getRequestHeaders(HttpServletRequest request) {
		
		StringBuffer strBufHeadersNameValues = new StringBuffer();
		
		if (request != null) {
			Enumeration enumerationHeaderNames = request.getHeaderNames();
			while (enumerationHeaderNames.hasMoreElements()) {
				String headerName = (String) enumerationHeaderNames.nextElement();
				strBufHeadersNameValues.append("(" + headerName);strBufHeadersNameValues.append(":");
				strBufHeadersNameValues.append(request.getHeader(headerName)+")");
			}
		}
		
		return strBufHeadersNameValues.toString();
	}
}
