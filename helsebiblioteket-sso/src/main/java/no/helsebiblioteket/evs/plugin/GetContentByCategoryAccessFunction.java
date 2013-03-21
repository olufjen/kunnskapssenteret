package no.helsebiblioteket.evs.plugin;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;
import com.enonic.cms.api.plugin.PluginEnvironment;


import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.jdom.Document;
import org.jdom.Element;

public class GetContentByCategoryAccessFunction {
	int categoryKey[];
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
	GetContentByCategoryParams getContentByCategoryParams;
    private GeoIpService geoIpService = null;
    private PluginEnvironment pluginEnvironment;

    public GetContentByCategoryAccessFunction() {
        getContentByCategoryParams = null;
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
        getContentByCategoryParams = new GetContentByCategoryParams();
        getContentByCategoryParams.categoryKeys = categoryKey;
        getContentByCategoryParams.levels = levels;
        getContentByCategoryParams.query = query;
        getContentByCategoryParams.orderBy = orderBy;
        getContentByCategoryParams.index = index;
        getContentByCategoryParams.count = count;
        getContentByCategoryParams.includeData = includeData;
        getContentByCategoryParams.childrenLevel = childrenLevel;
        getContentByCategoryParams.parentLevel = parentLevel;
    }
    
    public Document getContent(
    		int categoryKey[], 
    		int levels, 
    		String query, 
    		String orderBy, 
    		int index, 
    		int count, 
    		boolean includeData, 
    		int childrenLevel, 
    		int parentLevel, 
    		String countryCodes) {
    	this.categoryKey = categoryKey;
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
    	String remoteIp = LogInOrganization.getXforwardedForOrRemoteAddress(pluginEnvironment.getCurrentRequest());
    	if (this.geoIpService.hasAccess(remoteIp, countryCodes)) {
    		result = this.client.getContentByCategory(getContentByCategoryParams);
    	} else {
    		Element noAccessElement = new Element(this.noAccessTextName);
    		result = new Document(noAccessElement);
    	}
    	return result;
    }
}