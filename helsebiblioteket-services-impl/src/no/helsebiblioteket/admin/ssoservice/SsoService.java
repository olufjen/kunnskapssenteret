package no.helsebiblioteket.admin.ssoservice;


import org.apache.commons.logging.Log;
import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class SsoService implements Serializable {
	
	protected CacheHelper cacheHelper;
	public abstract Log getLogger();

	public void setCacheHelper(CacheHelper cacheHelper) {
		this.cacheHelper = cacheHelper;
	}
}