package no.naks.web.server.resource;

import no.naks.web.model.Mail;

import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.data.Parameter;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.resource.ClientResource;

public class MailServerResource extends ServerResource {
	
	   @Get
	    public Representation toHtml() throws ResourceException {
	        // Create the mail bean
		   System.out.println("Fagprosedyre MailserverResource: Startet");
	        Mail mail = new Mail();
	        mail.setStatus("received");
	        mail.setSubject("Message to self");
	        mail.setContent("Doh!");
	        mail.setAccountRef(new Reference(getReference(), "..").getTargetRef()
	                .toString());

	        // Load the FreeMarker template
	        Representation mailFtl = new ClientResource(
	               	                        "/mail/Mailplain.ftl").get();

	        // Wraps the bean with a FreeMarker representation
	        return new TemplateRepresentation(mailFtl, mail, MediaType.TEXT_HTML);
	    }

	    @Put
	    public String store(Form form) {
	        for (Parameter entry : form) {
	            System.out.println(entry.getName() + "=" + entry.getValue());
	        }

	        return "Mail updated!";
	    }	

}
