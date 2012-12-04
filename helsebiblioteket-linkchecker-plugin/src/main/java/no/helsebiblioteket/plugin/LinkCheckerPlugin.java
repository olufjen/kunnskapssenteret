package no.helsebiblioteket.plugin;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientException;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.CreateContentParams;
import com.enonic.cms.api.client.model.GetCategoriesParams;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;
import com.enonic.cms.api.client.model.GetMenuItemParams;
import com.enonic.cms.api.client.model.content.ContentDataInput;
import com.enonic.cms.api.client.model.content.HtmlAreaInput;
import com.enonic.cms.api.client.model.content.TextInput;
import com.enonic.cms.api.plugin.ext.TaskHandler;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.jdom.Element;

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
 * @author David Aasterud / Karine Haug
 * @version 2.0
 * 
 */

public class LinkCheckerPlugin extends TaskHandler {

	private Log log = LogFactory.getLog(this.getClass());
	private Client client;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm dd-MM-yy");
	private static final String CONTENT_PREFIX = "content://";
	private static final String ATTACHMENT_PREFIX = "attachment://";
	private static final String PAGE_PREFIX = "page://";
	private static final String ANCHOR_PREFIX = "#";
	private static final String CONTENT_FAGPROSEDYRE = "fagprosedyre";

	private int linksChecked;
	private int erroneousLinks;
	private int notWellFormedLinks;

	private StringBuffer report;
	private List<Category> allCategories = new ArrayList<Category>();
	private String saveCategoryName;

	private int saveCategory;
	private int linkTimeoutMillis = 6000;
	private String hostName; // smtp host
	private List<String> receivers = new ArrayList<String>();
	private List<Category> configCategories = new ArrayList<Category>();
	private List<Integer> excludedCategoryKeys = new ArrayList<Integer>();
	private String sender;
	private String reportUrl;
	private String remoteClientUrl;
	private String user;
	private String password;
	private String cmsSite;
	private boolean checkAnchors = true;
	private List<Integer> excludedResponseCodes = new ArrayList<Integer>();

	protected enum TaskPropertyKeys {
		cmsReceivers,
		cmsSender,
		cmsSaveFolder,
		cmsReportUrl,
		cmsLinkTimeoutMillis,
		cmsRemoteClientUrl,
		cmsUser,
		cmsPassword,
		cmsHostname,
		cmsCategoriesString,
		cmsExcludedCategoryKeys,
		cmsSite,
		cmsCheckAnchors,
		cmsExcludedResponseCodes
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
		report = new StringBuffer();
		receivers.clear();
		configCategories.clear();
		excludedCategoryKeys.clear();
		allCategories.clear();
		checkAnchors = true;
		excludedResponseCodes.clear();
	}

