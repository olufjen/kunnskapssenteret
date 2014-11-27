package no.naks.services.server.hello;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface vxc 
// En test
@WebService
@SOAPBinding(style = Style.RPC)
public interface HelloWorld{
	
	@WebMethod String getHelloWorldAsString();
	
}