package no.helsebiblioteket.alert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;
import com.enonic.cms.api.plugin.ext.TaskHandler;

/**
 * 
 * Enonic CMS plugin for sending out E-mail alerts when a content is ready for revision
 * 
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 *
 */
public class RevisionAlert extends TaskHandler {
	private Log log = LogFactory.getLog(RevisionAlert.class);
	private static final XMLOutputter XML_OUT = new XMLOutputter();
	private String cmsusername;
	private String cmspassword;
	private int[] categorykeys;
	private String contenttype;
	private String emailsender;

	private enum PropertyKeys {
		cmsusername,
		cmspassword,
		categorykeys,
		contenttype,
		emailsender
	}

	@Override
	public void execute(Properties props) throws Exception {
		initProperties(props);
		Document contents = getAllContentFromCMS();
		List<Element> todaysContent = getContentsReadyForRevision(contents);

		if (!todaysContent.isEmpty()) {
			sendEmailAlerts(todaysContent);
		}
	}

	/**
	 * Sends an e-mail for every content that needs a revision
	 * 
	 * @param todaysContent
	 */
	@SuppressWarnings("unchecked")
	private void sendEmailAlerts(List<Element> todaysContent) {

		for (Element content : todaysContent) {
			Element contentdata = content.getChild("contentdata");

			String subject = "P\u00e5minnelse: " + content.getChildText("title")  + "(" + content.getAttributeValue("key") + ")" ;
			String emailmessage = XML_OUT.outputString(contentdata.getChild("emailmessage"));
			List<Element> emailReceivers = contentdata.getChildren("email");
			List<String> receivers = new ArrayList<String>();

			for (Element receiver : emailReceivers) {
				receivers.add(receiver.getChildText("receiver"));
			}
			Email email = new Email(subject, emailmessage, this.emailsender, receivers, this.emailsender);
			email.send();
		}
	}

	/**
	 * 
	 * @return All content from given categories and contenttype
	 * @throws JDOMException
	 */
	private Document getAllContentFromCMS() throws JDOMException {
		GetContentByCategoryParams params = new GetContentByCategoryParams();
		params.categoryKeys = categorykeys;
		params.levels = 0;
		params.query = "contenttype = '" + this.contenttype + "'";
		params.count = 10000;
		params.includeData = true;

		Client client = ClientFactory.getLocalClient();
		client.login(this.cmsusername, this.cmspassword);
		return client.getContentByCategory(params);
	}

	/**
	 * Checks every content to see if todays date = revision date
	 * 
	 * @param contents
	 * @return Contents that are ready for revision
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	private List<Element> getContentsReadyForRevision(Document contents) throws JDOMException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(new Date());

		XPath xPath = XPath.newInstance("/contents/content");
		List<Element> allContents = xPath.selectNodes(contents);
		List<Element> todaysContent = new ArrayList<Element>();

		log.info("Checking " + allContents.size() + " contents for " + today);

		for (Element content : allContents) {
			String alertdate = content.getChild("contentdata").getChildText("alertdate");

			if (today.equals(alertdate)) {
				log.info(content.getChildText("title") + " is ready for revision");
				todaysContent.add(content);
			}
		}
		return todaysContent;
	}

	private void initProperties(Properties props) {
		this.cmsusername = props.getProperty(PropertyKeys.cmsusername.name());
		this.cmspassword = props.getProperty(PropertyKeys.cmspassword.name());
		String categorykeysString = props.getProperty(PropertyKeys.categorykeys.name());
		String[] categorykeysFromString = categorykeysString.replace("\\s", "").split(",");
		this.categorykeys = new int[categorykeysFromString.length];

		for (int i = 0; i < categorykeysFromString.length; i++) {
			this.categorykeys[i] = Integer.parseInt(categorykeysFromString[i]);
		}
		this.contenttype = props.getProperty(PropertyKeys.contenttype.name());
		this.emailsender = props.getProperty(PropertyKeys.emailsender.name());
	}
}
