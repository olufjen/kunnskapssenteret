package no.helsebiblioteket.admin.test.dao;

import org.springframework.util.Assert;

import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.factory.SupplierOrganizationFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceFactory;
import no.helsebiblioteket.admin.factory.SupplierSourceResourceFactory;
import no.helsebiblioteket.admin.test.BeanFactory;

public class ResourceDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testResource(){
		// 'Static' value
		OrganizationType organizationType = beanFactory.getOrganizationTypeDao().getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
		Position position = beanFactory.getPositionDao().getPositionByKey(PositionTypeKey.fotterapeut);
		ResourceType resourceType = beanFactory.getResourceTypeDao().getResourceTypeByKey(ResourceTypeKey.supplier_source);
		
		// Supplier
		SupplierOrganization supplierOrganization = SupplierOrganizationFactory.factory.completeOrganization(organizationType, position);
		new OrganizationDaoTests().insertSupplierOrganization(supplierOrganization);
		
		// Source
		SupplierSource supplierSource1 = SupplierSourceFactory.factory.completeSupplierSource();
		SupplierSource supplierSource2 = SupplierSourceFactory.factory.completeSupplierSource();

		beanFactory.getSupplierSourceDao().insertSupplierSource(supplierSource1);
		beanFactory.getSupplierSourceDao().insertSupplierSource(supplierSource2);

		// Resource
		SupplierSourceResource resource = SupplierSourceResourceFactory.factory.completeSupplierSourceResource(resourceType,
				supplierSource1, supplierOrganization);
		
		ResourceDao resourceDao = beanFactory.getResourceDao();
		
		// INSERT
		resourceDao.insertSupplierSourceResource(resource);
		
		// FIND
		SupplierSourceResource found = resourceDao.getResourceById(resource.getResource().getId());
		Assert.notNull(found, "Should have found");
		Assert.isInstanceOf(SupplierSourceResource.class, found, "Wrong kind. Impossible!");
		Assert.isTrue(((SupplierSourceResource)found).getSupplierSource().getId().equals(
				supplierSource1.getId()), "Saved with wrong source");
		
		// UPDATE
		resource.setSupplierSource(supplierSource2);
		resourceDao.updateSupplierSourceResource(resource);

		found = resourceDao.getResourceById(resource.getResource().getId());
		Assert.notNull(found, "Should have found");
		Assert.isInstanceOf(SupplierSourceResource.class, found, "Wrong kind. Impossible!");
		Assert.isTrue(((SupplierSourceResource)found).getSupplierSource().getId().equals(
				supplierSource2.getId()), "Not updated");

		// DELETE
		resourceDao.deleteSupplierSourceResource(resource);
		new OrganizationDaoTests().removeSupplierOrganization(supplierOrganization);
		beanFactory.getSupplierSourceDao().deleteSupplierSource(supplierSource1);
		beanFactory.getSupplierSourceDao().deleteSupplierSource(supplierSource2);

		// Deleted?
		found = resourceDao.getResourceById(resource.getResource().getId());
		Assert.isNull(found, "Should not have found");
	}
}
