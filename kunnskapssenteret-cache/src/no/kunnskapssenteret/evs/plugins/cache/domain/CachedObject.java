package no.kunnskapssenteret.evs.plugins.cache.domain;

import org.jdom.Document;

public class CachedObject {
	private Document document;
    private int timeToLive;
    private boolean checkForChanges;
    private int checkForChangesTimePeriod;
    private long timeRegistered;
    private boolean isBeeingUpdated;
	
    public CachedObject() {
    	this(null, null);
	}
    
    public CachedObject(CachedObjectParams cachedobjectparams)
    {
        this(cachedobjectparams, null);
    }

    public CachedObject(CachedObjectParams cachedobjectparams, Document document1)
    {
        timeRegistered = System.currentTimeMillis();
        if(null != cachedobjectparams)
        {
            timeToLive = cachedobjectparams.timeToLive;
            checkForChanges = cachedobjectparams.checkForChanges;
            checkForChangesTimePeriod = cachedobjectparams.checkForChangesTimePeriod;
        }
        document = document1;
    }

    public void setParams(CachedObjectParams cachedobjectparams)
    {
        timeRegistered = System.currentTimeMillis();
        timeToLive = cachedobjectparams.timeToLive;
        checkForChanges = cachedobjectparams.checkForChanges;
        checkForChangesTimePeriod = cachedobjectparams.checkForChangesTimePeriod;
    }

    public Boolean getCheckForChanges()
    {
        return Boolean.valueOf(checkForChanges);
    }

    public void setCheckForChanges(Boolean boolean1)
    {
        checkForChanges = boolean1.booleanValue();
    }

    public int getCheckForChangesTimePeriod()
    {
        return checkForChangesTimePeriod;
    }

    public void setCheckForChangesTimePeriod(int i)
    {
        checkForChangesTimePeriod = i;
    }

    public Document getDocument()
    {
        return document;
    }

    public void setDocument(Document document1)
    {
        document = document1;
    }

    public int getTimeToLive()
    {
        return timeToLive;
    }

    public void setTimeToLive(int i)
    {
        timeToLive = i;
    }

    public long getTimeRegistered()
    {
        return timeRegistered;
    }

    public void setTimeRegistered(Long long1)
    {
        timeRegistered = long1.longValue();
    }
    
    public boolean isBeeingUpdated() {
    	return this.isBeeingUpdated;
    }
    
    public void setIsBeeingUpdated(boolean isBeeingUpdated) {
    	this.isBeeingUpdated = isBeeingUpdated;
    }
}