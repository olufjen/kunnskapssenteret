package no.naks.services.nhn.client;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import no.naks.services.helloworld.client.HelloWorld;

public class TestnhnService {
	public static void main(String[] args) throws Exception {
		   //http://localhost:8080/HelloWorld/hello
		URL url = new URL("https://ws-test.nhn.no/v1/AR?wsdl");
        QName qname = new QName("http://register.nhn.no/CommunicationParty", "CommunicationParty");

        Service service = Service.create(url, qname);

        HelloWorld hello = service.getPort(HelloWorld.class);

        System.out.println(hello.getHelloWorldAsString());
 //       CommunicationPartyServiceClient client = new CommunicationPartyServiceClient();
        
	}    
}
