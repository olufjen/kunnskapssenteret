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
import java.lang.reflect.Array;


@SuppressWarnings("serial")
public abstract class BasicWebService implements Serializable {
	
	//private CacheHelper cacheHelper;
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
	@SuppressWarnings("unchecked")
	public synchronized Object invoke(QName name, Object[] args, Class[] returnTypes) {
		Object[] response = null;
		try {
			response = this.serviceClient.invokeBlocking(name, args, returnTypes);
			if(response == null || response.length == 0 || response[0] == null){
				getLogger().error("Invoking webservice [" + name + "]: No result!");
				return null;
			} else {
				return response[0];
			}
		} catch (AxisFault af) {
			String argsAsString = null;
			try {
				argsAsString = objectArrayToString(args);
			} catch (Exception e) {
				getLogger().error("Problem generating string for error output: Could not generate string representation of argumet array", e);
			}
			String returnTypesAsString = null;
			try {
				returnTypesAsString = objectArrayToString(returnTypes);
			} catch (Exception e) {
				getLogger().error("Problem generating string for error output: Could not generate string representation of return type array", e);
			}
			String serviceClientOptionsString = "";
			if (serviceClient != null && serviceClient.getOptions() != null) {
				if (serviceClient.getOptions().getTo() != null) {
					serviceClientOptionsString = serviceClientOptionsString + "targetEPR address = " + serviceClient.getOptions().getTo().getAddress() + ". ";
				}
				serviceClientOptionsString = serviceClientOptionsString + "chunked = " + serviceClient.getOptions().getProperty(HTTPConstants.CHUNKED);
			}
			getLogger().error(
					"Axis fault caught while trying to execute 'invokeBlocking' with the following arguments:" +
					" Qname:" + name + 
					", args:" + argsAsString + 
					", returnTypes:" + returnTypesAsString +
					", serviceClient is " + (serviceClient == null ? "null" : "not null") +
					". serviceClientOptions: " + serviceClientOptionsString +
					". Message: " + af.getMessage() + ". Trace follows.", af
					);
			return null;
		}
	}
	
	public String objectArrayToString(Object[] objectArray) {
		String objectArrayAsString = null;
		if (objectArray != null) {
			for (Object object : objectArray) {
				if (object instanceof Array) {
					objectArrayAsString = (objectArrayAsString != null) ? (objectArrayAsString + ", ") : "" + objectArrayToString((Object[]) object);
				} else {
					objectArrayAsString = (objectArrayAsString != null) ? (objectArrayAsString + ", ") : "" + object;
				}
			}
		}
		return objectArrayAsString;
	}
	
	/**
	 * Invokes a webservice cached.
	 * CacheKey.defaultCache is used as cache
	 */
	@SuppressWarnings("unchecked")
	public Object invokeCached(String cacheKey, QName name, Object[] args, Class[] returnTypes) {
		return null;
	}
	
	/**
	 * Invokes a webservice cached.
	 */
	@SuppressWarnings("unchecked")
	public Object invokeCached(CacheKey cacheKey, String key, QName name, Object[] args, Class[] returnTypes) {
		return null;
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

	//public void setCacheHelper(CacheHelper cacheHelper) {
	//	this.cacheHelper = cacheHelper;
	//}
}