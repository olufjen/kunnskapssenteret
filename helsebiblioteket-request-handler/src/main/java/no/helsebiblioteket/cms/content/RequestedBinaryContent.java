package no.helsebiblioteket.cms.content;

import org.jdom.Document;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetBinaryParams;

/**
 * 
 * @author <A HREF="mailto:karine.haug@edb.com">kah</A>
 * 
 */
public class RequestedBinaryContent {
	private GetBinaryParams binaryParams;
	private Document content;
	
	public RequestedBinaryContent(int binaryKey) {
		binaryParams = new GetBinaryParams();
		binaryParams.binaryKey = binaryKey;
		Client client = ClientFactory.getLocalClient();
		content = client.getBinary(binaryParams);
	}
	
	public String getContentKey() {
		return content.getRootElement().getChild("contentkey").getValue();
	}
}
