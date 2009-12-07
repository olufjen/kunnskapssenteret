package no.helsebiblioteket.admin.ssoservice.geoip;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;
import no.helsebiblioteket.admin.ssoservice.SsoService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@SuppressWarnings("serial")

public class GeoIpServiceSso extends SsoService implements GeoIpService {
	protected static final Log logger = LogFactory.getLog(GeoIpServiceSso.class);
	
	private GeoIpService geoIpService;

	@Override
	public Log getLogger() {
		return logger;
	}

	@Override
	public boolean hasAccess(String ipAddress, String countryCodes) {
		return geoIpService.hasAccess(ipAddress, countryCodes);
	}
	
	public GeoIpService getGeoIpService() {
		return geoIpService;
	}

	public void setGeoIpService(GeoIpService geoIpService) {
		this.geoIpService = geoIpService;
	}
}