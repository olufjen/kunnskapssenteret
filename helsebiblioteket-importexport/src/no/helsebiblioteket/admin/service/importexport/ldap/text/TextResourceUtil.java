package no.helsebiblioteket.admin.service.importexport.ldap.text;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TextResourceUtil
{
    private static final Logger LOG = Logger.getLogger(TextResourceUtil.class.getName());

    private static final TextResourceUtil INSTANCE = new TextResourceUtil();

    public static TextResourceUtil getInstance()
    {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
	private final HashMap mResourceBundleMap;

    @SuppressWarnings("unchecked")
	private TextResourceUtil() {
        mResourceBundleMap = new HashMap();
    }
    
    @SuppressWarnings("unchecked")
	public Double getDouble(Class aClass, String  key) {
        return getDouble(aClass, key, null);
    }
    
    @SuppressWarnings("unchecked")
	public Double getDouble(Class aClass, String  key, Locale locale) {
        Double out = null;
        String val = getString(aClass, key, locale);
        
        try {
            out = new Double(val);
        }
        catch (NumberFormatException e) {
            if (LOG.isLoggable(Level.FINE)) {
                LOG.log(Level.FINE, "Could not parse value", e);
            }
        }
        return out;  
    }
    
    @SuppressWarnings("unchecked")
	public double getdouble(Class aClass, String key) {
        return getdouble(aClass, key, null);
    }
    
    @SuppressWarnings("unchecked")
	public double getdouble(Class aClass, String key, Locale locale) {
        Double i = getDouble(aClass, key, locale);
        return (null != i) ? i.doubleValue() : 0;
    }
    
    @SuppressWarnings("unchecked")
	public Long getLong(Class aClass, String  key) {
        return getLong(aClass, key, null);
    }
    
    @SuppressWarnings("unchecked")
	public Long getLong(Class aClass, String  key, Locale locale) {
        Long out = null;
        String val = getString(aClass, key, locale);
        
        try {
            out = new Long(val);
        }
        catch (NumberFormatException e) {
            if (LOG.isLoggable(Level.FINE)) {
                LOG.log(Level.FINE, "Could not parse value", e);
            }
        }
        return out;  
    }
    
    
    @SuppressWarnings("unchecked")
	public long getlong(Class aClass, String key) {
        return getlong(aClass, key, null);
    }
    
    @SuppressWarnings("unchecked")
	public long getlong(Class aClass, String key, Locale locale) {
        Long i = getLong(aClass, key, locale);
        return (null != i) ? i.longValue() : 0;
    }
    
    @SuppressWarnings("unchecked")
	public Integer getInteger(Class aClass, String key) {
        return getInteger(aClass, key, null);
    }
    
    @SuppressWarnings("unchecked")
	public Integer getInteger(Class aClass, String key, Locale locale) {
        Integer out = null;
        String val = getString(aClass, key, locale);
        
        try {
            out = new Integer(val);

        } catch (NumberFormatException e) {
            if (LOG.isLoggable(Level.FINE)) {
                LOG.log(Level.FINE, "Could not parse value", e);
            }
        }
        return out;
    }
    
    @SuppressWarnings("unchecked")
	public int getInt(Class aClass, String key) {
        return getInt(aClass, key, null);
    }
    
    @SuppressWarnings("unchecked")
	public int getInt(Class aClass, String key, Locale locale) {
        Integer i = getInteger(aClass, key, locale);
        return (null != i) ? i.intValue() : 0;
    }

    /**
     * Returns the specified string from the properties file of the class
     * @param aClass
     * @param key
     * @return The string
     */
    @SuppressWarnings("unchecked")
	public String getString(Class aClass, String key){
        return getString(aClass, key, null);
    }
    
    /**
     * Returns the specified string from the properties file of the class
     * @param aClass
     * @param key
     * @param locale
     * @return The string
     */
    @SuppressWarnings("unchecked")
	public String getString(Class aClass, String key, Locale locale) {
        if (null == locale) locale = Locale.getDefault();
        
        TextResourceUtilKey tKey = new TextResourceUtilKey(null, aClass, locale);
        
        if (!mResourceBundleMap.containsKey(tKey)) {

            String path = aClass.getName().replaceAll("[.]", "/");
            try {
                mResourceBundleMap.put(tKey, ResourceBundle.getBundle(path, locale));

            } catch (Exception e) {
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.log(Level.FINE, "Could not load textresource " + path + ".properties");
                }
                mResourceBundleMap.put(tKey, null);
            }
        }

        return getString(key, tKey);
    }

    /**
     * Returns the specified string from the properties file of the package
     * @param aPackage
     * @param key
     * @return The string
     */
    public String getString(Package aPackage, String key) {
        return getString(aPackage, key, null);
    }
    
    /**
     * Returns the specified string from the properties file of the package
     * @param aPackage
     * @param key
     * @param locale
     * @return The sstring
     */
    @SuppressWarnings("unchecked")
	public String getString(Package aPackage, String key, Locale locale) {
        if (null == locale) locale = Locale.getDefault();
        
        TextResourceUtilKey tKey = new TextResourceUtilKey(aPackage, null, locale);
        
        if (!mResourceBundleMap.containsKey(tKey)) {
            String path = aPackage.getName().replaceAll("[.]", "/");
            StringTokenizer tokenizer = new StringTokenizer(path, "/");
            String packageShortName = "";
            while (tokenizer.hasMoreElements()) {
                packageShortName = tokenizer.nextToken();
            }
            path += "/" + packageShortName;
            try {
                mResourceBundleMap.put(tKey, ResourceBundle.getBundle(path, locale));

            } catch (Exception e) {
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.log(Level.FINE, "Could not load textresource " + path + ".properties");
                }
                mResourceBundleMap.put(tKey, null);
            }
        }

        return getString(key, tKey);

    }


    private String getString(String key, Object object) {
        ResourceBundle resourceBundle = (ResourceBundle)mResourceBundleMap.get(object);
        if (resourceBundle != null) {
            try {
                return resourceBundle.getString(key);

            } catch (Exception e) {
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.log(Level.FINE, "Could not find key \"" + key + "\" in properties file for object: " + object);
                }
                return null;
            }
        } else {
            return null;
        }

    }
    
    /**
     * Key class implementation to support different Locale's of a resourcebundle in the internal map
     * @since 
     * @author 
     */
    private static class TextResourceUtilKey {
        private final Package _package;
        @SuppressWarnings("unchecked")
		private final Class _clazz;
        private final Locale _locale;
        
        @SuppressWarnings("unchecked")
		public TextResourceUtilKey(Package aPackage, Class aClass, Locale locale) {
            this._package = aPackage;
            this._clazz = aClass;
            this._locale = locale;
        }
        
        public String toString(){
            return ((null != _package)? _package.toString() : "_package;") 
            + ((null != _clazz) ? _clazz.toString() : "_class;") 
            + ((null != _locale) ? _locale.toString() : "_locale;");
        }       
        
        public boolean equals(Object o) {
            boolean ret = false;
            if (o instanceof TextResourceUtilKey) {
                TextResourceUtilKey k = (TextResourceUtilKey)o;
                if ((null == _clazz && null == k._clazz) || (null != _clazz && _clazz.equals(k._clazz)) 
                    && (null == _package && null == k._package) || (null != _package && _package.equals(k._package))
                    && (null == _locale && null == k._locale) || (null != _locale && _locale.equals(k._locale))
                ){
                    ret = true;
                }
            }
            
            return ret;
        }
        
        public int hashCode() {
            int p, c, l, ret;
            
            p = (null != _package) ? _package.hashCode() : 0;
            c = (null != _clazz) ? _clazz.hashCode() : 0;
            l = (null != _locale) ? _locale.hashCode() : 0;
            
            ret = p ^ c ^ l;
            
            if (ret <= 0) ret = super.hashCode();
            
            return ret;
        }
    }   
}