	/**
	 * Setting task properties from the job configuration to fields of the instance
	 * 
	 * @param taskProperties
	 */
	private void handleProperties(Properties taskProperties) {

		if (taskProperties != null && !taskProperties.isEmpty()) {

			user = taskProperties.getProperty(TaskPropertyKeys.cmsUser.name());
			log.info("Configured user: " + user);

			password = taskProperties.getProperty(TaskPropertyKeys.cmsPassword.name());
			log.info("Configured password: ********");

			remoteClientUrl = taskProperties.getProperty(TaskPropertyKeys.cmsRemoteClientUrl.name());
			log.info("Configured remote client url: " + remoteClientUrl);

			try {
				linkTimeoutMillis = Integer.parseInt(taskProperties.getProperty(TaskPropertyKeys.cmsLinkTimeoutMillis.name()));
			} catch (NumberFormatException nfe) {
				log.error("Error parsing linkTimeoutMillis.", nfe);
			}
			log.info("Configured linkTimeoutMillis: " + linkTimeoutMillis);

			reportUrl = taskProperties.getProperty(TaskPropertyKeys.cmsReportUrl.name());
			cmsSite = taskProperties.getProperty(TaskPropertyKeys.cmsSite.name()) ;
			log.info("Configured report url: " + reportUrl);

			try {
				saveCategory = Integer.parseInt(taskProperties.getProperty(TaskPropertyKeys.cmsSaveFolder.name()));
			} catch (NumberFormatException nfe) {
				log.error("Error parsing saveFolder.", nfe);
			}
			log.info("Configured saveFolder: " + saveCategory);

			sender = taskProperties.getProperty(TaskPropertyKeys.cmsSender.name());
			log.info("Configured sender: " + sender);

			String receiversString = taskProperties.getProperty(TaskPropertyKeys.cmsReceivers.name());
			if (receiversString != null) {

				StringTokenizer emailTokenizer = new StringTokenizer(receiversString, ",");

				while (emailTokenizer.hasMoreElements()) {
					receivers.add(emailTokenizer.nextToken());
				}
			}
			log.info("Configured receivers: " + receiversString);

			hostName = taskProperties.getProperty(TaskPropertyKeys.cmsHostname.name());
			log.info("Configured hostName: " + hostName);

			// catKey1,fieldName1,ishtml1, ... fieldNameN,ishtmlN;catKey2,fieldName2,ishtml2 ...
			String categoriesString = taskProperties.getProperty(TaskPropertyKeys.cmsCategoriesString.name());
			categoriesString.replaceAll("\\s", "");
			log.info("Configured categories: " + categoriesString);
			StringTokenizer categoryTokenizer = new StringTokenizer(categoriesString, ";");

			while(categoryTokenizer.hasMoreElements()) {
				boolean categoryOk = true;
				String categoryToken = categoryTokenizer.nextToken();
				StringTokenizer fieldTokenizer = new StringTokenizer(categoryToken, ",");
				Category category = new Category();
				int position = 0;

				while (categoryOk && fieldTokenizer.hasMoreElements()) {
					position++;
					String fieldToken = fieldTokenizer.nextToken();

					// first token is category key, the following are pairs of fields and ishtmlarea
					if (position == 1) {
						int categoryKey = -1;
						try {
							categoryKey = Integer.parseInt(fieldToken);
						} catch (NumberFormatException nfe) {
							log.error("Could not parse category number: " + fieldToken, nfe);
							categoryOk = false;
						}
						category.setKey(categoryKey);
					} else if (categoryOk && position > 1 && position % 2 == 0) { // field, pos 2,4,6,8 ...
						category.setFieldValue(fieldToken);
					} else if (categoryOk && position > 1 && position % 2 != 0) { // htmlflag, pos 3,5,7,9 ...
						category.setIsHtmlAreaValue(Boolean.parseBoolean(fieldToken));
					}
				}

				if (category.getIsHtmlArea().size() != category.getFields().size()) {
					categoryOk = false;
					log.error("Un-even number of fields and htmlAreaflags.");
				}

				if (categoryOk) {
					configCategories.add(category);
				} else {
					log.error("Skipping bogus category: " + categoryToken);
				}
			}
			String excludedCategoryKeysString = taskProperties.getProperty(TaskPropertyKeys.cmsExcludedCategoryKeys.name());

			if (excludedCategoryKeysString != null) {
				StringTokenizer excludeTokenizer = new StringTokenizer(excludedCategoryKeysString, ",");

				while (excludeTokenizer.hasMoreElements()) {
					String excludedCategoryKey = excludeTokenizer.nextToken().trim();
					try {
						excludedCategoryKeys.add(Integer.parseInt(excludedCategoryKey));
					} catch (NumberFormatException nfe) {
						log.error("Could not parse excluded category key");
					}
				}
				log.info("Categories excluded: " + excludedCategoryKeysString);
			}
			String anchors = taskProperties.getProperty(TaskPropertyKeys.cmsCheckAnchors.name());

			if (anchors != null) {
				checkAnchors = Boolean.parseBoolean(anchors);
			}
			log.info("Check anchors: " + checkAnchors);
			String excludedResponseCodesString = taskProperties.getProperty(TaskPropertyKeys.cmsExcludedResponseCodes.name());

			if (excludedResponseCodesString != null) {
				StringTokenizer responseCodesTokenizer = new StringTokenizer(excludedResponseCodesString, ",");

				while (responseCodesTokenizer.hasMoreElements()) {
					String responsCode = responseCodesTokenizer.nextToken().trim();
					try {
						excludedResponseCodes.add(Integer.parseInt(responsCode));
					} catch (NumberFormatException nfe) {
						log.error("Could nor parse excluded response codes");
					}
				}
				log.info("Response codes excluded: " + excludedResponseCodesString);
			}
		} else {
			log.error("Empty task properties. Can't operate without proper configuration.");
		}
	}

