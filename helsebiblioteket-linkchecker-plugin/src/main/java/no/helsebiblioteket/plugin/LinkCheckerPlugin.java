
package no.helsebiblioteket.plugin;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.StringTokenizer;
import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientException;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.CreateContentParams;
import com.enonic.cms.api.client.model.GetCategoriesParams;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;
import com.enonic.cms.api.client.model.content.ContentDataInput;
import com.enonic.cms.api.client.model.content.HtmlAreaInput;
import com.enonic.cms.api.client.model.content.TextInput;
import com.enonic.cms.api.plugin.TaskPlugin;

import org.jdom.Document;
import org.jdom.filter.ElementFilter;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Plugin used for testing urls stored in Enonic CMS. The content is extracted from Enonic CMS.
 * The urls found in the content is accessed and the response code is received. The urls
 * returning non-OK codes is sent as an email.
 *
 * @author David Aasterud
 * @version 1.0
 * 
 */

public class LinkCheckerPlugin extends TaskPlugin {

	private Log log = LogFactory.getLog(this.getClass());
	private Client client;
	private int linksChecked;
	private int erroneousLinks;
	private int notWellFormedLinks;
	private int saveFolder;
	private int linkTimeoutMillis = 6000;
	private List<String> generatedMail = new ArrayList<String>();
	private String hostName; // smtp host
	private List<String> receivers = new ArrayList<String>();
	private List<Category> allCategories = new ArrayList<Category>();
	private List<Category> configCategories = new ArrayList<Category>();

	// task properties
	private String cmsReceivers; // comma separated list of email addresses, parsed and stored in receivers[]
	private String cmsSender;
	private String cmsSaveFolder; // parsed and stored in saveFolder
	private String cmsRemoteClientUrl;
	private String cmsLinkTimeoutMillis;
	private String cmsUser;
	private String cmsPassword;
	private String cmsCategoriesString; // catKey1,fieldName1,ishtml1, ... fieldNameN,ishtmlN;catKey2,fieldName2,ishtml2 ...

	protected enum TaskPropertyKeys {
		cmsReceivers,
		cmsSender,
		cmsSaveFolder,
		cmsRemoteClientUrl,
		cmsLinkTimeoutMillis,
		cmsUser,
		cmsPassword,
		cmsHostname,
		cmsCategoriesString
	}

	public LinkCheckerPlugin() {
		// default contructor needed for Task Plugin to work
	}

	public LinkCheckerPlugin(Client client) {
		this.client = client;
	}

	private void init() {
		linksChecked = 0;
		erroneousLinks = 0;
		notWellFormedLinks = 0;
		generatedMail.clear();
		receivers.clear();
		allCategories.clear();
		configCategories.clear();
	}

