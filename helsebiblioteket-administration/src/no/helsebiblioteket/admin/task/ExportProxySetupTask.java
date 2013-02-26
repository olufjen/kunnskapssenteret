package no.helsebiblioteket.admin.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.proxy.ConfigGroup;
import no.helsebiblioteket.admin.domain.proxy.ProxyConfig;
import no.helsebiblioteket.admin.domain.proxy.UserGroup;
import no.helsebiblioteket.admin.service.AccessService;

public class ExportProxySetupTask {
	private final Log logger = LogFactory.getLog(getClass());
	private AccessService accessService;
	private String comment;
	private String name;
	private int loginPort;
	private String adminPassword;
	private String proxyPassword;
	private String proxyServletURL;
	private String configFileName;
	private String userFileName;
	private String waitRestart;
	private String restartCommand;
	private String maxConcurrentTransfers;
	private String maxVirtualHosts;
	private String maxSessions;
	private String maxLifetime;
	private String logFile;
	private String interfaceIp;
	private String staticConfigFileName;
	
	public void exportSetup(){
		File configFile = new File(this.configFileName);
		File userFile = new File(this.userFileName);
		File staticConfigFile = new File(this.staticConfigFileName);

		SupplierSourceResource[] resources = this.accessService.getSupplierSourceResourceListAll("").getList();
		ProxyConfig[] config = this.accessService.getProxyConfigListAll("").getList();
		
		List<ConfigGroup> configList = createConfigGroupList(resources, config);
		List<UserGroup> userList = createUserGroupList(resources);

		try {
			FileWriter fstream = new FileWriter(configFile);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write("# " + this.comment); out.newLine(); out.newLine();
			
			out.write("Option ProxyByHostname"); out.newLine();
			out.write("Name " + this.name); out.newLine();
			out.write("Interface " + this.interfaceIp); out.newLine();
			out.write("LoginPort " + this.loginPort); out.newLine();
			out.write("MaxConcurrentTransfers " + this.maxConcurrentTransfers); out.newLine();
			out.write("MaxVirtualHosts " + this.maxVirtualHosts); out.newLine();
			out.write("MaxSessions " + this.maxSessions); out.newLine();
			out.write("MaxLifetime " + this.maxLifetime); out.newLine(); out.newLine();
			
			out.write("LogFile " + this.logFile); out.newLine(); out.newLine();
			
			out.write("# Static config start"); out.newLine();
			BufferedReader configReader = new BufferedReader(new FileReader(staticConfigFile));
			String line = null;
			
			while ((line = configReader.readLine()) != null) {
				out.write(line); out.newLine();
			}
			configReader.close();
			out.write("# Static config end"); out.newLine(); out.newLine();

	    	int i=0;
			for (ConfigGroup group : configList) {
				i++;
				out.write(padRight("Group") + group.getGroup()); out.newLine();
				// DomainCookieOnly before Title and at the end of Group
				if(group.isDomainCookieOnly()){
					out.write(padRight("Option") + "DomainCookieOnly"); out.newLine();
				}
				out.write(padRight("Title") + group.getTitle()); out.newLine();
				out.write(padRight("URL") + group.getUrl()); out.newLine();
				// Multiple hosts
				for (String host : group.getHosts()) {
					out.write(padRight("Host") + host); out.newLine();
				}
				// excludedomain
				if( ! group.isExcludeDomain()){
					for (String domain : group.getDomains()) {
						out.write(padRight("Domain") + domain); out.newLine();
					}
				}
				// DJ
				for (String dj : group.getDomainJavaScript()) {
					out.write(padRight("DJ") + dj); out.newLine();
				}
				// HJ
				for (String hj : group.getHostJavaScript()) {
					out.write(padRight("HJ") + hj); out.newLine();
				}
				// Find/replace pairs
				for (int j = 0; j < group.getFind().length; j++) {
					out.write(padRight("Find") + group.getFind()[j]); out.newLine();
					out.write(padRight("Replace") + group.getReplace()[j]); out.newLine();
				}
				if(group.isDomainCookieOnly()){
					out.write(padRight("Option") + "Cookie"); out.newLine();
				}
		    	if(i<configList.size()){
			    	out.newLine();
		    	}
			}
	    	out.close();

	    	
	    	
	    	fstream = new FileWriter(userFile);
	    	out = new BufferedWriter(fstream);

	    	out.write("proxyadmin:" + this.adminPassword + ":admin"); out.newLine();
	    	out.newLine();

	    	i=0;
			for (UserGroup group : userList) {
				i++;
		    	out.write("::group=" + group.getName()); out.newLine();
		    	out.write(group.getName() + ":" + this.proxyPassword + ":cgi=" +
		    			this.proxyServletURL); out.newLine();
		    	if(i<userList.size()){
			    	out.newLine();
		    	}
			}
	    	out.close();
			
		} catch (IOException e){
	    	this.logger.error(e.getMessage());
	    }
		try {
			long wait = new Integer(this.waitRestart).longValue();
			Thread.sleep(wait);
			
		    Runtime runtime = Runtime.getRuntime();
		    Process process = runtime.exec(this.restartCommand); 
		    process.waitFor();
	    } catch (Exception e) {
	    	this.logger.error(e.getMessage());
	    } 
	}
	private List<ConfigGroup> createConfigGroupList(SupplierSourceResource[] resources, ProxyConfig[] config){
		Map<String, ConfigGroup> map = new HashMap<String, ConfigGroup>();
		Map<String, ProxyConfig> fromConfigMap = new HashMap<String, ProxyConfig>();
		for (ProxyConfig proxyConfig : config) { fromConfigMap.put(proxyConfig.getGroup(), proxyConfig); }
		for (SupplierSourceResource resource : resources) {
			if(map.containsKey(resource.getSupplierSource().getProxyDatabaseName())){
				ConfigGroup group = map.get(resource.getSupplierSource().getProxyDatabaseName());
				group.setHosts(addHost(group.getHosts(), resource.getSupplierSource().getHost()));
				group.setDomains(addDomain(group.getDomains(), resource.getSupplierSource().getUrl().getDomain()));
			} else {
				ConfigGroup group = new ConfigGroup();
				group.setGroup(resource.getSupplierSource().getProxyDatabaseName());
				group.setTitle(resource.getSupplierSource().getSupplierSourceName());
				group.setDomains(new String[]{resource.getSupplierSource().getUrl().getDomain()});
				group.setUrl(resource.getSupplierSource().getUrl().getStringValue());
				group.setHosts(new String[]{resource.getSupplierSource().getHost()});
				
				if(fromConfigMap.containsKey(group.getGroup())){
					ProxyConfig from = fromConfigMap.get(group.getGroup());
					group.setDomainCookieOnly(from.isDomainCookieOnly());
					group.setExcludeDomain(from.isExcludeDomain());
					group.setDomainJavaScript(from.getDomainJavaScript());
					group.setHostJavaScript(from.getHostJavaScript());
					group.setFind(from.getFind());
					group.setReplace(from.getReplace());
				} else {
					group.setDomainCookieOnly(false);
					group.setExcludeDomain(false);
					group.setDomainJavaScript(new String[0]);
					group.setHostJavaScript(new String[0]);
					group.setFind(new String[0]);
					group.setReplace(new String[0]);
				}
				map.put(group.getGroup(), group);
			}
		}		
		List<ConfigGroup> list = new ArrayList<ConfigGroup>(map.values());
		Collections.sort(list);
		return list;
	}
	
