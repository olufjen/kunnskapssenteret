package no.helsebiblioteket.admin.service.geoip.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;

public class GeoIpServiceImpl implements GeoIpService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static boolean initiated = false;
	private String dataFileName = null;
	private String licenseKey = null;
	private static LookupService lookupService;
	
	public boolean hasAccess(InetAddress ipAddress, String countryCodes) {
		if (!initiated) {
			initGeoIpDb();
		}
		List<String> countryCodeList = Arrays.asList(countryCodes.split(","));
		return validateIp(ipAddress, countryCodeList);
	}
	
	
	private boolean validateIp(InetAddress ipAddress, List<String> countrycodes) {
        logger.debug("start looking up: " + ((ipAddress != null) ? ipAddress.toString() : null));
        Country country = lookupService.getCountry(ipAddress);
        String code = country.getCode();
        
        if (ipAddress != null) {
            logger.debug("country for " + ipAddress.getHostAddress() + ": " + code + " / " + country.getName());
        }

        if (!"".equals(code) && !"--".equals(code)) {
            for (String c : countrycodes) {
                if (c != null && !"".equals(c) && c.equals(code)) {
                    logger.debug("ip is valid");
                    return true;
                }
            }
        }
        
        return false;
    }
	
	private boolean initGeoIpDb() {
        try {
            File dbFile = new File(dataFileName);
            if (licenseKey != null && !"".equals(licenseKey)) {
                logger.debug("lookupService with licenseKey");
                lookupService = new LookupService(dbFile, licenseKey);
            } else {
                logger.debug("lookupService with GEOIP_MEMORY_CACHE");
                lookupService = new LookupService(dbFile, LookupService.GEOIP_MEMORY_CACHE);
            }
            initiated = true;
            return true;
        } catch (IOException e) {
            logger.warn("Failed to init LookupService", e);
        } catch (NullPointerException e) {
            logger.warn("Failed to init LookupService", e);
        }
        return false;
    }
	
	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
}