	/**
	 * Using task props instead of configuration file which is not suitable for
	 * deployment at an ISP, cumbersome updates of configuration.
	 * @param taskProperties
	 */
	public void handleProperties(Properties taskProperties) {

		allCategories.clear();
		configCategories.clear();

		if (taskProperties != null && !taskProperties.isEmpty()) {

			cmsUser = taskProperties.getProperty(TaskPropertyKeys.cmsUser.name());
			log.info("Configured cmsUser: " + cmsUser);

			cmsPassword = taskProperties.getProperty(TaskPropertyKeys.cmsPassword.name());
			log.info("Configured cmsPassword: ********");

			cmsLinkTimeoutMillis = taskProperties.getProperty(TaskPropertyKeys.cmsLinkTimeoutMillis.name());
			try {
				linkTimeoutMillis = Integer.parseInt(cmsLinkTimeoutMillis);
			} catch (NumberFormatException nfe) {
				log.error("Error parsing cmsLinkTimeoutMillis.", nfe);
			}
			log.info("Configured linkTimeoutMillis: " + linkTimeoutMillis);

			cmsRemoteClientUrl = taskProperties.getProperty(TaskPropertyKeys.cmsRemoteClientUrl.name());
			log.info("Configured cmsRemoteClientUrl: " + cmsRemoteClientUrl);

			cmsSaveFolder = taskProperties.getProperty(TaskPropertyKeys.cmsSaveFolder.name());
			try {
				saveFolder = Integer.parseInt(cmsSaveFolder);
			} catch (NumberFormatException nfe) {
				log.error("Error parsing cmsSaveFolder.", nfe);
			}
			log.info("Configured saveFolder: " + saveFolder);

			cmsSender = taskProperties.getProperty(TaskPropertyKeys.cmsSender.name());
			log.info("Configured cmsSender: " + cmsSender);

			cmsReceivers = taskProperties.getProperty(TaskPropertyKeys.cmsReceivers.name());
			log.info("Configured cmsReceivers: " + cmsReceivers);
			StringTokenizer emailTokenizer = new StringTokenizer(this.cmsReceivers, ",");
			while (emailTokenizer.hasMoreElements()) {
				receivers.add(emailTokenizer.nextToken());
			}

			hostName = taskProperties.getProperty(TaskPropertyKeys.cmsHostname.name());
			log.info("Configured hostName: " + hostName);

			cmsCategoriesString = taskProperties.getProperty(TaskPropertyKeys.cmsCategoriesString.name());
			log.info("Configured cmsCategoriesString: " + cmsCategoriesString);
			StringTokenizer categoryTokenizer = new StringTokenizer(this.cmsCategoriesString, ";");
			while(categoryTokenizer.hasMoreElements()) {
				boolean okCat = true;
				String categoryToken = categoryTokenizer.nextToken();
				StringTokenizer fieldTokenizer = new StringTokenizer(categoryToken, ",");
				Category cat = new Category(-1);
				int pos = 0;
				while (okCat && fieldTokenizer.hasMoreElements()) {
					++pos;
					String fieldToken = fieldTokenizer.nextToken();
					// first token is category key, the following are pairs of fields and ishtmlarea
					if (pos == 1) {
						int catKey = -1;
						try {
							catKey = Integer.parseInt(fieldToken);
						} catch (NumberFormatException nfe) {
							log.error("Could not parse category number: " + fieldToken, nfe);
							okCat = false;
						}
						cat.setCategoryKey(catKey);
					} else if (okCat && pos > 1 && pos % 2 == 0) { // field, pos 2,4,6,8 ...
						cat.setFieldValue(fieldToken);
					} else if (okCat && pos > 1 && pos % 2 != 0) { // htmlflag, pos 3,5,7,9 ...
						cat.setIsHtmlArea(fieldToken);
					}
				}
				if (cat.getIsHtmlArea().size() != cat.getFieldList().size()) {
					okCat = false;
					log.error("Un-even number of fields and htmlAreaflags.");
				}

				if (okCat) {
					configCategories.add(cat);
				} else {
					log.error("Skipping bogus category: " + categoryToken);
				}
			}
		} else {
			log.error("Empty task properties. Can't operate without proper configuration.");
		}
	}

	public void execute(Properties props) {

		init();
		handleProperties(props);

		log.info("LinkCheckerPlugin started");

		if (client == null) {
			client = ClientFactory.getRemoteClient(cmsRemoteClientUrl);
		}

		// login user
		client.login(cmsUser, cmsPassword);

		// time taker
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStarted = sdf.format(cal.getTime());

		List<Category> cat = configCategories; // readConfigFile(); // everything is now in

		// structure for going through array of categories to be checked
		for (Category c : cat) {
			getSubCategories(c.getCategoryKey(), c.getFieldList(), c.getIsHtmlArea());
		}

		// Runs the linkcheck across all categories and subcategories
		for (Category a : allCategories) {
			checkOnCategories(a.getCategoryKey(), a.getFieldList(), a.getIsHtmlArea());
		}

		// simple way of generating html markup for a mail
		String mailContent = "<div id='parent'><div>Total amount of links checked: " + linksChecked
		+ "<br/> Total amount of links with errors: " + erroneousLinks
		+ "<br/> Total amount of exceptions: " + notWellFormedLinks + "</div>";

		mailContent += "<table border='1px'>";
		mailContent += "<tr><th>content key</th><th>content title</th><th>url</th><th>response</th></tr>";


		for (String s : generatedMail) {
			mailContent += s;
		}

		mailContent += "</table></div>";

		cal = Calendar.getInstance();
		String timeEnded = sdf.format(cal.getTime());

		// to be used, instad of hardcoding
		//int save_folder =  Integer.parseInt(this.saveFolder);

		try {
			createContent(saveFolder, "linkCheckerResult", mailContent);
			sendMail(receivers, mailContent, "linkCheckerResult " + timeStarted + " - " + timeEnded, cmsSender, hostName);
		} catch (Exception e) {
			log.error("Error creating content or sending mail.", e);
		}
		log.info("LinkCheckerPlugin finished");
	}

