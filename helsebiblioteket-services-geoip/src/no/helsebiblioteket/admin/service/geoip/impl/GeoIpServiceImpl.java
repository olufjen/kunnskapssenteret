package no.helsebiblioteket.admin.service.geoip.impl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import no.helsebiblioteket.admin.service.geoip.GeoIpService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;

public class GeoIpServiceImpl implements GeoIpService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static boolean initiated = false;
	private Long reloadIntervalMilliseconds = null;
	private Long lastTimeDatabaseReadMilliseconds = null;
	private String dataFileName = null;
	private String licenseKey = null;
	private static LookupService lookupService;
	
	public boolean hasAccess(String ipAddress, String countryCodes) {
		if (!initiated && isTimeForRefresh()) {
			initiated = true;
			initGeoIpDb();
		}
		List<String> countryCodeList = Arrays.asList(countryCodes.split(","));
		return validateIp(ipAddress, countryCodeList);
	}
	
	
	private boolean validateIp(String ipAddress, List<String> countrycodes) {
        logger.debug("start looking up: " + ((ipAddress != null) ? ipAddress : null));
        Country country = lookupService.getCountry(ipAddress);
        String code = country.getCode();
        
        if (ipAddress != null) {
            logger.debug("country for " + ipAddress + ": " + code + " / " + country.getName());
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
		boolean ret = false;
        try {
            File dbFile = new File(dataFileName);
            if (licenseKey != null && !"".equals(licenseKey)) {
                logger.debug("lookupService with licenseKey");
                lookupService = new LookupService(dbFile, licenseKey);
            } else {
                logger.debug("lookupService with GEOIP_MEMORY_CACHE");
                lookupService = new LookupService(dbFile, LookupService.GEOIP_MEMORY_CACHE);
            }
            ret = true;
        } catch (IOException e) {
            logger.error("Failed to init LookupService", e);
            ret = false;
        } catch (NullPointerException e) {
            logger.error("Failed to init LookupService", e);
            ret = false;
        }
        return ret;
    }
	
	private boolean isTimeForRefresh() {
        if (reloadIntervalMilliseconds <= 0) {
            return false;
        }
        long now = System.currentTimeMillis();
        if ((now - lastTimeDatabaseReadMilliseconds) > reloadIntervalMilliseconds) {
            lastTimeDatabaseReadMilliseconds = now;
            logger.debug("time for geoip database reload");
            return true;
        }
        return false;
    }

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public void setReloadIntervalSeconds(Integer reloadIntervalSeconds) {
		this.reloadIntervalMilliseconds = (long) reloadIntervalSeconds*1000;
	}
}