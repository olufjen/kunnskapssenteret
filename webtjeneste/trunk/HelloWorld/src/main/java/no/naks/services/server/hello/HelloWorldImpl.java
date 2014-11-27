package no.naks.services.server.hello;

import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "no.naks.services.server.hello.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString() {
		return "Hello World JAX-WS Kunnskapssenteret";
	}
}