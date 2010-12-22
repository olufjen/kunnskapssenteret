package no.helsebiblioteket.evs.plugins.mcmaster;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.ImportContentsParams;

public abstract class GetArticlesGenericByDisciplineTask extends McMasterFeed {
	private Log logger = LogFactory.getLog(GetArticlesGenericByDisciplineTask.class);
	protected Client cmsClient;
	private String cmsUsername;
	private String cmsPassword;
	private String cmsImportArticlesName;
	private String cmsImportArticleCommentsName;
	private Integer cmsArticleDisciplineAndRatingsKey;
	private Integer cmsArticleCommentsArchiveKey;
	private Integer cmsArticlePatientPopulationsArchiveKey;
	private String cmsImportArticleDisciplineAndRatingsName;
	private String cmsImportArticlePatientPopulationsName;
	private String cmsRemoteClientUrl;
	private List<Integer> serviceDisciplineIdList;
	private List<Integer> cmsArticleArchiveKeyList;
	private Integer cmsArticleArchiveKey;
	private Map<Integer, Integer> disciplineIdArvhiveKeyMap = new HashMap<Integer, Integer>();
	private static List<String> genericCompositeKeyList = null;
	
	
	static {
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList = new ArrayList<String>();
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("ArticleId");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("DisciplineId");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("RelevanceAvg");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("RelevanceAvg");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("Newsworthiness");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("PopulationId");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("CategoryId");
		GetArticlesGenericByDisciplineTask.genericCompositeKeyList.add("SettingId");
	}
	
	
	
	protected enum TaskPropertyKeys {
		cmsUsername, 
		cmsPassword,
		cmsArticleArchiveKeyList,
		cmsArticleArchiveKey,
		cmsArticleDisciplineAndRatingsKey,
		cmsArticleCommentsArchiveKey,
		cmsArticlePatientPopulationsArchiveKey,
		cmsImportArticlesName,
		cmsImportArticleCommentsName,
		cmsImportArticleDisciplineAndRatingsName, 
		cmsImportArticlePatientPopulationsName, 
		serviceDisciplineIdList, 
		serviceKey, 
		serviceIV,
		cmsRemoteClientUrl
	}
	
	public GetArticlesGenericByDisciplineTask() {
	}
	
	protected abstract String getServiceResponseAsString(int disciplineId);
	
