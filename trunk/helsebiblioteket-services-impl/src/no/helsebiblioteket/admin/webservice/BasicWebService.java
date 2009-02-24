package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;

public abstract class BasicWebService {
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
	@SuppressWarnings("unchecked")
	public Object invoke(QName name, Object[] args, Class[] returnTypes) {
		try {
			Object[] response = this.serviceClient.invokeBlocking(name, args, returnTypes);
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
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
	public void setChunked(boolean chunked) {
		this.chunked = chunked;
	}
}
