package no.helsebiblioteket.cms.content;

import org.jdom.Document;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentParams;

/**
 * 
 * @author <A HREF="mailto:karine.haug@edb.com">kah</A>
 *
 */
public class RequestedContent {
	private GetContentParams contentParams;
	private Document content;
	
	public RequestedContent(int contentKey) {
		contentParams = new GetContentParams();
		contentParams.contentKeys = new int[] {contentKey};
		Client client = ClientFactory.getLocalClient();
		content = client.getContent(contentParams);
	}

	public boolean isPdf() {
		return getBinaryTitle().toLowerCase().endsWith(".pdf");
	}
	
	public String getBinaryTitle() {
		return content.getRootElement().getChild("content").getChild("binaries").getChild("binary").getAttributeValue("filename");
	}
}
