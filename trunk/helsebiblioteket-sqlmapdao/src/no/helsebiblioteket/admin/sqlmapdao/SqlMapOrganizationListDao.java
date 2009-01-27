package no.helsebiblioteket.admin.sqlmapdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.listobjects.OrganizationListItem;

public class SqlMapOrganizationListDao extends SqlMapClientDaoSupport implements OrganizationListDao {
	// TODO: Go through all!

	public List<OrganizationListItem> getOrganizationListPaged(int from, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrganizationListItem> getOrganizationListPagedSearchString(String searchString, int from, int max) {
		// TODO: Do this search in the database!
		ArrayList<OrganizationListItem> allOrganizations = null;
		List<OrganizationListItem> someOrganizations = new ArrayList<OrganizationListItem>();
		for (OrganizationListItem organization : allOrganizations) {
			if(organization.getNameEnglish().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			} else if(organization.getNameShortEnglish().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			} else if(organization.getNameNorwegian().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			} else if(organization.getNameShortNorwegian().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			}
		}
		return null;
	}
	
}