	private String[] addHost(String[] hosts, String newHost) {
		String[] newHosts = new String[hosts.length + 1];
		for (int i = 0; i < hosts.length; i++) {
			newHosts[i] = hosts[i];
		}
		newHosts[newHosts.length - 1] = newHost;
		return newHosts;
	}
	
	private String[] addDomain(String[] domains, String newDomain) {
		String[] newDomains = new String[domains.length + 1];
		for (int i = 0; i < domains.length; i++) {
			newDomains[i] = domains[i];
		}
		newDomains[newDomains.length - 1] = newDomain;
		return newDomains;
	}
	
	private List<UserGroup> createUserGroupList(SupplierSourceResource[] resources){
		Map<String, UserGroup> map = new HashMap<String, UserGroup>();
		for (SupplierSourceResource resource : resources) {
			if( ! map.containsKey(resource.getSupplierSource().getProxyDatabaseName())){
				UserGroup group = new UserGroup();
				group.setName(resource.getSupplierSource().getProxyDatabaseName());
				map.put(group.getName(), group);
			}
		}
		List<UserGroup> list = new ArrayList<UserGroup>(map.values());
		Collections.sort(list);
		return list;
	}
	private static String padRight(String s) {
	     return String.format("%1$-" + 8 + "s", s);  
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLoginPort() {
		return loginPort;
	}
	public void setLoginPort(int loginPort) {
		this.loginPort = loginPort;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getProxyPassword() {
		return proxyPassword;
	}
	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}
	public String getProxyServletURL() {
		return proxyServletURL;
	}
	public void setProxyServletURL(String proxyServletURL) {
		this.proxyServletURL = proxyServletURL;
	}
	public AccessService getAccessService() {
		return accessService;
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	public String getConfigFileName() {
		return configFileName;
	}
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	public String getUserFileName() {
		return userFileName;
	}
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
	public String getWaitRestart() {
		return waitRestart;
	}
	public void setWaitRestart(String waitRestart) {
		this.waitRestart = waitRestart;
	}
	public String getRestartCommand() {
		return restartCommand;
	}
	public void setRestartCommand(String restartCommand) {
		this.restartCommand = restartCommand;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setMaxConcurrentTransfers(String maxConcurrentTransfers) {
		this.maxConcurrentTransfers = maxConcurrentTransfers;
	}
	public void setMaxVirtualHosts(String maxVirtualHosts) {
		this.maxVirtualHosts = maxVirtualHosts;
	}
	public void setMaxSessions(String maxSessions) {
		this.maxSessions = maxSessions;
	}
	public void setMaxLifetime(String maxLifetime) {
		this.maxLifetime = maxLifetime;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	public void setInterfaceIp(String interfaceIp) {
		this.interfaceIp = interfaceIp;
	}
	public void setStaticConfigFileName(String staticConfigFileName) {
		this.staticConfigFileName = staticConfigFileName;
	}
}