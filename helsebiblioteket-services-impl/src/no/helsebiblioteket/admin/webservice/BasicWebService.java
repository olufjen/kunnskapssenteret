package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import no.helsebiblioteket.admin.domain.cache.key.CacheKey;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;


import java.io.Serializable;


public abstract class BasicWebService implements Serializable {
	
	private CacheHelper cacheHelper;
	public abstract Log getLogger();
	private Options options;
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private boolean chunked = false;
	
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	    options.setProperty(HTTPConstants.CHUNKED, this.chunked);
	}
	
	/**
	 * Invokes a generic webservice.
	 */
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
	}
	
	/**
	 * Invokes a webservice cached.
	 * CacheKey.defaultCache is used as cache
	 */
	public Object invokeCached(String cacheKey, QName name, Object[] args, Class[] returnTypes) {
		return invokeCached(CacheKey.defaultCache, cacheKey, name, args, returnTypes);
	}
	
	/**
	 * Invokes a webservice cached.
	 */
	@SuppressWarnings("unchecked")
	public Object invokeCached(CacheKey cacheKey, String key, QName name, Object[] args, Class[] returnTypes) {
		Object response = cacheHelper.findCache(cacheKey, key);
		if (response == null) {
			response = invoke(name, args, returnTypes);
			cacheHelper.addCache(cacheKey, key, response);
			getLogger().info("could not find cached content for key '" + key + "' in cache '" + cacheKey.name() + "', adding content to cache");
		} else {
			getLogger().info("found cached result for key '" + key + "' in cache '" + cacheKey.name() + "'");
		}
		return response;
	}
	
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}

	public void setChunked(boolean chunked) {
		this.chunked = chunked;
	}

	public void setCacheHelper(CacheHelper cacheHelper) {
		this.cacheHelper = cacheHelper;
	}
}