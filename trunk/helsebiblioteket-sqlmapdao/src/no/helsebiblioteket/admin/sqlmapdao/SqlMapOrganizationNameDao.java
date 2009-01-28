package no.helsebiblioteket.admin.sqlmapdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public class SqlMapOrganizationNameDao extends SqlMapClientDaoSupport implements OrganizationNameDao {
	// TODO: Go through all!

	@Override
	public void deleteOrganizationName(Organization organization,
			OrganizationName organizationName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrganizationName> getOrganizationNameListByOrganization(
			Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrganizationName(Organization organization,
			OrganizationName organizationName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrganizationName(Organization organization,
			OrganizationName organizationName) {
		// TODO Auto-generated method stub
		
	}


}
