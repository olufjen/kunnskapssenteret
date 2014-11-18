package no.naks.framework.web.control;

import java.util.Map;

/**
 * This class respresent the abstract class of the dictionary.
 * The Dictionary contains collections of modelobjects from the business domain
 *   
 * @author ojn
 *
 */
public abstract class AbstractDictionary implements DomainDictionary {
	
	protected Map tableDictionary = null;  // Contains collections of defined model objects in the domain
	protected Map sessionDictionary = null; // Contains session model objects to be represented to the user in a session
	/**
	 * @return the sessionDictionary
	 */
	public Map getSessionDictionary() {
		return sessionDictionary;
	}
	/**
	 * @param sessionDictionary the sessionDictionary to set
	 */
	public void setSessionDictionary(Map sessionDictionary) {
		this.sessionDictionary = sessionDictionary;
	}
	/**
	 * @return the tableDictionary
	 */
	public Map getTableDictionary() {
		return tableDictionary;
	}
	/**
	 * @param tableDictionary the tableDictionary to set
	 */
	public void setTableDictionary(Map tableDictionary) {
		this.tableDictionary = tableDictionary;
	}
	

}