	public void execute(Properties props) {
		long startTime = System.currentTimeMillis();
		init();
		handleProperties(props);
		log.info("LinkCheckerPlugin started");

		if (client == null) {
			client = ClientFactory.getRemoteClient(remoteClientUrl);
		}
		client.login(user, password);

		try {
			saveCategoryName = getCategoryName();

			Date timeStarted = new Date();

			// structure for going through array of categories to be checked
			for (Category category : configCategories) {
				getSubCategories(category);
			}

			report.append("<table border=\"1\">");
			// Runs the linkcheck across all categories and subcategories
			for (Category category : allCategories) {
				checkOnCategories(category);
			}
			report.insert(0, "<div>" + getResultData());
			report.append("</table></div>");
			Date timeEnded = new Date();

			try {
				createContent();
				if (sender != null && !receivers.isEmpty() && hostName != null) {
					sendMail("Lenkesjekk for " + saveCategoryName.toLowerCase() + " (" + SDF.format(timeStarted) + " - " + SDF.format(timeEnded) + ")");
				} else {
					log.warn("Missing sender, receiver or hostname, no mail will be sent.");
				}

			} catch (Exception e) {
				log.error("Error creating content or sending mail.", e);
			}
		} catch (JDOMException e) {
			log.error("Failed to get save category " + saveCategory, e);
		}
		long endTime = System.currentTimeMillis();
		log.info("LinkCheckerPlugin finished in " + TimeUnit.MILLISECONDS.toMinutes(endTime - startTime) + " minutes.");
	}

	private String getCategoryName() throws JDOMException {
		GetCategoriesParams params = new GetCategoriesParams();
		params.categoryKey = saveCategory;
		params.includeContentCount = false;
		params.includeTopCategory = true;
		params.levels = 1;

		Document category = client.getCategories(params);

		XPath xPath = XPath.newInstance("/categories/category/title");
		Element title = (Element) xPath.selectSingleNode(category);
		return title.getText();
	}

	private String createMailContent() {
		String url = reportUrl +"?cat=" + saveCategory;

		String mailContent = getResultData()
				+ "<p>Fullstendig rapport finnes p� <a href=\"" + url + "\">" + url + ".</p>";

		return convertStringtoUTF8(mailContent);
	}

	private String getResultData() {
		return "Antall lenker sjekket: " + linksChecked + "<br/>"
				+ "Antall lenker som feiler: " + erroneousLinks + "<br/>"
				+ "Antall lenker som kaster exceptions: " + notWellFormedLinks + "<br/>";
	}

