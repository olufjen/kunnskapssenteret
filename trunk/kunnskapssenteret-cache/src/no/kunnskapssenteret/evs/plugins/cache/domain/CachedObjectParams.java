package no.kunnskapssenteret.evs.plugins.cache.domain;

import org.jdom.Document;

public class CachedObjectParams {
	public Document document;
    public int timeToLive;
    public boolean checkForChanges;
    public int checkForChangesTimePeriod;
    
	public CachedObjectParams() {
    }
}
