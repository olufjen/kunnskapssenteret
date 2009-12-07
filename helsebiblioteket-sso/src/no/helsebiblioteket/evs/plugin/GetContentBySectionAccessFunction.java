package no.helsebiblioteket.evs.plugin;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentBySectionParams;
import com.enonic.cms.api.plugin.PluginEnvironment;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.jdom.Document;
import org.jdom.Element;

public class GetContentBySectionAccessFunction {
	int sectionIds[];
	int levels;
	String query; 
	String orderBy; 
	int index; 
	int count; 
	boolean includeData; 
	int childrenLevel; 
	int parentLevel;
	private Client client = null;
	private String noAccessTextName;
	GetContentBySectionParams getContentBySectionParams;
    private GeoIpService geoIpService = null;

    public GetContentBySectionAccessFunction() {
        getContentBySectionParams = null;
    }
    
    public GeoIpService getGeoIpService() {
		return this.geoIpService;
	}

	public void setGeoIpService(GeoIpService geoIpService) {
		this.geoIpService = geoIpService;
	}

	public String getNoAccessTextName() {
		return noAccessTextName;
	}

	public void setNoAccessTextName(String noAccessTextName) {
		this.noAccessTextName = noAccessTextName;
	}
	
    private void initGetContentParams() {
        getContentBySectionParams = new GetContentBySectionParams();
        getContentBySectionParams.menuItemKeys = this.sectionIds;
        getContentBySectionParams.levels = this.levels;
        getContentBySectionParams.query = this.query;
        getContentBySectionParams.orderBy = this.orderBy;
        getContentBySectionParams.index = this.index;
        getContentBySectionParams.count = this.count;
        getContentBySectionParams.includeData = this.includeData;
        getContentBySectionParams.childrenLevel = this.childrenLevel;
        getContentBySectionParams.parentLevel = this.parentLevel;
    }
    
    public Document getContent(
    		int sectionIds[], 
    		int levels, 
    		String query, 
    		String orderBy, 
    		int index, 
    		int count, 
    		boolean includeData, 
    		int childrenLevel, 
    		int parentLevel, 
    		String countryCodes) {
    	this.sectionIds = sectionIds;
    	this.levels = levels;
    	this.query = query;
    	this.orderBy = orderBy;
    	this.index = index;
    	this.count = count;
    	this.includeData = includeData;
    	this.childrenLevel = childrenLevel;
    	this.parentLevel = parentLevel;
    	initGetContentParams();
    	this.client = ClientFactory.getLocalClient();
    	Document result = null;
    	String remoteIp = LogInInterceptor.getXforwardedForOrRemoteAddress(PluginEnvironment.getInstance().getCurrentRequest());
    	if (this.geoIpService.hasAccess(remoteIp, countryCodes)) {
    		result = this.client.getContentBySection(getContentBySectionParams);
    	} else {
    		Element noAccessElement = new Element(this.noAccessTextName);
    		result = new Document(noAccessElement);
    	}
    	return result;
    }
}