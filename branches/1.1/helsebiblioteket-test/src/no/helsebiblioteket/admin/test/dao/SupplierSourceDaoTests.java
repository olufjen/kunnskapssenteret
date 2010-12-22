package no.helsebiblioteket.admin.test.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class SupplierSourceDaoTests implements Runnable {
	private BeanFactory beanFactory = BeanFactory.factory();
	private String url = null;
	
	@org.junit.Test
	public void testSupplierSource(){
		SupplierSourceDao supplierSourceDao = beanFactory.getSupplierSourceDao();
		
		SupplierSource supplierSource = SupplierSourceFactory.factory.completeSupplierSource();
		String url1 = "URL" + new Random().nextInt(1000000);
		String url2 = "URL" + new Random().nextInt(1000000);
		Url url = new Url();
		url.setStringValue(url1);
		supplierSource.setUrl(url);
		
		// INSERT
		supplierSourceDao.insertSupplierSource(supplierSource);
		
		// GET BY ID
		SupplierSource found = supplierSourceDao.getSupplierSourceById(supplierSource.getId());
		Assert.isTrue(found.getUrl().getStringValue().equals(url1), "Saved with wrong URL");
		
		// UPDATE
		url = new Url();
		url.setStringValue(url2);
		found.setUrl(url);
		supplierSourceDao.updateSupplierSource(supplierSource);
		found = supplierSourceDao.getSupplierSourceById(supplierSource.getId());
		Assert.isTrue(found.getUrl().getStringValue().equals(url1), "Not updated");

		List<SupplierSource> list = supplierSourceDao.getSupplierSourceListAll();
		Assert.notEmpty(list, "No sources found");
		
		// DELETE
		supplierSourceDao.deleteSupplierSource(supplierSource);
		found = supplierSourceDao.getSupplierSourceById(supplierSource.getId());
		Assert.isNull(found, "Not deleted");
	}
	
	public static void main(String args[]) throws Exception {
		SupplierSourceDaoTests test = new SupplierSourceDaoTests();
		// fortsett her, test ibatis-cacheing for urlstartswith!
		Set<String> urlSet = new HashSet<String>();
		urlSet.add("http://legehandboka.no");
		urlSet.add("http://ovid.com");
		urlSet.add("http://proquest.com");
		urlSet.add("http://sdfs");
		urlSet.add("http://sdfsdf");
		urlSet.add("http://sdfsdf");
		urlSet.add("http://fsdfsdsdf");
		urlSet.add("http://sdfsdfsdfsdf");
		urlSet.add("http://sdfsdffsdf");
		urlSet.add("http://sdfsdfsdf");
		urlSet.add("http://sdfsdfs");
		long timeStart = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			//Thread.sleep((long) (Math.random() + 1) * 5);
			for (String url : urlSet) {
				test.url = url;
				Thread thread = new Thread(test);
				thread.setName("Thread" + i);
				thread.run();
			}
		}
		System.out.println("time total: " + (System.currentTimeMillis() - timeStart));
	}
	
	public void run() {
		SupplierSourceDao supplierSourceDao = beanFactory.getSupplierSourceDao();
		supplierSourceDao.getSupplierSourceByDomain(new Url(this.url));
	}
}
