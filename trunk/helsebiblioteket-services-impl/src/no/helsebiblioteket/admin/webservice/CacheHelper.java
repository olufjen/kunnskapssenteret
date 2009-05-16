package no.helsebiblioteket.admin.webservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;


public class CacheHelper {
	private CacheManager cacheManager ;
	private Log logger = LogFactory.getLog(getClass());
	
	public CacheHelper(){
	}
	
	  
	private Cache getCache(CacheKey cacheKey) throws Exception {
		return cacheManager.getCache(cacheKey.name());
	}

	public void addCache(String key, Object element) {
		addCache(CacheKey.defaultCache, key, element);
	}
	   
	public void addCache(CacheKey cacheKey, String key, Object element) {
		Cache cache = null;
		try {
			cache = getCache(cacheKey);
		} catch (Exception e) {
			logger.error("Exception occured when trying to get cache with cache key '" + cacheKey + "'. Exception message: " + e.getMessage());
		}
		cache.put(new Element(key,element));
	}
	   
	public Object findCache(String key) {
		return findCache(CacheKey.defaultCache, key);
	}

	public Object findCache(CacheKey cacheKey, String key) {
		Element element = null;
		try {
	    	//long timeStart = System.currentTimeMillis();
	    	Cache cache = getCache(cacheKey);
		    element = cache.get(key);
		    //System.out.println("lookup of cache element with cacheKey '" + cacheKey + "' and key '" + key + "' took " + (System.currentTimeMillis() - timeStart));
		    if (element != null) {
		    	return (Object) element.getValue();		      
		    }
		} catch(Exception e){
			logger.error("Exception occured when trying to find cache with cache key '" + cacheKey + " and key '" + key + "'. Exception message: " + e.getMessage());
		}
		return element;
	}

	public void cacheFlush(Cache cache){
		cache.flush();
	}
	   
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}