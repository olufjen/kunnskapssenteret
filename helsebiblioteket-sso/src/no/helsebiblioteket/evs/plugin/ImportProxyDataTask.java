package no.helsebiblioteket.evs.plugin;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameter;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameterList;
import no.helsebiblioteket.admin.service.OrganizationService;

public class ImportProxyDataTask {
	private OrganizationService organizationService;

	protected void initLocalProperties(Properties taskProperties) { }
	protected void initEvsClient() { }
	protected String destroyEvsClient() { return ""; }
	protected void importContent() {
		ProxyHitParameterList list = new ProxyHitParameterList();
		Map<String, ProxyHitParameter> map = new HashMap<String, ProxyHitParameter>();
		
		File file = new File("C:\\dev\\Dokumenter\\ezproxy_201002.log");
		//62.101.205.228 - - [05/Feb/2010:10:53:14 +0100] "GET http://www.ncbi.nlm.nih.gov:80/stat?jsevent=autocomplete&userTyped=goitre%20&hasScrolled=false&usedArrows=false&selectionAction=enter&optionSelected=&optionIndex=-1&optionsCount=6&optionsDisplayed=goitre%20prevalence%2Cgoitre%20surgery%2Cgoitre%20children%2Cgoitre%20iodine%2Cgoitre%20n%2Cmultinodular%20goitre%20radioiodine&ncbi_phid=CE882E32B6BE92C100000000007D854C&ncbi_timesinceload=20686 HTTP/1.1" 200 912
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null)   {
				Pattern p = Pattern.compile("a*b");
				Matcher m = p.matcher("aaaaab");
				boolean b = m.matches();
				System.out.println (line);
				String ipAddress = "62.101.205.228";
				String date = "05/Feb/2010:10:53:14 +0100";
				String url = "http://www.ncbi.nlm.nih.gov:80/stat?jsevent=autocomplete&userTyped=goitre%20";
				

				String fromIP = "62.101.205.228";
				String toDomain = "www.ncbi.nlm.nih.gov";
				String period = "2010020510";
				
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean result = this.organizationService.insertProxyHits(list);
		if( ! result){
			
		}
		
	}
	public static void main(String[] args) {
		
	}
}