	/**
	 * Returns all subCategories of a supplied category in the config file
	 * Field information and if it is a html area that is inherited from the parent
	 * This is passed to the member variable allCategories
	 * 
	 * @param catKey        An integer that is a category key from the config
	 * @param fields        An ArrayList<String> holding the fieldnames that will be checked within the category
	 * @param isHtmlArea    An ArrayList<String> holding true/false values corresponding to the fieldname param 
	 */
	public void getSubCategories(int catKey, ArrayList<String> fields, ArrayList<String> isHtmlArea) {

		log.info("finding subcategories for category key " + catKey);

		GetCategoriesParams catPars = new GetCategoriesParams();
		catPars.categoryKey = catKey;
		catPars.levels = 1;

		// get categories
		Document cat = this.client.getCategories(catPars);
		Element categoryRoot = cat.getRootElement();

		// add to member variable
		Category catHolder = new Category(catKey, fields, isHtmlArea);
		this.allCategories.add(catHolder);

		List<Element> categories = categoryRoot.getChildren("category");
		ListIterator<Element> categoryiterator = categories.listIterator();
		while (categoryiterator.hasNext()) {
			Element temp = categoryiterator.next();
			String catID = temp.getAttributeValue("key");
			log.info("found subcategory key " + catID);
			// call recursively to get all subcategories as well
			getSubCategories(Integer.parseInt(catID), fields, isHtmlArea);
		}
	}

	/**
	 * Runs the url check on a category
	 *
	 * @param catKey        An integer that is a category key from the config
	 * @param fields        An ArrayList<String> holding the fieldnames that will be checked within the category
	 * @param isHtmlArea    An ArrayList<String> holding true/false values corresponding to the fieldname param
	 */
	public void checkOnCategories(int catKey, ArrayList<String> fields, ArrayList<String> isHtmlArea) {

		log.info("checking category key " + catKey);

		GetContentByCategoryParams gcp = new GetContentByCategoryParams();
		gcp.categoryKeys = new int[]{catKey};
		gcp.childrenLevel = 0;
		gcp.parentLevel = 0;
		gcp.includeData = true;
		gcp.includeUserRights = false;
		gcp.index = 0;
		gcp.levels = 1;
		gcp.count = 10000;


		// content from category as jdom
		Document cont = this.client.getContentByCategory(gcp);

		// get root
		Element temp1 = cont.getRootElement();
		List<Element> contents = temp1.getChildren("content");

		ListIterator<Element> contentiterator = contents.listIterator();
		while (contentiterator.hasNext()) {
			// get the contentdata for each content
			Element temp = contentiterator.next();
			String contentKey = temp.getAttributeValue("key");

			log.info("chekcing content key " + contentKey);

			Element contentdata = temp.getChild("contentdata");

			String contentTitle;

			// test to ensure that a title is selected, to avoid null pointer
			if (contentdata.getChild("heading") != null) {
				contentTitle = StringEscapeUtils.escapeXml(contentdata.getChild("heading").getText());
			} else if (contentdata.getChild("title") != null) {
				contentTitle = StringEscapeUtils.escapeXml(contentdata.getChild("title").getText());
			} else {
				contentTitle = "fant ingen innholdstittel";
			}

			int counter = 0;
			// go through the fields from the config file
			ListIterator<String> iter = fields.listIterator();
			while (iter.hasNext()) {
				String field = iter.next();

				// get any descendants of contentdata that has the same name as field
				Iterator fieldIter = contentdata.getDescendants(new ElementFilter(field));
				while (fieldIter.hasNext()) {

					Element ele = (Element) fieldIter.next();
					String urlText = ele.getText();

					if (isHtmlArea.get(counter).equals("true")) {
						Iterator aIterator = ele.getDescendants(new ElementFilter("a"));
						while (aIterator.hasNext()) {
							Element aTag = (Element) aIterator.next();
							String href = aTag.getAttributeValue("href");
							String message = href != null && !isMailUrl(href) ? this.checkURL(href) : "";
							if (!message.equals("")) {
								// make html markup
								this.generatedMail.add("<tr><td>" + contentKey + "</td>" + "<td>" + contentTitle + "</td>" + message + "</tr>");
							}
						}
					} else if (!urlText.equals("") && !isMailUrl(urlText)) {
						String message = this.checkURL(urlText);

						if (!message.equals("")) {
							// make html markup
							this.generatedMail.add("<tr>" + "<td>" + contentKey + "</td>" + "<td>" + contentTitle + "</td>" + message + "</tr>");
						}
					}
				}
				counter++;
			}
		}
	}

