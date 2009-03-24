package no.helsebiblioteket.admin.webservice;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.hibernate.EhCache;


public class InvokeCache {
	private CacheManager cacheManager ;
	
	public InvokeCache(){
	}
	
	  
	   private Cache getCache() throws Exception {
		   return cacheManager.getCache("invokeCache");
	   }

	   public void addInvokeCache(String key, Object[] element) {
		   try{
			   Cache cache = getCache();
			   cache.put(new Element(key,element));
		   }catch(Exception ex){
		     	System.out.println(ex);
		  }
	   }

	   public Object[] findInvokeCache(String key) {
	     try{
		   Cache cache = getCache();
		     Element element = cache.get(key);
		     if (element != null) {
		    	 return (Object[]) element.getValue();
		      
		     } else {
		       return null;
		     }
	     }catch(Exception ex){
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
