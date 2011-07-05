package no.helsebiblioteket.rss;


import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * Extend this class to cusomize different rss feeds.
 * Add the name of the import type to the ImportType enum and let the getInstance method
 * return an instance of the given type.
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 *
 */
public abstract class RssContent {
	protected static final XMLOutputter XML_OUT = new XMLOutputter();
	protected Element feed;
	
	public static enum ImportType {
		PsykNytt
	}
	
	/**
	 * 
	 * Customize the rss content, to make it ready for import to cms
	 * @throws Exception
	 */
	public abstract void customize() throws Exception;
	
	/**
	 * 
	 * @return the title of the content
	 */
	public abstract String getTitle();
	
	/**
	 * 
	 * @return the rss content as a xml string 
	 */
	public abstract String getXml();
	
	/**
	 * 
	 * @param type
	 * @param feed
	 * @return the rss content instance of given type
	 */
	public static RssContent getInstance(ImportType type, Element feed) {
		
		switch(type) {
		case PsykNytt: 
			return new PsykNyttRssContent(feed); 
		default: 
			return null;
		}
	}
}