	private boolean isMailUrl(String url) {
		return url.toLowerCase().startsWith("mailto:");
	}

	/**
	 * Checks a url a and returns the response code it generates
	 *
	 * @param url   A string containing a url
	 *
	 * @return      A string containing html table markup returning a link to the checked url as well as the response
	 *              code with its' explanation
	 */
	public String checkURL(String url) {
		log.info("Checking url: " + url);
		String validUrl = StringEscapeUtils.escapeXml(url);
		
		try {
			// setup connection to url
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("HEAD");
			con.setReadTimeout(linkTimeoutMillis);
			int response = con.getResponseCode();

			String response_message;

			switch (response) {
			case 200:
				response_message = "";
				break;  // all 200 codes is a success
			case 201:
				response_message = "";
				break;
			case 202:
				response_message = "";
				break;
			case 203:
				response_message = "";
				break;
			case 204:
				response_message = "";
				break;
			case 205:
				response_message = "";
				break;
			case 206:
				response_message = "";
				break;
			case 300:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Multiple Choices</td>";
				++this.erroneousLinks;
				break;
			case 301:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Moved Permanently</td>";
				++this.erroneousLinks;
				break;
			case 302:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Found</td>";
				++this.erroneousLinks;
				break;
			case 303:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " See other</td>";
				++this.erroneousLinks;
				break;
			case 304:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Not Modified</td>";
				++this.erroneousLinks;
				break;
			case 305:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Use Proxy</td>";
				++this.erroneousLinks;
				break;
			case 307:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Temporary Redirect</td>";
				++this.erroneousLinks;
				break;
			case 400:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Bad request</td>";
				++this.erroneousLinks;
				break;
			case 401:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Unauthorized</td>";
				++this.erroneousLinks;
				break;
			case 403:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Forbidden</td>";
				++this.erroneousLinks;
				break;
			case 404:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Not Found</td>";
				++this.erroneousLinks;
				break;
			case 405:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Method Not Allowed</td>";
				++this.erroneousLinks;
				break;
			case 406:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Not Acceptable</td>";
				++this.erroneousLinks;
				break;
			case 407:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Proxy Authentication Required</td>";
				++this.erroneousLinks;
				break;
			case 408:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Request Timeout</td>";
				++this.erroneousLinks;
				break;
			case 415:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Unsupported Media Type</td>";
				++this.erroneousLinks;
				break;
			case 500:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Internal Server Error</td>";
				++this.erroneousLinks;
				break;
			case 501:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Not Implemented</td>";
				++this.erroneousLinks;
				break;
			case 502:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Bad Gateway</td>";
				++this.erroneousLinks;
				break;
			case 503:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Service Unavailable</td>";
				++this.erroneousLinks;
				break;
			case 504:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " Gateway Timeout</td>";
				++this.erroneousLinks;
				break;
			case 505:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + response + " HTTP Version Not Supported</td>";
				++this.erroneousLinks;
				break;
			default:
				response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>" + " Something unexpected happened</td>";
				++this.erroneousLinks;
				break;
			}
			++this.linksChecked;
			return response_message;
		} catch(UnknownHostException e) {
			String response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>Unknown host</td>";
			++this.linksChecked;
			++this.erroneousLinks;
			return response_message;
		} catch (SocketTimeoutException e) {
			String response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>Connection timed out</td>";
			++this.linksChecked;
			this.notWellFormedLinks++;
			return response_message;
		} catch (Exception e) {
			log.error("Error checking link.", e);
			String message = StringEscapeUtils.escapeXml(e.getMessage());
			String response_message = "<td><a href='" + validUrl + "'>" + validUrl + "</a></td>" + "<td>Exception was thrown (and logged): " + message + "</td>";
			++this.linksChecked;
			this.notWellFormedLinks++;
			return response_message;
		}
	}

