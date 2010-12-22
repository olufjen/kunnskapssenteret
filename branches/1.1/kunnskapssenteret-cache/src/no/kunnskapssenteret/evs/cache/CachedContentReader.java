package no.kunnskapssenteret.evs.cache;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.AbstractParams;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import no.kunnskapssenteret.evs.plugins.cache.domain.CachedObject;
import no.kunnskapssenteret.evs.plugins.cache.domain.CachedObjectKey;

import org.jdom.Document;

public abstract class CachedContentReader {
    static enum ValidScope { APPLICATION };

    protected Client client;
    protected String cachedContentKey;
    protected AbstractParams params;
    protected int timeToLive;
    protected ValidScope cacheScope;
    protected static Map<CachedObjectKey, CachedObject> cachedAppContents = null;
    
    private static final Logger logger = Logger.getLogger(CachedContentReader.class.toString());
    
    static  {
        cachedAppContents = new HashMap<CachedObjectKey, CachedObject>();
    }
    
    public CachedContentReader() {
        client = null;
        cachedContentKey = null;
        params = null;
        cacheScope = ValidScope.APPLICATION;
    }

    protected CachedObjectKey getCachedObjectKeyByString(String s) {
    	if (cachedAppContents != null) {
    		for (CachedObjectKey key : cachedAppContents.keySet()) {
	    		if (key.getKey().equals(s)) {
	    			return key;
	    		}
    		}
    	}
    	return null;
    }
    
    protected void setCacheScope(String s) {
        cacheScope = Enum.valueOf(ValidScope.class, s);
    }

    protected void setTimeToLive(int i) {
        timeToLive = i;
    }

    protected void setCachedContentKey(String s) {
        cachedContentKey = s;
    }

    protected abstract void initGetContentParams();

    protected abstract void initCacheParams();

    protected void initClient() {
        if(client == null && cacheScope == ValidScope.APPLICATION) {
            client = ClientFactory.getLocalClient();
        }
    }

    protected void init() {
        initClient();
        initGetContentParams();
    }

    protected AbstractParams getParams() {
        return params;
    }

    protected void setParams(AbstractParams abstractparams) {
        params = abstractparams;
    }

    protected Document lookupContent(String stringKey) {
    	synchronized (CachedContentReader.class) {
	    	CachedObjectKey key = getCachedObjectKeyByString(stringKey);
	    	key = (key != null) ? key : new CachedObjectKey(stringKey);
	    	return lookupContent(key);
    	}
    }
    
    private Document lookupContent(CachedObjectKey key) {
    	//CachedObject cachedObject = cachedAppContents.get(key);
    	//if (cachedObject != null && cachedObject.isBeeingUpdated()) {
    		//logger.info("content is beeing updated, returning what is allready in cache for key: '" + key.getKey() + "'");
    	//	return cachedObject.getDocument();
    	//}
    	return lookupCachedOrStoredContent(key);
    }
    
    private Document lookupCachedOrStoredContent(CachedObjectKey key) {
    	Document document = null;
        CachedObject cachedObject = cachedAppContents.get(key);
        if (cachedObject != null && isCachedObjectValid(cachedObject)) {
        	logger.info("found valid content in cache for key: '" + key.getKey() + "'");
        	document = cachedObject.getDocument();
        } else {
        	logger.info("cache content for key: '" + key.getKey() + "' did not exist, adding content in cache for this key");
        	document = getStoredContent();
        	addCachedContent(key, document);
        }
        return document;
    }

    private boolean isCachedObjectValid(CachedObject cachedObject) {
    	return (System.currentTimeMillis() <= (long)(cachedObject.getTimeToLive() * 1000) + cachedObject.getTimeRegistered());
    }
    
    private void addCachedContent(CachedObjectKey key, Document document) {
    	//logger.info("adding content to cache with key: '" + key.getKey() + "'");
        CachedObject cachedObject = new CachedObject();
        cachedObject.setCheckForChanges(Boolean.valueOf(false));
        cachedObject.setDocument(document);
        cachedObject.setTimeToLive(timeToLive);
        cachedAppContents.put(key, cachedObject);
    }
    
    public abstract Document getStoredContent();
}