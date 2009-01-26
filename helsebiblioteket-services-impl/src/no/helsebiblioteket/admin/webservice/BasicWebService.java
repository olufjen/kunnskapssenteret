package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;

public abstract class BasicWebService {
	public abstract RPCServiceClient getServiceClient();
	public abstract Log getLogger();

	public Object invoke(QName name, Object[] args, Class[] returnTypes) {
		try {
			Object[] response = getServiceClient().invokeBlocking(name, args, returnTypes);
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
}
