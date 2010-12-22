package no.helsebiblioteket.admin.service.importexport.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;



public class CheckIpRegister {
	String ipRangDaoXmlAsString;
	public static final String filePath="C:\\dev\\eclipse_workspaces\\kunnskapssenteret2\\helsebiblioteket-importexport\\src\\no\\helsebiblioteket\\admin\\service\\importexport\\impl\\";
	
	public CheckIpRegister() {
	}
	
	public static void main(String args[]) throws Exception {
		CheckIpRegister check = new CheckIpRegister();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath + "IpRangeDaoXml.xml")));
		
		StringBuilder tmp = new StringBuilder();
		String line = null;
		String ipArray[] = null;
		
		while ((line = reader.readLine()) != null) {
			tmp.append(line);
		}
		
		check.ipRangDaoXmlAsString = tmp.toString();
		
		tmp = null;
		
		reader = new BufferedReader(new FileReader(new File(filePath + "ezproxy_conf_include_ip_group3")));
		
		while ((line = reader.readLine()) != null) {
			line = line.replace("IncludeIP", "");
			line = line.replace("IncludeIp", "");
			line = line.replace("\t", "");
			line = line.replace(" ", "");
			ipArray = line.split("-");
			for (String ip : ipArray) {
				//System.out.println("ip found: '" + ip + "'");
				if (! check.ipRangDaoXmlAsString.contains(ip)) {
					System.out.println("Group 3 IP '" + ip + "' found in proxyconfig 'ezproxy_conf_include_ip_group3', but not in in importfile (iprangedaoxml.xml)");
				}
			}			
		}
		
		tmp = null;
		
		reader = new BufferedReader(new FileReader(new File(filePath + "ezproxy_conf_include_ip_group1_group2")));
		
		while ((line = reader.readLine()) != null) {
			line = line.replace("IncludeIP", "");
			line = line.replace("IncludeIp", "");
			line = line.replace("\t", "");
			line = line.replace(" ", "");
			ipArray = line.split("-");
			for (String ip : ipArray) {
				//System.out.println("ip found: '" + ip + "'");
				if (! check.ipRangDaoXmlAsString.contains(ip)) {
					System.out.println("Group 1/2 IP '" + ip + "' found in proxyconfig 'ezproxy_conf_include_ip_group1_group2', but not in in importfile (iprangedaoxml.xml)");
				}
			}			
		}
		
		tmp = null;
		
		reader = new BufferedReader(new FileReader(new File(filePath + "ezproxy_conf_include_ip_nhn_profdoc")));
		
		while ((line = reader.readLine()) != null) {
			line = line.replace("IncludeIP", "");
			line = line.replace("IncludeIp", "");
			line = line.replace("\t", "");
			line = line.replace(" ", "");
			ipArray = line.split("-");
			for (String ip : ipArray) {
				//System.out.println("ip found: '" + ip + "'");
				if (! check.ipRangDaoXmlAsString.contains(ip)) {
					System.out.println("NHN-profdoc IP '" + ip + "' found in proxyconfig 'ezproxy_conf_include_ip_nhn_profdoc', but not in in importfile (iprangedaoxml.xml)");
				}
			}
		}
	}
}