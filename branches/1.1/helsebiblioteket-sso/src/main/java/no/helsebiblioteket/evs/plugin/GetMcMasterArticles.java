package no.helsebiblioteket.evs.plugin;

import java.util.Collections;
import java.util.List;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;
import no.helsebiblioteket.evs.plugin.LogInInterceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.Client;
import com.enonic.cms.api.client.ClientFactory;
import com.enonic.cms.api.client.model.GetContentByCategoryParams;
import com.enonic.cms.api.plugin.PluginEnvironment;

/**
 * Getting McMaster articles by index and count, with related disciplines
 * 
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 *
 */
public class GetMcMasterArticles {
	private Log log = LogFactory.getLog(GetMcMasterArticles.class);
	private GeoIpService geoIpService;
	private String noAccessTextName;
	private Client client;
	private Document artDiscRat;
	private PluginEnvironment pluginEnvironment;

	/**
	 * This method first get all the ratings.
	 * Then it selects the articles and disciplines from it's related content,
	 * and returns a sublist of the articles by index and count.
	 * 
	 * @param artDiscRatKey
	 * @param index
	 * @param count
	 * @param query
	 * @return Articles with related disciplines
	 * @throws JDOMException
	 */
	public Document getArticlesByDisciplines(int artDiscRatKey, int index, int count, String query) throws JDOMException {
		String ipAddress = LogInInterceptor.getXforwardedForOrRemoteAddress(pluginEnvironment.getCurrentRequest());
		Document result;

		if (geoIpService.hasAccess(ipAddress, "NO")) {
			this.artDiscRat = getArtDiscRat(artDiscRatKey, query);
			List<Element> articles = getArticles();
			List<Element> disciplines = getDisciplines();

			int totalcount = articles.size();
			int toIndex = totalcount < (index + count)? totalcount : index + count;
			int resultcount = toIndex - index;

			Element contents = new Element("contents");
			contents.setAttribute("index", Integer.toString(index));
			contents.setAttribute("totalcount", Integer.toString(totalcount));
			contents.setAttribute("resultcount", Integer.toString(resultcount));
			result = new Document(contents);
			
			articles = articles.subList(index, toIndex);

			for (Element article : articles) {
				List<Element> disciplineKeys = getDisciplineKeys(article.getAttributeValue("key"));

				if (disciplineKeys != null && disciplineKeys.size() > 0) {
					Element contentdata = article.getChild("contentdata");
					contentdata.addContent(disciplineKeys);
				}
				article.detach();
				contents.addContent(article);
			}
			Element relatedcontents = new Element("relatedcontents");
			relatedcontents.setAttribute("count", Integer.toString(disciplines.size()));

			for (Element discipline : disciplines) {
				discipline.detach();
				relatedcontents.addContent(discipline);
			}
			contents.addContent(relatedcontents);
		} else {
			Element noAccess = new Element(this.noAccessTextName);
			result = new Document(noAccess);
		}
		return result;
	}

	/**
	 * 
	 * @param articleKey
	 * @return The discipline keys of the given article
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	private List<Element> getDisciplineKeys(String articleKey) throws JDOMException {
		XPath xPath = XPath.newInstance("//content[contentdata/mcmasterarticle/content/@key = '" + articleKey + "']/contentdata/discipline");
		List<Element> contents = xPath.selectNodes(this.artDiscRat);
		log.info("Getting disciplines for article " + articleKey + ": " + contents.size());

		for (Element discipline : contents) {
			discipline.detach();
		}
		return contents;
	}

	/**
	 * 
	 * @return A list of the related disciplines
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	private List<Element> getDisciplines() throws JDOMException {
		XPath xPath = XPath.newInstance("//relatedcontents/content[@contenttype = 'mm_disc']");
		List<Element> disciplines = xPath.selectNodes(this.artDiscRat);
		return disciplines;
	}

	/**
	 * 
	 * @return A list of all the articles, ordered by contenkey, last first
	 * @throws JDOMException
	 */
	@SuppressWarnings("unchecked")
	private List<Element> getArticles() throws JDOMException {
		XPath xPath = XPath.newInstance("//relatedcontents/content[@contenttype = 'mm_article']");
		List<Element> articles = xPath.selectNodes(this.artDiscRat);
		Collections.reverse(articles);
		return articles;
	}

	private Document getArtDiscRat(int categoryKey, String query) {
		GetContentByCategoryParams artAndDiscParams = new GetContentByCategoryParams();
		artAndDiscParams.categoryKeys = new int[] { categoryKey };
		artAndDiscParams.levels = 1;
		artAndDiscParams.query = query;
		artAndDiscParams.index = 0;
		artAndDiscParams.count = 1000000;
		artAndDiscParams.includeData = true;
		artAndDiscParams.childrenLevel = 1;
		artAndDiscParams.parentLevel = 0;

		this.client = ClientFactory.getLocalClient();
		return this.client.getContentByCategory(artAndDiscParams);		
	}

	public void setNoAccessTextName(String noAccessTextName) {
		this.noAccessTextName = noAccessTextName;
	}

	public void setGeoIpService(GeoIpService geoIpService) {
		this.geoIpService = geoIpService;
	}

	public void setPluginEnvironment(PluginEnvironment pluginEnvironment) {
		this.pluginEnvironment = pluginEnvironment;
	}
}
