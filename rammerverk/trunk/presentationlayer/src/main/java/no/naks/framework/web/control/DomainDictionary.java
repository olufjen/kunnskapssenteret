package no.naks.framework.web.control;

import java.util.List;

import no.naks.rammeverk.kildelag.model.AbstractModel;

/**
 * This interface respresent the the dictionary classes.
 * The Dictionary contains collections of modelobjects from the business domain
 * @author ojn
 *
 */
public interface DomainDictionary {
	
	public void addtoSessionDictionary(Object o,String key);
	public void addtoTableDictionary(List tableList,String key);
	public List getDictionaryTable(String key);
	public AbstractModel getSessionModel(String key);
	public Object getSessionObject(String key);
	public void removeTabledictionaryEntry(String key);
	public void removeSessionDictionarEntry(String key);
}
