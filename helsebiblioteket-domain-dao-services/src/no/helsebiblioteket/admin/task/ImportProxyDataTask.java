package no.helsebiblioteket.admin.task;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private static final String requestPatternString = "^[A-Z]{3,4} http://([a-zA-Z0-9\\-]+[\\.a-zA-Z0-9\\-]+):80/.*";
	private static final String linePatternString = "^([\\d.]+) - - \\[([\\w:/]+\\s[\\+\\-]\\d{4})\\] \"(.+?)\".*";
	private OrganizationService organizationService;
	private String readLogFileName;
	public void importProxyData() {
		Map<String, ProxyHitParameter> map = new HashMap<String, ProxyHitParameter>();
		Pattern requestPattern = Pattern.compile(requestPatternString);
		Pattern linePattern = Pattern.compile(linePatternString);
		SimpleDateFormat fromDateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss z");
		SimpleDateFormat toDateFormat = new SimpleDateFormat("yyyyMMddhh");

		File readFile = new File(this.readLogFileName);
		FileInputStream readFstream;
		try {
			readFstream = new FileInputStream(readFile);
			DataInputStream in = new DataInputStream(readFstream);
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = inBr.readLine()) != null)   {
				Matcher lineMatcher = linePattern.matcher(line);
				if(!lineMatcher.matches()){
					this.logger.warn("Bad log entry: " + line);
					break;
				}
				String ipAddress = lineMatcher.group(1);
				String date = lineMatcher.group(2);
				String request = lineMatcher.group(3);
				Matcher m = requestPattern.matcher(request);
				if(!m.matches()){
					this.logger.warn("Bad log entry: " + request);
					break;
				}
				String domain = m.group(1);
				String fromIP = ipAddress;
				String toDomain = domain;
				String period = toDateFormat.format(fromDateFormat.parse(date));
				
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
			this.logger.warn("IO Exception: " + e);
		} catch (ParseException e) {
			this.logger.warn("Parse Exception: " + e);
		}
		
		ProxyHitParameterList list = new ProxyHitParameterList();
		for (String key : map.keySet()) {
			list.getList().add(map.get(key));
		}
		boolean result = this.organizationService.insertProxyHits(list);
		if( ! result){
			this.logger.error("Inserting proxy data failed");
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
}
