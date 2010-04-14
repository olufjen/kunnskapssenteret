package no.helsebiblioteket.admin.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.service.AccessService;

public class ExportProxySetupTask {
	private AccessService accessService;
	private String name;
	private int loginPort;
	private int loginPortSSL;
	private String adminPassword;
	private String proxyPassword;
	private String proxyServletURL;
	private String configFileName;
	private String userFileName;
	
	public void exportSetup(){
		File configFile = new File(this.configFileName);
		File userFile = new File(this.userFileName);

		SupplierSourceResource[] list = this.accessService.getSupplierSourceResourceListAll("").getList();

		try{
			FileWriter fstream = new FileWriter(configFile);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write("Option ProxyByHostname"); out.newLine();
			out.write("Name " + this.name); out.newLine();
			out.write("LoginPort " + this.loginPort); out.newLine();
			out.write("LoginPortSSL " + this.loginPortSSL); out.newLine();
			
			for (SupplierSourceResource resource : list) {
				out.write("Group " + resource.getSupplierSource().getProxyDatabaseName()); out.newLine();
				out.write("Title " + resource.getSupplierSource().getProxyDatabaseName()); out.newLine();
				out.write("URL " + resource.getSupplierSource().getUrl().getStringValue()); out.newLine();
				out.write("Domain " + resource.getSupplierSource().getUrl().getDomain()); out.newLine();
				out.newLine();
			}
	    	out.close();

	    	fstream = new FileWriter(userFile);
	    	out = new BufferedWriter(fstream);

	    	out.write("admin:" + this.adminPassword + ":admin"); out.newLine();

			for (SupplierSourceResource resource : list) {
		    	out.write("::group=" + resource.getSupplierSource().getProxyDatabaseName()); out.newLine();
		    	out.write(resource.getSupplierSource().getProxyDatabaseName() +
		    			":" + this.proxyPassword + ":cgi=" +
		    			this.proxyServletURL); out.newLine();
		    	out.newLine();
			}
		} catch (IOException e){
	    	System.err.println("Error: " + e.getMessage());
	    }
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
	public int getLoginPortSSL() {
		return loginPortSSL;
	}
	public void setLoginPortSSL(int loginPortSSL) {
		this.loginPortSSL = loginPortSSL;
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
}
