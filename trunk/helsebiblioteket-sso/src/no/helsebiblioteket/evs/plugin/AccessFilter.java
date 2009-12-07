package no.helsebiblioteket.evs.plugin;

/**
 * usage example: <hbaccess countrycodes="NO,SE" noaccesstext="Innholdet er desverre kun synlig for norske og svenske brukere.">hemmenlig innhold</hbaccess>
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.plugin.HttpResponseFilterPlugin;


public final class AccessFilter extends HttpResponseFilterPlugin {
	private final Log logger = LogFactory.getLog(getClass());
	private GeoIpService geoIpService = null;
    private String hbAccessTagName = null;
	private String attributeCountryCodesName =  null;
	private String attributeNoAccessTextName =  null;
	

	public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {
    	int firstIndexAccessElements = response.indexOf(hbAccessTagName);
    	if (firstIndexAccessElements != -1) {
    		//int lastIndexAccessElements = response.lastIndexOf(endTagName);
    		//String accessArea = "<" + surroundingStartTagName + ">"+
    		//					response.substring(firstIndexAccessElements-1, (lastIndexAccessElements + endTagName.length() + 1)) + 
    		//					"</" + surroundingEndTagName + ">";
    		try {
    			Document doc = new SAXBuilder().build(new ByteArrayInputStream(response.getBytes()));
        		XPath accessPath = XPath.newInstance("/descendant-or-self::" + hbAccessTagName);
        		List<Element> accessElements = accessPath.selectNodes(doc);
        		boolean contentChanged = false;
        		for (Element accessElement : accessElements) {
        			String countryCodes = accessElement.getAttributeValue(attributeCountryCodesName);
        			if (! geoIpService.hasAccess(LogInInterceptor.getXforwardedForOrRemoteAddress(request), countryCodes)) {
        				contentChanged = true;
        				String noAccessText = accessElement.getAttributeValue(attributeNoAccessTextName);
        				accessElement.removeContent();
        				accessElement.setText(noAccessText);
        			}
        		}
        		if (contentChanged) {
        			StringWriter writer = new StringWriter();
        	        XMLOutputter prettyOut = new XMLOutputter();
        	        try {
        				prettyOut.output(doc, writer);
        			} catch (IOException ioe) {
        				logger.error("Unable to get string representation from JDom document: " + ioe.getMessage());
        			}
        			response = response.replace(response, writer.toString());
        		}
    		} catch (IOException ioe) {
    			logger.error("Unable to build document from xmlstring", ioe);
    		} catch (JDOMException jde) {
    			logger.error("Unable to build document from xmlstring", jde);
    		}
    		
    	}
		return response;
    }
    
    public String getHbAccessTagName() {
		return this.hbAccessTagName;
	}

	public void setHbAccessTagName(String hbAccessTagName) {
		this.hbAccessTagName = hbAccessTagName;
	}
	
	public String getAttributeCountryCodesName() {
		return attributeCountryCodesName;
	}

	public void setAttributeCountryCodesName(String attributeCountryCodesName) {
		this.attributeCountryCodesName = attributeCountryCodesName;
	}
	
	public String getAttributeNoAccessTextName() {
		return attributeNoAccessTextName;
	}

	public void setAttributeNoAccessTextName(String attributeNoAccessTextName) {
		this.attributeNoAccessTextName = attributeNoAccessTextName;
	}
	
	public GeoIpService getGeoIpService() {
		return this.geoIpService;
	}

	public void setGeoIpService(GeoIpService geoIpService) {
		this.geoIpService = geoIpService;
	}
}