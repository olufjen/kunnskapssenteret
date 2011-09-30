package no.helsebiblioteket.cms.request;


import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientException;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.ContentDataInputUpdateStrategy;
import com.enonic.cms.api.client.model.GetContentParams;
import com.enonic.cms.api.client.model.UpdateContentParams;
import com.enonic.cms.api.client.model.content.ContentDataInput;
import com.enonic.cms.api.client.model.content.RelatedContentsInput;
import com.enonic.cms.api.plugin.HttpInterceptorPlugin;
import com.enonic.cms.api.client.model.content.ContentStatus;


/**
 * @author		Per Christian Røine (per.christian.roine@edb.com)
 * @since		2011-09-29
 */
public class JournalRelatedTitles extends HttpInterceptorPlugin{
	private final Log log = LogFactory.getLog(getClass());
	private Document content;
	private GetContentParams contentParams;
	private String lastVersionKey = "";
	private boolean contentSaved;
	private int currentKey;
	
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// We do not have to make any changes to this method.
		
	}


	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		final String page = (String)req.getParameter("page");
	
		// Verifying that "page" is "2035" ensures that the active content type is "tidsskrift"  
		if("2035".equals(page)){
			// The "updateNeeded" method makes sure the update only occurs once
			if(updateNeeded(req)){
				// Update related content
				updateRelatedContent(req);
			}
		}
		
		return true;
	}
	
	
	/**
	 * Updates related content
	 * 
	 * Grabs the current content and all the keys of former and latter titles,
	 * and uses these to find which contents to update. Using these keys, the
	 * method calls appropriate methods that will handle the update of each
	 * content.
	 * 
	 * @param req				The HttpServletRequest that is also a parameter of "preHandle"
	 * @throws JDOMException
	 */
	private void updateRelatedContent(HttpServletRequest req) throws JDOMException{
		try {
			Client client = ClientFactory.getLocalClient();
			
			contentParams = new GetContentParams();
			contentParams.contentKeys = new int[] {currentKey};
			
			content = client.getContent(contentParams);
			
			// Get keys of former and latter titles
			List<Attribute> formerTitleKeys = getFormerTitleKeys(content);
			List<Attribute> latterTitleKeys = getLatterTitleKeys(content);
			
			// If any keys (for former titles) exists, update corresponding content
			for(Attribute key : formerTitleKeys){
				// Update former keys
				updateContent(client, key.getIntValue(), "postTitle");
			}
			
			// If any keys (for latter titles) exists, update corresponding content
			for(Attribute key : latterTitleKeys){
				// Update latter keys
				updateContent(client, key.getIntValue(), "preTitle");
			}
		} catch (Exception e) {
			log.error("Failed to update related content to content with key " + currentKey);
		}
	}
	
	
	/**
	 * Updates the content with key "key"
	 * 
	 * Updates content of type "tidsskrift" based on the provided key and
	 * String "prePost" which may be either "preTitle" or "postTitle" based
	 * on whether it's a former or latter title.
	 * 
	 * To preserve already defined former and latter titles, we grab already
	 * existing titles and add them again
	 * 
	 * In order for related journals to continue being displayed, they
	 * need to get their status and date (publishfrom) set. To preserve
	 * former dates, it will collect the old publishfrom-date and keep that
	 * date as the publishfrom-date of the new version of the content.
	 * 
	 * @param client			Local client
	 * @param key				The key to the desired content
	 * @param prePost			String which should be "preTitle" or "postTitle" based on whether it is a former or latter title
	 * @throws JDOMException
	 */
	private void updateContent(Client client, Integer key, String prePost) throws JDOMException {
		ContentDataInput contentdata = new ContentDataInput("tidsskrift"); 
		RelatedContentsInput relatedcontent = new RelatedContentsInput(prePost);		
		Document con = getContentByKey(client, key);
		
		if(prePost.equals("preTitle")){
			List<Attribute> former = getFormerTitleKeys(con);
			
			for(Attribute attr : former){
				if(attr.getIntValue() != currentKey)
					relatedcontent.addRelatedContent(attr.getIntValue());
			}	
		}
		
		if(prePost.equals("postTitle")){
			List<Attribute> latter = getLatterTitleKeys(con);
			
			for(Attribute attr : latter){
				if(attr.getIntValue() != currentKey)
					relatedcontent.addRelatedContent(attr.getIntValue());
			}
		}
		
		
		
		relatedcontent.addRelatedContent(currentKey);
		contentdata.add(relatedcontent);
		

		if (contentdata.getInputs().size() > 0) {
			try {
				UpdateContentParams params = new UpdateContentParams();
				params.contentKey = key;
				params.contentData = contentdata;
				params.createNewVersion = true;
				params.publishFrom = getPublishedFromDate(client, key);
				params.status = ContentStatus.STATUS_APPROVED;
				params.updateStrategy = ContentDataInputUpdateStrategy.REPLACE_NEW;
				client.updateContent(params);
			} catch (ClientException e) {
				log.error("Failed to update content with key " + key);
			}
		}
	}
	
	
	/**
	 * Returns the content by the given key
	 * 
	 * @param client	Local client
	 * @param key		The key to the desired content
	 * @return			A "Document" which holds the content
	 */
	private Document getContentByKey(Client client, int key){
		contentParams = new GetContentParams();
		contentParams.contentKeys = new int[] {key};
		
		return client.getContent(contentParams);
	}
	
	
	/**
	 * Makes sure the update only occurs once
	 * 
	 * By investigating the parameters given by the URL, we are able to return a
	 * boolean where the value will be "true" in only one case. It might seem
	 * unnecessary to do this, however several requests are called upon pressing
	 * "Save" (and such). Without any control on this matter, the update would 
	 * have been run several times, and thus decreasing performance.
	 * 
	 * @param req	The HttpServletRequest that is also a parameter of "preHandle"
	 * @return		A boolean which will be true when an update is needed
	 */
	@SuppressWarnings("rawtypes")
	private boolean updateNeeded (HttpServletRequest req){
		final String op = req.getParameter("op");
		final String editLockedVersionMode = req.getParameter("editlockedversionmode");
		String reqVersionKey = "";
		boolean update = false;
		String tmpKey = req.getParameter("key");
		contentSaved = false;
		
		if(!(null == tmpKey || tmpKey.isEmpty()))
			currentKey = Integer.parseInt(tmpKey);
		
		
		Enumeration en = req.getParameterNames();
		
		while(en.hasMoreElements()){
			if(en.nextElement().equals("saved") && "form".equals(op)){
				contentSaved = true;
			}
		}
		
		// Check if content is being saved. If so, grab the request specific "versionkey"
		if(contentSaved)
			reqVersionKey = req.getParameter("versionkey");
		
		// If key from last request where content was saved matches the current request's "versionkey", ignore 
		// it (as this means that related content to this "versionkey" has already been modified) 
		if(contentSaved && lastVersionKey.equals(reqVersionKey) && !("true".equals(editLockedVersionMode))){
			update = true;
		} else {
			update = false;
			
			// Update last "versionkey" that updated related content.
			lastVersionKey = reqVersionKey;
		}
		
		return update;
	}
	
	
	/**
	 * Acquires all keys related to former titles
	 * 
	 * @return			A list of "Attributes" of all keys related to former titles
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	private List<Attribute> getFormerTitleKeys(Document doc) throws JDOMException{
		XPath xpath = XPath.newInstance("/contents/content/contentdata/preTitle/content/@key");
		List<Attribute> keys = xpath.selectNodes(doc);
		return keys;
	}
	
	
	/**
	 * Acquires all keys related to latter titles
	 * 
	 * @return			A list of "Attributes" of all keys related to latter titles
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	private List<Attribute> getLatterTitleKeys(Document doc) throws JDOMException{
		XPath xpath = XPath.newInstance("/contents/content/contentdata/postTitle/content/@key");
		List<Attribute> keys = xpath.selectNodes(doc);
		return keys;
	}
	
	
	/**
	 * Given key of content, it returns a java.util.Date (including hours and minutes)
	 * 
	 * @param client			Local client
	 * @param key				Key of the content which to return "publishfrom" from
	 * @return					java.util.Date with year, month, day, hours, minutes
	 * @throws JDOMException
	 */
	@SuppressWarnings("deprecation")
	private Date getPublishedFromDate(Client client, int key) throws JDOMException{
		// Acquire "publishfrom" from current version
		Document doc = getContentByKey(client, key);
		XPath xpath = XPath.newInstance("/contents/content/@publishfrom");
		String stringDate = xpath.selectSingleNode(doc).toString();
		
		log.info("stringDate: " + stringDate);
		
		// Manipulate string to fit the Date-constructor
		int year = Integer.parseInt(stringDate.substring(27, 29));
		year += 100;
		int month = Integer.parseInt(stringDate.substring(30, 32));
		month -= 1;
		int day = Integer.parseInt(stringDate.substring(33, 35));
		int hour = Integer.parseInt(stringDate.substring(36, 38));
		int min = Integer.parseInt(stringDate.substring(39, 41));
		
		log.info("Date: " + year + "." + month + "." + day + " " + hour + ":" + min);
				
		// Return date with correct values
		return new Date(year, month, day, hour, min);
	}
}