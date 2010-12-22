
package no.kunnskapssenteret.evs.plugins.cache;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;

import no.kunnskapssenteret.evs.cache.CachedContentReader;

import org.jdom.Document;

public class GetContentByCategoryCached extends CachedContentReader {
	
	int categoryKey[];
	int levels;
	String query; 
	String orderBy; 
	int index; 
	int count; 
	boolean includeData; 
	int childrenLevel; 
	int parentLevel;
	
    String scope;
    GetContentByCategoryParams getContentByCategoryParams;

    public GetContentByCategoryCached() {
        getContentByCategoryParams = null;
    }

    public void setIncludeData(boolean includeData) {
        this.includeData = includeData;
    }
    
    public void setCategoryKey(int categoryKey[]) {
        this.categoryKey = categoryKey;
    }
    
    public void setLevels(int levels) {
        this.levels = levels;
    }

    public void setChildrenLevel(int childrenLevel) {
        this.childrenLevel = childrenLevel;
    }

    public void setCount(int count) {
    	this.count = count;
    }

    public void setIndex(int index) {
    	this.index = index;
    }

    public void setOrderBy(String orderBy) {
    	this.orderBy = orderBy;
    }

    public void setParentLevel(int parentLevel) {
    	this.parentLevel = parentLevel;
    }

    public void setQuery(String query) {
    	this.query = query;
    }

    public void setClient(Client client) {
        super.client = client;
    }

    protected void initGetContentParams() {
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

    protected void initCacheParams() {
        setCacheScope(scope);
    }
    
    public Document getCachedContent(
    		int categoryKey[], 
    		int levels, 
    		String query, 
    		String orderBy, 
    		int index, 
    		int count, 
    		boolean includeData, 
    		int childrenLevel, 
    		int parentLevel, 
    		String cachedContentKey, 
    		String scope, 
    		int timeToLive) {
    	this.categoryKey = categoryKey;
    	this.levels = levels;
    	this.query = query;
    	this.orderBy = orderBy;
    	this.index = index;
    	this.count = count;
    	this.includeData = includeData;
    	this.childrenLevel = childrenLevel;
    	this.parentLevel = parentLevel;
    	this.cachedContentKey = cachedContentKey;
    	this.scope = scope;
    	this.timeToLive = timeToLive;
    	init();
        return lookupContent(cachedContentKey);
    }

	@Override
	public Document getStoredContent() {
		return client.getContentByCategory(getContentByCategoryParams);
	}
}