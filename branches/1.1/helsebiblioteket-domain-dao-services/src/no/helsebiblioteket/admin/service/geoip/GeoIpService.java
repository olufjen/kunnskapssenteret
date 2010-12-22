package no.helsebiblioteket.admin.service.geoip;

public interface GeoIpService {
	public boolean hasAccess(String ipAddress, String countryCodes);
}