	protected void initLocalProperties(Properties taskProperties) {
		if (taskProperties != null && taskProperties.size() > 0) {
			this.cmsUsername = taskProperties.getProperty(TaskPropertyKeys.cmsUsername.name());
			this.cmsPassword = taskProperties.getProperty(TaskPropertyKeys.cmsPassword.name());
			this.cmsImportArticlesName = taskProperties.getProperty(TaskPropertyKeys.cmsImportArticlesName.name());
			this.cmsImportArticleCommentsName = taskProperties.getProperty(TaskPropertyKeys.cmsImportArticleCommentsName.name());
			this.cmsImportArticlePatientPopulationsName = taskProperties.getProperty(TaskPropertyKeys.cmsImportArticlePatientPopulationsName.name());
			this.cmsImportArticleDisciplineAndRatingsName = taskProperties.getProperty(TaskPropertyKeys.cmsImportArticleDisciplineAndRatingsName.name());			
			this.cmsRemoteClientUrl = taskProperties.getProperty(TaskPropertyKeys.cmsRemoteClientUrl.name());
			Integer archiveKeyTmp = null;
			try {
				archiveKeyTmp = Integer.parseInt(taskProperties.getProperty(TaskPropertyKeys.cmsArticleCommentsArchiveKey.name()));
				this.cmsArticleCommentsArchiveKey = archiveKeyTmp;
			} catch (NumberFormatException nfe) {
				logger.error("Wrong datatype for cmsArticleCommentsArchiveKey. Value was '" + archiveKeyTmp + "'. Expected a valid Integer");
			}
			try {
				archiveKeyTmp = Integer.parseInt(taskProperties.getProperty(TaskPropertyKeys.cmsArticleDisciplineAndRatingsKey.name()));
				this.cmsArticleDisciplineAndRatingsKey = archiveKeyTmp;
			} catch (NumberFormatException nfe) {
				logger.error("Wrong datatype for cmsArticleDisciplineAndRatingsKey. Value was '" + archiveKeyTmp + "'. Expected a valid Integer");
			}
			try {
				archiveKeyTmp = Integer.parseInt(taskProperties.getProperty(TaskPropertyKeys.cmsArticlePatientPopulationsArchiveKey.name()));
				this.cmsArticlePatientPopulationsArchiveKey = archiveKeyTmp;
			} catch (NumberFormatException nfe) {
				logger.error("Wrong datatype for cmsArticlePatientPopulationsArchiveKey. Value was '" + archiveKeyTmp + "'. Expected a valid Integer");
			}
			super.serviceKey = taskProperties.getProperty(TaskPropertyKeys.serviceKey.name());
			super.serviceIV = taskProperties.getProperty(TaskPropertyKeys.serviceIV.name());
			
			String serviceDisciplineIdListAsString = taskProperties.getProperty(TaskPropertyKeys.serviceDisciplineIdList.name());
			if (null != serviceDisciplineIdListAsString) {
				serviceDisciplineIdListAsString = serviceDisciplineIdListAsString.replaceAll("\\s+", "");
				this.serviceDisciplineIdList = new ArrayList<Integer>();
				String[] serviceTopicIdArray = serviceDisciplineIdListAsString.split(",");
				for (String id : serviceTopicIdArray) {
					try {
						this.serviceDisciplineIdList.add(Integer.parseInt(id));
					} catch (NumberFormatException nfe) {
						logger.error("Wrong datatype encountered in serviceTopicIdList. Value was '" + id + "'. Expected a valid Integer");
					}
				}
			}
			
			String cmsArchiveKeyListAsString = taskProperties.getProperty(TaskPropertyKeys.cmsArticleArchiveKeyList.name());
			if (null != cmsArchiveKeyListAsString) {
				cmsArchiveKeyListAsString = cmsArchiveKeyListAsString.replaceAll("\\s+", "");
				this.cmsArticleArchiveKeyList = new ArrayList<Integer>();
				String[] cmsArchiveKeyArray = cmsArchiveKeyListAsString.split(",");
				for (String id : cmsArchiveKeyArray) {
					try {
						this.cmsArticleArchiveKeyList.add(Integer.parseInt(id));
					} catch (NumberFormatException nfe) {
						logger.error("Wrong encountered in cmsArchiveKeyList. Value was '" + id + "'. Expected a valid Integer");
					}
				}
			}
			
			this.disciplineIdArvhiveKeyMap = new HashMap<Integer, Integer>();
			int i = 0;
			try {
				for (Integer disciplineId : this.serviceDisciplineIdList) {
					this.disciplineIdArvhiveKeyMap.put(disciplineId, this.cmsArticleArchiveKeyList.get(i++));
				}
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				logger.error("Mismatch between arvhive key list and discipline id list in task configuration. Lists must be of same length. Please correct this via the cms web admin console." + aioobe);
			}
		}
	}
	
	protected void initEvsClient() {
		if (this.cmsClient == null) {
			logger.info("Logging in using remote client.");
			this.cmsClient = ClientFactory.getRemoteClient(this.cmsRemoteClientUrl, false);
			this.cmsClient.login(this.cmsUsername, this.cmsPassword);
		}
	}
	
