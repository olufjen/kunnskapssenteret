
package no.kunnskapssenteret.evs.plugins.cache;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.model.GetContentBySectionParams;

import no.kunnskapssenteret.evs.cache.CachedContentReader;

import org.jdom.Document;

public class GetContentBySectionCached extends CachedContentReader {
	
	int menuItemKeys[];
	int levels;
	String orderBy;
	int index;
	int count;
	int parentLevel;
	int childrenLevel;
	boolean includeUserRights;
	boolean includeData;
	boolean includeOfflineContent;
	String query;
	
    String scope;
    GetContentBySectionParams getContentBySectionParams;

    public GetContentBySectionCached() {
        getContentBySectionParams = null;
    }
    
    public void setMenuItemKeys(int[] menuItemKeys) {
		this.menuItemKeys = menuItemKeys;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setParentLevel(int parentLevel) {
		this.parentLevel = parentLevel;
	}

	public void setChildrenLevel(int childrenLevel) {
		this.childrenLevel = childrenLevel;
	}

	public void setIncludeUserRights(boolean includeUserRights) {
		this.includeUserRights = includeUserRights;
	}

	public void setClient(Client client) {
        super.client = client;
    }
    
    protected void initGetContentParams() {
    	getContentBySectionParams = new GetContentBySectionParams();
    	getContentBySectionParams.menuItemKeys = menuItemKeys;
    	getContentBySectionParams.levels = levels;
    	getContentBySectionParams.orderBy = orderBy;
    	getContentBySectionParams.index = index;
    	getContentBySectionParams.count = count;
    	getContentBySectionParams.parentLevel = parentLevel;
    	getContentBySectionParams.childrenLevel = childrenLevel;
    	getContentBySectionParams.includeUserRights = includeUserRights;
    	getContentBySectionParams.includeData = includeData;
    	getContentBySectionParams.includeOfflineContent = includeOfflineContent;
    	getContentBySectionParams.query = query;
    }

    protected void initCacheParams() {
        setCacheScope(scope);
    }
    
    public Document getCachedContent(
    		int menuItemKeys[],
    		int levels,
    		String query,
    		String orderBy,
    		int index,
    		int count,
    		boolean includeData,
    		int childrenLevel,
    		int parentLevel,
    		boolean includeUserRights,
    		boolean includeOfflineContent,
    		String cachedContentKey, 
    		String scope, 
    		int timeToLive) {
    	this.menuItemKeys = menuItemKeys;
    	this.levels = levels;
    	this.query = query;
    	this.orderBy = orderBy;
    	this.index = index;
    	this.count = count;    	
    	this.includeData = includeData;
    	this.parentLevel = parentLevel;
    	this.childrenLevel = childrenLevel;
    	this.includeUserRights = includeUserRights;
    	this.includeOfflineContent = includeOfflineContent;
    	this.cachedContentKey = cachedContentKey;
    	this.scope = scope;
    	this.timeToLive = timeToLive;
    	init();
        return lookupContent(cachedContentKey);
    }
    
    public Document getStoredContent() {
    	return client.getContentBySection(getContentBySectionParams);
    }
}