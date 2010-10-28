package no.kunnskapssenteret.evs.plugins.cache.domain;
/**
 * needed for synchronization purposes (can't synch on strings)
 * @author Leif Torger Grøndahl
 *
 */
public class CachedObjectKey {
	String key = null;
	
	public CachedObjectKey() {
		this(null);
	}
	
	public CachedObjectKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