	protected String destroyEvsClient() {
		String result = null;
		if (this.cmsClient != null) {
			result = this.cmsClient.logout();
			this.cmsClient = null;
		}
		return result;
	}
	/**
	 * Import articles before rest because articles need to exist in the cms archive before the "rest" is imported.
	 */
	protected void importAllContent(List<String> compositeKeyXpathNodeList, List<String> potentialInvalidXmlNodeNameList) {
		logger.info("importAllContent starting ..:");
		if ((this.cmsArticleArchiveKeyList != null && this.cmsArticleArchiveKeyList.size() > 0) && (serviceDisciplineIdList != null && serviceDisciplineIdList.size() > 0)) {
			logger.info("content found (non-empty)");
			String xmlString = null;
			Map<Integer, String> xmlResponseAsStringMap = new HashMap<Integer, String>();
			for (Integer disciplineId : this.disciplineIdArvhiveKeyMap.keySet()) {
				xmlString = fixResponseString(getServiceResponseAsString(disciplineId), compositeKeyXpathNodeList, potentialInvalidXmlNodeNameList);
				xmlResponseAsStringMap.put(disciplineId, xmlString);
				logger.info("Importing discipline id " + disciplineId);
			}
			
			Set<Integer> failedDisciplineIds = new HashSet<Integer>();
			
			for (Integer discId : xmlResponseAsStringMap.keySet()) {
				try {
					importArticles(xmlResponseAsStringMap.get(discId), this.disciplineIdArvhiveKeyMap.get(discId));
				} catch (Exception e) {
					logger.error("Failed importing article with discipline id " + discId, e);
					failedDisciplineIds.add(discId);
				}
			}
			
			for (Integer archiveKey : xmlResponseAsStringMap.keySet()) {
				if (failedDisciplineIds.contains(archiveKey)) {
					logger.warn("Skipping importRest for failed article with archive key/discipline id " + archiveKey);
				} else {
					try {
						importRest(xmlResponseAsStringMap.get(archiveKey));
					} catch (Exception e) {
						logger.error("Failed importing rest of article with archive key/discipline id " + archiveKey, e);
					}
				}
			}
		} else {
			logger.error("Plugins want to import content from McMaster, but archive key list or discipline id list is not set in pluginconfig. Plugin cannot perform any action. Please correct plugin configuration in admin gui");
		}
	}
	
	private void importArticles(String xmlContent, Integer articleArchiveKey) {
		ImportContentsParams params = new ImportContentsParams();
		params.categoryKey = articleArchiveKey;
		params.importName = this.cmsImportArticlesName;
		params.data = xmlContent;
		params.publishFrom = new Date();
		this.cmsClient.importContents(params);
	}
	
	private void importRest(String xmlContent) {
		ImportContentsParams params = new ImportContentsParams();
		
		params.categoryKey = this.cmsArticleCommentsArchiveKey;
		params.importName = this.cmsImportArticleCommentsName;
		params.data = xmlContent;
		params.publishFrom = new Date();
		this.cmsClient.importContents(params);
		
		params.categoryKey = this.cmsArticleDisciplineAndRatingsKey;
		params.importName = this.cmsImportArticleDisciplineAndRatingsName;
		this.cmsClient.importContents(params);
		
		params.categoryKey = this.cmsArticlePatientPopulationsArchiveKey;
		params.importName = this.cmsImportArticlePatientPopulationsName;
		this.cmsClient.importContents(params);
	}
	
