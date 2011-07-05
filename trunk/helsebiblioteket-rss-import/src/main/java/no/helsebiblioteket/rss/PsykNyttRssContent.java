package no.helsebiblioteket.rss;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;

import com.enonic.cms.api.client.model.content.RelatedContentsInput;

/**
 * 
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 *
 */
public class PsykNyttRssContent extends RssContent {
	private static final Namespace CONTENT_NAMESPACE = Namespace.getNamespace("content", "http://purl.org/rss/1.0/modules/content/");
	private List<Element> categories = null;

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
		Element imageDiv = encoded.getChild("div");
		imageDiv.getAttribute("class").setValue("articleimage");
		imageDiv.getChild("p").getAttribute("class").setValue("imagetxt");

		//Move the full description from the encoded element to the description element
		String description = encoded.getChild("p").getChildText("strong");

		if (description != null) {
			encoded.removeChild("p");
			this.feed.getChild("description").setText(description);
		}
		
		//Add more info to the disclaimer, or add new disclaimer if one does not exist
		Element disclaimer = getDisclaimer(encoded);
		
		if (disclaimer == null) {
			disclaimer = new Element("p");
			encoded.addContent(disclaimer);
		} else {
			disclaimer.addContent(0, new Element("br"));
		}
		disclaimer.addContent(0, new Text("."));
		disclaimer.addContent(0, getPsykNyttAnchor());
		disclaimer.addContent(0, new Text("Artikkelen har tidligere v\u00e6rt publisert i nyhetsbloggen "));
		
		//Add a read more paragraph with links to the relevant categories
		this.categories = this.feed.getChildren("category");
		
		if (categories != null && !categories.isEmpty()) {
			Element readMore = getReadMore();
			encoded.addContent(readMore);
		}
		//Remove all anchors that are not inside paragraphs (nofollow)
		encoded.removeChildren("a");
		encoded.detach();
		this.feed.addContent(encoded);
	}
	
	/**
	 * 
	 * @return an anchor to PsykNytt
	 */
	private static Element getPsykNyttAnchor() {
		Element a = new Element("a");
		a.setAttribute("href", "http://www.psyknytt.no");
		a.addContent(new Text("PsykNytt"));
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
			return last;
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
			Element a = new Element("a");
			a.setAttribute(new Attribute("href", url));
			a.setText(category);
			readMore.addContent(a);

			if (categories.indexOf(element) < categories.size()-2) {
				readMore.addContent(new Text(", "));
			} else if (categories.indexOf(element) == categories.size()-2) {
				readMore.addContent(new Text(" og "));
			} else {
				readMore.addContent(new Text("."));
			}
		}
		return readMore;
	}

	@Override
	public String getTitle() {
		return this.feed.getChildText("title");
	}
	
	@Override
	public String getXml() {
		return XML_OUT.outputString(feed);
	}

	/**
	 * 
	 * @return the related content - author, which is the same for all PsykNytt articles
	 */
	public static RelatedContentsInput getAuthor() {
		RelatedContentsInput relatedcontent = new RelatedContentsInput("authors");
		relatedcontent.addRelatedContent(78044);
		return relatedcontent;
	}
}
