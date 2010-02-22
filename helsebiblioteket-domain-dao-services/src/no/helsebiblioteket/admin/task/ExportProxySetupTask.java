package no.helsebiblioteket.admin.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.service.AccessService;

public class ExportProxySetupTask {
	private String name; // proxy-t.helsebiblioteket.no
	private int loginPort; // 80
	private int loginPortSSL; // 443
	private String adminPassword; // password
	private String proxyPassword; // password
	private String proxyServletURL; // http://www-t.helsebiblioteket.no/ProxyServlet?
	private AccessService accessService;
	
	public void exportSetup(){
		File configFile = new File("C:\\dev\\config.txt");
		File userFile = new File("C:\\dev\\user.txt");

		SupplierSourceResource[] list = accessService.getSupplierSourceResourceListAll("").getList();

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
}
