package no.helsebiblioteket.evs.plugin;

/**
 * usage example: <hbaccess countrycodes="NO,SE" noaccesstext="Innholdet er desverre kun synlig for norske og svenske brukere.">hemmenlig innhold</hbaccess>
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Parent;
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
	

	@SuppressWarnings("unchecked")
	public String filterResponse(HttpServletRequest request, String response, String contentType) throws Exception {
    	int firstIndexAccessElements = response.indexOf(hbAccessTagName);
    	if (firstIndexAccessElements != -1) {
    		//int lastIndexAccessElements = response.lastIndexOf(endTagName);
    		//String accessArea = "<" + surroundingStartTagName + ">"+
    		//					response.substring(firstIndexAccessElements-1, (lastIndexAccessElements + endTagName.length() + 1)) + 
    		//					"</" + surroundingEndTagName + ">";
    		try {
    			SAXBuilder saxb = new SAXBuilder();
    			saxb.setValidation(false);
    			saxb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    			Document doc = saxb.build(new ByteArrayInputStream(response.getBytes()));
        		XPath accessPath = XPath.newInstance("//*[name()='" + hbAccessTagName +"']");
        		List<Element> accessElements = accessPath.selectNodes(doc);
        		for (Element accessElement : accessElements) {
        			String countryCodes = accessElement.getAttributeValue(attributeCountryCodesName);
    				String noAccessText = accessElement.getAttributeValue(attributeNoAccessTextName);
    				Parent parentTmp = accessElement.getParent();
    				if(parentTmp instanceof Element){
    					Element parent = (Element) parentTmp;
    					int index = parent.indexOf(accessElement);
    					if(noAccessText != null){
    						accessElement.setName("div");
    	    				List<Attribute> attributes = new ArrayList<Attribute>();
    	    				attributes.addAll(accessElement.getAttributes());
    	    				for (Attribute attribute : attributes) {
    		    				accessElement.removeAttribute(attribute);
    						}
    	    				if ( ! geoIpService.hasAccess(LogInInterceptor.getXforwardedForOrRemoteAddress(request), countryCodes)) {
        	    				accessElement.removeContent();
        	    				accessElement.setText(noAccessText);    					
    	    				}
    					} else {
    						Element pos = accessElement.getChild("hasaccess", accessElement.getNamespace());
    						Element neg = accessElement.getChild("noaccess", accessElement.getNamespace());
    	    				List<Content> children = new ArrayList<Content>();
    	    				if (geoIpService.hasAccess(LogInInterceptor.getXforwardedForOrRemoteAddress(request), countryCodes)) {
    		    				if(pos != null){
    			    				children.addAll(pos.getContent());
    		    				}
    	    				} else {
    	    					if(neg != null){
    	    	   					children.addAll(neg.getContent());
    	    					}
    	    				}
    	    				for (Content elem : children) {
    	    					elem.detach();
    	    				}
   							parent.addContent(index, children);
   							accessElement.detach();
    					}
    				}
        		}
        		if (!accessElements.isEmpty()) {
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
