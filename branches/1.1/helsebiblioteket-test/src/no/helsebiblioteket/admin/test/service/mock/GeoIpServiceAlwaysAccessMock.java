package no.helsebiblioteket.admin.test.service.mock;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

public class GeoIpServiceAlwaysAccessMock implements GeoIpService {

	@Override
	public boolean hasAccess(String ipAddress, String countryCodes) {
		return true;
	}

}
