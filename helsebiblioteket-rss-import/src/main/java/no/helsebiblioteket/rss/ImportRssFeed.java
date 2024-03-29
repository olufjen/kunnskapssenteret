package no.helsebiblioteket.rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import no.helsebiblioteket.rss.RssContent.ImportType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientException;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.ContentDataInputUpdateStrategy;
import com.enonic.cms.api.client.model.ImportContentsParams;
import com.enonic.cms.api.client.model.UpdateContentParams;
import com.enonic.cms.api.client.model.content.ContentDataInput;
import com.enonic.cms.api.plugin.ext.TaskHandler;

/**
 * This class is used to get a rss feed, and import it's content to Enonic CMS
 * 
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 */
public class ImportRssFeed extends TaskHandler {
	private Log log = LogFactory.getLog(ImportRssFeed.class);
	private Client client;
	private String username;
	private String password;
	private ImportType importType;
	private int categoryKey;
	private URL feedUrl;
	private String[] mailReceivers;
	private String mailSender;
	private String mailHost;

	private enum TaskPropertyKeys {
		cmsUsername,
		cmsPassword,
		importType,
		categoryKey,
		feedUrl,
		mailReceivers,
		authorKey
	}

	private enum Status {
		OK, FAILED, NONE;

		String write() {
			switch (this) {
			case OK: return "<td>OK</td>"; 
			case FAILED: return "<td>FEILET</td>";
			case NONE: return "<td>N/A</td>";
			}
			return "";
		}
	}

	@Override
	public void execute(Properties props) throws Exception {
		initProperties(props);
		initClient();
		importRssFeed();
	}

	/**
	 * This method gets the rss feed, iterates through it's content, importing one bye one.
	 */
	@SuppressWarnings("unchecked")
	private void importRssFeed() {
		Document rssFeed = getRssFeed();

		if (rssFeed != null) {
			StringBuffer mailContent = new StringBuffer("<table border=\"1\"><tr><th>Tittel</th><th>Status import</th><th>Oppdatering</th></tr>");
			Element root = rssFeed.getRootElement();
			Iterator<Element> feedIt = root.getDescendants(new ElementFilter("item"));

			while (feedIt.hasNext()) {
				Element feed = feedIt.next();
				RssContent rssContent = RssContent.getInstance(this.importType, feed);
				//log.info("BEFORE\n" + rssContent.getXml());

				try {
					rssContent.customize();
					//log.info("AFTER\n" + rssContent.getXml());
					Integer contentKey = importContent(rssContent.getXml());
					Status updateStatus = updateContent(contentKey);
					mailContent.append("<tr><td>" + rssContent.getTitle() + "</td>" + Status.OK.write() + updateStatus.write() + "</tr>");
				} catch (Exception e) {
					mailContent.append("<tr><td>" + rssContent.getTitle() + "</td>" + Status.FAILED.write() + Status.NONE.write() + "</tr>");
					log.error("Failed to import content: " + rssContent.getTitle(), e);
				}
			}
			if (this.mailReceivers != null) {
				sendMail(mailContent.toString());
			}
		}
	}

	/**
	 * Applying changes to the content, that was not part of the import job
	 * 
	 * @param key of the content to update
	 * @return the status of the update
	 */
	private Status updateContent(Integer key) {
		ContentDataInput contentdata = new ContentDataInput("artikkel"); 

		switch(this.importType) {
		case PsykNytt: 
			contentdata.add(PsykNyttRssContent.getRelatedAuthor());
		}

		if (contentdata.getInputs().size() > 0) {
			try {
				UpdateContentParams params = new UpdateContentParams();
				params.contentKey = key;
				params.contentData = contentdata;
				params.createNewVersion = false;
				params.updateStrategy = ContentDataInputUpdateStrategy.REPLACE_NEW;
				this.client.updateContent(params);
			} catch (ClientException e) {
				log.error("Failed to update content with key " + key);
				return Status.FAILED;
			}
			return Status.OK;
		}
		return Status.NONE;
	}

	/**
	 * 
	 * @param xml the content to import
	 * @return the key of the Enonic CMS content
	 * @throws ClientException
	 */
	private Integer importContent(String xml) {
		log.info("User is: " + this.client.getUserName() + "/" + this.client.getRunAsUserName());
		ImportContentsParams params = new ImportContentsParams();
		params.importName = this.importType.name();
		params.categoryKey = this.categoryKey;
		params.data = xml;
		params.publishFrom = new Date();
		Document content = this.client.importContents(params);
		return Integer.parseInt(content.getRootElement().getChild("inserted").getChild("content").getAttributeValue("key"));
	}

	/**
	 * 
	 * @return the rss feed, or null if getting the feed failed
	 */
	private Document getRssFeed() {
		try {
			SAXBuilder builder = new SAXBuilder();
			return builder.build(this.feedUrl.openStream());
		} catch (Exception e) {
			log.error("Failed to retrieve rss feed", e);
		}
		return null;
	}

	/**
	 * Send report of the import to receivers set up in the task configuration
	 * 
	 * @param mailContent rss feed report
	 */
	private void sendMail(String mailContent) {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", mailHost);

		Session mailSession = Session.getDefaultInstance(props, null);

		try {
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject("Rss feed: " + this.importType.name());

			message.setFrom(new InternetAddress(mailSender));
			message.setContent(mailContent, "text/html; charset=utf-8");

			for (String receiver : mailReceivers) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			}
			Transport transport = mailSession.getTransport("smtp");
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (MessagingException e) {
			log.error("Error sending mail", e);
		}
	}

	private void initClient() {
		log.info("Logging in using local client.");
		this.client = ClientFactory.getLocalClient();
		this.client.login(this.username, this.password);
		log.info("User is logged in as " + this.client.getUserName() + "/" + this.client.getRunAsUserName());
	}

	private void initProperties(Properties props) {

		if (props != null && props.size() > 0) {
			this.username = props.getProperty(TaskPropertyKeys.cmsUsername.name());
			this.password = props.getProperty(TaskPropertyKeys.cmsPassword.name());
			this.importType = ImportType.valueOf(props.getProperty(TaskPropertyKeys.importType.name()));

			try {
				this.categoryKey = Integer.parseInt(props.getProperty(TaskPropertyKeys.categoryKey.name()));
			} catch (NumberFormatException e) {
				log.error("Invalid category key in plugin configuration", e);
			}
			String url = props.getProperty(TaskPropertyKeys.feedUrl.name());

			try {
				this.feedUrl = new URL(url);
			} catch (MalformedURLException e) {
				log.error("Invalid feed url in plugin configuration", e);
			}
			String receivers = props.getProperty(TaskPropertyKeys.mailReceivers.name());

			if (receivers != null) {
				this.mailReceivers = receivers.split(",");
			}
			switch (this.importType) {
			case PsykNytt:
				try {
					int authorKey = Integer.parseInt(props.getProperty(TaskPropertyKeys.authorKey.name()));
					PsykNyttRssContent.createRelatedAuthor(authorKey);
				} catch (NumberFormatException e) {
					log.error("Invalid author key in plugin configuration", e);
				}
			}
		}
	}

	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
}
