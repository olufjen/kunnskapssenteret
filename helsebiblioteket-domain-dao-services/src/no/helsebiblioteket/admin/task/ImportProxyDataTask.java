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
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameter;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameterList;
import no.helsebiblioteket.admin.service.OrganizationService;

public class ImportProxyDataTask {
	private String requestPatternString = "^[A-Z]{3,4} http://([a-zA-Z0-9\\-]+[\\.a-zA-Z0-9\\-]+):80/.*";
	private String linePatternString = "^([\\d.]+) - - \\[([\\w:/]+\\s[\\+\\-]\\d{4})\\] \"(.+?)\".*";
	private OrganizationService organizationService;
	protected void initLocalProperties(Properties taskProperties) { }
	protected void initEvsClient() { }
	protected String destroyEvsClient() { return ""; }
	public void importContent() {
		Map<String, ProxyHitParameter> map = new HashMap<String, ProxyHitParameter>();
		Pattern requestPattern = Pattern.compile(this.requestPatternString);
		Pattern linePattern = Pattern.compile(this.linePatternString);
		SimpleDateFormat fromDateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss z");
		SimpleDateFormat toDateFormat = new SimpleDateFormat("yyyyMMddhh");

		File file = new File("C:\\dev\\Notater\\ezproxy_201002.log");
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null)   {
				Matcher lineMatcher = linePattern.matcher(line);
				if(!lineMatcher.matches()){
					// TODO: Deal with this!
					System.out.println("Bad log entry: " + line);
					break;
				}
				String ipAddress = lineMatcher.group(1);
				String date = lineMatcher.group(2);
				String request = lineMatcher.group(3);
				Matcher m = requestPattern.matcher(request);
				if(!m.matches()){
					// TODO: Deal with this!
					System.out.println("Bad log entry: " + request);
					break;
				}
				String domain = m.group(1);
				// TODO: Remove this!
//				System.out.println (date + " -- " + ipAddress + " -- " + domain);

				String fromIP = ipAddress;
				String toDomain = domain;
				String period = toDateFormat.format(fromDateFormat.parse(date));
				
				String lookup = fromIP+"*"+toDomain+"*"+period;
				System.out.println ("lookup=" + lookup);
				
				if(map.containsKey(lookup)){
					map.get(lookup).inc();
				} else {
					map.put(lookup, new ProxyHitParameter(
							fromIP, toDomain, period, 1));
				}
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProxyHitParameterList list = new ProxyHitParameterList();
		for (String key : map.keySet()) {
			list.getList().add(map.get(key));
		}
		boolean result = this.organizationService.insertProxyHits(list);
		if( ! result){
			// TODO: Deal with this!
		}
		
	}
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public static void main(String[] args) {
		ImportProxyDataTask task = new ImportProxyDataTask();
		task.importContent();
	}
}
