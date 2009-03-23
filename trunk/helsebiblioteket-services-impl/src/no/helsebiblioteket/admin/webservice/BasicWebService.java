package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

//import no.helsebiblioteket.admin.domain.Url;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


import java.io.Serializable;


public abstract class BasicWebService implements Serializable {
	
	private InvokeCache invokeCache;
	public abstract Log getLogger();
	private Options options;
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private boolean chunked = false;
	// TODO: this will be spring-initiated.
	private boolean useCache = false;
	
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	    options.setProperty(HTTPConstants.CHUNKED, this.chunked);
	}
	
	public Object invoke(QName name, Object[] args, Class[] returnTypes) {
		Object[] response = null;
		try {
			response = this.serviceClient.invokeBlocking(name, args, returnTypes);
			if(response == null || response.length == 0 || response[0] == null){
				getLogger().error("Invoking webservice [" + name + "]: No result!");
				return null;
			} else {
				return response[0];
			}
		} catch (AxisFault e) {
			getLogger().error(e);
			return null;
		}

//		return invoke(this.useCache, generateCacheKey(name, args, returnTypes), name, args, returnTypes); 
	}
	
//	public Object invoke(String cacheKey, QName name, Object[] args, Class[] returnTypes) {
//		return invoke(true, cacheKey, name, args, returnTypes); 
//	}
//	
//	public Object invoke(boolean useCache, QName name, Object[] args, Class[] returnTypes) {
//		return invoke(true, generateCacheKey(name, args, returnTypes), name, args, returnTypes); 
//	}
	
	@SuppressWarnings("unchecked")
	public Object invokeCached(String cacheKey, QName name, Object[] args, Class[] returnTypes) {
		 //testCache();
		try {
			Object[] response = null;
			if (useCache) {
				response = invokeCache.findInvokeCache(cacheKey);
			}
			if (response == null) {
				response = this.serviceClient.invokeBlocking(name, args, returnTypes);
//				invokeCache.addInvokeCache(cacheKey, response);
			}
			if(response == null || response.length == 0 || response[0] == null){
				getLogger().error("Invoking webservice [" + name + "]: No result!");
				return null;
			} else {
				return response[0];
			}
		} catch (AxisFault e) {
			getLogger().error(e);
			return null;
		}
	}
	
	//@SuppressWarnings("unchecked")
	//public Object invokeCached(String key, QName name, Object[] args, Class[] returnTypes) {
		//Object[] result=null;
	//	Object[] result = invokeCache.findInvokeCache(key);
	//	if (result == null) {
	//		try {
	//			result =  this.serviceClient.invokeBlocking(name, args, returnTypes);
	//			invokeCache.addInvokeCache(key, result);
	//		} catch (AxisFault e) {
	//				getLogger().error(e);
	//		}
	//	}   
	//	return result[0];
	//}
	
	private String generateCacheKey(QName name, Object[] args, Class[] returnTypes) {
		StringBuilder key = new StringBuilder();
		key.append(name.getNamespaceURI()).append(name.getLocalPart());
		for (Object o : args) {
			key.append(generatePartialCacheKey(o));
		}
		for (Class clazz : returnTypes) {
			key.append(clazz.toString());
		}
		return key.toString();
	}
	
	private String generatePartialCacheKey(Object object) {
		if (object instanceof java.lang.reflect.Array) {
			for (Object subObject : (Object[]) object) {
				if (subObject instanceof java.lang.reflect.Array) {
					generatePartialCacheKey((Object[]) subObject);
				}
			}
		} else {
			return object.toString();
		}
		return null;
	}
	
	/*
	private void testCache(){
		InvokeCache invokeCache = new InvokeCache();
		findCache(invokeCache);
		loadCache(invokeCache);
		findCache(invokeCache);
		
		
	}
	
	 private static void loadCache(InvokeCache cache) {
	     System.out.println();
	     System.out.println("Adding Cache.");
	     cache.addInvokeCache(0, "Moving Pictures 0");
	     cache.addInvokeCache(1, "Moving Pictures 1");
	     cache.addInvokeCache(2, "Moving Pictures 2");
	     cache.addInvokeCache(3, "Moving Pictures 3");
	   }

	
	private static void findCache(InvokeCache cache) {
	     System.out.println();
	     System.out.println("Finding Cache:");
	     for(int i=0; i<4; i++) {
	       System.out.println(cache.findInvokeCache(i));
	     }
	   }
*/
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
	public void setChunked(boolean chunked) {
		this.chunked = chunked;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public void setInvokeCache(InvokeCache invokeCache) {
		this.invokeCache = invokeCache;
	}
}
