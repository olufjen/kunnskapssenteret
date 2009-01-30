package no.helsebiblioteket.admin.test.dao;

import java.util.List;
import java.util.Random;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class SupplierSourceDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testSupplierSource(){
		SupplierSourceDao supplierSourceDao = beanFactory.getSupplierSourceDao();
		
		SupplierSource supplierSource = SupplierSourceFactory.factory.completeSupplierSource();
		String url1 = "URL" + new Random().nextInt(1000000);
		String url2 = "URL" + new Random().nextInt(1000000);
		supplierSource.setUrl(new Url(url1));
		
		// INSERT
		supplierSourceDao.insertSupplierSource(supplierSource);
		
		// GET BY ID
		SupplierSource found = supplierSourceDao.getSupplierSourceById(supplierSource.getId());
		Assert.isTrue(found.getUrl().getStringValue().equals(url1), "Saved with wrong URL");
		
		// UPDATE
		found.setUrl(new Url(url2));
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
}
