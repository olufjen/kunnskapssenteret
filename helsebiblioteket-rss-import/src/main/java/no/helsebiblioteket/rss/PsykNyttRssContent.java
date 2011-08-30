package no.helsebiblioteket.rss;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.enonic.cms.api.client.model.content.RelatedContentsInput;

/**
 * 
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 *
 */
public class PsykNyttRssContent extends RssContent {
	private static final Namespace CONTENT_NAMESPACE = Namespace.getNamespace("content", "http://purl.org/rss/1.0/modules/content/");
	private static final int AUTHOR = 78044; //Content key for PsykNytt author
	private List<Element> categories = null;
	private Log log = LogFactory.getLog(PsykNyttRssContent.class);

	public PsykNyttRssContent(Element feed) {
		this.feed = (Element) feed.clone();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void customize() throws JDOMException, IOException {
		String encodedAsString = "<encoded>" + this.feed.getChildText("encoded", CONTENT_NAMESPACE) + "</encoded>";
		this.feed.removeChild("encoded", CONTENT_NAMESPACE);
		
		//Getting the encoded CDATA html elements into a jdom Document for manipulation
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new StringReader(encodedAsString));
		Element encoded = doc.getRootElement();
		
		//Setting classes on image for correct placement in article
		XPath getImageDiv = XPath.newInstance("//div[@class = 'wp-caption alignleft']");
		Element imageDiv = (Element)getImageDiv.selectSingleNode(encoded);
//		Element imageDiv = encoded.getChild("div");
		imageDiv.getAttribute("class").setValue("articleimage");
		imageDiv.getChild("p").getAttribute("class").setValue("imagetxt");

		//Move the full description from the encoded element to the description element
		XPath getDescription = XPath.newInstance("//p/strong");
		Element description = (Element)getDescription.selectSingleNode(encoded);
//		String description = encoded.getChild("p").getChildText("strong");

		if (description != null) {
			encoded.removeContent(description);
			this.feed.getChild("description").setText(description.getValue());
		} else {
			log.info("Fant ikke ingress for " + getTitle());
		}
		
		//Add more info to the disclaimer, or add new disclaimer if one does not exist
		Element disclaimer = getDisclaimer(encoded);
		
		if (disclaimer == null) {
			disclaimer = new Element("em");
			Element p = new Element("p");
			p.addContent(disclaimer);
			encoded.addContent(p);
		} else {
			disclaimer.addContent(0, new Element("br"));
		}
		disclaimer.addContent(0, new Text("."));
		disclaimer.addContent(0, getAnchor("http://www.psyknytt.no", "PsykNytt"));
		disclaimer.addContent(0, new Text("Artikkelen har tidligere v\u00e6rt publisert i nyhetsbloggen "));
		
		//Add a read more paragraph with links to the relevant categories
		this.categories = this.feed.getChildren("category");
		removeFromCategories("Nytt nummer");
		
		if (categories != null && !categories.isEmpty()) {
			Element readMore = getReadMore();
			encoded.addContent(readMore);
		}
		//Remove all anchors (nofollow), breaks and images that are not inside paragraphs
		encoded.removeChildren("a");
		encoded.removeChildren("br");
		encoded.removeChildren("img");
		
		//Remove sharedaddy div
		XPath xpath = XPath.newInstance("//div[@class = 'sharedaddy']");
		Element sharedaddy = (Element) xpath.selectSingleNode(encoded);
		encoded.removeContent(sharedaddy);
		
		encoded.detach();
		this.feed.addContent(encoded);
	}
	
	private void removeFromCategories(String category) {
		
		for (Element element : categories) {
			String text = element.getText();
			
			if(text.equals(category)) {
				categories.remove(element);
				break;
			}
		}
	}

	/**
	 * 
	 * @param url
	 * @param text
	 * @return an anchor Element
	 */
	private static Element getAnchor(String url, String text) {
		Element a = new Element("a");
		a.setAttribute("href", url);
		a.addContent(new Text(text));
		return a;
	}

	/**
	 * 
	 * @param encoded
	 * @return the disclaimer of the encoded element, or null if one does not exist
	 */
	@SuppressWarnings("unchecked")
	private Element getDisclaimer(Element encoded) {
		List<Element> elements = encoded.getChildren("p");
		Element last = elements.get(elements.size()-1);
		String text = last.getChildText("em");
		
		if (text != null && text.startsWith("Disclaimer:")) {
			Element disclaimer = last.getChild("em");
			disclaimer.removeChild("strong");
			return disclaimer;
		}
		return null;
	}

	/**
	 * 
	 * @return an element, containing anchors to relevant categories on PsykNytt
	 */
	private Element getReadMore() {
		Element readMore = new Element("p");
		readMore.addContent(new Text("Les mer om "));

		for (Element element : categories) {
			String category = element.getText().toLowerCase();
			String url = "http://psyknyheter.wordpress.com/category/" + category.replace(" ", "-");
			Element a = getAnchor(url, category);
			readMore.addContent(a);

			if (categories.indexOf(element) < categories.size()-2) {
				readMore.addContent(new Text(", "));
			} else if (categories.indexOf(element) == categories.size()-2) {
				readMore.addContent(new Text(" og "));
			} else {
				readMore.addContent(new Text(", eller gå til "));
				Element news = getAnchor("http://psyknyheter.wordpress.com/category/nytt-nummer/", "siste nummer");
				readMore.addContent(news);
				readMore.addContent(new Text(" av PsykNytt."));
			}
		}
		return readMore;
	}
	
	/**
	 * 
	 * @return the related content - author, which is the same for all PsykNytt articles
	 */
	public static RelatedContentsInput getRelatedAuthor() {
		RelatedContentsInput relatedcontent = new RelatedContentsInput("authors");
		relatedcontent.addRelatedContent(AUTHOR);
		return relatedcontent;
	}
	
	
}
