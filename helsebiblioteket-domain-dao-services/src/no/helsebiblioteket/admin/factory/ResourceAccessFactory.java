package no.helsebiblioteket.admin.factory;

import java.util.Calendar;
import java.util.Date;

import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;

public class ResourceAccessFactory {
	public static ResourceAccessFactory factory = new ResourceAccessFactory();
	private ResourceAccessFactory(){}
	public ResourceAccess createResourceAccess(){
		ResourceAccess access = new ResourceAccess();
		access.getAccess().setLastChanged(new Date());
		access.getAccess().setValidFrom(new Date());
		access.getAccess().setValidTo(new Date());
		return access;
	}
	public ResourceAccess completeResourceAccess(SupplierSourceResource resource, AccessType accessType, SupplierOrganization providedBy){
		ResourceAccess access = createResourceAccess();
		access.getAccess().setAccessType(accessType);
		access.getAccess().setProvidedBy(providedBy);
		access.setResource(resource);
		Calendar calendar = Calendar.getInstance();
		Date from = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Date to = calendar.getTime();
		access.getAccess().setValidFrom(from);
		access.getAccess().setValidTo(to);
		return access;
	}
}
