package no.naks.rammeverk.kildelag.dao;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageResourceReaderDAO {
	/** Logger for this class and subclasses */
    protected final static Log logger = LogFactory.getLog(MessageResourceReaderDAO.class);
    
	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {	
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if(loader == null){
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}
	
	/*
	 * Looks up and returns a faces message resource value based on a key and a bundle name. Returns a fallback value if value for key is not found
	 */
	public static String getMessageResourceString(String bundleName, String key, String fallBackValue) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String result = null;
		try {
			result = getMessageResourceString(bundleName, key, null, facesContext.getViewRoot().getLocale());
		} catch(MissingResourceException mre) {
			logger.warn("Could not find message value for lookup key '" + key + "', using fallback value '" + fallBackValue + "'");
			result = fallBackValue;
		}
		return result;
	}
	
	/*
	 * Looks up and returns a faces message resource value
	 */
	public static String getMessageResourceString(String bundleName, String key, Object params[], Locale locale) throws MissingResourceException {
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		text = bundle.getString(key);
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}
	public static ResourceBundle getMessageBundle(String bundleName, String key, Object params[], Locale locale) throws MissingResourceException {
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		return bundle;
	}

}
