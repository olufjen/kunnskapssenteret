package no.naks.framework.web.control;

import java.util.HashMap;
import java.util.List;

import no.naks.rammeverk.kildelag.model.AbstractModel;

/**
 * This class represent the implementation of the dictionary.
 * The Dictionary contains collections of modelobjects from the business domain
 * @author ojn
 * 
 */
public class DomainDictionaryImpl extends AbstractDictionary {

	
	public DomainDictionaryImpl() {
		super();
		sessionDictionary = new HashMap();
		tableDictionary = new HashMap();
	}

	public void addtoSessionDictionary(Object o, String key) {
		sessionDictionary.put(key, o);
		
	}

	public void addtoTableDictionary(List tableList, String key) {
		tableDictionary.put(key, tableList);
		
	}

	public void removeTabledictionaryEntry(String key){
		tableDictionary.remove(key);
	}
	public void removeSessionDictionarEntry(String key){
		sessionDictionary.remove(key);
	}
	/* (non-Javadoc)
	 * @see no.naks.framework.web.control.DomainDictionary#getDictionaryTable(java.lang.String)
	 */
	public List getDictionaryTable(String key) {
		
		return (List) tableDictionary.get(key);
	}
	
	public AbstractModel getSessionModel(String key){
		return (AbstractModel)sessionDictionary.get(key);		
	}
	public Object getSessionObject(String key){
		return sessionDictionary.get(key);
	}

	
}
