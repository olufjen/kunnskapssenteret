package no.helsebiblioteket.admin.task;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameter;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameterList;
import no.helsebiblioteket.admin.service.OrganizationService;

public class ImportProxyDataTask {
	private final Log logger = LogFactory.getLog(getClass());
	private static final String requestPatternString = "^[A-Z]{3,8} http[s]?://([a-zA-Z0-9\\-]+[\\.a-zA-Z0-9\\-]+):.*";
	private static final String linePatternString = "^([\\d.]+) - - \\[([\\w:/]+\\s[\\+\\-]\\d{4})\\] \"(.+?)\".*";
	private OrganizationService organizationService;
	private String readLogFileName;
	private String proxyDomainName;

	public void importProxyData() {
		Map<String, ProxyHitParameter> map = new HashMap<String, ProxyHitParameter>();
		Pattern requestPattern = Pattern.compile(requestPatternString);
		Pattern linePattern = Pattern.compile(linePatternString);
		SimpleDateFormat fromDateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss z");
		SimpleDateFormat toDateFormat = new SimpleDateFormat("yyyyMMddHH");
		SimpleDateFormat todayFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar today = Calendar.getInstance();
	    String startOfToday = todayFormatter.format(today.getTime()) + " 00:00:00.000000000";

		File readFile = new File(this.readLogFileName);
		FileInputStream readFstream;
		int lines = 0;
		try {
		    today.setTime(todayFormatter.parse(startOfToday));
		    Calendar yesterday = Calendar.getInstance();
		    yesterday.setTime(today.getTime());
		    yesterday.add(Calendar.DATE, -1);

			readFstream = new FileInputStream(readFile);
			DataInputStream in = new DataInputStream(readFstream);
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = inBr.readLine()) != null)   {
				lines++;
				
				Matcher lineMatcher = linePattern.matcher(line);
				if(!lineMatcher.matches()){
					this.logger.error("Bad log entry: " + line);
					break;
				}
				String ipAddress = lineMatcher.group(1);
				String dateString = lineMatcher.group(2);
				String request = lineMatcher.group(3);
				Matcher m = requestPattern.matcher(request);
				if(!m.matches()){
					this.logger.error("Bad log entry: " + request);
					break;
				}
				String domain = m.group(1);
				if(domain.equals(this.proxyDomainName)){
					continue;
				}
				String fromIP = ipAddress;
				String toDomain = domain;

				Date date = fromDateFormat.parse(dateString);
				if(date.before(yesterday.getTime())){
					continue;
				}
				if(date.after(today.getTime())){
					break;
				}
				String period = toDateFormat.format(date);
				String lookup = fromIP+"*"+toDomain+"*"+period;
				
				if(map.containsKey(lookup)){
					map.get(lookup).inc();
				} else {
					map.put(lookup, new ProxyHitParameter(
							fromIP, toDomain, period, 1));
				}
			}
			in.close();
		} catch (IOException e) {
			this.logger.error("IO Exception: " + e);
		} catch (ParseException e) {
			this.logger.error("Parse Exception: " + e);
		}
		
		ProxyHitParameterList list = new ProxyHitParameterList();
		for (String key : map.keySet()) {
			list.getList().add(map.get(key));
		}
		
		boolean result = this.organizationService.insertProxyHits(list);
		if( ! result){
			this.logger.error("Inserting proxy data failed");
		} else {
			this.logger.info("Imported " + list.getList().size() + " proxy elements from " + lines + " line file.");
		}
	}
	
	public String getReadLogFileName() {
		return readLogFileName;
	}
	public void setReadLogFileName(String readLogFileName) {
		this.readLogFileName = readLogFileName;
	}
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	public String getProxyDomainName() {
		return proxyDomainName;
	}
	public void setProxyDomainName(String proxyDomainName) {
		this.proxyDomainName = proxyDomainName;
	}
}