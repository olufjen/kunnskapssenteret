package no.helsebiblioteket.evs.plugins.mcmaster;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.plugin.TaskPlugin;

import no.helsebiblioteket.evs.plugins.mcmaster.webservice.McMasterWSClient;
import no.helsebiblioteket.evs.util.AES;

public abstract class McMasterFeed extends TaskPlugin  {
	private Log logger = LogFactory.getLog(McMasterFeed.class);
	protected String serviceKey;
	protected String serviceIV;
	
	protected McMasterWSClient initMcMasterClient() {
		McMasterWSClient mcMasterWsClient = null;
		try {
			mcMasterWsClient = new McMasterWSClient();
		} catch (AxisFault af) {
			logger.error("Could not initiate McMasterWSClient. Error message: " + af.getMessage(), af);
		}
		return mcMasterWsClient;
	}
	
	protected String generateDynamicServiceKey() {
		String encKey = null;
		try {
            String sToday = "datetime=" + (new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.CANADA).format(new Date()));
            encKey = AES.encrypt(this.serviceKey, this.serviceIV, sToday);
            encKey=URLEncoder.encode(encKey, "UTF-8");
            //The key to make requests to our web services is encKey below (encrypted and URL encoded)
            //Uncomment the lines below to test if the decryption is working
            //String decKey = URLDecoder.decode(encKey, "UTF-8");
            //System.out.println("Java Decrypt of Java Encrypt: " + AES.decrypt(serviceKey, serviceIV, decKey));
        } catch (Exception e) {
            logger.error("Unable to create dynamic key for McMaster webservice client", e);
        }
        return encKey;
	}
}
