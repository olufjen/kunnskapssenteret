package no.helsebiblioteket.evs.plugin;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentParams;
import com.enonic.cms.api.plugin.PluginEnvironment;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.jdom.Document;
import org.jdom.Element;

public class GetContentAccessFunction {
	int contentKeys[];
	String query; 
	String orderBy; 
	int index; 
	int count; 
	boolean includeData; 
	int childrenLevel; 
	int parentLevel;
	private Client client = null;
	private String noAccessTextName;
	GetContentParams getContentParams;
    private GeoIpService geoIpService = null;
    private PluginEnvironment pluginEnvironment;

    public GetContentAccessFunction() {
        getContentParams = null;
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
	
    public void setPluginEnvironment(PluginEnvironment pluginEnvironment) {
		this.pluginEnvironment = pluginEnvironment;
	}

	private void initGetContentParams() {
        getContentParams = new GetContentParams();
        getContentParams.contentKeys = this.contentKeys;
        getContentParams.query = this.query;
        getContentParams.orderBy = this.orderBy;
        getContentParams.index = this.index;
        getContentParams.count = this.count;
        getContentParams.includeData = this.includeData;
        getContentParams.childrenLevel = this.childrenLevel;
        getContentParams.parentLevel = this.parentLevel;
    }
    
    public Document getContent(
    		int contentKeys[],
    		String query, 
    		String orderBy, 
    		int index, 
    		int count, 
    		boolean includeData, 
    		int childrenLevel, 
    		int parentLevel, 
    		String countryCodes) {
    	this.contentKeys = contentKeys;
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
    	String remoteIp = LogInOrganization.getXforwardedForOrRemoteAddress(pluginEnvironment.getCurrentRequest());
    	if (this.geoIpService.hasAccess(remoteIp, countryCodes)) {
    		result = this.client.getContent(getContentParams);
    	} else {
    		Element noAccessElement = new Element(this.noAccessTextName);
    		result = new Document(noAccessElement);
    	}
    	return result;
    }
}