	/**
	 * Recursive function for adding subcategories of the configured categories to allCategories
	 * Field information and if it is a html area, is inherited from the parent
	 * 
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	private void getSubCategories(Category parent) {

		if (notExcludedCategory(parent)) {
			log.info("finding subcategories for category key " + parent.getKey());
			allCategories.add(parent);

			GetCategoriesParams params = new GetCategoriesParams();
			params.categoryKey = parent.getKey();
			params.levels = 1;

			Document doc = client.getCategories(params);
			Element root = doc.getRootElement();

			List<Element> categories = root.getChildren("category");

			for (Element category : categories) {
				int key = Integer.parseInt(category.getAttributeValue("key"));
				log.info("found subcategory key " + key);
				getSubCategories(new Category(key, parent.getFields(), parent.getIsHtmlArea()));
			}
		}
	}

	private boolean notExcludedCategory(Category category) {

		for (Integer excludedKey : excludedCategoryKeys) {

			if (category.getKey() == excludedKey) {
				log.info(excludedKey + " is excluded from the linkcheck");
				return false;
			}
		}
		return true;
	}

	/**
	 * Runs the url check on a category
	 * 
	 * @param category
	 */
	@SuppressWarnings("unchecked")
	private void checkOnCategories(Category category) {
		log.info("checking category key " + category.getKey());

		GetContentByCategoryParams params = new GetContentByCategoryParams();
		params.categoryKeys = new int[]{ category.getKey() };
		params.childrenLevel = 0;
		params.parentLevel = 0;
		params.includeData = true;
		params.includeUserRights = false;
		params.index = 0;
		params.levels = 1;
		params.count = 10;

		Document doc = client.getContentByCategory(params);
		Element root = doc.getRootElement();

		// getting 10 contents at the time to avoid OutOfMemoryError on large categories
		do {
			List<Element> contents = root.getChildren("content");

			for (Element content : contents) {
				String contentKey = content.getAttributeValue("key");
				String contentType = content.getAttributeValue("contenttype");
				String title = StringEscapeUtils.escapeXml(content.getChildText("title"));
				Element contentdata = content.getChild("contentdata");
				List<String> htmlAreas = null;
				boolean htmlAreasFound = false;

				log.info("chekcing content key " + contentKey);

				int i = 0;

				for (String field : category.getFields()) {
					// get any descendants of contentdata that has the same name as field
					Iterator<Element> fieldIterator = contentdata.getDescendants(new ElementFilter(field));

					while (fieldIterator.hasNext()) {
						Element element = fieldIterator.next();
						String urlText = element.getText();

						if (category.getIsHtmlArea().get(i)) {
							Iterator<Element> urlIterator = element.getDescendants(new ElementFilter("a"));

							while (urlIterator.hasNext()) {
								Element a = urlIterator.next();
								String href = a.getAttributeValue("href");

								if (isInternalUrl(href)) {

									// If the url is an anchor and the htmlAreas of the content is not found yet, 
									// get all htmlAreas if checkAnchors is true
									if (href.startsWith(ANCHOR_PREFIX) && checkAnchors && !htmlAreasFound) {
										htmlAreas = getHtmlAreas(contentdata, category);
										htmlAreasFound = true;
									}
									href = getValidInternalUrl(href, contentType, htmlAreas);
								}
								String message = href != null && !isMailUrl(href) ? checkURL(href) : "";

								if (!message.equals("")) {
									// make html markup
									report.append("<tr><td>" + contentKey + "</td>" + "<td>" + title + "</td>" + message + "</tr>");
								}
							}
						} else if (!urlText.equals("") && !isMailUrl(urlText)) {
							String message = checkURL(urlText);

							if (!message.equals("")) {
								// make html markup
								report.append("<tr>" + "<td>" + contentKey + "</td>" + "<td>" + title + "</td>" + message + "</tr>");
							}
						}
					}
					i++;
				}
			}
			params.index += 10;
			doc = client.getContentByCategory(params);
			root = doc.getRootElement();
		} while ((root.getAttributeValue("resultcount") != null) && (!root.getAttributeValue("resultcount").equals("0")));
	}

	/**
	 * 
	 * @param contentdata
	 * @param category
	 * @return a list containing all the htmlareas of the content
	 */
	@SuppressWarnings("unchecked")
	private List<String> getHtmlAreas(Element contentdata, Category category) {
		List<String> htmlAreas = new ArrayList<String>();
		int i = 0;

		for (String field : category.getFields()) {

			if (category.getIsHtmlArea().get(i)) {
				Iterator<Element> fieldIterator = contentdata.getDescendants(new ElementFilter(field));

				while (fieldIterator.hasNext()) {
					Element htmlElement = fieldIterator.next();
					String html = new XMLOutputter().outputString(htmlElement);
					htmlAreas.add(html);
				}
			}
			i++;
		}
		return htmlAreas;
	}

