package no.helsebiblioteket.admin.webservice;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;


public class CacheHelper {
	private CacheManager cacheManager ;
	
	public CacheHelper(){
	}
	
	  
	   private Cache getCache(CacheKey cacheKey) throws Exception {
		   return cacheManager.getCache(cacheKey.name());
	   }

	   public void addCache(String key, Object element) {
		   addCache(CacheKey.defaultCache, key, element);
	   }
	   
	   public void addCache(CacheKey cacheKey, String key, Object element) {
		   try {
			   Cache cache = getCache(cacheKey);
			   cache.put(new Element(key,element));
		   } catch(Exception ex){
		     	System.out.println(ex);
		  }
	   }
	   
	   public Object findCache(String key) {
		   return findCache(CacheKey.defaultCache, key);
	   }

	   public Object findCache(CacheKey cacheKey, String key) {
	     try{
		   Cache cache = getCache(cacheKey);
		     Element element = cache.get(key);
		     if (element != null) {
		    	 return (Object) element.getValue();
		      
		     } else {
		       return null;
		     }
	     } catch(Exception ex){
		     	System.out.println(ex);
		     	return null;
		  }
	   }

	public void cacheFlush(Cache cache){
		cache.flush();
	}
	   
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	   

}