	private String fixResponseString(String responseString, List<String> compositeKeyXpathNodeList, List<String> potentialInvalidXmlNodeNameList) {
		String fixedResponseString = responseString;
		if (! fixedResponseString.contains("<?xml")) {
			fixedResponseString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + fixedResponseString;
		}
		fixedResponseString = fixedResponseString.replaceAll("<diffgr:diffgram xmlns:diffgr=\"urn:schemas-microsoft-com:xml-diffgram-v1\" xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\">", "");
		fixedResponseString = fixedResponseString.replaceAll("</diffgr:diffgram>", "");
		fixedResponseString = fixedResponseString.replaceAll("diffgr:", "");
		fixedResponseString = fixedResponseString.replaceAll("msdata:", "");
		
		String xmlNodeStringStart = null;
		String xmlNodeStringEnd = null;
		for (String xmlNodeName : potentialInvalidXmlNodeNameList) {
			xmlNodeStringStart = "<" + xmlNodeName + ">";
			xmlNodeStringEnd = "</" + xmlNodeName + ">";
			fixedResponseString = fixedResponseString.replaceAll(xmlNodeStringStart, (xmlNodeStringStart + "<![CDATA["));
			fixedResponseString = fixedResponseString.replaceAll(xmlNodeStringEnd, ("]]>" + xmlNodeStringEnd));
		}
		
		Document doc = null;
		try {
			doc = new SAXBuilder().build(new ByteArrayInputStream(fixedResponseString.getBytes()));
		} catch (IOException ioe) {
			logger.error("Unable to build document from xmlstring", ioe);
		} catch (JDOMException jde) {
			logger.error("Unable to build document from xmlstring", jde);
		}
		XPath xpath = null;
		
		List<List<Element>> elementLists = null;
		try {
			elementLists = new ArrayList<List<Element>>();
			for (String xpathString : compositeKeyXpathNodeList) {
				xpath = XPath.newInstance(xpathString);
				elementLists.add(xpath.selectNodes(doc));
			}
			/*
			elementLists = new ArrayList<List<Element>>();
			xpath = XPath.newInstance("/NewDataSet/DisciplineAndRatingsTbl");
			elementLists.add(xpath.selectNodes(doc));
			xpath = XPath.newInstance("/NewDataSet/ArticleCommentsTbl");
			elementLists.add(xpath.selectNodes(doc));
			xpath = XPath.newInstance("/NewDataSet/ArticlePatientPopulationTbl");
			elementLists.add(xpath.selectNodes(doc));
			xpath = XPath.newInstance("/NewDataSet/ArticleCategoryTbl");
			elementLists.add(xpath.selectNodes(doc));*/
		} catch (JDOMException jde) {
			logger.error("Could not perform xpath query. Error message: " + jde.getMessage(), jde);
		}
		for (List<Element> elementList : elementLists) {
			generateUniqueCompositeKeyElementForRelatedContent(elementList);
		}
		
		StringWriter writer = new StringWriter();
        XMLOutputter prettyOut = new XMLOutputter();
        try {
			prettyOut.output(doc, writer);
		} catch (IOException ioe) {
			logger.error("Unable to get string representation from JDom document: " + ioe.getMessage());
		}
		return writer.toString();
	}
	
	
	
	/**
	 * Unique identifier is required for each imported content element by cms solution in order to perform import.
	 * This method generates composite keys and adds them to the xml structure prior to import.
	 * @param elementList
	 */
	private Element generateUniqueCompositeKeyElementForRelatedContent(List<Element> elementList) {
		Element uidElement = null;
		XPath xpath = null;
		Element singleElement = null;
		String uidAsString = null;
		for (Element element : elementList) {
			uidAsString = null;
			uidElement = new Element("uid");
			try {
				for (String xpathString : GetArticlesGenericByDisciplineTask.genericCompositeKeyList) {
					xpath = XPath.newInstance(xpathString);
					if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
						uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
					}
				}
				/*
				xpath = XPath.newInstance("ArticleId");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = singleElement.getText();
				}
				xpath = XPath.newInstance("DisciplineId");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
				}
				xpath = XPath.newInstance("RelevanceAvg");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
				}
				xpath = XPath.newInstance("Newsworthiness");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
				}
				xpath = XPath.newInstance("PopulationId");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
				}
				xpath = XPath.newInstance("CategoryId");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
				}
				xpath = XPath.newInstance("SettingId");
				if (null != (singleElement  = (Element) xpath.selectSingleNode(element))) {
					uidAsString = ((uidAsString != null) ? uidAsString + "-" : "") + singleElement.getText();
				}*/
				
			} catch (JDOMException jde) {
				logger.error("Unable to perform XPath query while selecting XML Elements for composity key", jde);
			}
			uidElement.setText(uidAsString);
			element.addContent(uidElement);
		}
		return uidElement;
	}
}