	private boolean isMailUrl(String url) {
		return url.toLowerCase().startsWith("mailto:");
	}

	private boolean isInternalUrl(String url) {
		return url != null && (url.startsWith(CONTENT_PREFIX) || url.startsWith(ATTACHMENT_PREFIX) ||
				url.startsWith(PAGE_PREFIX) || url.startsWith(ANCHOR_PREFIX));
	}

	/**
	 * 
	 * @param internalUrl
	 * @param contentType
	 * @param htmlAreas
	 * @return internal urls that is possible to check, or null if the url is a menuitem or an anchor that is found
	 */
	private String getValidInternalUrl(final String internalUrl, String contentType, List<String> htmlAreas) {
		String validUrl = internalUrl;

		// Handeling content urls
		if (internalUrl.startsWith(CONTENT_PREFIX)){
			String replaceUrl = internalUrl.replaceFirst(CONTENT_PREFIX, cmsSite);
			validUrl = replaceUrl + ".cms";
		} 
		// Handeling attachment urls
		else if (internalUrl.startsWith(ATTACHMENT_PREFIX)){
			String attachUrl = cmsSite + "_attachment/" ;
			String replaceUrl = internalUrl.replaceFirst(ATTACHMENT_PREFIX, attachUrl);
			validUrl = replaceUrl;
		} 
		// Handeling page urls
		else if (internalUrl.startsWith(PAGE_PREFIX)) {
			try {
				int menuitemKey = Integer.parseInt(internalUrl.replaceFirst(PAGE_PREFIX, ""));
				GetMenuItemParams menuItemParams = new GetMenuItemParams();
				menuItemParams.menuItemKey = menuitemKey;
				Document result = client.getMenuItem(menuItemParams);

				Element menuitem = result.getRootElement().getChild("menuitem");

				//If the menuitem is found, the url need not to be checked 
				if (menuitem != null) {
					validUrl = null;
				}
			} catch (NumberFormatException e) {
				log.warn("Tried to convert invalid internal page url: " + internalUrl, e);
			}
		} 
		// Handeling anchors
		else if (internalUrl.startsWith(ANCHOR_PREFIX) && htmlAreas != null) {
			String anchor = internalUrl.replaceFirst(ANCHOR_PREFIX, "");

			// Handeling fagprosedyre subtheme anchor
			if (CONTENT_FAGPROSEDYRE.equals(contentType) && anchor.startsWith("subtheme-")) {
				try {
					int themeCount = htmlAreas.size() - 1;
					int themeNumber = Integer.parseInt(anchor.substring(9));

					// If the subtheme number is within the count of theme htmlareas, the url need not to be checked
					if (themeNumber <= themeCount) {
						validUrl = null;
						log.info("fagprosedyre anchor found: " + anchor + ", theme number " + themeNumber + "/" + themeCount);
					}
				} catch (NumberFormatException e) {
					log.warn("Tried to convert invalid subtheme number for " + contentType + " anchor: " + internalUrl, e);
				}
			}
			//Handeling other anchors
			else {
				for (String htmlArea : htmlAreas) {
					String regex = "<\\w+\\s+.*id=\"" + Pattern.quote(anchor) + "\".*>";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(htmlArea);

					//If the html contains the anchor, the url need not to be checked
					if (matcher.find()) {
						validUrl = null;
						break;
					}
				}
			}
		} 
		// Skip anchor test
		else if (internalUrl.startsWith(ANCHOR_PREFIX) && htmlAreas == null) {
			validUrl = null;
		}
		log.info("The internal url " + internalUrl + " is replaced: " + validUrl);

		return validUrl;
	}