	/**
	 * Reads the config for being able to specify what categories and fields should be checked
	 * config file is embedded in the package. Also returns some config to the member variables.
	 *
	 * @deprecated
	 * @return      An ArrayList containing Category objects
	 */
	public ArrayList<Category> readConfigFile() {
		SAXBuilder parser = new SAXBuilder();

		ArrayList<Category> catCollection = new ArrayList<Category>();

		InputStream xml = this.getClass().getResourceAsStream("config.xml");
		if (xml == null) {
			throw new IllegalStateException("no xml");
		}

		try {
			// auda System.out.println(xml);
			Document doc = parser.build(xml);
			Element root = doc.getRootElement();

			// config stuff for the mail process
			// taskprops  Element mailConf = root.getChild("mail");
			// taskprops  this.hostName = mailConf.getChild("host-name").getText();
			//this.authUser = mailConf.getChild("auth-user").getText();
			//this.authPwd = mailConf.getChild("auth-pwd").getText();
			// taskprops  this.cmsSender = mailConf.getChild("sender").getText();
			// taskprops  this.cmsSaveFolder = mailConf.getChild("save-folder").getText();

			// get multiple receivers
			/* taskprops
            Element receiver = mailConf.getChild("receivers");
            List<Element> receivers = receiver.getChildren("receiver");
            this.receivers = new String[receivers.size()];
            ListIterator<Element> receiveriterator = receivers.listIterator();
            int counter = 0;
            while (receiveriterator.hasNext()) {
                Element current = receiveriterator.next();
                this.receivers[counter] = current.getText();
                ++counter;
            }
			 */
			List<Element> categories = root.getChildren("category");

			// find all the categories and the fields that should be checked
			ListIterator<Element> contentiterator = categories.listIterator();
			while (contentiterator.hasNext()) {
				Element current = contentiterator.next();
				Element fields = current.getChild("fields");
				List field = fields.getChildren("field");

				int key = Integer.parseInt(current.getAttributeValue("key"));

				Category cat = new Category(key);

				// iterate all children of fields as well
				ListIterator<Element> fielditerator = field.listIterator();
				while (fielditerator.hasNext()) {
					Element currentfield = fielditerator.next();
					cat.setFieldValue(currentfield.getAttributeValue("name"));
					if (currentfield.getAttributeValue("htmlarea") != null) {
						cat.setIsHtmlArea("true");
					} else {
						cat.setIsHtmlArea("false");
					}
				}
				catCollection.add(cat);
			}
		} catch (Exception e) {
			log.error("Error reading configuration file.", e);
		}
		return catCollection;
	}

	public void createContent(int categoryKey, String contentType, String contentText) {

		/* create a content */
		//String contenttype = "linkCheckerResult";
		CreateContentParams cc = new CreateContentParams();
		ContentDataInput cdi = new ContentDataInput(contentType);

		// time taker
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStarted = sdf.format(cal.getTime());

		// heading and checker result
		TextInput heading = new TextInput("heading", "Checker report " + timeStarted);
		HtmlAreaInput checkerResult = new HtmlAreaInput("htmlarea_input", contentText);

		cdi.add(heading);
		cdi.add(checkerResult);
		cc.publishTo = null;
		cc.publishFrom = new Date();
		cc.status = 2;
		cc.contentData = cdi;
		cc.categoryKey = categoryKey;

		try {
			client.createContent(cc);
		} catch (ClientException e) {
			log.error("Failed to create content with html: " + contentText, e);
		}
	}

	public static void sendMail(List<String> receivers, String mailcontent, String mailsubject, String mailfrom, String hostname) {

		String[] mailaddress = receivers.toArray(new String[0]);
		mailcontent = convertStringtoUTF8(mailcontent);

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", hostname);

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(false);
		try {
			Transport transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(mailsubject);
			message.setFrom(new InternetAddress(mailfrom));
			message.setContent(mailcontent, "text/html; charset=utf-8");

			// message.setText("", "UTF-8");
			for (String c : mailaddress) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(c));
			}

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (MessagingException me) {
			LogFactory.getLog("LinkCheckerPlugin").error("Error sending mail.", me);
		}
	}

	/**
	 * Converts a String to an UTF-8 encoded String
	 *
	 * @param brS
	 * @return
	 */
	protected static String convertStringtoUTF8(String brS) {
		String utf8str;
		try {
			// Converts from Unicode to UTF-8
			byte[] utf8 = brS.getBytes("UTF-8");
			utf8str = new String(utf8, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// This should never happen.
			throw new IllegalStateException("Java VM error.  UTF-8 not recognized as a charset.");
		}
		return utf8str;
	}
}
