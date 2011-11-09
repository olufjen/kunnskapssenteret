package no.helsebiblioteket.evs.plugin.test;

/**
 * Important: Properties injected by Spring in this class are being set correctly,
 * but the same properties are null when the method "filterResponse" run.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpServletRequest;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.test.service.mock.GeoIpServiceAlwaysAccessMock;
import no.helsebiblioteket.evs.plugin.LinkFilter;
import no.helsebiblioteket.evs.plugin.LogInInterceptor;


public class LinkFilterTest {
	private final Log logger = LogFactory.getLog(getClass());

	URI contentFileURI = null;
	String content = null;
	MockHttpServletRequest request;
	LinkFilter linkFilterPlugin;
	
	private void initResources(String responseContentFile) throws IOException {
		this.linkFilterPlugin = new LinkFilter();
		Resource res = new FileSystemResource("./desc/evs-plugin-test/applicationContextLinkFilterTest.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
	    linkFilterPlugin.setUrlService((URLService) factory.getBean("urlService"));
	    linkFilterPlugin.setCountryCodes("NO");
	    linkFilterPlugin.setGeoIpService(new GeoIpServiceAlwaysAccessMock());
		this.contentFileURI = new FileSystemResource(responseContentFile).getURI();
		this.content = getFileContentAsString(this.contentFileURI);
		this.request = new MockHttpServletRequest();
		request.addHeader(LogInInterceptor.XForwardedForHeaderName, "");
		
	}
	
	
	@Test
	public void testGenerateProxyLinksRopReferanser() throws Exception {
		initResources("./desc/evs-plugin-test/linkfiltertestresponse_ropreferanser.html");
		Assert.assertFalse("content contains double proxy prefixes!", content.contains("proxy.helsebiblioteket.no/login?url=http://proxy.helsebiblioteket.no/login?url="));
		Assert.assertTrue("content does not contain a predefined valid link", content.contains("<a href=\"http://www.ncbi.nlm.nih.gov/pubmed/15289279\">PubMed: 15289279"));
		long timeStart = System.currentTimeMillis();
		this.content = this.linkFilterPlugin.generateProxyLinks(request, request.getSession(true), this.content, this.request.getContentType());
		logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
		Assert.assertFalse("content contains double proxy prefixes!", content.contains("proxy-t.helsebiblioteket.no/login?url=http://proxy"));
		Assert.assertTrue("content does not contain a predefined valid proxy link", content.contains("<a href=\"http://proxy-t.helsebiblioteket.no/login?url=http://www.ncbi.nlm.nih.gov/pubmed/15289279\">PubMed: 15289279"));
		logger.info(content);
	}
	
	@Test
	public void testGenerateProxyLinksDatabaser() throws Exception {
		initResources("./desc/evs-plugin-test/linkfiltertestresponse_databaser.html");
		Assert.assertFalse("content contains double proxy prefixes!", content.contains("proxy.helsebiblioteket.no/login?url=http://proxy.helsebiblioteket.no/login?url="));
		long timeStart = System.currentTimeMillis();
		this.content = this.linkFilterPlugin.generateProxyLinks(request, request.getSession(true), this.content, this.request.getContentType());
		logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
		Assert.assertFalse("content contains double proxy prefixes!", content.contains("proxy.helsebiblioteket.no/login?url=http://proxy"));
		Assert.assertFalse("content contains proxy prefix(es) og type 'proxy.helsebibliokteket.no', should only contain prefixes of type 'proxy-.helsebibliokteket.no'", content.contains("proxy.helsebiblioteket.no/login?url="));
		logger.info(content);
	}
	
	@Test
	@Ignore
	public void testGenerateProxyLinksSimple() throws Exception {
		initResources("./desc/evs-plugin-test/linkfiltertestresponsesimple.html");
		Assert.assertFalse("content contains double proxy prefixes!", content.contains("proxy.helsebiblioteket.no/login?url=http://proxy.helsebiblioteket.no/login?url="));
		Assert.assertTrue("content does not contain a predefined valid link", content.contains("<a href=\"http://www.ncbi.nlm.nih.gov/pubmed/15289279\">PubMed: 15289279"));
		long timeStart = System.currentTimeMillis();
		this.content = this.linkFilterPlugin.generateProxyLinks(request, request.getSession(true), this.content, this.request.getContentType());
		logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
		Assert.assertFalse("content contains double proxy prefixes!", content.contains("proxy.helsebiblioteket.no/login?url=http://proxy.helsebiblioteket.no/login?url="));
		Assert.assertTrue("content does not contain a predefined valid proxy link", content.contains("<a href=\"http://proxy.helsebiblioteket.no/login?url=http://www.ncbi.nlm.nih.gov/pubmed/15289279\">PubMed: 15289279"));
		logger.info(content);
	}
	
	@Test
	public void testGenerateProxyLinksTidsskrifterSimple() throws Exception {
		initResources("./desc/evs-plugin-test/linkfiltertestresponse_tidsskrifter_simple.html");
		long timeStart = System.currentTimeMillis();
		this.content = this.linkFilterPlugin.generateProxyLinks(request, request.getSession(true), this.content, this.request.getContentType());
		Assert.assertFalse("content contains non-proxy-link (should have been proxyfied)", content.contains("<a href=\"http://www.ncbi.nlm.nih.gov/sites/entrez?otool=bibsys&amp;holding=inohelib_fft_ndi&amp;myncbishare=helsebiblioteket\">"));
		Assert.assertTrue("content does not contains an expected-proxy-link (should have been proxyfied)", content.contains("href=\"http://proxy-t.helsebiblioteket.no/login?url=http://www.ncbi.nlm.nih.gov/sites/entrez?otool=bibsys&amp;holding=inohelib_fft_ndi&amp;myncbishare=helsebiblioteket\""));
		logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
		logger.info(content);
	}
	
	@Test
	@Ignore
	public void testGenerateProxyLinksTidsskrifterFull() throws Exception {
		initResources("./desc/evs-plugin-test/linkfiltertestresponse_tidsskrifter_full.html");
		long timeStart = System.currentTimeMillis();
		this.content = this.linkFilterPlugin.generateProxyLinks(request, request.getSession(true), this.content, this.request.getContentType());
		Assert.assertFalse("content contains non-proxy-link (should have been proxyfied)", content.contains("<a href=\"http://www.ncbi.nlm.nih.gov/sites/entrez?otool=bibsys&amp;holding=inohelib_fft_ndi&amp;myncbishare=helsebiblioteket\">"));
		Assert.assertTrue("content does not contains an expected-proxy-link (should have been proxyfied)", content.contains("href=\"http://proxy-t.helsebiblioteket.no/login?url=http://www.ncbi.nlm.nih.gov/sites/entrez?otool=bibsys&amp;holding=inohelib_fft_ndi&amp;myncbishare=helsebiblioteket\""));
		logger.info("linkfilter took " + (System.currentTimeMillis() - timeStart) + " milliseconds");
		logger.info(content);
	}
	
	private String getFileContentAsString(URI fileURI) throws java.io.IOException{
		File file = new File(fileURI);
	    byte[] buffer = new byte[(int) file.length()];
	    FileInputStream f = new FileInputStream(file);
	    f.read(buffer);
	    return new String(buffer, "UTF-8");
	}
}