	/**
	 *
	 * @param url   A string containing a url
	 *
	 * @return      A string containing html table markup returning a link to the checked url as well as the response
	 *              code with its' explanation
	 */
	private String checkURL(String url) {
		log.info("Checking url: " + url);

		String xmlValidUrl = StringEscapeUtils.escapeXml(url);
		String responseMessage = "";

		try {
			// setup connection to url
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(linkTimeoutMillis);
			int response = con.getResponseCode();
			con.disconnect();

			if (!excludedResponseCodes.contains(response)) {
				switch (response) {
				case 400:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Bad request</td>";
					erroneousLinks++;
					break;
				case 401:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Unauthorized</td>";
					erroneousLinks++;
					break;
				case 403:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Forbidden</td>";
					erroneousLinks++;
					break;
				case 404:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Not Found</td>";
					erroneousLinks++;
					break;
				case 405:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Method Not Allowed</td>";
					erroneousLinks++;
					break;
				case 406:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Not Acceptable</td>";
					erroneousLinks++;
					break;
				case 407:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Proxy Authentication Required</td>";
					erroneousLinks++;
					break;
				case 408:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Request Timeout</td>";
					erroneousLinks++;
					break;
				case 415:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Unsupported Media Type</td>";
					erroneousLinks++;
					break;
				case 500:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Internal Server Error</td>";
					erroneousLinks++;
					break;
				case 501:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Not Implemented</td>";
					erroneousLinks++;
					break;
				case 502:
					//Not handeled
					break;
				case 503:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Service Unavailable</td>";
					erroneousLinks++;
					break;
				case 504:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " Gateway Timeout</td>";
					erroneousLinks++;
					break;
				case 505:
					responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + response + " HTTP Version Not Supported</td>";
					erroneousLinks++;
					break;
				default: 
					// Other errorcodes
					if (response >= 400 && response <= 599) {
						responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>" + " Something unexpected happened</td>";
						erroneousLinks++;
					}
				}
			}
		} catch(UnknownHostException e) {
			responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>Unknown host</td>";
			erroneousLinks++;
		} catch (SocketTimeoutException e) {
			// Not handeled
		} catch (Exception e) {
			String message = "";

			if (url.startsWith(PAGE_PREFIX)) {
				message = "This internal page does not exist";
				log.error(message + ": " + url, e);
			} else if (url.startsWith(ANCHOR_PREFIX)) {
				message = "This anchor does not exist in the content";
				log.error(message + ": " + url, e);
			} else {
				message = StringEscapeUtils.escapeXml(e.getMessage());
				log.error("Error checking link.", e);
			}
			responseMessage = "<td><a href='" + xmlValidUrl + "'>" + xmlValidUrl + "</a></td>" + "<td>Exception was thrown (and logged): " + message + "</td>";
			notWellFormedLinks++;
		} 
		linksChecked++;
		return responseMessage;
	}

	/**
	 * Saves the result of the link check to the archive
	 */
	private void createContent() {
		CreateContentParams params = new CreateContentParams();
		ContentDataInput contentdata = new ContentDataInput("linkCheckerResult");
		Date time = new Date();

		// heading and checker result
		TextInput heading = new TextInput("heading", saveCategoryName + " " + SDF.format(time));
		HtmlAreaInput checkerResult = new HtmlAreaInput("htmlarea_input", report.toString());

		contentdata.add(heading);
		contentdata.add(checkerResult);
		params.publishTo = null;
		params.publishFrom = time;
		params.status = 2;
		params.contentData = contentdata;
		params.categoryKey = saveCategory;

		try {
			client.createContent(params);
		} catch (ClientException e) {
			log.error("Failed to create content with html: " + report.toString(), e);
		}
	}

	/**
	 * Sends a reminder to configured e-mails
	 * 
	 * @param subject
	 */
	private void sendMail(String subject) {
		String[] mailaddresses = receivers.toArray(new String[0]);
		String  mailcontent = createMailContent();

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", hostName);

		Session mailSession = Session.getDefaultInstance(props, null);
		try {
			Transport transport = mailSession.getTransport("smtp");
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(sender));
			message.setContent(mailcontent, "text/html; charset=utf-8");

			for (String address : mailaddresses) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
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
